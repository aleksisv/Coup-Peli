package fi.aleksisv.logiikka;

import java.util.Random;
import java.util.Scanner;

/**
 * Luokka kuvaa sellaista pelin Osanottaja-luokan oliota, jota ihminen käyttää
 * pelatessaan Coup-Peliä.
 */
public class Pelaaja extends Osanottaja {

    public Pelaaja(String nimi) {
        super(nimi);
    }

    @Override
    public Korttikasi getKorttikasi() {
        return super.getKorttikasi();
    }

    @Override
    public boolean epaile(Osanottaja osanottaja, Kortti mitaEiOle) {
        return super.epaile(osanottaja, mitaEiOle);
    }

    @Override
    public void setRaha(int maara) {
        super.setRaha(maara);
    }

    @Override
    public void menetaRahaa(int maara) {
        super.menetaRahaa(maara);
    }

    @Override
    public void saaRahaa(int maara) {
        super.saaRahaa(maara);
    }

    @Override
    public void annaRahaaPankille(int maara, Pankki pankki) {
        super.annaRahaaPankille(maara, pankki);
    }

    @Override
    public void otaRahaaPankilta(int maara, Pankki pankki) {
        super.otaRahaaPankilta(maara, pankki);
    }

    @Override
    public void paljastaKortti() {
        super.paljastaKortti();
    }

    @Override
    public void kaytaBasicIncome(Pankki pankki) {
        super.kaytaBasicIncome(pankki);
    }

    @Override
    public void kaytaForeignAid(Pankki pankki) {
        super.kaytaForeignAid(pankki);
    }

    @Override
    public void kaytaAssassinate(Pankki pankki, Osanottaja osanottaja) {
        super.kaytaAssassinate(pankki, osanottaja);
    }

    @Override
    public String toString() {
        return super.getNimi();
    }

}
