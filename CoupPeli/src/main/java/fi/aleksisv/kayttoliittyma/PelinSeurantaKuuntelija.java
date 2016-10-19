package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.Osanottaja;
import fi.aleksisv.logiikka.Pelaaja;
import fi.aleksisv.logiikka.Vastustaja;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

/**
 * Luokka kuuntelee pelin vuoronapin painamista ja tätä kautta ohjaa pelin
 * etenemistä.
 *
 */
public class PelinSeurantaKuuntelija implements ActionListener {

    /**
     * Pelausikkuna.
     */
    PelausIkkuna pelausIkkuna;
    /**
     * Peliä pyörittävä taho.
     */
    PeliOhjaus peliOhjaus;
    /**
     * Graafinen käyttöliittymä.
     */
    GraafinenKayttoliittyma gkl;

    /**
     * Luokan konstruktori.
     *
     * @param peliOhjaus Peliä pyörittävä taho.
     * @param gkl Graafinen käyttöliittymä.
     */
    public PelinSeurantaKuuntelija(PeliOhjaus peliOhjaus, GraafinenKayttoliittyma gkl) {
        this.peliOhjaus = peliOhjaus;
        this.gkl = gkl;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Osanottaja vuorossa = this.peliOhjaus.getPeli().getOsanottajajoukko().get(this.peliOhjaus.getPeli().getVuoronumero()
                % this.peliOhjaus.getPeli().getOsanottajajoukko().size());
        this.pelausIkkuna = new PelausIkkuna(peliOhjaus, gkl);
        gkl.setPelausIkkuna(pelausIkkuna);

        if (vuorossa instanceof Pelaaja) {
            pelausIkkuna.luoPelausVaihtoehdot(pelausIkkuna.getContentPane());
        } else {
            Vastustaja vastustaja = (Vastustaja) vuorossa;
            pelausIkkuna.luoVastustajanVuoro(vastustaja, pelausIkkuna.getContentPane());
        }

        this.gkl.paivitaPelinseuranta(this.gkl.getValiIkkuna().pelinSeurantapaneeli);

        pelausIkkuna.pack();
    }

}
