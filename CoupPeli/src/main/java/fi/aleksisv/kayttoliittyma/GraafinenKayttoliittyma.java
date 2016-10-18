package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Random;
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
        peliOhjaus = new PeliOhjaus();

        if (onkoAloitettu) {
            if (this.peliOhjaus.getPeli().getOsanottajajoukko().size() == 1) {
                lopetaPeli();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent tapahtuma) {
        if (tapahtuma.getSource() == aloitaPeliNappi) {
            aloitaPeli();
            this.valiIkkuna.luoPelausYmparisto();
            avausIkkuna.getAloitaPeliNappi().setVisible(false);
            avausIkkuna.getMontakoPelaajaa().setVisible(false);
        }
        
        SwingUtilities.updateComponentTreeUI(valiIkkuna);
    }

    private void aloitaPeli() {
        int pelaajaMaara = Integer.parseInt(avausIkkuna.getMontakoPelaajaa().getText());
        if (!(2 <= pelaajaMaara && pelaajaMaara <= 5)) {
            this.huomioTekstit.setText("Anna validi määrä (2-5) pelaajia.");
        } else {
            pelaajaMaara = (int) pelaajaMaara;
            this.avausIkkuna.dispose();
            this.peliOhjaus.luoPeli(pelaajaMaara);
            this.valiIkkuna = new ValiIkkuna(peliOhjaus, this);
            this.peliOhjaus.luoPeli(pelaajaMaara);
            this.huomioTekstit.setText("Aloitit pelin " + pelaajaMaara + " pelaajalla. Tee siirto.");
        }
        onkoAloitettu = true;
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

    private void lopetaPeli() {
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
