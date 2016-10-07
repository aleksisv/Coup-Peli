package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.Peli;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Luokka seuraa, haluaako käyttäjä esityksen pelin tämänhetkisestä tilanteesta.
 *
 */
public class TilanteenSeuraajaKuuntelija implements ActionListener {

    private JTextArea tekstikentta;
    private PeliOhjaus peliOhjaus;
    
    /**
     * Luokan konstruktori.
     * 
     * @param peliOhjaus Mihin peliOhjaus-olioon kuuntelija liittyy.
     * @param tekstikentta Mihin tilanteen kuvaus tulostetaan.
     */
    public TilanteenSeuraajaKuuntelija(PeliOhjaus peliOhjaus, JTextArea tekstikentta) {
        this.tekstikentta = tekstikentta;
        this.peliOhjaus = peliOhjaus;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Peli peli = this.peliOhjaus.getPeli();
        this.tekstikentta.setText(peli.kerroTilanneTekstina());
    }

}
