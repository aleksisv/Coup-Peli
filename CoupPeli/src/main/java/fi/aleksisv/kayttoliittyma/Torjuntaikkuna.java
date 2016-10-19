package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.Vastustaja;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Luokka kuvaa pelaajan torjuntatilanteeseen liittyvää ikkunaa.
 */
public class Torjuntaikkuna extends JFrame {

    /**
     * Vastustaja, joka liittyy tilanteeseen.
     */
    Vastustaja vastustaja;
    /**
     * Pelin pyörittämisestä vastaava taho.
     */
    PeliOhjaus peliOhjaus;
    /**
     * Siirtonumero.
     */
    int siirto;
    /**
     * Graafinen käyttöliittymä.
     */
    GraafinenKayttoliittyma gkl;

    /**
     * Luokan konstruktori.
     * @param vastustaja Tilanteeseen liittyvä vastustaja.
     * @param peliOhjaus Pelin pyörittämisestä vastaava taho.
     * @param siirto Siirtonumero
     * @param gkl Graafinen käyttöliittymä.
     */
    public Torjuntaikkuna(Vastustaja vastustaja, PeliOhjaus peliOhjaus, int siirto, GraafinenKayttoliittyma gkl) {
        super("Torjuntaikkuna");
        this.vastustaja = vastustaja;
        this.peliOhjaus = peliOhjaus;
        this.siirto = siirto;
        this.gkl = gkl;
    }
    
    /** 
     * Metodi luo tarvittavat komponentit.
     * @param sailio Container-olio.
     */
    public void luoKomponentit(Container sailio) {
        JPanel torjuntaPaneeli = new JPanel(new GridLayout(3, 1));
        sailio.setVisible(true);

        JButton epaileVastustajanTorjuntaa = new JButton("Epäile vastustajan torjuntaa.");
        JButton peruutaSiirto = new JButton("Peruuta siirto.");

        Epailykuuntelija epailykuuntelija = new Epailykuuntelija(epaileVastustajanTorjuntaa,
                peruutaSiirto, false, vastustaja, peliOhjaus, siirto, gkl, this);

        epaileVastustajanTorjuntaa.addActionListener(epailykuuntelija);
        peruutaSiirto.addActionListener(epailykuuntelija);
        torjuntaPaneeli.add(new JTextArea("Vastustaja haluaa torjua siirtosi. Haluatko epäillä vastustajan torjumista?"));
        torjuntaPaneeli.add(epaileVastustajanTorjuntaa);
        torjuntaPaneeli.add(peruutaSiirto);

        sailio.add(torjuntaPaneeli);
        sailio.setSize(700, 400);

        this.setVisible(true);

        this.pack();
    }

}
