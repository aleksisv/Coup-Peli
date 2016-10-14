/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.Osanottaja;
import fi.aleksisv.logiikka.Pelaaja;
import fi.aleksisv.logiikka.Peli;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class PelinSeurantaPaneeli extends JPanel {
    
    /**
     * Luokan konstruktori.
     * 
     * @param layout Mikä layout paneelille halutaan.
     */
    public PelinSeurantaPaneeli(GridLayout layout) {
        super(layout);
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
        ArrayList<Osanottaja> kaikki = (ArrayList<Osanottaja>) peli.getOsanottajajoukko().clone();
        kaikki.addAll(peli.getHavinnytJoukko());
        for (Osanottaja o : kaikki) {
            if (o instanceof Pelaaja) {
                JTextArea teksti = new JTextArea("Pelaaja: \n" + o.toString());
                teksti.setSize(new Dimension(80, 80));
                teksti.setMargin(new Insets(10, 10, 10, 10));
                super.add(teksti);
            } else {
                JTextArea teksti = new JTextArea("Vastustaja: \n" + o.toString());
                teksti.setSize(new Dimension(80, 80));
                teksti.setMargin(new Insets(10, 10, 10, 10));
                super.add(teksti);
            }
        }
        SwingUtilities.updateComponentTreeUI(this);
    }

}
