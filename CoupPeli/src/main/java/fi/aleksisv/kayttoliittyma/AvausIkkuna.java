package fi.aleksisv.kayttoliittyma;

import java.awt.*;
import javax.swing.*;
import javax.swing.JTextField;

/**
 * Luokan olio toimii pelin aloitusvalikkona.
 */
public class AvausIkkuna extends JFrame {
    
    /** GUI, johon frame kuuluu.*/
    private GraafinenKayttoliittyma gkl;
    /** Nappi, jolla peli aloitetaan.*/
    private JButton aloitaPeliNappi;
    /** Pelaajien määrästä kertova JTextField.*/
    private JTextField montakoPelaajaa;
    /** Erityishuomioista kertova tekstialue.*/
    private JTextArea huomioTekstit;

    /**
     * Luokan konstruktori.
     * 
     * @param otsikko Ikkunan otsikko.
     * @param gkl Graafinen käyttöliittymä.
     * 
     * @throws HeadlessException Heittää headless-exceptionin.
     */
    public AvausIkkuna(String otsikko, GraafinenKayttoliittyma gkl) throws HeadlessException {
        super(otsikko);

        this.gkl = gkl;

        this.setPreferredSize(new Dimension(1000, 600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        luokomponentit(this.getContentPane());

        this.pack();
        this.setVisible(true);

    }

    private void luokomponentit(Container sailio) {
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
