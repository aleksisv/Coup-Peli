package fi.aleksisv.logiikka;

import java.util.ArrayList;
import java.util.Random;

/**
 * Luokka kuvaa kokoelmaa kortteja sellaisena kuin ne ovat osanottajan kädessä.
 */
public class Korttikasi {
    
    /** Lista korteista.*/
    private ArrayList<Kortti> kortit;

    /**
     * Luokan konstruktori.
     *
     * @param kortit ArrayList, joka kuvaa korttikättä.
     */
    public Korttikasi(ArrayList<Kortti> kortit) {
        this.kortit = kortit;
    }

    /**
     * Luokan konstruktori.
     */
    public Korttikasi() {
        this.kortit = new ArrayList();
    }

    /**
     * Metodi palauttaa korttikäden koon.
     *
     * @return Korttikäden koko.
     */
    public int koko() {
        return kortit.size();
    }

    /**
     * Metodi tarkastaa, sisältyykö jokin kortti pelaajan piilossa olevien korttien
     * joukkoon.
     *
     * @param kortti Minkä kortin sisältyminen halutaan tarkastaa.
     *
     *
     * @return Totuusarvo.
     */
    public boolean sisaltyykoKortti(Kortti kortti) {
        for (Kortti kortit1 : kortit) {
            if (kortit1.equals(kortti) && !kortit1.onkoPaljastettu()) {
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
        int lisattavanSijainti = r.nextInt(pakka.korttipakanKoko());
        lisaaKorttikateen(pakka.nostaPakastaTassaKohdassaOleva(lisattavanSijainti));
        lisattavanSijainti = r.nextInt(pakka.korttipakanKoko());
        lisaaKorttikateen(pakka.nostaPakastaTassaKohdassaOleva(lisattavanSijainti));
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
