
package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.SwingUtilities;


public class VastustajanVuoroTorjuKuuntelija implements ActionListener {
    GraafinenKayttoliittyma gkl;
    PeliOhjaus peliOhjaus;
    PelausIkkuna pelausIkkuna;
    Vastustaja vastustaja;
    int siirto;

    public VastustajanVuoroTorjuKuuntelija(GraafinenKayttoliittyma gkl, PeliOhjaus peliOhjaus, Vastustaja vastustaja, int siirto) {
        this.gkl = gkl;
        this.peliOhjaus = peliOhjaus;
        this.vastustaja = vastustaja;
        this.siirto = siirto;
    }

    @Override
        public void actionPerformed(ActionEvent e) {
            Peli peli = this.peliOhjaus.getPeli();
            Pelaaja kohde = peli.getPelaaja();
            HashMap siirtoMappi = this.peliOhjaus.getPeli().getSiirtoNumerot();
            
            if(siirtoMappi.containsKey(siirto)) {
                if (vastustaja.haluaaTorjua(kohde, (Kortti) siirtoMappi.get(siirto))) {
                    vastustaja.torju(kohde, (Kortti) siirtoMappi.get(siirto));
                }
            }
            
            this.gkl.getPelinSeurantapaneeli().paivitaTila(peli);
            SwingUtilities.updateComponentTreeUI(this.gkl.getValiIkkuna());
        }
    
}
