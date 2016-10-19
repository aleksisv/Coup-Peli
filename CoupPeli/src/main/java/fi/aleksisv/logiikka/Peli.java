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

    /**
     * Montako osanottajaa pelissä on.
     */
    private int osanottajamaara;
    /**
     * Pelissä mukana olevat sanottajat sisälleen säilövä lista.
     */
    private ArrayList<Osanottaja> osanottajajoukko;
    /**
     * Mikä vuoro pelissä on menossa.
     */
    private int vuoronumero;
    /**
     * Kuka on pelin pelaaja.
     */
    private Pelaaja pelinPelaaja;
    /**
     * Pelin pankki.
     */
    private Pankki pankki;
    /**
     * Pelin korttipakka.
     */
    private Korttipakka korttipakka;
    /**
     * Hävinneet osanottajat sisällään pitävä lista.
     */
    private ArrayList<Osanottaja> havinnytJoukko;
    /**
     * HashMap, joka liittää siirtonumeron vastaavaan korttiin.
     */
    private HashMap<Integer, Kortti> siirtoNumerot;
    /**
     * HashMap, joka liittää siirtonumeron tätä vastaavaan nimeen.
     */
    private HashMap<Integer, String> siirtoNimet;
    /**
     * HashMap, joka liittää siirtonumeron tätä vastaavaan nimeen.
     */
    private HashMap<Integer, Kortti> torjuntaLista;

    /**
     * Luokan konstruktori.
     *
     * @param pelaajamaara Montako pelaajaa pelissä on.
     * @param pelaajanNimi Minkä niminen pelin pelaaja on.
     */
    public Peli(int pelaajamaara, String pelaajanNimi) {
        this.osanottajamaara = pelaajamaara;
        this.osanottajajoukko = new ArrayList();
        this.havinnytJoukko = new ArrayList();
        this.vuoronumero = 0;
        this.pelinPelaaja = new Pelaaja(pelaajanNimi);
        this.pankki = new Pankki();
        this.korttipakka = new Korttipakka();
        this.siirtoNumerot = luoSiirtonumerot();
        this.siirtoNimet = luoSiirtoNimet();
        this.torjuntaLista = luoTorjuntalista();
    }

    /**
     * Metodi pistää pelin käyntiin. Tällä hetkellä metodi ei ole käytössä.
     */
    public void kaynnistaPeli() {
        luoOsanottajat();
    }

    /**
     * Metodi lisää pelaajan osanottajien joukkoon.
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
        Random r = new Random();
        String[] nimet = {"Azra", "Mazra", "Miia", "Jea", "Sirpiina", "Make", "Santtu",
            "Siiri", "Pia", "Pai"};
        annaKortit(this.pelinPelaaja);
        lisaaPelaajaJoukkoon();
        for (int i = 0; i < osanottajamaara - 1; i++) {
            luojaLisaaVastustaja(nimet[r.nextInt(nimet.length)]);
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
        vastustaja.setPeli(this);
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
     * Metodi pudottaa vastustajan osanottajien joukosta.
     *
     * @param vastustaja Pudotettava vastustaja.
     */
    public void pudotaVastustaja(Vastustaja vastustaja) {
        this.osanottajajoukko.remove(vastustaja);
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
        return this.pelinPelaaja;
    }

    /**
     * Vuoronumerosetteri.
     *
     * @param vuoronumero Mikä vuoronumero halutaan asettaa.
     */
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
        mappi.put(4, new Kortti("Herttua"));
        mappi.put(5, new Kortti("Salamurhaaja"));
        mappi.put(6, new Kortti("Kapteeni"));
        return mappi;
    }

    private HashMap<Integer, String> luoSiirtoNimet() {
        HashMap<Integer, String> mappi = new HashMap<Integer, String>();
        mappi.put(1, "Perustulo");
        mappi.put(2, "Ulkomaanapu");
        mappi.put(3, "Vallankumous");
        mappi.put(4, "Verotus");
        mappi.put(5, "Assassinoi");
        mappi.put(6, "Varasta");
        return mappi;
    }

    private HashMap<Integer, Kortti> luoTorjuntalista() {
        HashMap<Integer, Kortti> mappi = new HashMap();
        mappi.put(2, new Kortti("Herttua"));
        mappi.put(5, new Kortti("Kreivitär"));
        mappi.put(6, new Kortti("Kapteeni"));

        return mappi;
    }

    public HashMap<Integer, String> getSiirtoNimet() {
        return siirtoNimet;
    }

    public HashMap<Integer, Kortti> getSiirtoNumerot() {
        return siirtoNumerot;
    }

    public ArrayList<Osanottaja> getHavinnytJoukko() {
        return havinnytJoukko;
    }

    public HashMap<Integer, Kortti> getTorjuntaLista() {
        return torjuntaLista;
    }
}
