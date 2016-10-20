package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.Vastustaja;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.*;

/**
 * Luokka kuvaa sellaista JFramea, jota käytetään tilanteessa, jossa vastustaja
 * haluaa epäillä siirtoasi.
 */
public class Epailyikkuna extends JFrame {
    
    /** Onko kyseessä vuoron ensimmäinen epäilyikkuna.*/
    private boolean onkoEnsimmainen;
    /** Tilanteeseen liittyvä vastustaja.*/
    private Vastustaja vastustaja;
    /** Pelin pyörittämisestä vastaava taho.*/
    PeliOhjaus peliOhjaus;
    /** Siirron numero.*/
    int siirto;
    /** Graafinen käyttöliittymä.*/
    GraafinenKayttoliittyma gkl;

    /**
     * Luokan konstruktori.
     * 
     * @param onkoEnsimmainen Onko kyseessä kierroksen ensimmäinen vai toinen
     * epäilykerta. Onko epäilijä siis pelaaja vai vastustaja.
     * @param vastustaja Tilanteessa osallisena oleva vastustaja.
     * @param peliOhjaus PeliOhjaus.
     * @param siirto Siirron numero.
     * @param gkl Graafinen käyttöliittymä.
     */
    public Epailyikkuna(boolean onkoEnsimmainen, Vastustaja vastustaja, PeliOhjaus peliOhjaus, int siirto, GraafinenKayttoliittyma gkl) {
        super("Epäilyikkuna");
        this.onkoEnsimmainen = onkoEnsimmainen;
        this.vastustaja = vastustaja;
        this.peliOhjaus = peliOhjaus;
        this.siirto = siirto;
        this.gkl = gkl;

    }
    
    /**
     * Luokan konstruktori.
     * 
     * @param sailio Container-olio.
     */
    public void luoKomponentit(Container sailio) {
        JPanel epailyPaneeli = new JPanel(new GridLayout(3, 1));
        sailio.setVisible(true);
        
        JTextArea teksti = new JTextArea("Vastustaja haluaa epäillä siirtoasi."
                + " Haluatko perua siirron?");
        JPanel tekstiPaneeli = new JPanel(new BorderLayout());
        
        tekstiPaneeli.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        tekstiPaneeli.add(teksti);

        JButton tee = new JButton("Tee siirto.");
        JButton alaTee = new JButton("Älä tee siirtoa.");
        epailyPaneeli.add(new JTextArea("Vastustaja haluaa epäillä siirtoasi. Haluatko perua siirron?"));

        Epailykuuntelija epailykuuntelija = new Epailykuuntelija(tee, alaTee,
                onkoEnsimmainen, vastustaja, peliOhjaus, siirto, gkl, this);

        tee.addActionListener(epailykuuntelija);
        alaTee.addActionListener(epailykuuntelija);

        epailyPaneeli.add(tee);
        epailyPaneeli.add(alaTee);

        sailio.add(epailyPaneeli);
        
        this.setVisible(true);
        this.setSize(400, 500);
        
        
    }

}
