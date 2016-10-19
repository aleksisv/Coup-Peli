package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Luokka vastaa siirtoon liittyvästä kuuntelemisesta.
 */
public class PeliSiirtoKuuntelija implements ActionListener {

    /**
     * Tarvittavat nappularyhmät.
     */
    ButtonGroup siirtonappularyhma, kohdenappularyhma;
    /**
     * Pelausikkuna, jota käytetään.
     */
    PelausIkkuna pelausIkkuna;
    /**
     * Graafinen käyttöliittymä.
     */
    GraafinenKayttoliittyma gkl;
    /**
     * Erityishuomioista kertova tekstialue.
     */
    JTextArea huomioTekstit;
    /**
     * Peliä pyörittävä taho.
     */
    PeliOhjaus peliOhjaus;

    /**
     * Luokan konstruktori.
     *
     * @param siirtonappularyhma Siirtoihin liittyvät nappulat.
     * @param pelausIkkuna Pelaus-näkymästä vastaava ikkuna.
     * @param peliOhjaus Peliä ohjaava taho.
     * @param gkl Graafinen käyttöliittymä.
     * @param kohdenappularyhma Kohteisiin liittyvät nappulat.
     */
    public PeliSiirtoKuuntelija(PeliOhjaus peliOhjaus, PelausIkkuna pelausIkkuna,
            GraafinenKayttoliittyma gkl, ButtonGroup siirtonappularyhma,
            ButtonGroup kohdenappularyhma) {

        this.peliOhjaus = peliOhjaus;
        this.pelausIkkuna = pelausIkkuna;
        this.gkl = gkl;
        this.siirtonappularyhma = siirtonappularyhma;
        this.kohdenappularyhma = kohdenappularyhma;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int siirtoNumero = Integer.parseInt(siirtonappularyhma.getSelection().getActionCommand());
        if (this.peliOhjaus.onkoPelaajallaRahaa(siirtoNumero)) {
            yritaSiirtoa(Integer.parseInt(siirtonappularyhma.getSelection().getActionCommand()),
                    Integer.parseInt(kohdenappularyhma.getSelection().getActionCommand()));
        } else {
            pelausIkkuna.getHuomiotekstit().setText("Liian vähän rahaa siirtoon. Valitse uusi siirto.");
        }
        SwingUtilities.updateComponentTreeUI(pelausIkkuna);
    }

    private void yritaSiirtoa(int siirtonumero, int kohde) {
        Pelaaja pelaaja = this.peliOhjaus.getPeli().getPelaaja();
        Vastustaja vastustaja = (Vastustaja) this.peliOhjaus.getPeli().getOsanottajajoukko().get(kohde);
        Kortti siirto = this.peliOhjaus.getPeli().getSiirtoNumerot().get(siirtonumero);

        if (vastustaja.haluaaEpailla(pelaaja, siirto)) {
            Epailyikkuna epailyIkkuna = new Epailyikkuna(true, vastustaja, peliOhjaus, siirtonumero, gkl);
            epailyIkkuna.luoKomponentit(epailyIkkuna.getContentPane());

        } else if (vastustaja.haluaaTorjua(pelaaja, siirto)) {
            Torjuntaikkuna torjuntaikkuna = new Torjuntaikkuna(vastustaja, peliOhjaus, siirtonumero, gkl);
            torjuntaikkuna.luoKomponentit(torjuntaikkuna.getContentPane());

        } else {
            this.peliOhjaus.suoritaSiirto(pelaaja, vastustaja, siirtonumero);
            this.gkl.getValiIkkuna().huomioTekstit.setText("Onnistuit tekemään siirron " 
                    + this.peliOhjaus.getPeli().getSiirtoNimet().get(siirtonumero) 
                    + " vastustajaa " + vastustaja.getNimi() + " kohtaan.");
            this.pelausIkkuna.setVisible(false);
        }
        this.gkl.getValiIkkuna().pelinSeurantapaneeli.paivitaTila(peliOhjaus.getPeli());
    }

}
