package fi.aleksisv.kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 * Luokka vastaa ohjelman GUI-puolesta.
 */
public class GraafinenKayttoliittyma implements Runnable {

    private JFrame ikkuna;
    private Object WindowConstant;

    public GraafinenKayttoliittyma() {
//        this.ikkuna = new PelausIkkuna();
//        ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        JComponent pelausNappi = new PelausNappi();
//        pelausNappi.setOpaque(true);
//
//        ikkuna.setContentPane(pelausNappi);
//        ikkuna.setVisible(true);
    }

    public void run() {
        ikkuna = new JFrame("Coup-Peli");

        ikkuna.setPreferredSize(new Dimension(200, 100));
        ikkuna.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(ikkuna.getContentPane());

        ikkuna.pack();
        ikkuna.setVisible(true);
    }

    private void luoKomponentit(Container sailio) {
        JButton nappi = new JButton("Aloita peli!");
        sailio.setLayout(new BorderLayout());
        nappi.addActionListener(new Seuraaja());
        

        sailio.add(nappi, BorderLayout.NORTH);

        sailio.add(new JButton("Oletuspaikka (Center)"));

    }

    public JFrame getIkkuna() {
        return this.ikkuna;
    }

}
