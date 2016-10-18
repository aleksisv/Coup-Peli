package fi.aleksisv.kayttoliittyma;

import java.awt.*;
import javax.swing.*;

public class ValiIkkuna extends JFrame {
    JTextArea huomioTekstit;
    PeliOhjaus peliOhjaus;
    PelinSeurantaPaneeli pelinSeurantapaneeli;
    JButton pelaaVuoroNappi;
    GraafinenKayttoliittyma gkl;

    public ValiIkkuna(PeliOhjaus peliOhjaus, GraafinenKayttoliittyma gkl) throws HeadlessException {
        super("Coup-Peli");
        this.peliOhjaus = peliOhjaus;
        this.gkl = gkl;

        this.setVisible(true);
        this.setSize(1000, 700);
        
        this.luoKomponentit();
    }

    public void luoKomponentit() {
        Container sailio = this.getContentPane();

        sailio.setLayout(new GridLayout(3, 2));
        this.huomioTekstit = new JTextArea("JEE");

        sailio.add(huomioTekstit);
        
        this.huomioTekstit.setText("Aloitit pelin " + this.peliOhjaus.getPeli().getOsanottajamaara() + " pelaajalla. Tee siirto.");
    }

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
        pelaaVuoroNappi.addActionListener(new PelinSeurantaKuuntelija(peliOhjaus, gkl));
        this.add(pelaaVuoroNappi);
    }

}
