package fi.aleksisv.logiikka;

import java.util.ArrayList;
import java.util.Random;

/**
 * Luokka vangitsee pelin sellaisen osanottajan, jota ohjaa tekoäly ja jota
 * vastaan pelaaja pelaa.
 */
public class Vastustaja extends Osanottaja {

    private Korttikasi kasi;
    
    /**
     * Luokan konstruktori.
     * 
     * @param nimi Luotavan vastustajan nimi.
     */
    public Vastustaja(String nimi) {
        super(nimi);
    }

    @Override
    public void annaRahaaPankille(int maara, Pankki pankki) {
        super.annaRahaaPankille(maara, pankki);
    }

    @Override
    public boolean haluaaEpailla(Osanottaja osanottaja, Kortti mitaEiOle) {
        return super.haluaaEpailla(osanottaja, mitaEiOle);
    }

    @Override
    public boolean epaile(Osanottaja osanottaja, Kortti mitaEiOle) {
        return super.epaile(osanottaja, mitaEiOle);
    }

    @Override
    public Korttikasi getKorttikasi() {
        return super.getKorttikasi();
    }

    @Override
    public String getNimi() {
        return super.getNimi();
    }

    @Override
    public int getRaha() {
        return super.getRaha();
    }

    @Override
    public void kaytaPerustulo(Pankki pankki) {
        super.kaytaPerustulo(pankki);
    }

    @Override
    public void kaytaUlkomaanapu(Pankki pankki) {
        super.kaytaUlkomaanapu(pankki);
    }

    @Override
    public void lisaaKorttiKorttipakkaan(Kortti kortti) {
        super.lisaaKorttiKorttipakkaan(kortti);
    }
    
    /**
     * Metodi tekee Assassinointi-siirron.
     * 
     * @param pankki Pankki, jolle siirtoon tarvittava raha annetaan.
     * @param osanottajajoukko Pelissä mukana olevat osanottajat.
     */
    public void kaytaAssassinoi(Pankki pankki, ArrayList<Osanottaja> osanottajajoukko) {
        Random r = new Random();
        Osanottaja assassinoitava = osanottajajoukko.get(r.nextInt(osanottajajoukko.size()));
        assassinoitava.paljastaKortti();
    }
    
    /**
     * Metodi tekee Vallankaappaus-siirron.
     * 
     * @param pankki Pankki, jolle siirtoon tarvittava raha annetaan.
     * @param osanottajajoukko Pelissä mukana olevat osanottajat.
     */
    public void kaytaVallankaappaus(Pankki pankki, ArrayList<Osanottaja> osanottajajoukko) {
        Random r = new Random();
        Osanottaja kohde = osanottajajoukko.get(r.nextInt(osanottajajoukko.size()));
        kohde.paljastaKortti();
    }

    @Override
    public void kaytaVarasta(Pankki pankki, Osanottaja osanottaja) {
        super.kaytaVarasta(pankki, osanottaja);
    }

    @Override
    public void kaytaVerotus(Pankki pankki) {
        super.kaytaVerotus(pankki);
    }

    @Override
    public String naytaNakyvatKortit() {
        return super.naytaNakyvatKortit();
    }

    @Override
    public boolean haluaaTorjua(Osanottaja osanottaja, Kortti kortti) {
        return super.haluaaTorjua(osanottaja, kortti);
    }

    @Override
    public void menetaRahaa(int maara) {
        super.menetaRahaa(maara);
    }

    @Override
    public void nostaSatunnainenKorttiPakasta(Korttipakka pakka) {
        super.nostaSatunnainenKorttiPakasta(pakka);
    }

    @Override
    public void otaRahaaPankilta(int maara, Pankki pankki) {
        super.otaRahaaPankilta(maara, pankki);
    }

    @Override
    public void saaRahaa(int maara) {
        super.saaRahaa(maara);
    }

    @Override
    public void setRaha(int raha) {
        super.setRaha(raha);
    }

    @Override
    public void paljastaKortti() {
        super.paljastaKortti();
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
