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
    private Pelaaja pelinPelaaja;
    private Scanner lukija;
    private Pankki pankki;
    private Korttipakka korttipakka;

    public Peli(int pelaajamaara) {
        this.osanottajamaara = pelaajamaara;
        this.osanottajajoukko = new ArrayList();
        this.vuoronumero = 0;
        this.pelinPelaaja = new Pelaaja("Aleksis");
        this.lukija = new Scanner(System.in);
        this.pankki = new Pankki();
        this.korttipakka = new Korttipakka();
    }

    public void kaynnistaPeli() {
        Random r = new Random();
        luoOsanottajat();

        while (this.osanottajajoukko.size() > 1) {
            pelaaVuoro(this.osanottajajoukko.get(vuoronumero % osanottajajoukko.size()));
            this.vuoronumero++;
        }
        System.out.println("Voittaja: " + this.osanottajajoukko.get(0).toString());

    }

    public int getOsanottajamaara() {
        return osanottajamaara;
    }

    public int getVuoronumero() {
        return vuoronumero;
    }
    
    

    private void luoOsanottajat() {
        annaKortit(this.pelinPelaaja);
        osanottajajoukko.add(this.pelinPelaaja);
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
        this.osanottajajoukko.add(vastustaja);
        for (Osanottaja o : osanottajajoukko) {
            System.out.println(o);
        }
        this.vuoronumero++;
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

        if (!osanottajajoukko.contains(this.pelinPelaaja)) {
            pysayta = true;
            System.out.println("Hävisit pelin.");
        }
    }

    private void pelaajanVuoro(Pelaaja pelaaja) {

        boolean poistutaankoLoopista = false;

        while (poistutaankoLoopista = false) {

            System.out.println("Vuoro: " + vuoronumero + "\n");
            System.out.println("Minkä siirron haluat tehdä?");
            System.out.println("Vaihtoehdot: (1) Income (ota 1 kolikko pankista)\n"
                    + "             (2) Foreign Aid (ota kaksi kolikkoa pankista)\n"
                    + "             (3) Coup (maksaa seitsemän kolikkoa, tapa mikä tahansa kortti)\n"
                    + "             (4) Taxes (ota kolme kolikkoa pankista)\n"
                    + "             (5) Assassinate (maksa kolme kolikkoa, tapa mikä tahansa kortti)\n"
                    + "             (6) Steal (ota kaksi kolikkoa toiselta pelaajalta)\n"
                    + "             (7) Swap Influence (nosta kaksi korttia pakasta ja vaihda yksi omaan pakkaasi).");
            int vastaus1 = Integer.parseInt(lukija.nextLine());

            if (vastaus1 == 1) {
                poistutaankoLoopista = true;
                System.out.print("Valitsit Income.");
                pelaaja.kaytaBasicIncome(pankki);
            } else if (vastaus1 == 2) {
                poistutaankoLoopista = true;
                System.out.println("Valitsit Foreign Aid.");
                pelaaja.kaytaForeingAid(pankki);
            } else if (vastaus1 == 3) {
                System.out.println("Valitsit Taxes");
                if (!epailyVuoro(pelaaja, new Kortti("Duke")) && !blokkiVuoro(pelaaja, new Kortti("Duke"))) {
                    pelaaja.kaytaTaxes(pankki);
                }
            }
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

    public boolean epailyVuoro(Osanottaja osanottaja, Kortti kortti) {

        for (Osanottaja o : osanottajajoukko) {
            if (o.haluaaEpailla(osanottaja, kortti)) {
                return o.epaile(osanottaja, kortti);
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
        return pelinPelaaja;
    }

    public void kerroTilanne() {
        System.out.println("Pelaajia jäljellä: ");
        for (Osanottaja o : osanottajajoukko) {
            System.out.println("Osanottaja: " + o + ", rahaa: " + o.getRaha() + ", näkyvät kortit: " + o.naytaNakyvatKortit());
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
        this.pelinPelaaja = pelaaja;
    }

    public void setPysayta(boolean pysayta) {
        this.pysayta = pysayta;
    }

    public void setVuoronumero(int vuoronumero) {
        this.vuoronumero = vuoronumero;
    }

}
