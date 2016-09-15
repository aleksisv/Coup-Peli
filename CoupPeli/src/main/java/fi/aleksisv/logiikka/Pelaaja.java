package fi.aleksisv.logiikka;

import java.util.Random;

public class Pelaaja extends Osanottaja {

    public Pelaaja(String nimi) {
        super(nimi);
    }

    public Korttikasi getKorttikasi() {
        return super.getKorttikasi();
    }

    @Override
    public void setRaha(int maara) {
        super.setRaha(maara);
    }

    public void menetaRahaa(int maara) {
        super.menetaRahaa(maara);
    }

    @Override
    public void saaRahaa(int maara) {
        this.setRaha(this.getRaha() + maara);
    }

    public void annaRahaaPankille(int maara, Pankki pankki) {
        super.annaRahaaPankille(maara, pankki);
    }

    public void otaRahaaPankilta(int maara, Pankki pankki) {
        this.setRaha(pankki.annaRahat(maara));
    }

    public void tapaKortti() {
        super.tapaKortti();
    }

    public void kaytaBasicIncome(Pankki pankki) {
        this.saaRahaa(pankki.annaRahat(1));
    }

    public void kaytaForeingAid(Pankki pankki) {
        this.saaRahaa(pankki.annaRahat(2));
    }

    public void kaytaAssassinate(Pankki pankki) {
        this.menetaRahaa(pankki.annaRahat(2));
    }

    @Override
    public String toString() {
        return super.getNimi();
    }

}
