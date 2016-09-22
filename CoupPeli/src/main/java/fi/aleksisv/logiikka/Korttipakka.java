package fi.aleksisv.logiikka;

import java.util.ArrayList;
import java.util.Random;

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
        }
    }

    public ArrayList<Kortti> getPakka() {
        return pakka;
    }

    public int korttipakanKoko() {
        return this.pakka.size();
    }

    public void poistaPakastaTassaKohdassaOleva(int i) {
        this.pakka.remove(i);
    }

    public Kortti nostaPakastaTassaKohdassaOleva(int i) {
        Kortti apu = this.getPakka().get(i);
        this.getPakka().remove(i);
        return apu;
    }

    public Kortti nostaPakastaSatunnainenKortti() {
        Random r = new Random();
        int i = r.nextInt(this.korttipakanKoko());
        Kortti apukortti = this.nostaPakastaTassaKohdassaOleva(i);
        return apukortti;
    }

    public Kortti nostaPakastaTamanNiminenKortti(String tyyppi) {
        for (Kortti kortti : pakka) {
            if (kortti.getTyyppi().equals(tyyppi)) {
                pakka.remove(kortti);
                return kortti;
            }
        }
        return null;
    }

    public boolean sisaltyyko(Kortti kortti) {
        for (Kortti kor : pakka) {
            if (kortti.equals(kor)) {
                return true;
            }
        }
        return false;
    }

    public boolean onkoTamanNiminenKorttiPakassa(String kortinNimi) {
        for (Kortti kor : pakka) {
            if (kor.getTyyppi().equals(kortinNimi)) {
                return true;
            }
        }
        return false;
    }

}
