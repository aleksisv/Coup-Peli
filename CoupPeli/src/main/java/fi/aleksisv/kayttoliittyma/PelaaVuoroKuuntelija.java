package fi.aleksisv.kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

/**
 * Luokka vastaa "Pelaa vuoro"-napin kuuntelemisesta ja kutsuu peliä pelaamaan
 * vuoron, kun käyttäjä painaa sitä.
 */
public class PelaaVuoroKuuntelija implements ActionListener {

    private PeliOhjaus peliOhjaus;
    private JTextField siirtoIkkuna;
    private JTextField kohdeIkkuna;
    
    /**
     * Luokan konstruktori.
     * 
     * @param peliOhjaus Mihin peliOhjaus-olioon kuuntelija liittyy.
     * @param siirtoIkkuna Ikkuna, johon siirron numero kirjoitetaan.
     * @param keneenSiirtoKohdistuu Ikkuna, johon siirron kohde kirjoitetaan.
     */
    public PelaaVuoroKuuntelija(PeliOhjaus peliOhjaus, JTextField siirtoIkkuna, JTextField keneenSiirtoKohdistuu) {
        this.peliOhjaus = peliOhjaus;
        this.siirtoIkkuna = siirtoIkkuna;
        this.kohdeIkkuna = keneenSiirtoKohdistuu;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int siirto = Integer.parseInt(siirtoIkkuna.getText());
        int kohde = Integer.parseInt(kohdeIkkuna.getText());
        this.peliOhjaus.pelaaSiirto(siirto, kohde);
    }

}
