
package fi.aleksisv.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AvausValikko extends JPanel implements ActionListener {
    private GraafinenKayttoliittyma gkl;
    private JButton aloitaPeliNappi;
    private JTextField montakoPelaajaa;
    
    public AvausValikko(GraafinenKayttoliittyma gkl) {
        this.gkl = gkl;
        this.aloitaPeliNappi = new JButton("Aloita peli!");
        this.aloitaPeliNappi.addActionListener(this);
        this.montakoPelaajaa = new JTextField();

        this.add(aloitaPeliNappi);
        this.add(montakoPelaajaa);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
