package fi.aleksisv.logiikka;

import fi.aleksisv.kayttoliittyma.PeliOhjaus;
import java.util.ArrayList;

public class NewMain {

    public static void main(String[] args) {
        PeliOhjaus peliOhjaus = new PeliOhjaus();
        
        Peli peli = new Peli(4);
        peli.kaynnistaPeli();
    }

}