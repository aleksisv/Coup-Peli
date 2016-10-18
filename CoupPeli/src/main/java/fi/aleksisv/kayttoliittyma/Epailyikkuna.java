package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.Vastustaja;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.*;

public class Epailyikkuna extends JFrame {
    private boolean onkoEnsimmainen;
    private Vastustaja vastustaja;
    PeliOhjaus peliOhjaus;
    int siirto;
    GraafinenKayttoliittyma gkl;

    public Epailyikkuna(boolean onkoEnsimmainen, Vastustaja vastustaja, PeliOhjaus peliOhjaus, int siirto, GraafinenKayttoliittyma gkl) {
        super("Epäilyikkuna");
        this.onkoEnsimmainen = onkoEnsimmainen;
        this.vastustaja = vastustaja;
        this.peliOhjaus = peliOhjaus;
        this.siirto = siirto;
        this.gkl = gkl;
        
    }

    public void luoKomponentit(Container sailio) {
        JPanel epailyPaneeli = new JPanel(new GridLayout(3, 1));
        sailio.setVisible(true);

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
        
        this.pack();
    }

}
