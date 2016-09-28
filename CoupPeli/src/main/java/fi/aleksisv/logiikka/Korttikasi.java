package fi.aleksisv.logiikka;

import java.util.ArrayList;
import java.util.Random;

/**
 * Luokka kuvaa kokoelmaa kortteja sellaisena kuin ne ovat osanottajan kädessä.
 */
public class Korttikasi {

    private ArrayList<Kortti> kortit;

    public Korttikasi(ArrayList<Kortti> kortit) {
        this.kortit = kortit;
    }

    public Korttikasi() {
        this.kortit = new ArrayList();
    }

    public int koko() {
        return kortit.size();
    }

    public boolean sisaltyykoKortti(Kortti kortti) {
        for (Kortti kortit1 : kortit) {
            if (kortit1.equals(kortti)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Kortti> getKorttikasi() {
        return this.kortit;
    }

    public void setKortit(ArrayList<Kortti> kortit) {
        this.kortit = kortit;
    }

    public void paljastaPakastaTassaKohdassaOleva(int tapettavanKohta) {
        this.kortit.get(tapettavanKohta).setOnkoPaljastettu(true);
    }

    public void lisaaKaksiSatunnaistaKorttia(Korttipakka pakka) {
        Random r = new Random();
        for (int i = 0; i < 2; i++) {
            int lisattavanSijainti = r.nextInt(pakka.korttipakanKoko());
            lisaaKorttikateen(pakka.nostaPakastaTassaKohdassaOleva(i));
        }
    }

    public int paljastettujenKorttienLukumaara() {
        int i = 0;
        for (Kortti kortti : kortit) {
            if (kortti.onkoPaljastettu() == true) {
                i++;
            }
        }
        return i;
    }

    public void lisaaKorttikateen(Kortti kortti) {
        if (this.kortit.size() < 2) {
            this.kortit.add(kortti);
        }
    }

}
