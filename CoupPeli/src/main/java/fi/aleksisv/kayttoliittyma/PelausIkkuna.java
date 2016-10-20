package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.Vastustaja;
import java.awt.*;
import java.util.Enumeration;
import javax.swing.*;

/**
 * Luokka kuvaa ikkunaa, jossa siirron tapahtuvat.
 */
public class PelausIkkuna extends JFrame {

    /**
     * Tarvittavat nappularyhmät.
     */
    ButtonGroup siirtonappularyhma;
    ButtonGroup kohdenappularyhma;
    /**
     * Peliä pyörittävä taho.
     */
    PeliOhjaus peliOhjaus;
    /**
     * Graafinen käyttöliittymä.
     */
    GraafinenKayttoliittyma gkl;
    /**
     * Erityishuomioista ilmoittava tekstialue..
     */
    JTextArea huomiotekstit;

    /**
     * Luokan konstruktori.
     *
     * @param peliOhjaus Peliä pyörittävä taho.
     * @param gkl Graafinen käyttöliittymä.
     *
     * @throws HeadlessException
     */
    public PelausIkkuna(PeliOhjaus peliOhjaus, GraafinenKayttoliittyma gkl) throws HeadlessException {
        super("Pelausikkuna");
        this.peliOhjaus = peliOhjaus;
        this.gkl = gkl;
        this.huomiotekstit = new JTextArea();

        this.setVisible(true);
        this.setSize(1000, 700);
    }

    /**
     * Metodi luo pelausvaihtoehdot ikkunaan.
     *
     * @param sailio Container-olio.
     *
     * @throws HeadlessException
     */
    public void luoPelausVaihtoehdot(Container sailio) {
        this.huomiotekstit.setText("Valitse siirto ja mahdollinen kohde.");
        JPanel kokonaisuus = new JPanel(new BorderLayout());
        this.huomiotekstit.setPreferredSize(new Dimension(1000, 100));
        kokonaisuus.add(this.huomiotekstit, BorderLayout.PAGE_START);
        JPanel napit = new JPanel(new GridLayout(2, 1));
        JPanel siirtonapit = new JPanel();
        JPanel kohdenapit = new JPanel();
        String[][] siirtoVaihtoehdot = {{"Perustulo:", "Ota 1 kolikko pankista."}, 
            {"Ulkomaanapu:", "Ota 2 kolikkoa pankista. Torjuu: Herttua"},
        {"Vallankumous:", "Maksa 7 kolikkoa ja hyökkää yhtä osanottajaa vastaan."},
        {"Verotus:", "Ota 3 kolikkoa pankista. Tarvitset: Herttua."},
        {"Assassinoi:", "Maksa 3 kolikkoa ja hyökkää yhtä osanottajaa kohtaan. Tarvitset: Salamurhaaja. Torjuu: Kreivitär."},
        {"Varasta:", "Ota vastustajalta 2 kolikkoa. Tarvitsee: Kapteeni. Torjuu: Kapteeni."}};

        napit.add(lisaaSiirtonapit(siirtoVaihtoehdot, siirtonapit));

        napit.add(lisaaKohdenapit(kohdenapit));
        
        kokonaisuus.add(napit, BorderLayout.CENTER);
        
        JButton teeSiirto = new JButton("Tee siirto!");
        teeSiirto.setPreferredSize(new Dimension(1000, 80));
        teeSiirto.addActionListener(new PeliSiirtoKuuntelija(peliOhjaus, this, gkl, this.siirtonappularyhma, this.kohdenappularyhma));
        kokonaisuus.add(teeSiirto, BorderLayout.PAGE_END);
        kokonaisuus.validate();

        sailio.add(kokonaisuus);
        this.pack();
    }

    private JPanel lisaaSiirtonapit(String[][] siirtoVaihtoehdot, JPanel siirtonapit) {
        JPanel seliteJaNappi = new JPanel(new GridLayout(6, 1));
        this.siirtonappularyhma = new ButtonGroup();
        for (int i = 0; i < siirtoVaihtoehdot.length; i++) {
            JRadioButton nappi = new JRadioButton(siirtoVaihtoehdot[i][0]);
            nappi.setActionCommand(Integer.toString(i + 1));
            seliteJaNappi.add(nappi, BorderLayout.PAGE_START);
            seliteJaNappi.add(new JLabel(siirtoVaihtoehdot[i][1]));
            siirtonappularyhma.add(nappi);
            if (i == 0) {
                nappi.setSelected(true);
            }
        }
        siirtonapit.add(seliteJaNappi);
        return siirtonapit;
    }

    private JPanel lisaaKohdenapit(JPanel kohdenapit) {
        int osanottajiaMukana = this.peliOhjaus.getPeli().getOsanottajajoukko().size();

        JPanel seliteJaNappi = new JPanel(new GridLayout(osanottajiaMukana, 1));

        this.kohdenappularyhma = new ButtonGroup();
        for (int i = 1; i < this.peliOhjaus.getPeli().getOsanottajajoukko().size(); i++) {
            JRadioButton nappi = new JRadioButton(Integer.toString(i));
            nappi.setActionCommand(Integer.toString(i));
            seliteJaNappi.add(nappi);
            seliteJaNappi.add(new JLabel("osanottaja"));
            kohdenappularyhma.add(nappi);
            if (i == 1) {
                nappi.setSelected(true);
            }
        }
        
        
        
        kohdenapit.add(seliteJaNappi);
        return kohdenapit;
    }

    /**
     * Metodi luo vastustajan vuoroon tarvittavat komponentit ja kuuntelijat.
     *
     * @param vastustaja Vastustaja, jonka vuoro luodaan.
     * @param sailio Container-olio.
     */
    public void luoVastustajanVuoro(Vastustaja vastustaja, Container sailio) {
        sailio.removeAll();

        JPanel paneeli = new JPanel(new GridLayout(2, 1));
        paneeli.setSize(200, 500);
        JLabel otsikko = new JLabel("Vastustajan vuoro.");
        paneeli.add(otsikko);

        JButton vastustajanSiirto = new JButton("Anna vastustajan tehdä siirto!");
        vastustajanSiirto.addActionListener(new VastustajanSiirtoKuuntelija(this.peliOhjaus, this.gkl, this, vastustaja));
        paneeli.add(vastustajanSiirto);

        sailio.add(paneeli);
        sailio.setPreferredSize(new Dimension(400, 200));
        
        
        setVisible(true);
        this.pack();
    }

    /**
     * Metodi palauttaa huomiotekstit-tekstialueen.
     *
     * @return Huomiotekstit.
     */
    public JTextArea getHuomiotekstit() {
        return huomiotekstit;
    }

}
