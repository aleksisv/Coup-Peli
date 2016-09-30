package fi.aleksisv.logiikka;

import java.util.ArrayList;
import java.util.Random;

/**
 * Luokka kuvaa koko pelissä mukana olevien korttien joukkoa.
 */
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

    private void lisaaTietynTyyppisetKortit(ArrayList<Kortti> pakka, String tyyppi) {
        for (int i = 0; i < 3; i++) {
            Kortti kortti = new Kortti(tyyppi);
            pakka.add(kortti);
        }
    }

    public ArrayList<Kortti> getPakka() {
        return pakka;
    }

    /**
     * Kertoo, kuinka monta korttia korttipakassa on tällä hetkellä.
     * 
     * @return Korttipakan koko.
     */
    public int korttipakanKoko() {
        return this.pakka.size();
    }

    /**
     * Poistaa korttipakasta tietyssä kohdassa olevan kortin.
     * 
     * @param i Mistä kohtaa kortti poistetaan.
     */
    public void poistaPakastaTassaKohdassaOleva(int i) {
        this.pakka.remove(i);
    }

    /**
     * Metodi nostaa korttipakasta tietyssä kohdassa olevan kortin.
     *
     * @param kohta Kohta, josta käyttäjä haluaa nostaa kortin.
     *
     *
     * @return Pakka, josta on nostettu haluttu kortti.
     */
    public Kortti nostaPakastaTassaKohdassaOleva(int kohta) {
        Kortti apu = this.getPakka().get(kohta);
        this.getPakka().remove(kohta);
        return apu;
    }

    /**
     * Metodi nostaa korttipakasta satunnaisessa kohdassa olevan kortin.
     *
     *
     *
     * @return pakka, josta on poistettu haluttu kortti.
     */
    public Kortti nostaPakastaSatunnainenKortti() {
        Random r = new Random();
        int i = r.nextInt(this.korttipakanKoko());
        Kortti apukortti = this.nostaPakastaTassaKohdassaOleva(i);
        return apukortti;
    }

    /**
     * Metodi poistaa korttipakasta kortin, joka on haluttua tyyppiä, esim.
     * "Duke" tai "Contessa".
     *
     * @param tyyppi Minkä tyyppinen kortti halutaan nostaa.
     *
     *
     * @return Nostettu kortti.
     */
    public Kortti nostaPakastaTamanNiminenKortti(String tyyppi) {
        for (Kortti kortti : pakka) {
            if (kortti.getTyyppi().equals(tyyppi)) {
                pakka.remove(kortti);
                return kortti;
            }
        }
        return null;
    }

    /**
     * Metodi tarkistaa, sisältyykö tiettyä tyyppiä oleva kortti korttipakkaan.
     *
     * @param kortti Minkä kortin sisältymistä korttipakkaan halutaan tutkia.
     *
     *
     * @return True, jos kortti sisältyy. False, jos se ei sisälly.
     */
    public boolean sisaltyyko(Kortti kortti) {
        for (Kortti kor : pakka) {
            if (kortti.equals(kor)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodi tarkistaa, onko tietyn niminen kortti pakassa.
     *
     * @param kortinNimi Minkä nimise kortin sisältyminen halutaan tarkistaa.
     *
     *
     * @return Totuusarvo: sisältyykö vai eikö sisälly.
     */
    public boolean onkoTamanNiminenKorttiPakassa(String kortinNimi) {
        for (Kortti kor : pakka) {
            if (kor.getTyyppi().equals(kortinNimi)) {
                return true;
            }
        }
        return false;
    }

}
