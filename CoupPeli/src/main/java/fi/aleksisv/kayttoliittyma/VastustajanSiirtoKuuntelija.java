package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * Luokka implementoi ActionListenerin ja seuraa tilannetta, jossa vastustaja on
 * siirtovuorossa.
 */
public class VastustajanSiirtoKuuntelija implements ActionListener {

    /**
     * Pelin ohjauksesta vastaava taho.
     */
    PeliOhjaus peliOhjaus;
    /**
     * Pelausnäkymä.
     */
    PelausIkkuna pelausIkkuna;
    /**
     * Graafinen käyttöliittymä.
     */
    GraafinenKayttoliittyma gkl;
    /**
     * Vastustaja.
     */
    Vastustaja vastustaja;
    /**
     * Random-olio.
     */
    Random r;

    /**
     * Luokan konstruktori.
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
        String ketaVastaan = "";
        

        this.pelausIkkuna.getContentPane().removeAll();
        
        this.pelausIkkuna.setSize(400, 500);
        JPanel paneeli = new JPanel(new GridLayout(2, 1));
        JPanel napit = new JPanel(new GridLayout(1, 3));

        int siirto = vastustaja.valitseSiirto(r);
        int kohde = vastustaja.valitseKohde(r, this.peliOhjaus.getPeli().getOsanottajajoukko().size());
        
        if(siirto == 3 || siirto ==5 || siirto == 6) {
            ketaVastaan = " osanottajaa " 
                    + this.peliOhjaus.getPeli().getOsanottajajoukko().get(kohde).getNimi() 
                    + " vastaan";
        }

        JButton alaTeeMitaan = new JButton("Älä tee mitään!");
        alaTeeMitaan.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        HashMap torjuntalista = this.peliOhjaus.getPeli().getTorjuntaLista();
        HashMap siirtonumerot = this.peliOhjaus.getPeli().getSiirtoNumerot();
        HashMap siirtonimet = this.peliOhjaus.getPeli().getSiirtoNimet();

        JButton torju = new JButton("Torju.");
        torju.setEnabled(false);

        JButton epaile = new JButton("Epäile");
        epaile.setEnabled(false);

        JTextArea ilmoitus = new JTextArea("Vastustaja " + vastustaja.getNimi() + " haluaa tehdä\nsiirron "
                + siirtonimet.get(siirto).toString().toLowerCase()  + ketaVastaan + ".");
        Osanottaja oKohde = this.peliOhjaus.getPeli().getOsanottajajoukko().get(kohde);

        if (oKohde instanceof Pelaaja && torjuntalista.keySet().contains(siirto)) {
            ilmoitus.setText(ilmoitus.getText() + "\n\nSiirron voi torjua kortilla " 
                    + torjuntalista.get(siirto) + ".\nHaluatko torjua siirron?");

            torju.setEnabled(true);
            torju.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.black));

            torju.addActionListener(new VastustajanVuoroTorjuKuuntelija(this.gkl,
                    this.peliOhjaus, this.vastustaja, siirto));

            napit.add(torju);
        }

        if (oKohde instanceof Pelaaja && siirtonumerot.keySet().contains(siirto)) {
            ilmoitus.setText(ilmoitus.getText() + "\n\nSiirtoon tarvitaan kortti "
                    + siirtonumerot.get(siirto) + "\nHaluatko epäillä siirtoa?");
            epaile.setEnabled(true);
            
            epaile.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.black));

            epaile.addActionListener(new VastustajanVuoroEpailyKuuntelija(this.gkl,
                    this.peliOhjaus, this.pelausIkkuna, this.vastustaja, siirto));

            napit.add(epaile);

        }

        alaTeeMitaan.addActionListener(new VastustajanVuoroAlaTeeMitaanKuuntelija(gkl,
                peliOhjaus, pelausIkkuna, vastustaja, siirto, oKohde));
        
        alaTeeMitaan.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.black));
        napit.add(alaTeeMitaan);
        napit.setBorder(BorderFactory.createEmptyBorder(60,20, 60,20));
        
        ilmoitus.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.black));
        paneeli.add(ilmoitus);
        paneeli.add(napit);

        this.pelausIkkuna.getContentPane().add(paneeli);
        this.pelausIkkuna.setVisible(true);

        SwingUtilities.updateComponentTreeUI(pelausIkkuna);
    }

}
