package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.SwingUtilities;

/**
 * Luokka kuuntelee tilannetta, jossa käyttäjä haluaa torjua vastustajan
 * siirron.
 */
public class VastustajanVuoroTorjuKuuntelija implements ActionListener {

    /**
     * Graafinen käyttöliittymä.
     */
    GraafinenKayttoliittyma gkl;
    /**
     * Peliä pyörittävä taho.
     */
    PeliOhjaus peliOhjaus;
    /**
     * Tilanteeseen liittyvä ikkuna.
     */
    PelausIkkuna pelausIkkuna;
    /**
     * Tilanteeseen liittyvä vastustaja.
     */
    Vastustaja vastustaja;
    /**
     * Siirtonumero.
     */
    int siirto;

    /**
     * Luokan konstruktori.
     *
     * @param gkl Graafinen käyttöliittymä.
     * @param peliOhjaus Pelin ohjauksesta vastaava taho.
     * @param vastustaja Vastustaja.
     * @param siirto Siirtonumero.
     */
    public VastustajanVuoroTorjuKuuntelija(GraafinenKayttoliittyma gkl, PeliOhjaus peliOhjaus, Vastustaja vastustaja, int siirto) {
        this.gkl = gkl;
        this.peliOhjaus = peliOhjaus;
        this.vastustaja = vastustaja;
        this.siirto = siirto;
        this.pelausIkkuna = this.gkl.getPelausIkkuna();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Peli peli = this.peliOhjaus.getPeli();
        Pelaaja kohde = peli.getPelaaja();
        HashMap siirtoMappi = this.peliOhjaus.getPeli().getSiirtoNumerot();

        if (siirtoMappi.containsKey(siirto)) {
            if (vastustaja.haluaaEpailla(kohde, (Kortti) siirtoMappi.get(siirto))) {
                if (vastustaja.epaile((Osanottaja) kohde, (Kortti) siirtoMappi.get(siirto))) {
                    peliOhjaus.suoritaSiirto(vastustaja, kohde, siirto);
                    this.gkl.getValiIkkuna().huomioTekstit.setText("Vastustaja epäili torjuntaasi onnistuneesti. Menetit kortin.");
                } else {
                    peliOhjaus.paivitaTilanne();
                    this.gkl.getValiIkkuna().huomioTekstit.setText("Vastustaja koitti epäillä torjuntaasi, mutta epäonnistui ja menetti kortin.");
                }
            } else {
                peliOhjaus.paivitaTilanne();
                this.gkl.getValiIkkuna().huomioTekstit.setText("Vastustaja ei halua epäillä torjuntaasi.");
            }
        } else {
            peliOhjaus.paivitaTilanne();
            this.gkl.getValiIkkuna().huomioTekstit.setText("Torjuit vastustajan siirron.");
        }

        peli.setVuoronumero(peli.getVuoronumero() + 1);

        this.gkl.paivitaPelinseuranta(this.gkl.getPelinSeurantapaneeli());
        this.pelausIkkuna.setVisible(false);
        SwingUtilities.updateComponentTreeUI(this.gkl.getValiIkkuna());

    }

}
