package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

public class VastustajanVuoroEpailyKuuntelija implements ActionListener {

    GraafinenKayttoliittyma gkl;
    PeliOhjaus peliOhjaus;
    PelausIkkuna pelausIkkuna;
    Vastustaja vastustaja;
    int siirto;

    public VastustajanVuoroEpailyKuuntelija(GraafinenKayttoliittyma gkl, PeliOhjaus peliOhjaus, PelausIkkuna pelausIkkuna, Vastustaja vastustaja, int siirto) {
        this.gkl = gkl;
        this.peliOhjaus = peliOhjaus;
        this.pelausIkkuna = pelausIkkuna;
        this.vastustaja = vastustaja;
        this.siirto = siirto;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Osanottaja o = (Osanottaja) this.vastustaja;
        Peli peli = this.peliOhjaus.getPeli();
        Osanottaja kohdistuu = peli.getPelaaja();
        if (peli.getPelaaja().epaile(o, peli.getSiirtoNumerot().get(this.siirto))) {
            peli.setVuoronumero(peli.getVuoronumero() + 1);
            return;
        } else {
            this.peliOhjaus.suoritaSiirto(o, kohdistuu, siirto);
        }
        this.gkl.paivitaPelinseuranta(this.gkl.getPelinSeurantapaneeli());
        this.pelausIkkuna.setVisible(false);
        SwingUtilities.updateComponentTreeUI(this.gkl.getValiIkkuna());
    }

}
