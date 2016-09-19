package fi.aleksisv.logiikka;

import java.util.ArrayList;

public class NewMain {

    public static void main(String[] args) {
        Peli peli = new Peli(3);
        peli.kaynnistaPeli();
        
        Osanottaja osanottaja1 = new Osanottaja("Jaa");
        osanottaja1.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        Osanottaja osanottaja2 = new Osanottaja("Ei");
        osanottaja2.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        
        osanottaja1.epaile(new Kortti("Contessa"),osanottaja2);
        
        System.out.println(osanottaja1.getKorttikasi().koko());
        System.out.println(osanottaja2.getKorttikasi().koko());
        
        
    }

}
