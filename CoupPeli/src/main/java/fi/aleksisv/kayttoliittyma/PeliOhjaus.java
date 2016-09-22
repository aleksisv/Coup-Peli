package fi.aleksisv.kayttoliittyma;
import fi.aleksisv.logiikka.*;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author aleksisvuoksenmaa
 */
public class PeliOhjaus {
    private Scanner lukija;
    private Random r;
    Peli peli;
    

    public PeliOhjaus() {
        alkutoimet();
        this.lukija = new Scanner(System.in);
        this.r = new Random();
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
            int i = siirtovalinta(this.peli.getOsanottajajoukko().get(this.peli.getVuoronumero() % this.peli.getOsanottajajoukko().size()));
            ilmoitaSiirtovalinnasta(this.peli.getOsanottajajoukko().get(this.peli.getVuoronumero() % this.peli.getOsanottajajoukko().size()), i);
            this.peli.setVuoronumero(this.peli.getVuoronumero() + 1);
        }
    }
    
    private int siirtovalinta (Osanottaja o) {
        if (o instanceof Pelaaja) {
            return pelaajanSiirtovalinta((Pelaaja) o);
        } else if (o instanceof Vastustaja) {
            return vastustajanSiirtovalinta((Vastustaja) o);
        } return 1;
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
        
        while(true) {
            int i = Integer.parseInt(lukija.nextLine());
            if(0 <=i && i <= 7) {
                System.out.println("Valitsit vaihtoehdon " + i + ".\n");
                return i;
            }
            System.out.println("Anna sääntöjen mukainen vastaus. \n");
        }
    }
    
    private int vastustajanSiirtovalinta(Vastustaja vastustaja) {
        System.out.println("Vastustajan " + vastustaja + " vuoro.");
        return r.nextInt(7) + 1;
    }
    
    private void ilmoitaSiirtovalinnasta(Osanottaja osanottaja, int siirtoVaihtoehto) {
        System.out.println("Osanottaja " + osanottaja + " haluaa yrittää siirtoa " + siirtoVaihtoehto + ".");
        boolean pelaajanEpaily = false;
        boolean pelaajanBlokkaus = true;
        Vastustaja vastustajaJokaEpailee = this.peli.kukaVastustajiastaEpailee(osanottaja, siirtoVaihtoehto);
        boolean vastustajanBlokki = this.peli.haluaakoJokuVastustajiastaBlokata(osanottaja, siirtoVaihtoehto);
        if(!osanottaja.equals(this.peli.getPelaaja())) {
            System.out.println("\nHaluatko epäillä siirtoa?");
            pelaajanEpaily = Boolean.parseBoolean(lukija.nextLine());
            System.out.println("\nHaluatko blokata siirron?");
            pelaajanBlokkaus = Boolean.parseBoolean(lukija.nextLine());
        }
        
        if(vastustajanBlokki) {
            return;
        } else if (pelaajanBlokkaus) {
            return;
        } else if (pelaajanEpaily) {
            this.peli.getPelaaja().epaileSiirtoa(osanottaja, siirtoVaihtoehto);
        } else if (!(vastustajaJokaEpailee ==null)) {
            vastustajaJokaEpailee.epaileSiirtoa(osanottaja, siirtoVaihtoehto);
        } else {
            suoritaSiirto(osanottaja, siirtoVaihtoehto);
        }
    }

    private void suoritaSiirto(Osanottaja osanottaja, int siirtoVaihtoehto) {
        if (osanottaja.equals(this.peli.getPelaaja())) {
            suoritaPelaajanSiirto(siirtoVaihtoehto);
        } else {
            suoritaVastustajanSiirto(siirtoVaihtoehto);
        }
    }

    private void suoritaPelaajanSiirto(int siirtoVaihtoehto) {
        
    }

    private void suoritaVastustajanSiirto(int siirtoVaihtoehto) {
        
    }
    
}
