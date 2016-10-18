package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.Vastustaja;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Torjuntaikkuna extends JFrame {
    Vastustaja vastustaja;
    PeliOhjaus peliOhjaus;
    int siirto;
    GraafinenKayttoliittyma gkl;


    public Torjuntaikkuna(Vastustaja vastustaja, PeliOhjaus peliOhjaus, int siirto, GraafinenKayttoliittyma gkl) {
        super("Torjuntaikkuna");
        this.vastustaja = vastustaja;
        this.peliOhjaus = peliOhjaus;
        this.siirto = siirto;
        this.gkl = gkl;
    }

    public void luoKomponentit() {
        

    }

    public void luoKomponentit(Container sailio) {
        sailio.setSize(700, 400);
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
        
        this.setVisible(true);
        
        this.pack();
    }

}
