
package main;

import java.util.ArrayList;

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
        if(!pelaaja.getKasi().sisaltyykoKortti(mitaEiOle)) {
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
    
    

    @Override
    public String toString() {
        return this.nimi;
    }
    
    
    
    
    
    
}
