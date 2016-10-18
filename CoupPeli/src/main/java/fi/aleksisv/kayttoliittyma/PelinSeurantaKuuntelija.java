
package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.Osanottaja;
import fi.aleksisv.logiikka.Pelaaja;
import fi.aleksisv.logiikka.Vastustaja;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;


public class PelinSeurantaKuuntelija implements ActionListener{
    PelausIkkuna pelausIkkuna;
    PeliOhjaus peliOhjaus;
    GraafinenKayttoliittyma gkl;

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
        pelausIkkuna.validate();
        this.pelausIkkuna.setVisible(false);
        SwingUtilities.updateComponentTreeUI(this.gkl.getValiIkkuna());
        SwingUtilities.updateComponentTreeUI(this.pelausIkkuna);
        
        pelausIkkuna.pack();
        pelausIkkuna.setVisible(true);
    }
    
}
