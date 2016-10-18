
package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class PelinSeurantaPaneeli extends JPanel {
    PeliOhjaus peliOhjaus;
    
    /**
     * Luokan konstruktori.
     * 
     * @param layout Mikä layout paneelille halutaan.
     */
    public PelinSeurantaPaneeli(GridLayout layout, PeliOhjaus peliOhjaus) {
        super(layout);
        this.peliOhjaus = peliOhjaus;
        this.setVisible(true);
    }

    /**
     * Metodi asettaa pelin tilanne-esityksen alkutilaansa.
     *
     * @param peli Peli, jota tällä hetkellä käsitellään.
     */
    public void asetaAlkutila(Peli peli) {
        for (Osanottaja o : peli.getOsanottajajoukko()) {
            if (o instanceof Pelaaja) {
                JTextArea teksti = new JTextArea("Pelaaja: \n" + o.toString());
                teksti.setMargin(new Insets(5, 5, 5, 5));
                teksti.setSize(new Dimension(100, 100));
                super.add(teksti);
            } else {
                JTextArea teksti = new JTextArea("Vastustaja: \n" + o.toString());
                teksti.setSize(new Dimension(100, 100));
                teksti.setMargin(new Insets(5, 5, 5, 5));
                super.add(teksti);
            }
        }
        SwingUtilities.updateComponentTreeUI(this);
    }

    /**
     * Metodin avulla pelinseurannan tila päivitetään.
     *
     * @param peli Mikä peli päivitetään.
     */
    public void paivitaTila(Peli peli) {
        this.removeAll();
        ArrayList<Osanottaja> kaikki = (ArrayList<Osanottaja>) peli.getOsanottajajoukko().clone();
        kaikki.addAll(peli.getHavinnytJoukko());
        for (Osanottaja o : kaikki) {
            if (o instanceof Pelaaja) {
                JTextArea teksti = new JTextArea("Pelaaja: \n" + o.toString());
                teksti.setSize(new Dimension(80, 80));
                teksti.setMargin(new Insets(10, 10, 10, 10));
                this.add(teksti);
            } else {
                JTextArea teksti = new JTextArea("Vastustaja: \n" + o.toString());
                teksti.setSize(new Dimension(80, 80));
                teksti.setMargin(new Insets(10, 10, 10, 10));
                this.add(teksti);
            }
        }
        SwingUtilities.updateComponentTreeUI(this);
    }

}
