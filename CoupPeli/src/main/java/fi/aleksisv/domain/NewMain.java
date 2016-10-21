package fi.aleksisv.domain;

import fi.aleksisv.kayttoliittyma.GraafinenKayttoliittyma;
import javax.swing.SwingUtilities;

/**
 * Pääluokka.
 */
public class NewMain {
    
    /**
     * Metodi pistää kaiken pyörimää.
     * @param args Argumentit.
     */
    public static void main(String[] args) {
        GraafinenKayttoliittyma gkl = new GraafinenKayttoliittyma();
        SwingUtilities.invokeLater(gkl);

    }

}
