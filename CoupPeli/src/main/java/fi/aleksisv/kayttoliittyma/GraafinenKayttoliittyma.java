
package fi.aleksisv.kayttoliittyma;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Luokka vastaa ohjelman GUI-puolesta.
 */
public class GraafinenKayttoliittyma {
    
    private JFrame ikkuna;

    public GraafinenKayttoliittyma() {
        this.ikkuna = new PelausIkkuna();
        ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JComponent pelausNappi = new PelausNappi();
        pelausNappi.setOpaque(true);
        
        ikkuna.setContentPane(pelausNappi);
        ikkuna.setVisible(true);
    }
    
    
    
}
