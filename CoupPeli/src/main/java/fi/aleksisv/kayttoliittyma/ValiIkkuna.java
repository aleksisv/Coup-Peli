package fi.aleksisv.kayttoliittyma;

import java.awt.*;
import javax.swing.*;

/**
 * Luokka vastaa pelin perusnäkymästä.
 */
public class ValiIkkuna extends JFrame {

    /**
     * Tekstialue, joka ilmoittaa erityisistä huomioista.
     */
    JTextArea huomioTekstit;
    /**
     * Peliä ohjaava taho.
     */
    PeliOhjaus peliOhjaus;
    /**
     * Pelinseurantaa yllä pitävä taho.
     */
    PelinSeurantaPaneeli pelinSeurantapaneeli;
    /**
     * Nappi, jota painamalla edetään yksi vuoro.
     */
    JButton pelaaVuoroNappi;
    /**
     * Graafinen käyttöliittymä.
     */
    GraafinenKayttoliittyma gkl;

    /**
     * Luokan konstruktori.
     *
     * @param peliOhjaus Peliä pyörittävä taho.
     * @param gkl Käyttöliittymä.
     *
     * @throws HeadlessException
     */
    public ValiIkkuna(PeliOhjaus peliOhjaus, GraafinenKayttoliittyma gkl) throws HeadlessException {
        super("Coup-Peli");
        this.peliOhjaus = peliOhjaus;
        this.gkl = gkl;

        this.setVisible(true);
        this.setSize(1200, 700);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.luoKomponentit();
    }

    /**
     * Metodi luo tarvittavat komponentit.
     */
    public void luoKomponentit() {
        Container sailio = this.getContentPane();

        sailio.setLayout(new GridLayout(3, 2));

        this.huomioTekstit = new JTextArea();

        sailio.add(huomioTekstit);

        this.huomioTekstit.setText("Aloitit pelin " + this.peliOhjaus.getPeli().getOsanottajamaara() + " pelaajalla. Tee siirto.");
    }

    /**
     * Metodi luo pelausympäristön.
     */
    void luoPelausYmparisto() {
        luoPelinseuranta();
        luoPelausNappi();
        this.validate();
    }

    private void luoPelinseuranta() {
        this.pelinSeurantapaneeli = new PelinSeurantaPaneeli(new GridLayout(1,
                this.peliOhjaus.getPeli().getOsanottajajoukko().size()), this.peliOhjaus);
        this.pelinSeurantapaneeli.asetaAlkutila(this.peliOhjaus.getPeli());
        this.add(pelinSeurantapaneeli);
    }

    private void luoPelausNappi() {
        this.pelaaVuoroNappi = new JButton("Pelaa vuoro!");
        pelaaVuoroNappi.setPreferredSize(new Dimension(150, 50));
        pelaaVuoroNappi.addActionListener(new PelinSeurantaKuuntelija(peliOhjaus, gkl));

        JPanel takaPaneeli = new JPanel();
        takaPaneeli.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.black));

        JPanel nappiPaneeli = new JPanel();
        nappiPaneeli.setBorder(BorderFactory.createEmptyBorder(50, 30, 30, 30));

        nappiPaneeli.add(pelaaVuoroNappi);
        takaPaneeli.add(nappiPaneeli);
        this.add(takaPaneeli);
    }

}
