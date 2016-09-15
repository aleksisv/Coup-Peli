package fi.aleksisv.logiikka;

import java.util.ArrayList;

public class Korttipakka {
    private ArrayList<Kortti> pakka;

    public Korttipakka() {
        this.pakka = new ArrayList();
        this.lisaaKortit(this.pakka);
    }
    
    private void lisaaKortit(ArrayList<Kortti> pakka) {
        this.lisaaTietynTyyppisetKortit(pakka, "Contessa");
        this.lisaaTietynTyyppisetKortit(pakka, "Assassin");
        this.lisaaTietynTyyppisetKortit(pakka, "Captain");
        this.lisaaTietynTyyppisetKortit(pakka, "Duke");
        this.lisaaTietynTyyppisetKortit(pakka, "Ambassador");
    }
    
    public void lisaaTietynTyyppisetKortit(ArrayList<Kortti> pakka, String tyyppi) {
        for (int i = 0; i < 3; i++) {
            Kortti kortti = new Kortti(tyyppi);
            pakka.add(kortti);
            System.out.println(kortti);
        }
    }

    public ArrayList<Kortti> getPakka() {
        return pakka;
    }
    
    public int korttipakanKoko() {
        return this.pakka.size();
    }
    
    public void poistaPakastaTassaKohdassaOleva(int i){
        this.getPakka().remove(i);
    }
    
    public boolean sisaltyyko(Kortti kortti) {
        for (Kortti kor : pakka) {
            if (kortti.equals(kor)) {
                return true;
            }
        }
        return false;
    }

    public void setPakka(ArrayList<Kortti> pakka) {
        this.pakka = pakka;
    }
    
    

    @Override
    public String toString() {
        return this.pakka.toString();
    }

    
    
    
    
}
