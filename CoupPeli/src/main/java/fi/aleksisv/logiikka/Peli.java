package fi.aleksisv.logiikka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Peli extends AbstraktiPeli {

    private int osanottajamaara;
    private ArrayList<Osanottaja> osanottajajoukko;
    private boolean pysayta;
    private int vuoronumero;
    private Pelaaja pelaaja;
    private Scanner lukija;
    private Pankki pankki;
    private Korttipakka korttipakka;

    public Peli(int pelaajamaara) {
        this.osanottajamaara = pelaajamaara;
        this.osanottajajoukko = new ArrayList();
        this.vuoronumero = 0;
        this.pelaaja = new Pelaaja("Aleksis");
        this.lukija = new Scanner(System.in);
        this.pankki = new Pankki();
        this.korttipakka = new Korttipakka();
    }

    public void kaynnistaPeli() {
        Random r = new Random();
        luoOsanottajat();
        this.vuoronumero = 0;

        while (this.osanottajajoukko.size() > 1) {
            pelaaVuoro(this.osanottajajoukko.get(vuoronumero % osanottajajoukko.size()));
            this.vuoronumero++;
        }
        System.out.println("Voittaja: " + this.osanottajajoukko.get(0).toString());

    }

    public int getOsanottajamaara() {
        return osanottajamaara;
    }

    private void luoOsanottajat() {
        annaKortit(this.pelaaja);
        osanottajajoukko.add(this.pelaaja);
        for (int i = 0; i < osanottajamaara - 1; i++) {
            luojaLisaaVastustaja(Integer.toString(i));
        }
        System.out.print("Pelaajia jäljellä: ");
        for (Osanottaja osanottajat : osanottajajoukko) {
            System.out.print(osanottajat);
            System.out.print(" ");
        }
        System.out.println("");
    }

    public void luojaLisaaVastustaja(String nimi) {
        Vastustaja vastustaja = new Vastustaja(nimi);
        lisaaVastustaja(vastustaja);
        annaKortit(vastustaja);
    }

    public void lisaaVastustaja(Vastustaja vastustaja) {
        osanottajajoukko.add(vastustaja);
    }

    public void annaKortit(Osanottaja osanottaja) {
        for (int i = 0; i < 2; i++) {
            osanottaja.getKorttikasi().lisaaKorttikateen(this.korttipakka.nostaPakastaSatunnainenKortti());
        }
    }

    public void pelaaVuoro(Osanottaja osanottaja) {
        if (osanottaja instanceof Pelaaja) {
            pelaajanVuoro((Pelaaja) osanottaja);
        } else if (osanottaja instanceof Vastustaja) {
            vastustajanVuoro((Vastustaja) osanottaja);
        }

        for (Osanottaja o : osanottajajoukko) {
            if (o.getKorttikasi().koko() < 1) {
                osanottajajoukko.remove(o);
                this.osanottajamaara--;
            }
        }

        kerroTilanne();

        if (!osanottajajoukko.contains(this.pelaaja)) {
            pysayta = true;
            System.out.println("Hävisit pelin.");
        }
    }

    private void pelaajanVuoro(Pelaaja pelaaja) {
        System.out.println("Vuoro: " + vuoronumero + "\n");
        System.out.println("Minkä siirron haluat tehdä?");
        System.out.println("Vaihtoehdot: (1) poista vastustajan kortti \n"
                + "             (2) älä tee mitään.");

//        int vastaus1 = Integer.parseInt(lukija.nextLine());
        int vastaus1 = 1;

        if (vastaus1 == 1) {
            System.out.print("Valitse pelaaja, jonka kortin haluat poistaa. Vaihtoehdot: ");
            for (Osanottaja o : osanottajajoukko) {
                if (!o.equals(this.pelaaja)) {
                    System.out.print(o + " ");
                }
            }
//            int vastaus2 = Integer.parseInt(lukija.nextLine());
            int vastaus2 = 1;
            this.osanottajajoukko.remove(vastaus2);
        } else if (vastaus1 == 2) {
            System.out.println("");
        }
        System.out.println("");
    }

    private void vastustajanVuoro(Vastustaja osanottaja) {
        Random r = new Random();
        int kukaPudotetaan = r.nextInt(this.osanottajajoukko.size());
        System.out.println("Vastustaja " + osanottaja + " aikoo pudottaa osanottajan " + this.osanottajajoukko.get(kukaPudotetaan));
        pudotaTassaKohdassaOleva(kukaPudotetaan);
        this.osanottajamaara--;

    }

    public void pudotaVastustaja(Vastustaja vastustaja) {
        this.osanottajajoukko.remove(vastustaja);
        this.osanottajamaara--;
    }

    public void pudotaTassaKohdassaOleva(int osanottajanPaikka) {
        this.osanottajajoukko.remove(osanottajanPaikka);
        this.osanottajamaara--;
    }

    public Korttipakka getKorttipakka() {
        return korttipakka;
    }

    public Scanner getLukija() {
        return lukija;
    }

    public ArrayList<Osanottaja> getOsanottajajoukko() {
        return osanottajajoukko;
    }

    public Pankki getPankki() {
        return pankki;
    }

    public Pelaaja getPelaaja() {
        return pelaaja;
    }

    public void kerroTilanne() {
        System.out.println("Pelaajia jäljellä: ");
        for (Osanottaja o : osanottajajoukko) {
            System.out.println(o);
        }
    }

    public void setKorttipakka(Korttipakka korttipakka) {
        this.korttipakka = korttipakka;
    }

    public void setOsanottajajoukko(ArrayList<Osanottaja> osanottajajoukko) {
        this.osanottajajoukko = osanottajajoukko;
    }

    public void setOsanottajamaara(int osanottajamaara) {
        this.osanottajamaara = osanottajamaara;
    }

    public void setPankki(Pankki pankki) {
        this.pankki = pankki;
    }

    public void setPelaaja(Pelaaja pelaaja) {
        this.pelaaja = pelaaja;
    }

    public void setPysayta(boolean pysayta) {
        this.pysayta = pysayta;
    }

    public void setVuoronumero(int vuoronumero) {
        this.vuoronumero = vuoronumero;
    }

}
