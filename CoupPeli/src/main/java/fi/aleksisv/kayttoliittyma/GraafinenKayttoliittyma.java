package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

/**
 * Luokka vastaa ohjelman GUI-puolesta.
 */
public class GraafinenKayttoliittyma implements Runnable, ActionListener {

    /**
     * Komponentit, joita käyttöliittymä tarvitsee.
     */
    private AvausIkkuna avausIkkuna;
    private ValiIkkuna valiIkkuna;
    private PelausIkkuna pelausIkkuna;
    private PeliOhjaus peliOhjaus;
    private JButton aloitaPeliNappi;
    private JTextArea huomioTekstit;
    private boolean onkoAloitettu;

    /**
     * Random r.
     */
    private Random r;

    /**
     * Luokan konstruktori.
     */
    public GraafinenKayttoliittyma() {
        this.r = new Random();
    }

    /**
     * Metodi pyörittää peliä.
     */
    public void run() {
        avausIkkuna = new AvausIkkuna("Coup-Peli", this);
        peliOhjaus = new PeliOhjaus(this);
    }

    @Override
    public void actionPerformed(ActionEvent tapahtuma) {
        if (tapahtuma.getSource() == aloitaPeliNappi) {
            aloitaPeli();
        }
    }

    private void aloitaPeli() {
        String pelaajaMaaraTeksti = avausIkkuna.getMontakoPelaajaa().getText();
        Pattern p = Pattern.compile("[2-5]");
        Matcher m = p.matcher(pelaajaMaaraTeksti);

        if (!m.matches()) {
            this.huomioTekstit.setText("Validi määrä (2-5) pelaajia.");
            return;
        } else {
            int pelaajaMaara = Integer.parseInt(avausIkkuna.getMontakoPelaajaa().getText());
            this.avausIkkuna.dispose();
            this.peliOhjaus.luoPeli(pelaajaMaara);
            this.valiIkkuna = new ValiIkkuna(peliOhjaus, this);
            this.peliOhjaus.luoPeli(pelaajaMaara);
            this.huomioTekstit.setText("Aloitit pelin " + pelaajaMaara + " pelaajalla. Tee siirto.");

            this.valiIkkuna.luoPelausYmparisto();
            avausIkkuna.getAloitaPeliNappi().setVisible(false);
            avausIkkuna.getMontakoPelaajaa().setVisible(false);
            onkoAloitettu = true;
        }

    }

    public void paivitaPelinseuranta(PelinSeurantaPaneeli seuranta) {
        seuranta.paivitaTila(this.peliOhjaus.getPeli());
    }

    void setHuomioTekstit(JTextArea huomioTekstit) {
        this.huomioTekstit = huomioTekstit;
    }

    void setAloitaPeliNappi(JButton aloitaPeliNappi) {
        this.aloitaPeliNappi = aloitaPeliNappi;
    }
    
    /**
     * Metodi lopettaa pelin.
     *
     */
    public void lopetaPeli() {
        this.valiIkkuna.dispose();
        JFrame lopetusikkuna = new JFrame("Peli on ohi!");
        lopetusikkuna.add(new JTextArea("Voittaja: " + this.peliOhjaus.getPeli().getOsanottajajoukko().get(0)));
        lopetusikkuna.setSize(400, 400);
        lopetusikkuna.setVisible(true);
        lopetusikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public PelinSeurantaPaneeli getPelinSeurantapaneeli() {
        return this.valiIkkuna.pelinSeurantapaneeli;
    }

    public ValiIkkuna getValiIkkuna() {
        return valiIkkuna;
    }

    public void setPelausIkkuna(PelausIkkuna pelausIkkuna) {
        this.pelausIkkuna = pelausIkkuna;
    }

    public PelausIkkuna getPelausIkkuna() {
        return pelausIkkuna;
    }

}
