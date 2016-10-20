package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

/** Luokka kuuntelee vastustajaan kohdistuvaa epäilyä.
 */
public class VastustajanVuoroEpailyKuuntelija implements ActionListener {
    /** Onko kyseessä vuoron ensimmäinen epäilyikkuna.*/
    GraafinenKayttoliittyma gkl;
    /** Pelin pyörittämisestä vastaava taho.*/
    PeliOhjaus peliOhjaus;
    /**Pelausikkuna, joka liittyy tilanteeseen.*/
    PelausIkkuna pelausIkkuna;
    /** Tilanteeseen liittyvä vastustaja.*/
    Vastustaja vastustaja;
    /** Siirtonumero.*/
    int siirto;
    
    /** Luokan konstruktori. 
     *
     * @param gkl Graafinen käyttöliittymä.
     * @param peliOhjaus Pelin pyörittämisestä vastaava taho.
     * @param pelausIkkuna Pelaustilanteeseen liittyvä ikkuna.
     * @param vastustaja Tilanteeseen liittyvä vastustaja.
     * @param siirto Siirtonumero.
     */
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
            this.gkl.getValiIkkuna().huomioTekstit.setText("Onnistuit epäilemään vastustajan siirtoa.");
            peli.setVuoronumero(peli.getVuoronumero() + 1);
            this.peliOhjaus.paivitaTilanne();
        } else {
            this.peliOhjaus.suoritaSiirto(o, kohdistuu, siirto);
            this.gkl.getValiIkkuna().huomioTekstit.setText(vuoroteksti());
        }
        
        
        this.gkl.paivitaPelinseuranta(this.gkl.getPelinSeurantapaneeli());
        this.pelausIkkuna.setVisible(false);
        SwingUtilities.updateComponentTreeUI(this.gkl.getValiIkkuna());
    }
    
    private String vuoroteksti() {
        String siirronNimi = this.peliOhjaus.getPeli().getSiirtoNimet().get(siirto);
        
        if(siirto == 3 || siirto == 5 || siirto == 6) {
            return "Vastustaja onnistui tekemään siirron " + siirronNimi + " sinua vastaan.";
        } else {
            return "Vastustaja onnistui tekemään siirron " + siirronNimi + ".";
        }
    }

}
