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
public class GraafinenKayttoliittyma extends JPanel implements Runnable, ActionListener {
    
    /** Komponentit, joita käyttöliittymä tarvitsee. */
    private JFrame valiIkkuna, pelausIkkuna, epailyJaTorjuntaIkkuna;
    private AvausIkkuna avausIkkuna;
    private PeliOhjaus peliOhjaus;
    private JButton aloitaPeliNappi, pelaaVuoroNappi, teeSiirto, vastustajanSiirto;
    private JTextField montakoPelaajaa;
    private JTextArea huomioTekstit;
    private PelinSeurantaPaneeli pelinSeurantapaneeli;
    private ButtonGroup nappularyhma1, nappularyhma2;
    
    /** Random r.*/
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
    }

    @Override
    public void actionPerformed(ActionEvent tapahtuma) {
        if (tapahtuma.getSource() == aloitaPeliNappi) {
            aloitaPeli();
            luoPelausYmparisto();
        } else if (tapahtuma.getSource() == pelaaVuoroNappi) {
            pelaaVuoro();
            SwingUtilities.updateComponentTreeUI(pelausIkkuna);
        } else if (tapahtuma.getSource() == teeSiirto) {
            int siirtoNumero = Integer.parseInt(nappularyhma1.getSelection().getActionCommand());
            if (this.peliOhjaus.onkoPelaajallaRahaa(siirtoNumero)) {
                yritaSiirtoa(Integer.parseInt(nappularyhma1.getSelection().getActionCommand()),
                        Integer.parseInt(nappularyhma2.getSelection().getActionCommand()));
            } else {
                this.huomioTekstit.setText("Liian vähän rahaa siirtoon. Valitse uusi siirto.");
            }
            SwingUtilities.updateComponentTreeUI(pelausIkkuna);
        } else if (tapahtuma.getSource() == vastustajanSiirto) {
            int pelivuorossa = this.peliOhjaus.getPeli().getVuoronumero() % this.peliOhjaus.getPeli().getOsanottajajoukko().size();
            Vastustaja vastustaja = (Vastustaja) this.peliOhjaus.getPeli().getOsanottajajoukko().get(pelivuorossa);
            vastustajanSiirto(vastustaja);
            SwingUtilities.updateComponentTreeUI(pelausIkkuna);
        }
        if (this.peliOhjaus.getPeli().getOsanottajajoukko().size() == 1) {
            this.valiIkkuna.dispose();
            this.pelausIkkuna.dispose();
            this.valiIkkuna = new JFrame("Peli on ohi!");
            this.valiIkkuna.add(new JTextArea("Voittaja: " + this.peliOhjaus.getPeli().getOsanottajajoukko().get(0)));
            this.valiIkkuna.setSize(400, 400);
            this.valiIkkuna.setVisible(true);
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
            this.valiIkkuna = new JFrame("Coup-Peli");
            this.valiIkkuna.setVisible(true);
            this.valiIkkuna.setSize(1000, 700);
            valiIkkuna.getContentPane().setLayout(new GridLayout(3, 2));
            valiIkkuna.add(huomioTekstit);
            this.peliOhjaus.luoPeli(pelaajaMaara);
            this.huomioTekstit.setText("Aloitit pelin " + pelaajaMaara + " pelaajalla. Tee siirto.");
        }
    }

    private void luoPelausYmparisto() {
        luoPelinseuranta();
        luoPelausNappi();
        avausIkkuna.getAloitaPeliNappi().setVisible(false);
        avausIkkuna.getMontakoPelaajaa().setVisible(false);
        this.valiIkkuna.validate();

    }

    private void luoPelinseuranta() {
        this.pelinSeurantapaneeli = new PelinSeurantaPaneeli(new GridLayout(1,
                this.peliOhjaus.getPeli().getOsanottajajoukko().size()));
        this.pelinSeurantapaneeli.asetaAlkutila(this.peliOhjaus.getPeli());
        this.valiIkkuna.add(pelinSeurantapaneeli);
    }

    private void luoPelausNappi() {
        this.pelaaVuoroNappi = new JButton("Pelaa vuoro!");
        pelaaVuoroNappi.addActionListener(this);
        this.valiIkkuna.add(pelaaVuoroNappi);
    }

    private void paivitaPelinseuranta(PelinSeurantaPaneeli seuranta) {
        seuranta.removeAll();
        seuranta.paivitaTila(this.peliOhjaus.getPeli());
    }

    private void pelaaVuoro() {
        this.pelausIkkuna = new JFrame("Pelaus-ikkuna");
        if (this.peliOhjaus.getPeli().getOsanottajajoukko().get(this.peliOhjaus.getPeli().getVuoronumero()
                % this.peliOhjaus.getPeli().getOsanottajajoukko().size()) instanceof Pelaaja) {
            pelausIkkuna.add(luoPelausVaihtoehdot());
        } else {
            pelausIkkuna.add(luoVastustajanvuoro());
        }
        pelausIkkuna.pack();
        pelausIkkuna.setVisible(true);
    }

    private JPanel luoPelausVaihtoehdot() {
        JPanel napit = new JPanel(new GridLayout(3, 1));
        JPanel siirtonapit = new JPanel();
        JPanel kohdenapit = new JPanel();
        String[][] siirtoVaihtoehdot = {{"Perustulo:", "Ota 1 kolikko pankista."}, {"Ulkomaanapu:", "Ota 2 kolikkoa pankista."},
            {"Vallankumous:", "Maksa 7 kolikkoa ja hyökkää yhtä osanottajaa vastaan."},
            {"Verotus:", "Ota 3 kolikkoa pankista."}, {"Assassinoi:", "Maksa 3 kolikkoa ja hyökkää yhtä osanottajaa kohtaan."}, {"Varasta:", "Ota vastustajalta kaksi kolikkoa."}};

        lisaaSiirtonapit(siirtoVaihtoehdot, siirtonapit);
        lisaaKohdenapit(kohdenapit);
        napit.add(siirtonapit);
        napit.add(kohdenapit);

        teeSiirto = new JButton("Tee siirto!");
        teeSiirto.addActionListener(this);
        napit.add(teeSiirto);

        return napit;
    }

    private void yritaSiirtoa(int siirtonumero, int kohde) {
        Pelaaja pelaaja = this.peliOhjaus.getPeli().getPelaaja();
        Vastustaja vastustaja = (Vastustaja) this.peliOhjaus.getPeli().getOsanottajajoukko().get(kohde);
        Kortti siirto = this.peliOhjaus.getPeli().getSiirtoNumerot().get(siirtonumero);

        if (vastustaja.haluaaEpailla(pelaaja, siirto) && false) {
            this.epailyJaTorjuntaIkkuna = new JFrame("Vastustaja haluaa epäillä.");
            JPanel epailyPaneeli = new JPanel(new GridLayout(3, 1));
            this.epailyJaTorjuntaIkkuna.setVisible(true);
            JButton tee = new JButton("Tee siirto.");
            JButton alaTee = new JButton("Älä tee siirtoa.");
            epailyPaneeli.add(new JTextArea("Vastustaja haluaa epäillä siirtoasi. Haluatko perua siirron?"));
            tee.addActionListener(this);
            alaTee.addActionListener(this);
            epailyPaneeli.add(tee);
            epailyPaneeli.add(alaTee);
            this.epailyJaTorjuntaIkkuna.pack();
        } else if (vastustaja.haluaaTorjua(pelaaja, siirto) && false) {
            this.epailyJaTorjuntaIkkuna = new JFrame("Vastustaja haluaa torjua siirtosi.");
            JPanel torjuntaPaneeli = new JPanel(new GridLayout(3, 1));
            this.epailyJaTorjuntaIkkuna.setVisible(true);
            JButton tee = new JButton("Tee siirto.");
            JButton alaTee = new JButton("Älä tee siirtoa");
            tee.addActionListener(this);
            alaTee.addActionListener(this);
            torjuntaPaneeli.add(new JTextArea("Vastustaja haluaa torjua siirtosi. Haluatko epäillä vastustajan torjumista?"));
            torjuntaPaneeli.add(tee);
            torjuntaPaneeli.add(alaTee);
            this.epailyJaTorjuntaIkkuna.pack();
        } else {
            this.peliOhjaus.suoritaSiirto(pelaaja, vastustaja, siirtonumero);
            this.pelausIkkuna.setVisible(false);
        }
        this.pelausIkkuna.validate();
        this.paivitaPelinseuranta(this.pelinSeurantapaneeli);
    }

    private void lisaaSiirtonapit(String[][] siirtoVaihtoehdot, JPanel napit) {
        JPanel selitejaNappi = new JPanel(new GridLayout(6, 1));
        this.nappularyhma1 = new ButtonGroup();
        for (int i = 0; i < siirtoVaihtoehdot.length; i++) {
            JRadioButton nappi = new JRadioButton(siirtoVaihtoehdot[i][0]);
            nappi.setActionCommand(Integer.toString(i + 1));
            selitejaNappi.add(nappi);
            selitejaNappi.add(new JLabel(siirtoVaihtoehdot[i][1]));
            napit.add(selitejaNappi);
            nappularyhma1.add(nappi);
            if (i == 0) {
                nappi.setSelected(true);
            }
        }

    }

    private void lisaaKohdenapit(JPanel kohdenapit) {
        this.nappularyhma2 = new ButtonGroup();
        for (int i = 1; i < this.peliOhjaus.getPeli().getOsanottajajoukko().size(); i++) {
            JRadioButton nappi = new JRadioButton(Integer.toString(i));
            nappi.setActionCommand(Integer.toString(i));
            kohdenapit.add(nappi);
            nappularyhma2.add(nappi);
            if (i == 1) {
                nappi.setSelected(true);
            }
        }
    }

    private JPanel luoVastustajanvuoro() {
        JPanel paneeli = new JPanel(new GridLayout(3, 1));
        JLabel otsikko = new JLabel("Vastustajan vuoro.");
        paneeli.add(otsikko);
        vastustajanSiirto = new JButton("Anna vastustajan tehdä siirto!");
        vastustajanSiirto.addActionListener(this);
        paneeli.add(vastustajanSiirto);

        return paneeli;
    }

    private void vastustajanSiirto(Vastustaja vastustaja) {
        this.pelausIkkuna.setVisible(false);
        this.pelausIkkuna = new JFrame("Vastustajan siirto.");
        this.pelausIkkuna.setSize(400, 400);
        JPanel lisaykset = new JPanel(new GridLayout(4, 1));
        int siirto = vastustaja.valitseSiirto(r);
        int kohde = vastustaja.valitseKohde(r, this.peliOhjaus.getPeli().getOsanottajajoukko().size());
        JTextArea ilmoitus = new JTextArea("Vastutaja haluaa tehdä siirron " + this.peliOhjaus.getPeli().getSiirtoNimet().get(siirto) + " \nosanottajaa " + kohde + " vastaan.");
        JButton alaTeeMitaan = new JButton("Ok!");
        if (siirto == 4 || siirto == 5 || siirto == 6 && this.peliOhjaus.getPeli().getOsanottajajoukko().get(kohde) instanceof Pelaaja) {
            ilmoitus.setText("Vastustajan " + vastustaja.getNimi()
                    + " vuoro.\nVastustaja haluaa tehdä siirron " + this.peliOhjaus.getPeli().getSiirtoNimet().get(siirto)
                    + "\nosanottajaa " + kohde + " kohtaan."
                    + "\nHaluatko torjua siirron tai epäillä sitä?");
            JButton epaily = new JButton("Epäile!");
            JButton torju = new JButton("Torju!");
            alaTeeMitaan = new JButton("Älä tee mitään!");

            epaily.addActionListener(new EpailyActionListener(peliOhjaus, vastustaja, siirto, kohde,
                    this.pelausIkkuna, this.pelinSeurantapaneeli));
            lisaykset.add(epaily);
            torju.addActionListener(new TorjuActionListener(peliOhjaus, vastustaja, siirto, kohde,
                    this.pelausIkkuna, this.pelinSeurantapaneeli));
            lisaykset.add(torju);
        }

        alaTeeMitaan.addActionListener(new AlaTeeMitaanActionListener(peliOhjaus, vastustaja, siirto, kohde,
                this.pelausIkkuna, this.pelinSeurantapaneeli));
        lisaykset.add(alaTeeMitaan);
        lisaykset.add(ilmoitus);

        this.pelausIkkuna.add(lisaykset);

        this.pelausIkkuna.setVisible(true);

    }

    private class EpailyActionListener implements ActionListener {

        private PeliOhjaus peliOhjaus;
        private Vastustaja vastustaja;
        private int siirto;
        private int kohde;
        private Peli peli;
        private JFrame pelausikkuna;
        private PelinSeurantaPaneeli pelinseuranta;

        /**
         * Luokan olio toimii pelin aloitusvalikkona.
         */
        public EpailyActionListener(PeliOhjaus peliOhjaus, Vastustaja vastustaja,
                int siirto, int kohde, JFrame pelausikkuna, PelinSeurantaPaneeli pelinseuranta) {
            this.peliOhjaus = peliOhjaus;
            this.vastustaja = vastustaja;
            this.siirto = siirto;
            this.kohde = kohde;
            this.peli = peliOhjaus.getPeli();
            this.pelausikkuna = pelausikkuna;
            this.pelinseuranta = pelinseuranta;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Osanottaja o = (Osanottaja) this.vastustaja;
            Osanottaja kohdistuu = this.peli.getOsanottajajoukko().get(kohde);
            if (peli.getPelaaja().epaile(o, peli.getSiirtoNumerot().get(this.siirto))) {
                this.peli.setVuoronumero(this.peli.getVuoronumero() + 1);
                return;
            } else {
                this.peliOhjaus.suoritaSiirto(o, kohdistuu, siirto);
            }
            paivitaPelinseuranta(pelinseuranta);
            this.pelausikkuna.setVisible(false);
            SwingUtilities.updateComponentTreeUI(valiIkkuna);
        }
    }

    private class TorjuActionListener implements ActionListener {

        private PeliOhjaus peliOhjaus;
        private Vastustaja vastustaja;
        private int siirto;
        private int kohde;
        private Peli peli;
        private JFrame pelausikkuna;
        private PelinSeurantaPaneeli pelinseuranta;

        /**
         * Luokan konstruktori.
         */
        public TorjuActionListener(PeliOhjaus peliOhjaus, Osanottaja torjuja, int siirto, int kohde, JFrame pelausikkuna,
                PelinSeurantaPaneeli pelinseuranta) {
            this.peliOhjaus = peliOhjaus;
            this.vastustaja = vastustaja;
            this.siirto = siirto;
            this.kohde = kohde;
            this.peli = peliOhjaus.getPeli();
            this.pelausikkuna = pelausikkuna;
            this.pelinseuranta = pelinseuranta;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Osanottaja o = (Osanottaja) this.vastustaja;
            Osanottaja kohdistuu = (Osanottaja) this.peli.getOsanottajajoukko().get(kohde);
            if (peli.getPelaaja().torju(o, peli.getSiirtoNumerot().get(this.siirto))) {
                return;
            } else {
                this.peliOhjaus.suoritaSiirto(o, kohdistuu, siirto);
            }
            paivitaPelinseuranta(pelinseuranta);
            this.pelausikkuna.setVisible(false);
            SwingUtilities.updateComponentTreeUI(valiIkkuna);
        }
    }

    private class AlaTeeMitaanActionListener implements ActionListener {

        private PeliOhjaus peliOhjaus;
        private Vastustaja vastustaja;
        private int siirto;
        private int kohde;
        private Peli peli;
        private JFrame pelausikkuna;
        private PelinSeurantaPaneeli pelinseuranta;

        /**
         * Luokan konstruktori.
         */
        public AlaTeeMitaanActionListener(PeliOhjaus peliOhjaus, Vastustaja vastustaja,
                int siirto, int kohde, JFrame pelausikkuna, PelinSeurantaPaneeli pelinseuranta) {
            this.peliOhjaus = peliOhjaus;
            this.vastustaja = vastustaja;
            this.siirto = siirto;
            this.kohde = kohde;
            this.peli = peliOhjaus.getPeli();
            this.pelausikkuna = pelausikkuna;
            this.pelinseuranta = pelinseuranta;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Osanottaja o = (Osanottaja) this.vastustaja;
            Osanottaja kohdistuu = (Osanottaja) this.peli.getOsanottajajoukko().get(kohde);
            this.peliOhjaus.suoritaSiirto(o, kohdistuu, siirto);
            paivitaPelinseuranta(pelinseuranta);
            this.pelausikkuna.setVisible(false);
            SwingUtilities.updateComponentTreeUI(valiIkkuna);
        }
    }

    public void setHuomioTekstit(JTextArea huomioTekstit) {
        this.huomioTekstit = huomioTekstit;
    }

    public void setAloitaPeliNappi(JButton aloitaPeliNappi) {
        this.aloitaPeliNappi = aloitaPeliNappi;
    }

}
