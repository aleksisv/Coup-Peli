package fi.aleksisv.logiikka;

import java.util.ArrayList;
import java.util.Random;

/**
 * Luokka vangitsee pelin Osanottajan.
 */
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

    /**
     * Luokka kuvaa pelin Osanottajaa.
     */
    public boolean haluaaEpailla(Osanottaja osanottaja, Kortti mitaEiOle) {
        if (this.korttikasi.sisaltyykoKortti(mitaEiOle)) {
            return true;
        }
        return false;
    }
    
    /**
     * Metodi kuvaa epäilytapahtumaa, jossa metodin kutsuja haluaa väittää, ett
     * jollakin osanottajalla ei ole tietynlaista korttia kädessään.
     *
     * @param osanottaja Osanottaja, jolta epäillään puuttuvan jokin kortti.
     * @param mitaEiOle Minkä kortin epäillään puuttuvan osanottajan kädestä.
     *
     * @return Totuusarvo: sisältyykö kortti pelaajan käteen vai ei.
     */
    public boolean epaile(Osanottaja osanottaja, Kortti mitaEiOle) {
        if (!osanottaja.getKorttikasi().sisaltyykoKortti(mitaEiOle)) {
            osanottaja.paljastaKortti();
            return true;
        } else {
            this.paljastaKortti();
            return false;
        }
    }
    
    /**
     * Metodi antaa merkkijonon, joka on esitys siitä, mitkä kaikki pelaajan
     * kortit näkyvät muille pelaajile.
     *
     *
     *
     *
     * @return Merkkijonoesitys näkyvistä korteista.
     */
    public String naytaNakyvatKortit() {
        StringBuilder merkkijono = new StringBuilder();
        for (Kortti kortti : this.korttikasi.getKorttikasi()) {
            if (kortti.onkoPaljastettu()) {
                merkkijono.append(kortti.toString() + ", ");
            }
        }
        String palautettava = merkkijono.toString();
        return palautettava.replaceAll(", $", "");
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
    
    /**
     * Metodi ta
     *
     * @param tyyppi Minkä tyyppinen kortti halutaan nostaa.
     *
     *
     * @return Nostettu kortti.
     */
    public void paljastaKortti() {
        Korttikasi korttikasi = this.getKorttikasi();
        for (int i = 0; i < this.korttikasi.getKorttikasi().size(); i++) {
            if (this.korttikasi.getKorttikasi().get(i).onkoPaljastettu() == false) {
                korttikasi.paljastaPakastaTassaKohdassaOleva(i);
                break;
            }
        }
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

    public boolean haluaaBlokata(Osanottaja osanottaja, Kortti kortti) {
        if (osanottaja.getKorttikasi().sisaltyykoKortti(kortti)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean blokkaa(Osanottaja osanottaja, Kortti kortti) {
        if (osanottaja.korttikasi.sisaltyykoKortti(kortti)) {
            return false;
        }
        return true;
    }

    public void menetaRahaa(int maara) {
        if (this.getRaha() - maara >= 0) {
            this.setRaha(this.getRaha() - maara);
        } else {
            this.setRaha(0);
        }

    }

    public void annaRahaaPankille(int maara, Pankki pankki) {
        if (this.getRaha() >= maara) {
            pankki.lisaaRahaa(maara);
            this.menetaRahaa(maara);
        } else {
            pankki.lisaaRahaa(this.getRaha());
            this.menetaRahaa(this.getRaha());
        }
    }

    public void otaRahaaPankilta(int maara, Pankki pankki) {
        this.setRaha(pankki.annaRahat(maara) + this.getRaha());
    }

    public void kaytaBasicIncome(Pankki pankki) {
        this.saaRahaa(pankki.annaRahat(1));
    }

    public void kaytaForeignAid(Pankki pankki) {
        this.saaRahaa(pankki.annaRahat(2));
    }

    public void kaytaAssassinate(Pankki pankki, Osanottaja osanottaja) {
        this.menetaRahaa(3);
        pankki.lisaaRahaa(3);
        osanottaja.paljastaKortti();
    }

    public void kaytaCoup(Pankki pankki, Osanottaja osanottaja) {
        this.menetaRahaa(7);
        pankki.lisaaRahaa(7);
        osanottaja.paljastaKortti();
    }

    public void kaytaSteal(Pankki pankki, Osanottaja osanottaja) {
        osanottaja.menetaRahaa(2);
        this.saaRahaa(2);
    }

    public void kaytaTaxes(Pankki pankki) {
        this.saaRahaa(pankki.annaRahat(3));
    }

    public int montakoNakyvaaKorttia() {
        int i = 0;
        for (Kortti kortti : this.getKorttikasi().getKorttikasi()) {
            if (kortti.onkoPaljastettu()) {
                i++;
            }
        }
        return i;
    }

    @Override
    public String toString() {
        return this.nimi;
    }

}
