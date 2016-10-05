package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.*;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Luokka vastaa pelin ohjauksesta.
 */
public class PeliOhjaus {

    private Scanner lukija;
    private Random r;
    private Peli peli;
    GraafinenKayttoliittyma gkl;

    public PeliOhjaus() {
        this.lukija = new Scanner(System.in);
        this.r = new Random();
        GraafinenKayttoliittyma kayttoliittyma = new GraafinenKayttoliittyma();
        SwingUtilities.invokeLater(kayttoliittyma);
//        this.gkl = new GraafinenKayttoliittyma();
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
            boolean epailikoTaiTorjuikoJoku = false;
            Osanottaja keneenKohdistuu = null;
            Osanottaja pelivuorossa = this.peli.getOsanottajajoukko().get(this.peli.getVuoronumero() % this.peli.getOsanottajajoukko().size());
            int siirtovalinta = siirtovalinta(pelivuorossa);
            if (siirtovalinta == 3 || siirtovalinta == 5 || siirtovalinta == 6) {
                keneenKohdistuu = selvitaKeneenSiirtoKohdistuu(pelivuorossa);
                if (keneenKohdistuu instanceof Pelaaja) {
                    if (haluaakoPelaajaTorjuaSiirron(siirtovalinta)) {
                        keneenKohdistuu.torju(pelivuorossa, this.peli.getSiirtoNumerot().get(siirtovalinta));
                        epailikoTaiTorjuikoJoku = true;
                    }
                    if (haluaakoPelaajaEpaillaSiirtoa(siirtovalinta)) {
                        keneenKohdistuu.epaile(pelivuorossa, this.peli.getSiirtoNumerot().get(siirtovalinta));
                        epailikoTaiTorjuikoJoku = true;
                    }
                } else {
                    if (keneenKohdistuu.haluaaTorjua(pelivuorossa, this.peli.getSiirtoNumerot().get(siirtovalinta))) {
                        keneenKohdistuu.torju(pelivuorossa, this.peli.getSiirtoNumerot().get(siirtovalinta));
                        epailikoTaiTorjuikoJoku = true;
                    }

                    if (keneenKohdistuu.haluaaEpailla(pelivuorossa, this.peli.getSiirtoNumerot().get(siirtovalinta))) {
                        keneenKohdistuu.epaile(pelivuorossa, this.peli.getSiirtoNumerot().get(siirtovalinta));
                        epailikoTaiTorjuikoJoku = true;
                    }
                }
            }
            if (!epailikoTaiTorjuikoJoku) {
                this.suoritaSiirto(pelivuorossa, keneenKohdistuu, siirtovalinta);
            }
            this.peli.setVuoronumero(this.peli.getVuoronumero() + 1);
            this.peli.paivitaTilanne();
            this.peli.kerroTilanne();
        }
        System.out.println("Peli on ohi. Voittaja: \n   " + this.peli.getOsanottajajoukko().get(0));
    }

    private Osanottaja selvitaKeneenSiirtoKohdistuu(Osanottaja pelivuorossa) {
        int kohde = 0;
        if (pelivuorossa instanceof Pelaaja) {
            System.out.println("Kehen haluat kohdistaa siirron?");
            kohde = Integer.parseInt(lukija.nextLine());
        } else if (pelivuorossa instanceof Vastustaja) {
            kohde = r.nextInt(this.peli.getOsanottajajoukko().size());
            if (this.peli.getOsanottajajoukko().get(kohde) == pelivuorossa) {
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
        } else {
            return 0;
        }
    }

    private int pelaajanSiirtovalinta(Pelaaja pelaaja) {
        System.out.println("Vuoro " + this.peli.getVuoronumero() + ". \n");
        System.out.println("Minkä siirron haluat tehdä?");
        System.out.println("Vaihtoehdot: (1) Perustulo (ota 1 kolikko pankista)\n"
                + "             (2) Ulkomaanapu (ota 2 kolikkoa pankista)\n"
                + "             (3) Vallankaappaus (maksaa 7 kolikkoa, tapa mikä tahansa kortti)\n"
                + "             (4) Verotus (ota 3 kolikkoa pankista)\n"
                + "             (5) Assassinoi (maksa 3 kolikkoa, tapa mikä tahansa kortti)\n"
                + "             (6) Varasta (ota 2 kolikkoa toiselta pelaajalta)\n"
                + "             (7) Swap Influence (nosta 2 korttia pakasta ja vaihda 1 omaan pakkaasi).");

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

    private boolean haluaakoPelaajaTorjuaSiirron(int siirtovalinta) {
        System.out.println("Vastustaja teki valinnan " + siirtovalinta + ". Haluatko"
                + " torjua siirron?");
        return Boolean.parseBoolean(lukija.nextLine());
    }

    private boolean haluaakoPelaajaEpaillaSiirtoa(int siirtovalinta) {
        System.out.println("Haluatko epäillä vastustajan siirtoa " + siirtovalinta + "?");
        return Boolean.parseBoolean(lukija.nextLine());
    }

}
