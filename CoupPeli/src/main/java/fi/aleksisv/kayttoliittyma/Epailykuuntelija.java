package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Luokka tarkkailee, haluaako pelaaja epäillä vastustajan siirtoa.
 */
public class Epailykuuntelija implements ActionListener {

    /**
     * Tarvittavat napit.
     */
    JButton tee, alaTee;

    /**
     * Onko kyseessä vastustaja joka haluaa epäillä.
     */
    boolean haluaakoVastustajaEpailla;
    /**
     * Tilanteeseen liittyvä vastustaja.
     */
    Vastustaja vastustaja;
    /**
     * Pelin pyörittämisestä vastaava taho.
     */
    PeliOhjaus peliOhjaus;
    /**
     * Siirron numero.
     */
    int siirto;
    /**
     * Graafinen käyttöliittymä.
     */
    GraafinenKayttoliittyma gkl;
    /**
     * Tilanteeseen liittyvä näkymä.
     */
    JFrame epailyikkuna;

    /**
     * Luokan konstruktori.
     *
     * @param tee Nappi, jota painamalla pelaaja epäilee.
     * @param alaTee Nappi, jota painamalla pelaaja ei epäile.
     * @param haluaakoVastustajaEpailla Totuusarvo: haluaako vastustaja epäillä
     * vai ei.
     * @param vastustaja Vastustaja, joka on siirrossa osallisena.
     * @param siirto Siirron numero.
     * @param peliOhjaus Peliä ohjaava taho.
     * @param gkl Graafinen käyttöliittymä.
     * @param epailyikkuna Ikkuna, johon kuuntelija liittyy.
     */
    public Epailykuuntelija(JButton tee, JButton alaTee, boolean haluaakoVastustajaEpailla,
            Vastustaja vastustaja, PeliOhjaus peliOhjaus, int siirto, GraafinenKayttoliittyma gkl, JFrame epailyikkuna) {
        this.tee = tee;
        this.alaTee = alaTee;
        this.haluaakoVastustajaEpailla = haluaakoVastustajaEpailla;
        this.vastustaja = vastustaja;
        this.peliOhjaus = peliOhjaus;
        this.siirto = siirto;
        this.gkl = gkl;
        this.epailyikkuna = epailyikkuna;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Peli peli = this.peliOhjaus.getPeli();
        Osanottaja pelaaja = (Osanottaja) peli.getPelaaja();

        if (haluaakoVastustajaEpailla) {
            if (e.getSource() == this.tee) {
                if (!vastustaja.epaile(pelaaja, peli.getSiirtoNumerot().get(siirto))) {
                    this.peliOhjaus.suoritaSiirto(pelaaja, vastustaja, siirto);
                } else {
                    this.peliOhjaus.paivitaTilanne();
                }
                this.gkl.getPelausIkkuna().setVisible(false);
                this.epailyikkuna.setVisible(false);
            } else {
                this.peliOhjaus.paivitaTilanne();
                this.gkl.getPelausIkkuna().setVisible(false);
                this.epailyikkuna.setVisible(false);
            }
        } else if (e.getSource() == this.tee) {
            if (pelaaja.epaile(vastustaja, peli.getTorjuntaLista().get(siirto))) {
                this.peliOhjaus.suoritaSiirto(pelaaja, vastustaja, siirto);
            } else {
                this.peliOhjaus.paivitaTilanne();
            }
            this.gkl.getPelausIkkuna().setVisible(false);
            this.epailyikkuna.setVisible(false);
        } else {
            this.peliOhjaus.paivitaTilanne();
            this.gkl.getPelausIkkuna().setVisible(false);
            this.epailyikkuna.setVisible(false);
        }
        peli.setVuoronumero(this.peliOhjaus.getPeli().getVuoronumero() + 1);
        this.gkl.getValiIkkuna().pelinSeurantapaneeli.paivitaTila(this.peliOhjaus.getPeli());
    }

}
