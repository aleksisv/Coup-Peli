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

    public void kaynnistaPeli() {
        Random r = new Random();
        luoOsanottajat();

        System.out.println("Voittaja: " + this.osanottajajoukko.get(0).toString());

    }

    public int getOsanottajamaara() {
        return osanottajamaara;
    }

    public int getVuoronumero() {
        return vuoronumero;
    }

    public void luoOsanottajat() {
        annaKortit(this.pelinPelaaja);
        osanottajajoukko.add(this.pelinPelaaja);
        for (int i = 0; i < osanottajamaara - 1; i++) {
            luojaLisaaVastustaja(Integer.toString(i + 1));
        }

    }

    public void luojaLisaaVastustaja(String nimi) {
        Vastustaja vastustaja = new Vastustaja(nimi);
        lisaaVastustaja(vastustaja);
        annaKortit(vastustaja);
    }

    public void lisaaVastustaja(Vastustaja vastustaja) {
        this.osanottajajoukko.add(vastustaja);
    }

    public void annaKortit(Osanottaja osanottaja) {
        for (int i = 0; i < 2; i++) {
            osanottaja.getKorttikasi().lisaaKorttikateen(this.korttipakka.nostaPakastaSatunnainenKortti());
        }
    }

    public void paivitaTilanne() {
        boolean kaytaPoistettavaa = false;
        Osanottaja poistettava = new Osanottaja("");
        for (Osanottaja o : osanottajajoukko) {
            if(o.getKorttikasi().paljastettujenKorttienLukumaara() == 2) {
                poistettava = o;
                kaytaPoistettavaa = true;
            }
        }
        if(kaytaPoistettavaa) {
            this.havinnytJoukko.add(poistettava);
            this.osanottajajoukko.remove(poistettava);
        }
    }

    public Vastustaja kukaVastustajistaEpailee(Osanottaja osanottaja, int siirtoVaihtoehto) {
        for (int i = 0; i < this.osanottajajoukko.size(); i++) {
            if (!this.osanottajajoukko.get(i).equals(osanottaja)) {
                if (this.osanottajajoukko.get(i).haluaaEpailla(osanottaja, this.siirtoNumerot.get(siirtoVaihtoehto))) {
                    return (Vastustaja) this.osanottajajoukko.get(i);
                }
            }
        }
        return null;
    }

    public boolean epailyVuoro(Osanottaja osanottaja, Kortti kortti) {

        for (Osanottaja o : osanottajajoukko) {
            if (!o.equals(osanottaja)) {
                if (o.haluaaEpailla(osanottaja, kortti)) {
                    return o.epaile(osanottaja, kortti);
                }
            }
        }
        return false;
    }

    public boolean blokkiVuoro(Osanottaja osanottaja, Kortti kortti) {
        for (Osanottaja o : osanottajajoukko) {
            if (o.haluaaBlokata(osanottaja, kortti)) {
                return o.blokkaa(osanottaja, kortti);
            }
        }
        return false;
    }

    public void pudotaVastustaja(Vastustaja vastustaja) {
        this.osanottajajoukko.remove(vastustaja);
        this.osanottajamaara--;
    }

    public void pudotaTassaKohdassaOleva(int osanottajanPaikka) {
        this.osanottajajoukko.remove(osanottajanPaikka);
        this.osanottajamaara--;
    }

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

    public void kerroTilanne() {
        for (Osanottaja o : osanottajajoukko) {
            System.out.println("Mukana pelissä: ");
            System.out.println("    Nimi: " + o + ", rahaa: " + o.getRaha() + ", näkyvät kortit: " + o.naytaNakyvatKortit());
        }
        for (Osanottaja o : havinnytJoukko) {
            System.out.println("Pudonnut pelistä: ");
            System.out.println("    Nimi: " + o + ", rahaa: " + o.getRaha() + ", näkyvät kortit: " + o.naytaNakyvatKortit());
        }
        
        if (!osanottajajoukko.contains(this.pelinPelaaja)) {
            pysayta = true;
            System.out.println("Hävisit pelin.");
        }
    }

    public void setVuoronumero(int vuoronumero) {
        this.vuoronumero = vuoronumero;
    }

    private HashMap<Integer, Kortti> luoSiirtonumerot() {
        HashMap<Integer, Kortti> mappi = new HashMap<Integer, Kortti>();
        mappi.put(4, new Kortti("Duke"));
        mappi.put(5, new Kortti("Assassin"));
        mappi.put(6, new Kortti("Captain"));
        return mappi;
    }

    public boolean haluaakoJokuVastustajistaBlokata(Osanottaja osanottaja, int siirtoVaihtoehto) {
        for (Osanottaja o : osanottajajoukko) {
            if (o.haluaaBlokata(osanottaja, this.siirtoNumerot.get(siirtoVaihtoehto))) {
                return true;
            }
        }
        return false;
    }

    public HashMap<Integer, Kortti> getSiirtoNumerot() {
        return siirtoNumerot;
    }
}
