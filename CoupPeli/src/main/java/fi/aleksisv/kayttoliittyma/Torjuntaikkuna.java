package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.Vastustaja;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
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
        this.setLocation(0, 500);
    }
    
    /** 
     * Metodi luo tarvittavat komponentit.
     * @param sailio Container-olio.
     */
    public void luoKomponentit(Container sailio) {
        JPanel torjuntaPaneeli = new JPanel(new GridLayout(3, 1));
        torjuntaPaneeli.setBounds(30, 30, 30, 30);
        
        sailio.setVisible(true);
        
        JTextArea teksti = new JTextArea("Vastustaja haluaa torjua siirtosi.\nHaluatko epäillä vastustajan torjumista?");
        
        JPanel ilmoitusPaneeli = new JPanel(new BorderLayout());
        ilmoitusPaneeli.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.black));
        
       
        JButton epaileVastustajanTorjuntaa = new JButton("Epäile vastustajan torjuntaa.");
        JButton peruutaSiirto = new JButton("Peruuta siirto.");

        Epailykuuntelija epailykuuntelija = new Epailykuuntelija(epaileVastustajanTorjuntaa,
                peruutaSiirto, false, vastustaja, peliOhjaus, siirto, gkl, this);
        epaileVastustajanTorjuntaa.addActionListener(epailykuuntelija);
        
        peruutaSiirto.addActionListener(epailykuuntelija);
        
        ilmoitusPaneeli.add(teksti, BorderLayout.CENTER);
        
        torjuntaPaneeli.add(ilmoitusPaneeli);
        
        torjuntaPaneeli.add(epaileVastustajanTorjuntaa);
        torjuntaPaneeli.add(peruutaSiirto);

        sailio.add(torjuntaPaneeli);
        
        this.setVisible(true);
        this.setSize(400, 500);
        
    }

}
