package fi.aleksisv.logiikka;

import java.util.ArrayList;
import java.util.Random;

public class Osanottaja {

    private String nimi;
    private int raha;
    private Korttikasi kasi;
    
    public Osanottaja(String nimi) {
        this.nimi = nimi;
        this.raha = 2;
        this.kasi = new Korttikasi();
    }
    
    public Korttikasi getKasi() {
        return kasi;
    }
    
    public void epaile(Pelaaja pelaaja, Kortti mitaEiOle) {
        if (!pelaaja.getKasi().sisaltyykoKortti(mitaEiOle)) {
            pelaaja.setRaha(pelaaja.getRaha() - 2);
        }
    }
    
    public String getNimi() {
        return nimi;
    }
    
    public void setRaha(int raha) {
        this.raha = raha;
    }
    
    public void setKasi(Korttikasi kasi) {
        this.kasi = kasi;
    }
    
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
    public int getRaha() {
        return raha;
    }
    
    public void saaRahaa(int maara) {
        this.setRaha(this.getRaha() + maara);
    }
    
    public void nostaSatunnainenKorttiPakasta(Korttipakka pakka) {
        Random r = new Random();
        int i = r.nextInt(pakka.korttipakanKoko());
        Kortti apu = pakka.nostaPakastaTassaKohdassaOleva(i);
        this.lisaaKorttiKorttipakkaan(apu);
    }
    
    public void lisaaKorttiKorttipakkaan(Kortti kortti) {
        this.getKasi().lisaaKorttikateen(kortti);
    }
    
    @Override
    public String toString() {
        return this.nimi;
    }
    
}
