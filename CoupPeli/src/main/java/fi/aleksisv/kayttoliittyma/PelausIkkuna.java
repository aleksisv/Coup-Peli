package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.Vastustaja;
import java.awt.*;
import javax.swing.*;


public class PelausIkkuna extends JFrame {
    ButtonGroup nappularyhma1;
    ButtonGroup nappularyhma2;
    PeliOhjaus peliOhjaus;
    GraafinenKayttoliittyma gkl;
    JTextArea huomiotekstit;

    public PelausIkkuna(PeliOhjaus peliOhjaus, GraafinenKayttoliittyma gkl) throws HeadlessException {
        super("Pelausikkuna");
        this.peliOhjaus = peliOhjaus;
        this.gkl = gkl;
        this.huomiotekstit = new JTextArea();
        
        this.setVisible(true);
        this.setSize(1000, 700);
    }

    public void luoPelausVaihtoehdot(Container sailio) {
        this.huomiotekstit.setText("Valitse siirto ja mahdollinen kohde.");
        JPanel napit = new JPanel(new GridLayout(4, 1));
        napit.add(this.huomiotekstit);
        JPanel siirtonapit = new JPanel();
        JPanel kohdenapit = new JPanel();
        String[][] siirtoVaihtoehdot = {{"Perustulo:", "Ota 1 kolikko pankista."}, {"Ulkomaanapu:", "Ota 2 kolikkoa pankista."},
        {"Vallankumous:", "Maksa 7 kolikkoa ja hyökkää yhtä osanottajaa vastaan."},
        {"Verotus:", "Ota 3 kolikkoa pankista."}, {"Assassinoi:", "Maksa 3 kolikkoa ja hyökkää yhtä osanottajaa kohtaan."}, {"Varasta:", "Ota vastustajalta kaksi kolikkoa."}};

        napit.add(lisaaSiirtonapit(siirtoVaihtoehdot, siirtonapit));
        napit.add(lisaaKohdenapit(kohdenapit));

        JButton teeSiirto = new JButton("Tee siirto!");
        teeSiirto.addActionListener(new PeliSiirtoKuuntelija(peliOhjaus, this, gkl, this.nappularyhma1, this.nappularyhma2));
        napit.add(teeSiirto);
        
        sailio.add(napit);
    }

    private JPanel lisaaSiirtonapit(String[][] siirtoVaihtoehdot, JPanel siirtonapit) {
        JPanel seliteJaNappi = new JPanel(new GridLayout(6, 1));
        this.nappularyhma1 = new ButtonGroup();
        for (int i = 0; i < siirtoVaihtoehdot.length; i++) {
            JRadioButton nappi = new JRadioButton(siirtoVaihtoehdot[i][0]);
            nappi.setActionCommand(Integer.toString(i + 1));
            seliteJaNappi.add(nappi);
            seliteJaNappi.add(new JLabel(siirtoVaihtoehdot[i][1]));
            nappularyhma1.add(nappi);
            if (i == 0) {
                nappi.setSelected(true);
            }
        }
        siirtonapit.add(seliteJaNappi);
        return siirtonapit;
    }

    private JPanel lisaaKohdenapit(JPanel kohdenapit) {
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
        return kohdenapit;
    }

    public void luoVastustajanVuoro(Vastustaja vastustaja, Container sailio) {
        sailio.removeAll();
        JPanel paneeli = new JPanel(new GridLayout(2, 1));
        JLabel otsikko = new JLabel("Vastustajan vuoro.");
        paneeli.add(otsikko);
        JButton vastustajanSiirto = new JButton("Anna vastustajan tehdä siirto!");
        vastustajanSiirto.addActionListener(new VastustajanSiirtoKuuntelija(this.peliOhjaus, this.gkl, this, vastustaja));
        paneeli.add(vastustajanSiirto);
        sailio.add(paneeli);
        validate();
        setVisible(true);
    }

    public JTextArea getHuomiotekstit() {
        return huomiotekstit;
    }
    
    

}
