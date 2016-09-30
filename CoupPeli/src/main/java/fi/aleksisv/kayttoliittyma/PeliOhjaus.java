package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.*;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 * Luokka vastaa pelin ohjauksesta.
 */
public class PeliOhjaus {

    private Scanner lukija;
    private Random r;
    private Peli peli;
    GraafinenKayttoliittyma GKL;

    public PeliOhjaus() {
        this.lukija = new Scanner(System.in);
        this.r = new Random();
//        this.GKL = new GraafinenKayttoliittyma();
        alkutoimet();
        pelaa();
    }

    private void alkutoimet() {
        System.out.println("Tämä on Coup-Peli. \n"
                + "------------\n"
                + "Monellako pelaajalla haluat pelata peliä?");

        int i = Integer.parseInt(lukija.nextLine());
        luoPeli(i);

    }

    private void luoPeli(int pelaajaMaara) {
        this.peli = new Peli(pelaajaMaara);
        peli.luoOsanottajat();
    }

    private void pelaa() {
        while (this.peli.getOsanottajajoukko().size() > 1) {
            Osanottaja keneenKohdistuu = null;
            boolean kohdistuukoJohonkuhun = false;
            Osanottaja pelivuorossa = this.peli.getOsanottajajoukko().get(this.peli.getVuoronumero() % this.peli.getOsanottajajoukko().size());
            int siirtovalinta = siirtovalinta(pelivuorossa);
            if(siirtovalinta == 3 || siirtovalinta == 5 ||siirtovalinta == 6) {
                keneenKohdistuu = selvitaKeneenSiirtoKohdistuu(pelivuorossa);
            }
            this.suoritaSiirto(pelivuorossa, keneenKohdistuu, siirtovalinta);
            this.peli.setVuoronumero(this.peli.getVuoronumero() + 1);
            this.peli.paivitaTilanne();
            this.peli.kerroTilanne();
        }
    }
    
    private Osanottaja selvitaKeneenSiirtoKohdistuu(Osanottaja pelivuorossa) {
        int kohde = 0;
        if (pelivuorossa instanceof Pelaaja) {
            System.out.println("Kehen haluat kohdistaa siirron?");
            kohde = Integer.parseInt(lukija.nextLine());
        } else if (pelivuorossa instanceof Vastustaja) {
            kohde = r.nextInt(this.peli.getOsanottajajoukko().size());
            if(this.peli.getOsanottajajoukko().get(kohde) == pelivuorossa) {
                kohde = (kohde + 1) % this.peli.getOsanottajajoukko().size();
            }
        }
        return this.peli.getOsanottajajoukko().get(kohde);
    }

    private int siirtovalinta(Osanottaja o) {
        if (o instanceof Pelaaja) {
            return pelaajanSiirtovalinta((Pelaaja) o);
        } else if (o instanceof Vastustaja) {
            return vastustajanSiirtovalinta((Vastustaja) o);
        } else return 0;
    }

    private int pelaajanSiirtovalinta(Pelaaja pelaaja) {
        System.out.println("Vuoro numero " + this.peli.getVuoronumero() + ". \n");
        System.out.println("Minkä siirron haluat tehdä?");
        System.out.println("Vaihtoehdot: (1) Income (ota 1 kolikko pankista)\n"
                + "             (2) Foreign Aid (ota kaksi kolikkoa pankista)\n"
                + "             (3) Coup (maksaa seitsemän kolikkoa, tapa mikä tahansa kortti)\n"
                + "             (4) Taxes (ota kolme kolikkoa pankista)\n"
                + "             (5) Assassinate (maksa kolme kolikkoa, tapa mikä tahansa kortti)\n"
                + "             (6) Steal (ota kaksi kolikkoa toiselta pelaajalta)\n"
                + "             (7) Swap Influence (nosta kaksi korttia pakasta ja vaihda yksi omaan pakkaasi).");

        while (true) {
            int i = Integer.parseInt(lukija.nextLine());
            if (1 <= i && i <= 7) {
                if (i == 3) {
                    if (pelaaja.getRaha() < 7) {
                        System.out.println("Sinulla ei ole rahaa tähän siirtoon. Valitse joku toinen. \n");
                        continue;
                    }
                
                } else if (i == 5) {
                    if (pelaaja.getRaha() < 3) {
                        System.out.println("Sinulla ei ole rahaa tähän siirtoon. Valitse joku toinen. \n");
                        continue;
                    }
                }
                return i;
            }
            System.out.println("Anna sääntöjen mukainen vastaus. \n");
        }
    }

    private int vastustajanSiirtovalinta(Vastustaja vastustaja) {
        System.out.println("Vastustajan " + vastustaja + " vuoro.");
        return r.nextInt(7) + 1;
    }

    private void suoritaSiirto(Osanottaja pelivuorossa, Osanottaja kohde, int siirtoVaihtoehto) {
        if (pelivuorossa.equals(this.peli.getPelaaja())) {
            suoritaPelaajanSiirto(pelivuorossa, kohde, siirtoVaihtoehto);
        } else {
            suoritaVastustajanSiirto(pelivuorossa, kohde, siirtoVaihtoehto);
        }
    }

    private void suoritaPelaajanSiirto(Osanottaja pelivuorossa, Osanottaja kohde, int siirtoVaihtoehto) {
        Pankki pankki = this.peli.getPankki();
        Pelaaja pelaaja = (Pelaaja) pelivuorossa;
        if(siirtoVaihtoehto == 1) {
            pelaaja.kaytaBasicIncome(pankki);
        } else if (siirtoVaihtoehto == 2) {
            pelaaja.kaytaForeignAid(pankki);
        } else if (siirtoVaihtoehto == 3) {
            pelaaja.kaytaAssassinate(pankki, kohde);
        }
    }

    private void suoritaVastustajanSiirto(Osanottaja pelivuorossa, Osanottaja kohde, int siirtoVaihtoehto) {
        
    }

}
