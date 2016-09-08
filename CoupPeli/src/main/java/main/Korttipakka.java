package main;

import java.util.ArrayList;

public class Korttipakka {
    private ArrayList<Kortti> pakka;

    public Korttipakka() {
        this.pakka = new ArrayList<Kortti>();
        this.lisaaKortit(this.pakka);
    }
    
    public void lisaaKortit(ArrayList<Kortti> pakka) {
        this.lisaaTietynTyyppisetKortit(pakka, "Contessa");
        this.lisaaTietynTyyppisetKortit(pakka, "Assassin");
        this.lisaaTietynTyyppisetKortit(pakka, "Captain");
        this.lisaaTietynTyyppisetKortit(pakka, "Duke");
        this.lisaaTietynTyyppisetKortit(pakka, "Ambassador");
    }
    
    public void lisaaTietynTyyppisetKortit(ArrayList<Kortti> pakka, String tyyppi){
        for (int i = 0; i < 3; i++) {
            Kortti kortti = new Kortti(i, tyyppi);
        }
    } 
    
}
