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
        String pelaajanNimi = avausIkkuna.getPelaajanNimi().getText();
        Pattern p1 = Pattern.compile("[2-5]");
        Matcher m1 = p1.matcher(pelaajaMaaraTeksti);
        Pattern p2 = Pattern.compile("(\\s|[a-z|å|ö|ä|0-9]+)+");
        Matcher m2 = p2.matcher(pelaajanNimi.toLowerCase());

        if (!m1.matches()) {
            this.huomioTekstit.setText("Anna validi määrä (2-5) pelaajia.");
        } else if (!m2.matches()) {
            this.huomioTekstit.setText("Nimeen saa kuulua vain aakkosia, välilyöntejä ja numeraaleja.");
        } else {
            int pelaajaMaara = Integer.parseInt(avausIkkuna.getMontakoPelaajaa().getText());
            this.avausIkkuna.dispose();
            this.peliOhjaus.luoPeli(pelaajaMaara, pelaajanNimi);
            this.valiIkkuna = new ValiIkkuna(peliOhjaus, this);
            this.huomioTekstit.setText("Aloitit pelin " + pelaajaMaara + " pelaajalla. Tee siirto.");

            this.valiIkkuna.luoPelausYmparisto();
        }

    }

    /**
     * Metodi päivittää pelinseurantapaneelin.
     *
     * @param seuranta Pelin seurannasta vastaava paneeli.
     */
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
     * @param voittanut Onko lopetustilanteessa pelaaja voittanut vai hävinnyt.
     */
    public void lopetaPeli(boolean voittanut) {
        this.valiIkkuna.dispose();
        JFrame lopetusikkuna = new JFrame("Peli on ohi!");

        if (voittanut) {
            lopetusikkuna.add(new JTextArea("Voitit:\n" + this.peliOhjaus.getPeli().getOsanottajajoukko().get(0)));
        } else {
            lopetusikkuna.add(new JTextArea("Valitettavasti hävisit."));
        }

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

    void pelaajaOnHavinnyt() {

    }

}
