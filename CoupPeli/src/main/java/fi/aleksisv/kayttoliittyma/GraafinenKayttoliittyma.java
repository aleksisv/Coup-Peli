package fi.aleksisv.kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.PopupMenu;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Luokka vastaa ohjelman GUI-puolesta.
 */
public class GraafinenKayttoliittyma implements Runnable {

    private JFrame ikkuna;
    private PeliOhjaus peliOhjaus;

    /**
     * Luokan konstruktori.
     */
    public GraafinenKayttoliittyma() {

    }
    
    /**
     * Metodi pyörittää peliä.
     */
    public void run() {
        ikkuna = new JFrame("Coup-Peli");
        peliOhjaus = new PeliOhjaus();

        ikkuna.setPreferredSize(new Dimension(400, 400));
        ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        luoKomponentit(ikkuna.getContentPane());
        ikkuna.pack();
        ikkuna.setVisible(true);
    }

    private void luoKomponentit(Container sailio) {
        sailio.setLayout(new GridLayout(3, 3));

        sailio.add(luoPelinAvausvalikko());

        sailio.add(luoPelausValikko());

        sailio.add(luoTilanteenSeuraaja());
    }

    private JPanel luoPelausValikko() {
        JPanel paneeli = new JPanel(new GridLayout(2, 3));

        JButton pelaaVuoroNappi = new JButton("Pelaa vuoro!");
        JPanel siirtoPaneeli = new JPanel(new GridLayout(2, 2));
        JTextField siirtoIkkuna = new JTextField();
        JLabel siirtoOtsikko = new JLabel("Minkä siirron haluat tehdä?");
        JTextField kehenKohdistuu = new JTextField();
        JLabel keneenSiirtoKohdistuu = new JLabel("Kehen siirto kohdistuu?");

        pelaaVuoroNappi.addActionListener(new PelaaVuoroKuuntelija(this.peliOhjaus, siirtoIkkuna, kehenKohdistuu));

        siirtoPaneeli.add(siirtoOtsikko);
        siirtoPaneeli.add(siirtoIkkuna);
        siirtoPaneeli.add(keneenSiirtoKohdistuu);
        siirtoPaneeli.add(kehenKohdistuu);

        paneeli.add(pelaaVuoroNappi);
        paneeli.add(siirtoPaneeli);

        return paneeli;
    }

    private JPanel luoPelinAvausvalikko() {
        JPanel paneeli = new JPanel(new GridLayout(1, 3));

        JButton pelaaNappi = new JButton("Aloita peli!");
        JLabel pelaajaMaaraTeksti = new JLabel("Montako pelaajaa: ");
        JTextField pelaajaMaaraKentta = new JTextField();
        pelaaNappi.addActionListener(new PelinAloitusKuuntelija(this.peliOhjaus, pelaajaMaaraKentta));

        paneeli.add(pelaaNappi);
        paneeli.add(pelaajaMaaraTeksti);
        paneeli.add(pelaajaMaaraKentta);
        return paneeli;

    }

    public JFrame getIkkuna() {
        return this.ikkuna;
    }

    private JPanel luoTilanteenSeuraaja() {
        JPanel paneeli = new JPanel(new GridLayout(1, 2));
        JButton paivitaTilanneNappi = new JButton("Näytä tilanne: ");

        JTextArea tilanteenSeuraaja = new JTextArea();
        paivitaTilanneNappi.addActionListener(new TilanteenSeuraajaKuuntelija(this.peliOhjaus, tilanteenSeuraaja));

        paneeli.add(paivitaTilanneNappi);
        paneeli.add(tilanteenSeuraaja);

        return paneeli;
    }

}
