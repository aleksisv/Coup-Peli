package fi.aleksisv.logiikka;

import java.util.Random;

/**
 * Luokka kuvaa pelin osanottajaa.
 */
public class Osanottaja {

    private String nimi;
    private int raha;
    private Korttikasi korttikasi;
    
    /**
     * Luokan konstruktori.
     *
     * @param nimi Minkä niminen osanottaja luodaan.
     */
    public Osanottaja(String nimi) {
        this.nimi = nimi;
        this.raha = 2;
        this.korttikasi = new Korttikasi();
    }

    public Korttikasi getKorttikasi() {
        return this.korttikasi;
    }

    /**
     * Metodi tarkastaa, haluaako osanottaja epäillä sitä, että toisella
     * osanottajalla on jokin kortti kädessään.
     *
     * @param osanottaja Osanottaja, jolta epäillään puuttuvan jokin kortti.
     * @param mitaEiOle Minkä kortin epäillään puuttuvan osanottajan kädestä.
     *
     * @return Totuusarvo: haluaako tämä osanottaja epäillä toisen siirtoa vai
     * ei.
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
        System.out.println(mitaEiOle);
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
     * Metodi paljastaa osanottajan kädessä olevan kortin.
     *
     */
    public void paljastaKortti() {
        for (int i = 0; i < korttikasi.getKorttikasi().size(); i++) {
            if (this.korttikasi.getKorttikasi().get(i).onkoPaljastettu() == false) {
                korttikasi.paljastaPakastaTassaKohdassaOleva(i);
                break;
            }
        }
    }

    /**
     * Metodin avulla osanottajalle annetaan rahaa.
     *
     * @param maara Minkä tyyppinen kortti halutaan nostaa.
     */
    public void saaRahaa(int maara) {
        this.setRaha(this.getRaha() + maara);
    }

    /**
     * Metodin avulla osanottaja nostaa korttipakasta kortin.
     *
     * @param pakka Pakka, josta osanottaja kortin nostaa.
     */
    public void nostaSatunnainenKorttiPakasta(Korttipakka pakka) {
        Random r = new Random();
        int i = r.nextInt(pakka.korttipakanKoko());
        Kortti apu = pakka.nostaPakastaTassaKohdassaOleva(i);
        this.lisaaKorttiKorttipakkaan(apu);
    }

    /**
     * Metodin avulla osanottajan käteen lisätään kortti.
     *
     * @param kortti Kortti, joka on tarkoitus lisätä osanottajan käteen.
     */
    public void lisaaKorttiKorttipakkaan(Kortti kortti) {
        this.getKorttikasi().lisaaKorttikateen(kortti);
    }

    /**
     * Metodin avulla osanottajan käteen lisätään kortti.
     * @param osanottaja Osanottaja, jonka käteen kortti lisätään.
     * @param kortti Kortti, joka on tarkoitus lisätä osanottajan käteen.
     *
     * @return Totuusarvo: haluaako metodia kutsuva osanottaja torjua kortin vai
     * ei.
     */
    public boolean haluaaTorjua(Osanottaja osanottaja, Kortti kortti) {
        if (osanottaja.getKorttikasi().sisaltyykoKortti(kortti)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodin kuvaa tilannetta, jossa vastustaja yrittää torjua toisen
     * osanottajan siirron.
     * @param osanottaja Osanottaja, jonka siirto halutaan torjua.
     * @param kortti Kortti, jonka torjuja tarvitsee torjuakseen.
     *
     * @return Totuusarvo, joka kertoo onnistuiko torjuminen vai ei.
     */
    public boolean torju(Osanottaja osanottaja, Kortti kortti) {
        if (this.korttikasi.sisaltyykoKortti(kortti)) {
            return true;
        }
        return false;
    }

    /**
     * Metodin avulla osanottajalta otetaan pois rahaa tietty määrä.
     *
     * @param maara Kuinka paljon osanottajalta otetaan rahaa pois.
     */
    public void menetaRahaa(int maara) {
        if (this.getRaha() - maara >= 0) {
            this.setRaha(this.getRaha() - maara);
        } else {
            this.setRaha(0);
        }

    }

    /**
     * Metodin osanottajan varoja siirretään pankille.
     *
     * @param maara Kuinka paljon osanottajalta otetaan rahaa pois.
     * @param pankki Mille pankille rahat annetaan.
     */
    public void annaRahaaPankille(int maara, Pankki pankki) {
        if (this.getRaha() >= maara) {
            pankki.lisaaRahaa(maara);
            this.menetaRahaa(maara);
        } else {
            pankki.lisaaRahaa(this.getRaha());
            this.menetaRahaa(this.getRaha());
        }
    }

    /**
     * Metodin avulla osanottaja saa varoja pankilta.
     *
     * @param maara Kuinka paljon osanottajalle annetaan rahaa.
     * @param pankki Mistä pankista rahat otetaan.
     */
    public void otaRahaaPankilta(int maara, Pankki pankki) {
        this.setRaha(pankki.annaRahat(maara) + this.getRaha());
    }

    /**
     * Metodi kuvaa osanottajan Perustulo-siirtoa.
     *
     * @param pankki Antaa pelaajalle Perustulo-siirrosta saadut rahat.
     */
    public void kaytaPerustulo(Pankki pankki) {
        this.saaRahaa(pankki.annaRahat(1));
    }

    /**
     * Metodi kuvaa osanottajan Ulkomaanapu-siirtoa.
     *
     * @param pankki Antaa pelaajalle siirrosta saadut rahat.
     */
    public void kaytaUlkomaanapu(Pankki pankki) {
        this.saaRahaa(pankki.annaRahat(2));
    }

    /**
     * Metodi kuvaa osanottajan Assassinoi-siirtoa.
     *
     * @param pankki Ottaa pelaajalta siirtoon vaadittavat rahat.
     * @param osanottaja Kehen metodin kutsuja haluaa kohdistaa siirron.
     */
    public void kaytaAssassinoi(Pankki pankki, Osanottaja osanottaja) {
        this.menetaRahaa(3);
        pankki.lisaaRahaa(3);
        osanottaja.paljastaKortti();
    }

    /**
     * Metodi kuvaa osanottajan Vallankaappaus-siirtoa.
     *
     * @param pankki Ottaa pelaajalta siirtoon vaadittavat rahat.
     * @param osanottaja Kehen metodin kutsuja haluaa kohdistaa siirron.
     */
    public void kaytaVallankaappaus(Pankki pankki, Osanottaja osanottaja) {
        this.menetaRahaa(7);
        pankki.lisaaRahaa(7);
        osanottaja.paljastaKortti();
    }

    /**
     * Metodi kuvaa osanottajan Varasta-siirtoa.
     *
     * @param pankki --.
     * @param osanottaja Kehen metodin kutsuja haluaa kohdistaa siirron.
     */
    public void kaytaVarasta(Pankki pankki, Osanottaja osanottaja) {
        osanottaja.menetaRahaa(2);
        this.saaRahaa(2);
    }

    /**
     * Metodi kuvaa osanottajan Verotus-siirtoa.
     *
     * @param pankki Mistä pankista metodin kutsuja ottaa rahat.
     */
    public void kaytaVerotus(Pankki pankki) {
        this.saaRahaa(pankki.annaRahat(3));
    }

    /**
     * Metodi laskee, kuinka monta näkyvää korttia pelaajalla on kädessään.
     *
     * @return i Montako näkyvää korttia pelaajalla on kädessään.
     */
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
    
    /**
     * Metodi palauttaa merkkijonoesityksen osanottajan korttikädestä.
     * @return Korttikäden merkkijonoesitys.
     */
    public String naytaKorttikasi() {
        StringBuilder merkkijono = new StringBuilder();
        for (Kortti kortti : this.korttikasi.getKorttikasi()) {

            merkkijono.append(kortti.toString() + ", ");
        }
        String palautettava = merkkijono.toString();
        return palautettava.replaceAll(", $", "");
    }

}
