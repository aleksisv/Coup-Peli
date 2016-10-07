
package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.Peli;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PelaajienLisaysKuuntelija implements ActionListener {
    private Peli peli;
    
    /**
     * Luokan konstruktori.
     * @param peli Mihin peliin kuuntelija liittyy.
     */
    public PelaajienLisaysKuuntelija(Peli peli) {
        this.peli = peli;
    }
    
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.peli.luoOsanottajat();
    }
}
