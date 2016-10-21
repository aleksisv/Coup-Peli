package fi.aleksisv.kayttoliittyma;

import java.awt.*;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.JTextField;

/**
 * Luokan olio toimii pelin aloitusvalikkona.
 */
public class AvausIkkuna extends JFrame {

    /**
     * GUI, johon frame kuuluu.
     */
    private GraafinenKayttoliittyma gkl;
    /**
     * Nappi, jolla peli aloitetaan.
     */
    private JButton aloitaPeliNappi;
    /**
     * Pelaajien määrästä kertova JTextField.
     */
    private JTextField montakoPelaajaa;
    /**
     * Pelaajan nimestä kertova JTextField.
     */
    private JTextField pelaajanNimi;
    /**
     * Erityishuomioista kertova tekstialue.
     */
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
        sailio.setLayout(new GridLayout(2, 1));
        sailio.add(luoAvausvalikko());
        this.huomioTekstit = new JTextArea("Tämä on Coup-peli. Tehtävänäsi on paljastaa vastustajiesi kortit ja suojella omiasi. \n"
                + "Saat pelin alussa kaksi korttia. Jokaisella kortilla on erikoisominaisuuksia. Joka vuoro joudut tekemään siirron, joista johoinkin vaaditaan"
                + " tietyn kortin omistamista.\nVastustaja voi yrittää torjua siirron jollain omista korteistaan. Vaihtoehtoisesti hän voi epäillä, omistatko väittämääsi korttia lainkaan."
                + "Voit tehdä samoin. Jos epäilijä on oikeassa eikä siirron tekijällä ole korttia, siirron tekijä menettää yhden kortin.\n"
                + "Vastaavasti jos epäilijä on väärässä epäilijä itse menettää kortin ja siirto osuu häneen."
                + "\n\nValitse sopiva nimi itsellesi ja luo peli haluamallasi määrällä pelaajia!"
                + "\nSallittu määrä pelaajia on 2-5.");
        this.huomioTekstit.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.black));
        JPanel tekstiPaneeli = new JPanel(new BorderLayout());
        tekstiPaneeli.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        tekstiPaneeli.add(huomioTekstit);
        sailio.add(tekstiPaneeli);
        gkl.setHuomioTekstit(huomioTekstit);

    }

    private JPanel luoAvausvalikko() {
        JPanel paneeli = new JPanel(new GridLayout(1, 3));

        JPanel aloitusSala = new JPanel(new BorderLayout());
        this.aloitaPeliNappi = new JButton("Aloita peli!");
        this.aloitaPeliNappi.setSize(40, 40);

        aloitusSala.setBorder(BorderFactory.createEmptyBorder(135, 20, 20, 20));
        aloitusSala.add(this.aloitaPeliNappi, BorderLayout.CENTER);
        this.aloitaPeliNappi.addActionListener(gkl);
        gkl.setAloitaPeliNappi(aloitaPeliNappi);

        JPanel osanottajia = new JPanel(new GridLayout(2, 1));
        osanottajia.setBorder(BorderFactory.createEmptyBorder(100, 20, 20, 20));
        osanottajia.add(new JLabel("Monellako pelaajalla haluat pelata?"));
        this.montakoPelaajaa = new JTextField();
        osanottajia.add(montakoPelaajaa);

        JPanel nimi = new JPanel(new GridLayout(2, 1));
        nimi.setBorder(BorderFactory.createEmptyBorder(100, 20, 20, 20));
        nimi.add(new JLabel("Minkä nimen haluat?"));
        this.pelaajanNimi = new JTextField();
        nimi.add(pelaajanNimi);

        paneeli.add(aloitusSala);
        paneeli.add(osanottajia);
        paneeli.add(nimi);

        return paneeli;
    }

    public JTextField getMontakoPelaajaa() {
        return montakoPelaajaa;
    }

    public JButton getAloitaPeliNappi() {
        return aloitaPeliNappi;
    }

    public JTextField getPelaajanNimi() {
        return pelaajanNimi;
    }

}
