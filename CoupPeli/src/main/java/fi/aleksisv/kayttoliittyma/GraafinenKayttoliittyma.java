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

    private JFrame ikkuna, pelausIkkuna;
    private PeliOhjaus peliOhjaus;
    private JButton aloitaPeliNappi, pelaaVuoroNappi, teeSiirto, vastustajanSiirto;
    private JTextField montakoPelaajaa;
    private JTextArea huomioTekstit;
    private PelinSeurantaPaneeli pelinSeurantapaneeli;
    private ButtonGroup nappularyhma1, nappularyhma2;
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
        ikkuna = new JFrame("Coup-Peli");
        peliOhjaus = new PeliOhjaus();

        ikkuna.setPreferredSize(new Dimension(1000, 6000));
        ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        luoKomponentit(ikkuna.getContentPane());

        ikkuna.pack();
        ikkuna.setVisible(true);

    }

    private void luoKomponentit(Container sailio) {
        sailio.setLayout(new GridLayout(4, 2));

        sailio.add(luoPelinAvausvalikko(), 0, 0);
        this.huomioTekstit = new JTextArea("Luo peli haluamallasi määrällä pelaajia!");
        sailio.add(huomioTekstit);

    }

    private JPanel luoPelinAvausvalikko() {
        JPanel paneeli = new JPanel(new GridLayout(1, 2));
        this.aloitaPeliNappi = new JButton("Aloita peli!");
        this.aloitaPeliNappi.addActionListener(this);
        this.montakoPelaajaa = new JTextField();

        paneeli.add(aloitaPeliNappi);
        paneeli.add(montakoPelaajaa);
        return paneeli;

    }

    private void aloitaPeli() {
        int pelaajaMaara = Integer.parseInt(this.montakoPelaajaa.getText());
        if (!(2 <= pelaajaMaara && pelaajaMaara <= 5)) {
            this.huomioTekstit.setText("Anna validi määrä (2-5) pelaajia.");
        } else {
            pelaajaMaara = (int) pelaajaMaara;
            this.peliOhjaus.luoPeli(pelaajaMaara);
            this.huomioTekstit.setText("Aloitit pelin " + pelaajaMaara + " pelaajalla. Tee siirto.");
        }
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
            yritaSiirtoa(Integer.parseInt(nappularyhma1.getSelection().getActionCommand()),
                    Integer.parseInt(nappularyhma2.getSelection().getActionCommand()));
            SwingUtilities.updateComponentTreeUI(pelausIkkuna);
        } else if (tapahtuma.getSource() == vastustajanSiirto) {
            int pelivuorossa = this.peliOhjaus.getPeli().getVuoronumero() % this.peliOhjaus.getPeli().getOsanottajajoukko().size();
            Vastustaja vastustaja = (Vastustaja) this.peliOhjaus.getPeli().getOsanottajajoukko().get(pelivuorossa);
            vastustajanSiirto(vastustaja);
            SwingUtilities.updateComponentTreeUI(pelausIkkuna);
        }
        if (this.peliOhjaus.getPeli().getOsanottajajoukko().size() == 1) {
            System.out.println("Peli on ohi! Osanottaja " + this.peliOhjaus.getPeli().getOsanottajajoukko().get(0).getNimi() + " voitti.");
            ikkuna.dispose();
            this.ikkuna = new JFrame("Peli on ohi!");
            this.ikkuna.setSize(200, 200);
            this.ikkuna.setVisible(true);
        }
        SwingUtilities.updateComponentTreeUI(ikkuna);
    }

    private void luoPelausYmparisto() {
        luoPelinseuranta();
        luoPelausNappi();
        aloitaPeliNappi.setVisible(false);
        montakoPelaajaa.setVisible(false);
    }

    private void luoPelinseuranta() {
        this.pelinSeurantapaneeli = new PelinSeurantaPaneeli(new GridLayout(1, this.peliOhjaus.getPeli().getOsanottajajoukko().size()));
        this.pelinSeurantapaneeli.asetaAlkutila(this.peliOhjaus.getPeli());
        this.ikkuna.add(pelinSeurantapaneeli);
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

    private void luoPelausNappi() {
        this.pelaaVuoroNappi = new JButton("Pelaa vuoro!");
        pelaaVuoroNappi.addActionListener(this);
        this.ikkuna.add(pelaaVuoroNappi);
    }

    private JPanel luoPelausVaihtoehdot() {
        JPanel napit = new JPanel(new GridLayout(3, 1));
        JPanel siirtonapit = new JPanel();
        JPanel kohdenapit = new JPanel();
        String[][] siirtoVaihtoehdot = {{"Perustulo:", "Ota 1 kolikko pankista."}, {"Ulkomaanapu:", "Ota 2 kolikkoa pankista."},
            {"Vallankumous:", "Maksa 7 kolikkoa ja hyökkää yhtä osanottajaa vastaan."},
            {"Verotus:", "Ota 3 kolikkoa pankista."}, {"Assassinoi:","Maksa 3 kolikkoa ja hyökkää yhtä osanottajaa kohtaan."}, {"Varasta:", "Ota vastustajalta kaksi kolikkoa."}};

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

        if (vastustaja.haluaaEpailla(pelaaja, siirto)) {
            JButton tee = new JButton("Tee siirto.");
            JButton alaTee = new JButton("Älä tee siirtoa.");
            this.pelausIkkuna.add(new JTextArea("Vastustaja haluaa epäillä siirtoasi. Haluatko perua siirron?"));
            this.pelausIkkuna.add(tee);
            this.pelausIkkuna.add(alaTee);
        } else if (vastustaja.haluaaTorjua(pelaaja, siirto)) {
            JButton tee = new JButton("Tee siirto.");
            JButton alaTee = new JButton("Älä tee siirtoa");
            this.pelausIkkuna.add(new JTextArea("Vastustaja haluaa torjua siirtosi. Haluatko epäillä vastustajan torjumista?"));
            this.pelausIkkuna.add(tee);
            this.pelausIkkuna.add(alaTee);
        } else {
            this.peliOhjaus.suoritaSiirto(pelaaja, vastustaja, siirtonumero);
        }
        this.paivitaPelinseuranta(pelinSeurantapaneeli);
        this.pelausIkkuna.setVisible(false);
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
        this.pelausIkkuna.setSize(200, 200);
        JPanel lisaykset = new JPanel(new FlowLayout());
        int siirto = r.nextInt(6) + 1;
        int kohde = r.nextInt(this.peliOhjaus.getPeli().getOsanottajajoukko().size() - 1) + 1;
        JButton alaTeeMitaan = new JButton("Ok!");
        if (siirto == 4 || siirto == 5 || siirto == 6 && this.peliOhjaus.getPeli().getOsanottajajoukko().get(kohde) instanceof Pelaaja) {
            lisaykset.add(new JTextArea("Vastustajan " + vastustaja.getNimi()
                    + " vuoro.\nVastustaja haluaa tehdä siirron " + siirto
                    + "\nosanottajaa " + kohde + " kohtaan."
                    + "\nHaluatko torjua siirron tai epäillä sitä?"));

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
            SwingUtilities.updateComponentTreeUI(ikkuna);
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

        public TorjuActionListener(PeliOhjaus peliOhjaus, Vastustaja vastustaja, int siirto, int kohde, JFrame pelausikkuna,
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
            SwingUtilities.updateComponentTreeUI(ikkuna);
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
            SwingUtilities.updateComponentTreeUI(ikkuna);
        }
    }

}
