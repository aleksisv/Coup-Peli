package fi.aleksisv.logiikka;

import java.util.ArrayList;
import java.util.Random;

public class Osanottaja {
    
    private String nimi;
    private int raha;
    private Korttikasi korttikasi;
    
    public Osanottaja(String nimi) {
        this.nimi = nimi;
        this.raha = 2;
        this.korttikasi = new Korttikasi();
    }
    
    public Korttikasi getKorttikasi() {
        return this.korttikasi;
    }
    
    public void epaile(Pelaaja pelaaja, Kortti mitaEiOle) {
        if (!pelaaja.getKorttikasi().sisaltyykoKortti(mitaEiOle)) {
            pelaaja.tapaKortti();
        } else {
            this.tapaKortti();
        }
    }
    
    public String getNimi() {
        return nimi;
    }
    
    public void setRaha(int raha) {
        this.raha = raha;
    }
    
    public void setKasi(Korttikasi kasi) {
        this.korttikasi = kasi;
    }
    
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
    public int getRaha() {
        return raha;
    }
    
    public void tapaKortti() {
        Korttikasi korttikasi = this.getKorttikasi();
        Random r = new Random();

        int tapettavanKohta = r.nextInt(korttikasi.koko());

        korttikasi.poistaPakastaTassaKohdassaOleva(tapettavanKohta);
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
        this.getKorttikasi().lisaaKorttikateen(kortti);
    }
    
    @Override
    public String toString() {
        return this.nimi;
    }
    
}
