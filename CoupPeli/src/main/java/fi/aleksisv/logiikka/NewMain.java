package fi.aleksisv.logiikka;

import fi.aleksisv.kayttoliittyma.GraafinenKayttoliittyma;
import fi.aleksisv.kayttoliittyma.PeliOhjaus;
import java.util.ArrayList;
import javax.swing.SwingUtilities;

/**
 * Pääluokka.
 */
public class NewMain {
    
    /**
     * Luokan konstruktori.
     * @param args Argumentit.
     */
    public static void main(String[] args) {
//        PeliOhjaus peliohjaus = new PeliOhjaus();
        GraafinenKayttoliittyma gkl = new GraafinenKayttoliittyma();
        SwingUtilities.invokeLater(gkl);

    }

}
