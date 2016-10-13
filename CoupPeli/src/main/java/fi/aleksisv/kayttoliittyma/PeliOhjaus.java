package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.*;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
/**
 * Luokka vastaa pelin ohjauksesta.
 */
public class PeliOhjaus {
    private Random r;
    private Peli peli;
    GraafinenKayttoliittyma gkl;
    
    /**
     * Luokan konstruktori.
     */
    public PeliOhjaus() {
        this.r = new Random();
    }
    
    
    /**
     * Metodi luo pelin halutulla pelaajamäärällä.
     * 
     * @param pelaajaMaara Monenko pelaajan peli luodaan.
     */
    public void luoPeli(int pelaajaMaara) {
        this.peli = new Peli(pelaajaMaara);
        peli.luoOsanottajat();
    }

    /**
     * Metodi vastaa yhden siirron suorittamisesta.
     * 
     * @param siirto Mikä siirto halutaan suorittaa.
     * @param kohde Mitä osanottajaa vastaan siirto halutaan tehdä.
     */
    public void pelaaSiirto(int siirto, int kohde) {
        Osanottaja keneenKohdistuu = this.peli.getOsanottajajoukko().get(kohde);
        Pelaaja pelivuorossa = this.peli.getPelaaja();
        boolean epailikoTaiTorjuikoJoku = false;
        if (siirto == 3 || siirto == 5 || siirto == 6) {

            if (this.peli.getOsanottajajoukko().get(kohde).haluaaTorjua(this.peli.getPelaaja(), this.peli.getSiirtoNumerot().get(siirto))) {
                keneenKohdistuu.torju(pelivuorossa, this.peli.getSiirtoNumerot().get(siirto));
                epailikoTaiTorjuikoJoku = true;
            }

            if (keneenKohdistuu.haluaaEpailla(pelivuorossa, this.peli.getSiirtoNumerot().get(siirto))) {
                keneenKohdistuu.epaile(pelivuorossa, this.peli.getSiirtoNumerot().get(siirto));
                epailikoTaiTorjuikoJoku = true;
            }

        }
        if (!epailikoTaiTorjuikoJoku) {
            this.suoritaSiirto(pelivuorossa, keneenKohdistuu, siirto);
        }
        this.peli.setVuoronumero(this.peli.getVuoronumero() + 1);
        this.peli.paivitaTilanne();
    }
    
    public void yritaSiirtoa(int siirtonumero, int kohde, JFrame pelausIkkuna) {
        Pelaaja pelaaja = this.getPeli().getPelaaja();
        Vastustaja vastustaja = (Vastustaja) this.getPeli().getOsanottajajoukko().get(kohde);
        Kortti siirto = this.getPeli().getSiirtoNumerot().get(siirtonumero);

        if (vastustaja.haluaaEpailla(pelaaja, siirto)) {
            
            JButton tee = new JButton("Tee siirto.");
            JButton alaTee = new JButton("Älä tee siirtoa.");
            pelausIkkuna.add(new JTextArea("Vastustaja haluaa epäillä siirtoasi. Haluatko perua siirron?"));
            pelausIkkuna.add(tee);
            pelausIkkuna.add(alaTee);
            SwingUtilities.updateComponentTreeUI(pelausIkkuna);
            
        } else if (vastustaja.haluaaTorjua(pelaaja, siirto)) {
            JButton tee = new JButton("Tee siirto.");
            JButton alaTee = new JButton("Älä tee siirtoa");
            pelausIkkuna.add(new JTextArea("Vastustaja haluaa torjua siirtosi. Haluatko epäillä vastustajan torjumista?"));
            pelausIkkuna.add(tee);
            pelausIkkuna.add(alaTee);
            SwingUtilities.updateComponentTreeUI(pelausIkkuna);
            
        } else {
            this.suoritaSiirto(pelaaja, vastustaja, siirtonumero);
        }
    }

    public void suoritaSiirto(Osanottaja pelivuorossa, Osanottaja kohde, int siirtoVaihtoehto) {
        if (pelivuorossa.equals(this.peli.getPelaaja())) {
            suoritaPelaajanSiirto(pelivuorossa, kohde, siirtoVaihtoehto);
        } else {
            suoritaVastustajanSiirto(pelivuorossa, kohde, siirtoVaihtoehto);
        }
        this.peli.setVuoronumero(this.peli.getVuoronumero() + 1);
        this.peli.paivitaTilanne();
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

    public boolean onkoPelaajallaRahaa(int siirtoNumero) {
        if(siirtoNumero == 3) {
            return this.peli.getPelaaja().getRaha() >= 7;
        } else if (siirtoNumero == 5) {
            return this.peli.getPelaaja().getRaha() >= 3;
        } else return true;
    }

}
