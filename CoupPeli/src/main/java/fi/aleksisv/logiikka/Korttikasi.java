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

    /**
     * Metodi tarkastaa, sisältyykö jokin kortti pelaajan käteen.
     *
     * @param kortti Minkä kortin sisältyminen halutaan tarkastaa.
     *
     *
     * @return Totuusarvo.
     */
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

    /**
     * Metodi muuttaa kädessä olevan kortin muille pelaajille näkyväksi, eli
     * paljastaa sen.
     *
     * @param paljastettavanKohta Missä kohtaa oleva kortti halutaan paljastaa.
     *
     *
     *
     */
    public void paljastaPakastaTassaKohdassaOleva(int paljastettavanKohta) {
        this.kortit.get(paljastettavanKohta).setOnkoPaljastettu(true);
    }

    /**
     * Metodi lisää korttikäteen kaksi satunnaista korttia annetusta
     * korttipakasta.
     *
     * @param pakka Korttipakka, josta kortit käteen lisätään.
     *
     *
     *
     */
    public void lisaaKaksiSatunnaistaKorttia(Korttipakka pakka) {
        Random r = new Random();
        for (int i = 0; i < 2; i++) {
            int lisattavanSijainti = r.nextInt(pakka.korttipakanKoko());
            lisaaKorttikateen(pakka.nostaPakastaTassaKohdassaOleva(i));
        }
    }

    /**
     * Metodi laskee, kuinka monta paljastettua korttia pelaajalla on kädessään.
     *
     *
     *
     *
     * @return lukumaara Paljastettujen korttien lukumäärä.
     */
    public int paljastettujenKorttienLukumaara() {
        int lukumaara = 0;
        for (Kortti kortti : kortit) {
            if (kortti.onkoPaljastettu() == true) {
                lukumaara++;
            }
        }
        return lukumaara;
    }

    /**
     * Metodi lisää halutun tyyppisen kortin korttikäteen.
     *
     * @param kortti Mikä kortti käteen halutaan lisätä.
     *
     *
     *
     */
    public void lisaaKorttikateen(Kortti kortti) {
        if (this.kortit.size() < 2) {
            this.kortit.add(kortti);
        }
    }

}
