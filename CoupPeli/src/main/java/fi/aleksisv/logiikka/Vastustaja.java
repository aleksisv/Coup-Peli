package fi.aleksisv.logiikka;

import java.util.ArrayList;
import java.util.Random;

/**
 * Luokka vangitsee pelin sellaisen osanottajan, jota ohjaa tekoäly ja jota vastaan pelaaja pelaa.
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
    public void kaytaBasicIncome(Pankki pankki) {
        super.kaytaBasicIncome(pankki);
    }

    @Override
    public void kaytaForeignAid(Pankki pankki) {
        super.kaytaForeignAid(pankki); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void lisaaKorttiKorttipakkaan(Kortti kortti) {
        super.lisaaKorttiKorttipakkaan(kortti);
    }

    public void kaytaAssassinate(Pankki pankki, ArrayList<Osanottaja> osanottajajoukko) {
        Random r = new Random();
        osanottajajoukko.get(r.nextInt(osanottajajoukko.size())).paljastaKortti();
    }

    public void kaytaCoup(Pankki pankki, ArrayList<Osanottaja> osanottajajoukko) {
        Random r = new Random();
        osanottajajoukko.get(r.nextInt(osanottajajoukko.size())).paljastaKortti();
    }

    @Override
    public void kaytaSteal(Pankki pankki, Osanottaja osanottaja) {
        super.kaytaSteal(pankki, osanottaja); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void kaytaTaxes(Pankki pankki) {
        super.kaytaTaxes(pankki); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String naytaNakyvatKortit() {
        return super.naytaNakyvatKortit(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean haluaaBlokata(Osanottaja osanottaja, Kortti kortti) {
        return super.haluaaBlokata(osanottaja, kortti); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
