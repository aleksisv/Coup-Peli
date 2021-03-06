package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.*;
import java.util.Random;

/**
 * Luokka vastaa pelin ohjauksesta.
 */
public class PeliOhjaus {

    /**
     * Random-olio.
     */
    private Random r;

    /**
     * Peli, jota ohjataan.
     */
    private Peli peli;

    /**
     * Graafinen käyttöliittymä.
     */
    GraafinenKayttoliittyma gkl;

    /**
     * Luokan konstruktori.
     *
     * @param gkl Graafinen käyttöliittymä.
     */
    public PeliOhjaus(GraafinenKayttoliittyma gkl) {
        this.r = new Random();
        this.gkl = gkl;
    }

    /**
     * Metodi luo pelin halutulla pelaajamäärällä.
     *
     * @param pelaajaMaara Monenko pelaajan peli luodaan.
     * @param pelaajanNimi Mikä on pelaajan nimi.
     */
    public void luoPeli(int pelaajaMaara, String pelaajanNimi) {
        this.peli = new Peli(pelaajaMaara, pelaajanNimi);
        peli.luoOsanottajat();
    }

    /**
     * Metodi vastaa yhdestä siirtoyrityksestä ja tämän tiedon kertomisesta
     * GUI:lle.
     *
     * @param pelivuorossa Kuka on pelivuorossa.
     * @param kohde Mitä osanottajaa vastaan siirto halutaan tehdä.
     * @param siirtoVaihtoehto mikä siirto halutaan tehdä.
     */
    public void suoritaSiirto(Osanottaja pelivuorossa, Osanottaja kohde, int siirtoVaihtoehto) {
        if (pelivuorossa.equals(this.peli.getPelaaja())) {
            suoritaPelaajanSiirto(pelivuorossa, kohde, siirtoVaihtoehto);
        } else {
            suoritaVastustajanSiirto(pelivuorossa, kohde, siirtoVaihtoehto);
        }
        this.peli.setVuoronumero(this.peli.getVuoronumero() + 1);
        this.paivitaTilanne();
    }

    private void suoritaPelaajanSiirto(Osanottaja pelivuorossa, Osanottaja kohde, int siirtoVaihtoehto) {
        Pankki pankki = this.peli.getPankki();
        Pelaaja pelaaja = (Pelaaja) pelivuorossa;
        if (siirtoVaihtoehto == 1) {
            pelaaja.kaytaPerustulo(pankki);
        } else if (siirtoVaihtoehto == 2) {
            pelaaja.kaytaUlkomaanapu(pankki);
        } else if (siirtoVaihtoehto == 3) {
            pelaaja.kaytaVallankaappaus(pankki, kohde);
        } else if (siirtoVaihtoehto == 4) {
            pelaaja.kaytaVerotus(pankki);
        } else if (siirtoVaihtoehto == 5) {
            pelaaja.kaytaAssassinoi(pankki, kohde);
        } else if (siirtoVaihtoehto == 6) {
            pelaaja.kaytaVarasta(pankki, kohde);
        }
    }

    private void suoritaVastustajanSiirto(Osanottaja pelivuorossa, Osanottaja kohde, int siirtoVaihtoehto) {
        Pankki pankki = this.peli.getPankki();
        Vastustaja vastustaja = (Vastustaja) pelivuorossa;
        if (siirtoVaihtoehto == 1) {
            vastustaja.kaytaPerustulo(pankki);
        } else if (siirtoVaihtoehto == 2) {
            vastustaja.kaytaUlkomaanapu(pankki);
        } else if (siirtoVaihtoehto == 3) {
            vastustaja.kaytaVallankaappaus(pankki, kohde);
        } else if (siirtoVaihtoehto == 4) {
            vastustaja.kaytaVerotus(pankki);
        } else if (siirtoVaihtoehto == 5) {
            vastustaja.kaytaAssassinoi(pankki, kohde);
        } else if (siirtoVaihtoehto == 6) {
            vastustaja.kaytaVarasta(pankki, kohde);
        }
    }

    public Peli getPeli() {
        return peli;
    }

    /**
     * Metodi vastaa yhdestä siirtoyrityksestä ja tämän tiedon kertomisesta
     * GUI:lle.
     *
     * @param siirtoNumero Minkä numeroista siirtoa yritetään.
     *
     * @return Onko pelaajalla tarvpeeksi rahaa vai ei.
     */
    public boolean onkoPelaajallaRahaa(int siirtoNumero) {
        if (siirtoNumero == 3) {
            return this.peli.getPelaaja().getRaha() >= 7;
        } else if (siirtoNumero == 5) {
            return this.peli.getPelaaja().getRaha() >= 3;
        } else {
            return true;
        }
    }

    /**
     * Metodi päivittää pelitilanteen, eli tarkistaa, ketkä osanottajista ovat
     * jo pudonneet ja ketkä ovat vielä mukana pelissä. Lisäksi metodi ilmoittaa
     * käyttöliittymälle, mikäli pelin on aika loppua.
     */
    public void paivitaTilanne() {
        boolean kaytaPoistettavaa = false;
        Osanottaja poistettava = new Osanottaja("");
        for (Osanottaja o : peli.getOsanottajajoukko()) {
            if (o.getKorttikasi().paljastettujenKorttienLukumaara() == 2) {
                poistettava = o;
                kaytaPoistettavaa = true;
            }
        }
        if (kaytaPoistettavaa) {
            peli.getHavinnytJoukko().add(poistettava);
            peli.getOsanottajajoukko().remove(poistettava);
        }
        
        if (!peli.getOsanottajajoukko().contains(peli.getPelaaja())) {
            this.gkl.lopetaPeli(false);
        } else if (peli.getOsanottajajoukko().size() == 1) {
            this.gkl.lopetaPeli(true);
        }
    }

    public void setGkl(GraafinenKayttoliittyma gkl) {
        this.gkl = gkl;
    }

}
