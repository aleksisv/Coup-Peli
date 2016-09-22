package fi.aleksisv.logiikka;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Peli extends AbstraktiPeli {

    private int osanottajamaara;
    private ArrayList<Osanottaja> osanottajajoukko;
    private boolean pysayta;
    private int vuoronumero;
    private Pelaaja pelinPelaaja;
    private Scanner lukija;
    private Pankki pankki;
    private Korttipakka korttipakka;
    private ArrayList<Osanottaja> havinnytJoukko;

    public Peli(int pelaajamaara) {
        this.osanottajamaara = pelaajamaara;
        this.osanottajajoukko = new ArrayList();
        this.havinnytJoukko = new ArrayList();
        this.vuoronumero = 0;
        this.pelinPelaaja = new Pelaaja("Aleksis");
        this.lukija = new Scanner(System.in);
        this.pankki = new Pankki();
        this.korttipakka = new Korttipakka();
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

    public void pelaaVuoro(Osanottaja osanottaja) {
//        boolean kaytaPoistettavaa = false;
//        Osanottaja poistettava = new Osanottaja("A");
//        for (Osanottaja o : osanottajajoukko) {
//            if (o.getKorttikasi().paljastettujenKorttienLukumaara() == 2) {
//                poistettava = o;
//                kaytaPoistettavaa = true;
//            }
//        }
//        if (kaytaPoistettavaa) {
//            this.havinnytJoukko.add(poistettava);
//            osanottajajoukko.remove(poistettava);
//        }
//
//        kerroTilanne();
//
//        if (!osanottajajoukko.contains(this.pelinPelaaja)) {
//            pysayta = true;
//            System.out.println("Hävisit pelin.");
//        }
    }


    public Vastustaja kukaVastustajistaEpailee (Osanottaja osanottaja, Kortti korttiMitaEiOle) {
        for (Osanottaja o : osanottajajoukko) {
            if (!o.equals(osanottaja)) {
                if (o.haluaaEpailla(osanottaja, korttiMitaEiOle)) {
                    return (Vastustaja) o;
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
            System.out.print("Pelissä mukana: ");
            System.out.println("Osanottaja: " + o + ", rahaa: " + o.getRaha() + ", näkyvät kortit: " + o.naytaNakyvatKortit());
        }
        for (Osanottaja o : havinnytJoukko) {
            System.out.print("Pelistä pudonneita: ");
            System.out.println("Osanottaja: " + o + ", rahaa: " + o.getRaha() + ", näkyvät kortit: " + o.naytaNakyvatKortit());
        }
    }

    public void setVuoronumero(int vuoronumero) {
        this.vuoronumero = vuoronumero;
    }

    public Vastustaja kukaVastustajiastaEpailee(Osanottaja osanottaja, int siirtoVaihtoehto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean haluaakoJokuVastustajiastaBlokata(Osanottaja osanottaja, int siirtoVaihtoehto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
