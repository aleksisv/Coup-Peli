package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.*;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * Luokka implementoi ActionListenerin ja seuraa tilannetta, jossa vastustaja on
 * siirtovuorossa.
 */
public class VastustajanSiirtoKuuntelija implements ActionListener {

    /** Pelin ohjauksesta vastaava taho.*/
    PeliOhjaus peliOhjaus;
    /** Pelausnäkymä.*/
    PelausIkkuna pelausIkkuna;
    /** Graafinen käyttöliittymä.*/
    GraafinenKayttoliittyma gkl;
    /** Vastustaja.*/
    Vastustaja vastustaja;
    /** Random-olio.*/
    Random r;
    
    /** Luokan konstruktori
     * 
     *
     * @param peliOhjaus Pelin pyörittämisestä vastaava taho.
     * @param gkl Graafinen käyttöliittymä.
     * @param pelausIkkuna Pelausnäkymä.
     * @param vastustaja Vuoroon liittyvä vastustaja.
     */
    public VastustajanSiirtoKuuntelija(PeliOhjaus peliOhjaus, GraafinenKayttoliittyma gkl, PelausIkkuna pelausIkkuna, Vastustaja vastustaja) {
        this.peliOhjaus = peliOhjaus;
        this.gkl = gkl;
        this.pelausIkkuna = pelausIkkuna;
        this.vastustaja = vastustaja;
        this.r = new Random();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.pelausIkkuna.getContentPane().removeAll();
        JPanel lisaykset = new JPanel(new GridLayout(4, 1));

        int siirto = vastustaja.valitseSiirto(r);
        int kohde = vastustaja.valitseKohde(r, this.peliOhjaus.getPeli().getOsanottajajoukko().size());

        JButton alaTeeMitaan = new JButton("Ok!");

        HashMap torjuntalista = this.peliOhjaus.getPeli().getTorjuntaLista();
        HashMap siirtonumerot = this.peliOhjaus.getPeli().getSiirtoNumerot();
        HashMap siirtonimet = this.peliOhjaus.getPeli().getSiirtoNimet();

        JTextArea ilmoitus = new JTextArea("Vastutaja haluaa tehdä siirron "
                + siirtonimet.get(siirto) + " \nosanottajaa "
                + this.peliOhjaus.getPeli().getOsanottajajoukko().get(kohde).getNimi()
                + " vastaan.");
        Osanottaja oKohde = this.peliOhjaus.getPeli().getOsanottajajoukko().get(kohde);

        if (oKohde instanceof Pelaaja && torjuntalista.keySet().contains(siirto)) {
            ilmoitus.setText(ilmoitus.getText() + "\nHaluatko torjua siirron?");

            JButton torju = new JButton("Torju.");

            torju.addActionListener(new VastustajanVuoroTorjuKuuntelija(this.gkl,
                    this.peliOhjaus, this.vastustaja, siirto));

            lisaykset.add(torju);
        }

        if (oKohde instanceof Pelaaja && siirtonumerot.keySet().contains(siirto)) {
            ilmoitus.setText(ilmoitus.getText() + "\nHaluatko epäillä siirtoa?");

            JButton epaile = new JButton("Epäile.");

            epaile.addActionListener(new VastustajanVuoroEpailyKuuntelija(this.gkl, this.peliOhjaus, this.pelausIkkuna, this.vastustaja, siirto));

            lisaykset.add(epaile);

        }

        alaTeeMitaan.addActionListener(new VastustajanVuoroAlaTeeMitaanKuuntelija(gkl, peliOhjaus, pelausIkkuna, vastustaja, siirto, oKohde));

        lisaykset.add(alaTeeMitaan);
        lisaykset.add(ilmoitus);
        this.pelausIkkuna.getContentPane().add(lisaykset);
        this.pelausIkkuna.setVisible(true);

        SwingUtilities.updateComponentTreeUI(pelausIkkuna);
    }

}
