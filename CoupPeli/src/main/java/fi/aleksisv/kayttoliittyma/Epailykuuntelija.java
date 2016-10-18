
package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.Vastustaja;
import java.awt.event.*;
import javax.swing.*;


public class Epailykuuntelija implements ActionListener{
    JButton tee, alaTee;
    boolean haluaakoVastustajaEpailla;
    Vastustaja vastustaja;
    PeliOhjaus peliOhjaus;
    int siirto;
    GraafinenKayttoliittyma gkl;
    JFrame epailyikkuna;

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
        
        if(haluaakoVastustajaEpailla) {
            if(e.getSource() == this.tee) {
                vastustaja.epaile(this.peliOhjaus.getPeli().getPelaaja(), this.peliOhjaus.getPeli().getSiirtoNumerot().get(siirto));
                this.gkl.getPelausIkkuna().setVisible(false);
                this.epailyikkuna.setVisible(false);
            } else {
                this.gkl.getPelausIkkuna().setVisible(false);
                this.epailyikkuna.setVisible(false);
            }
        } else {
            if(e.getSource() == this.tee) {
                this.peliOhjaus.getPeli().getPelaaja().epaile(vastustaja, this.peliOhjaus.getPeli().getSiirtoNumerot().get(siirto));
                this.gkl.getPelausIkkuna().setVisible(false);
                this.epailyikkuna.setVisible(false);
            } else {
                this.gkl.getPelausIkkuna().setVisible(false);
                this.epailyikkuna.setVisible(false);
            }
        }
        this.peliOhjaus.getPeli().setVuoronumero(this.peliOhjaus.getPeli().getVuoronumero() + 1);
        this.gkl.getValiIkkuna().pelinSeurantapaneeli.paivitaTila(this.peliOhjaus.getPeli());
    }
    
}
