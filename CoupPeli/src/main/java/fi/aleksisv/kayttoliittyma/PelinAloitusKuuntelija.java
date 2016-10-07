package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.Peli;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JTextField;

/**
 * Luokka vastaa pelin aloittamiseen tarvittavien graafisten komponenttien
 * tarkkailemisesta.
 */
public class PelinAloitusKuuntelija implements ActionListener {

    private PeliOhjaus peliOhjaus;
    private JTextField tekstikentta;
    
    /**
     * Luokan konstruktori.
     * 
     * @param peliOhjaus Mihin peliOhjaus-olioon kuuntelija liittyy.
     * @param tekstikentta Mihin tilanteen kuvaus tulostetaan.
     */
    public PelinAloitusKuuntelija(PeliOhjaus peliOhjaus, JTextField tekstikentta) {
        this.peliOhjaus = peliOhjaus;
        this.tekstikentta = tekstikentta;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.peliOhjaus.luoPeli(Integer.parseInt(tekstikentta.getText()));
    }

}
