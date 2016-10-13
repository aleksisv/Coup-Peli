package fi.aleksisv.kayttoliittyma;

import java.awt.*;
import javax.swing.*;
import javax.swing.JTextField;

/**
 * Luokan olio toimii pelin aloitusvalikkona.
 */
public class AvausIkkuna extends JFrame {

    private GraafinenKayttoliittyma gkl;
    private JButton aloitaPeliNappi;
    private JTextField montakoPelaajaa;
    private JTextArea huomioTekstit;

    public AvausIkkuna(String otsikko, GraafinenKayttoliittyma gkl) throws HeadlessException {
        super(otsikko);

        this.gkl = gkl;

        this.setPreferredSize(new Dimension(1000, 600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        luokomponentit();

        this.pack();
        this.setVisible(true);

    }

    private void luokomponentit() {
        Container sailio = this.getContentPane();
        sailio.setLayout(new GridLayout(2, 2));
        sailio.add(luoAvausvalikko());
        this.huomioTekstit = new JTextArea("Luo peli haluamallasi määrällä pelaajia!");
        sailio.add(huomioTekstit);
        gkl.setHuomioTekstit(huomioTekstit);

    }

    private JPanel luoAvausvalikko() {
        JPanel paneeli = new JPanel(new GridLayout(1, 2));
        this.aloitaPeliNappi = new JButton("Aloita peli!");
        this.aloitaPeliNappi.addActionListener(gkl);
        gkl.setAloitaPeliNappi(aloitaPeliNappi);

        this.montakoPelaajaa = new JTextField();

        paneeli.add(aloitaPeliNappi);
        paneeli.add(montakoPelaajaa);

        return paneeli;
    }

    public JTextField getMontakoPelaajaa() {
        return montakoPelaajaa;
    }

    public JButton getAloitaPeliNappi() {
        return aloitaPeliNappi;
    }
    
    


}
