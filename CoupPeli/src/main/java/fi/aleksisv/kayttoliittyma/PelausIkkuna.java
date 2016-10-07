package fi.aleksisv.kayttoliittyma;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 * Luokka kuvaa ikkunaa, joka kertoo pelaajalle pelin tilanteen ja antaa tälle
 * mahdollisuuden tehdä siirtoja.
 */
public class PelausIkkuna extends JFrame {
    
    /**
     * Luokan konstruktori.
     */
    public PelausIkkuna() {
        setTitle("Coup-Peli");
        setSize(400, 300);

    }

}
