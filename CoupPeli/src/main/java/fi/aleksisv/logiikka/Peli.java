package fi.aleksisv.logiikka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Luokka kuvaa Coup-Peliä. Peli-luokan olio pitää sisältää pelaajan, muita
 * osanottajia (vastustajat), Pankin, Korttipakan, sekä muita tarpeellisia
 * olioita.
 */
public class Peli {

    private int osanottajamaara;
    private ArrayList<Osanottaja> osanottajajoukko;
    private boolean pysayta;
    private int vuoronumero;
    private Pelaaja pelinPelaaja;
    private Pankki pankki;
    private Korttipakka korttipakka;
    private ArrayList<Osanottaja> havinnytJoukko;
    private HashMap<Integer, Kortti> siirtoNumerot;
    
    /**
     * Luokan konstruktori.
     * 
     * @param pelaajamaara Montako pelaajaa pelissä on.
     */
    public Peli(int pelaajamaara) {
        this.osanottajamaara = pelaajamaara;
        this.osanottajajoukko = new ArrayList();
        this.havinnytJoukko = new ArrayList();
        this.vuoronumero = 0;
        this.pelinPelaaja = new Pelaaja("Aleksis");
        this.pankki = new Pankki();
        this.korttipakka = new Korttipakka();
        this.siirtoNumerot = luoSiirtonumerot();
    }

    /**
     * Metodi pistää pelin käyntiin. Tällä hetkellä metodi ei ole käytössä.
     *
     */
    public void kaynnistaPeli() {
        luoOsanottajat();
    }
    
    /**
     * Metodi lisää pelaajan osanottajien joukkoon.
     *
     */
    public void lisaaPelaajaJoukkoon() {
        this.osanottajajoukko.add(pelinPelaaja);
    }
    
    /**
     * Metodi kertoo, kuinka monta osanottajaa pelissä on.
     *
     * @return Osanottajien määrä.
     */
    public int getOsanottajamaara() {
        return osanottajamaara;
    }
    
    /**
     * Metodi kertoo, monesko vuoro on menossa.
     *
     * @return Vuoronumero.
     */
    public int getVuoronumero() {
        return vuoronumero;
    }

    /**
     * Metodi luo pelin osanottajat.
     *
     */
    public void luoOsanottajat() {
        annaKortit(this.pelinPelaaja);
        lisaaPelaajaJoukkoon();
        for (int i = 0; i < osanottajamaara - 1; i++) {
            luojaLisaaVastustaja(Integer.toString(i + 1));
        }

    }

    /**
     * Metodi luo vastustajan ja lisää sen osanottajajoukkoon.
     *
     * @param nimi Minkä niminen vastustaja luodaan ja lisätään peliin.
     *
     */
    public void luojaLisaaVastustaja(String nimi) {
        Vastustaja vastustaja = new Vastustaja(nimi);
        lisaaVastustaja(vastustaja);
        annaKortit(vastustaja);
    }

    /**
     * Metodi lisää vastustajan osanottajajoukkoon.
     *
     * @param vastustaja Vastustaja, joka lisätään joukkoon.
     *
     */
    public void lisaaVastustaja(Vastustaja vastustaja) {
        this.osanottajajoukko.add(vastustaja);
    }

    /**
     * Metodi antaa parametrina annetulle osanottajalle kortit pelin alussa.
     *
     * @param osanottaja Osanottaja, jolle kortit annetaan.
     *
     */
    public void annaKortit(Osanottaja osanottaja) {
        osanottaja.getKorttikasi().lisaaKorttikateen(this.korttipakka.nostaPakastaSatunnainenKortti());
        osanottaja.getKorttikasi().lisaaKorttikateen(this.korttipakka.nostaPakastaSatunnainenKortti());

    }

    /**
     * Metodi päivittää pelitilanteen, eli tarkistaa, ketkä osanottajista ovat
     * jo pudonneet ja ketkä ovat vielä mukana pelissä.
     *
     */
    public void paivitaTilanne() {
        boolean kaytaPoistettavaa = false;
        Osanottaja poistettava = new Osanottaja("");
        for (Osanottaja o : osanottajajoukko) {
            if (o.getKorttikasi().paljastettujenKorttienLukumaara() == 2) {
                poistettava = o;
                kaytaPoistettavaa = true;
            }
        }
        if (kaytaPoistettavaa) {
            this.havinnytJoukko.add(poistettava);
            this.osanottajajoukko.remove(poistettava);
        }
    }
    
    /**
     * Metodi pudottaa vastustajan osanottajien joukosta.
     * 
     * @param vastustaja Pudotettava vastustaja.
     */
    public void pudotaVastustaja(Vastustaja vastustaja) {
        this.osanottajajoukko.remove(vastustaja);
        this.osanottajamaara--;
    }
    
    /**
     * Metodi pudottaa tietyssä kohdassa olevan osanottajan.
     * 
     * @param osanottajanPaikka Pudotettavan osanottajan paikka.
     */
    public void pudotaTassaKohdassaOleva(int osanottajanPaikka) {
        this.osanottajajoukko.remove(osanottajanPaikka);
        this.osanottajamaara--;
    }

    /**
     * Metodi tarkastaa, kuinka monta osanottajaa pelissä on tällä hetkellä.
     *
     * @return i Montako osanottajaa pelissä on jäljellä.
     */
    public int pelissaOlevatOsanottajat() {
        int i = this.osanottajamaara;
        for (Osanottaja osanottaja : osanottajajoukko) {
            if (osanottaja.montakoNakyvaaKorttia() == 2) {
                i--;
            }
        }
        return i;
    }

    public Korttipakka getKorttipakka() {
        return korttipakka;
    }

    public ArrayList<Osanottaja> getOsanottajajoukko() {
        return osanottajajoukko;
    }

    public Pankki getPankki() {
        return pankki;
    }

    public Pelaaja getPelaaja() {
        return pelinPelaaja;
    }

    /**
     * Metodi tuottaa String-olion pelin tämänhetkisestä tilanteesta.
     * 
     * @return Merkkijonoesitys tilanteesta.
     *
     */
    public String kerroTilanneTekstina() {
        StringBuilder teksti = new StringBuilder();
        for (Osanottaja o : osanottajajoukko) {
            teksti.append("Mukana pelissä: \n");
            if (o instanceof Pelaaja) {
                teksti.append("    Nimi: " + o + "\n    Rahaa: " + o.getRaha()
                        + "\n    Kädessä olevat kortit: " + o.naytaKorttikasi()
                        + "\n    Näkyvät kortit: " + o.naytaNakyvatKortit() + "\n");
            } else {
                teksti.append("    Nimi: " + o + "\n    Rahaa: " + o.getRaha()
                        + "\n    Näkyvät kortit: " + o.naytaNakyvatKortit() + "\n");
            }
        }
        teksti.append("\n");
        for (Osanottaja o : havinnytJoukko) {
            teksti.append("Pudonnut pelistä: \n");
            teksti.append("    Nimi: " + o + "\n    Rahaa: " + o.getRaha() + "\n    Näkyvät kortit: " + o.naytaNakyvatKortit() + "\n");
        }
        teksti.append("\n");
        if (!osanottajajoukko.contains(this.pelinPelaaja)) {
            pysayta = true;
            teksti.append("Hävisit pelin.");
        }
        return teksti.toString();
    }

    public void setVuoronumero(int vuoronumero) {
        this.vuoronumero = vuoronumero;
    }

    /**
     * Metodi luo HashMapin, joka liittää siirron siihen korttii, joka siirtoon
     * tarvitaan.
     *
     * @return mappi Valmis HashMap.
     */
    private HashMap<Integer, Kortti> luoSiirtonumerot() {
        HashMap<Integer, Kortti> mappi = new HashMap<Integer, Kortti>();
        mappi.put(4, new Kortti("Duke"));
        mappi.put(5, new Kortti("Assassin"));
        mappi.put(6, new Kortti("Captain"));
        return mappi;
    }

    public HashMap<Integer, Kortti> getSiirtoNumerot() {
        return siirtoNumerot;
    }

    public ArrayList<Osanottaja> getHavinnytJoukko() {
        return havinnytJoukko;
    }

}
