
package fi.aleksisv.kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Luokka kuvaa nappia, jonka avulla peli käynnistetään.
 */
public class PelausNappi extends JPanel implements ActionListener {
    JButton nappi;

    public PelausNappi() {
        super(new BorderLayout());
        nappi = new JButton("Pelaa");
        nappi.setPreferredSize(new Dimension(100, 100));
        add(nappi, BorderLayout.CENTER);
        nappi.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
