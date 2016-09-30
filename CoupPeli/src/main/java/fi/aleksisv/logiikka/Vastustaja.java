package fi.aleksisv.logiikka;

import java.util.ArrayList;
import java.util.Random;

/**
 * Luokka vangitsee pelin sellaisen osanottajan, jota ohjaa tekoäly ja jota
 * vastaan pelaaja pelaa.
 */
public class Vastustaja extends Osanottaja {

    private Korttikasi kasi;

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
        super.kaytaUlkomaanapu(pankki); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void lisaaKorttiKorttipakkaan(Kortti kortti) {
        super.lisaaKorttiKorttipakkaan(kortti);
    }

    public void kaytaAssassinoi(Pankki pankki, ArrayList<Osanottaja> osanottajajoukko) {
        Random r = new Random();
        osanottajajoukko.get(r.nextInt(osanottajajoukko.size())).paljastaKortti();
    }

    public void kaytaVallankaappaus(Pankki pankki, ArrayList<Osanottaja> osanottajajoukko) {
        Random r = new Random();
        osanottajajoukko.get(r.nextInt(osanottajajoukko.size())).paljastaKortti();
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
    public void setKasi(Korttikasi kasi) {
        super.setKasi(kasi);
    }

    @Override
    public void setNimi(String nimi) {
        super.setNimi(nimi);
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

    public void epaileSiirtoa(Osanottaja osanottaja, int siirtoVaihtoehto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
