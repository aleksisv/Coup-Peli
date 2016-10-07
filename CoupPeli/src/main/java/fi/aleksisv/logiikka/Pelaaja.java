package fi.aleksisv.logiikka;

/**
 * Luokka kuvaa sellaista pelin Osanottaja-luokan oliota, jota ihminen käyttää
 * pelatessaan Coup-Peliä.
 */
public class Pelaaja extends Osanottaja {
    
    /**
     * Luokan konstruktori.
     * 
     * @param nimi Pelaajan nimi.
     */
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
    public void kaytaPerustulo(Pankki pankki) {
        super.kaytaPerustulo(pankki);
    }

    @Override
    public void kaytaUlkomaanapu(Pankki pankki) {
        super.kaytaUlkomaanapu(pankki);
    }

    @Override
    public void kaytaAssassinoi(Pankki pankki, Osanottaja osanottaja) {
        super.kaytaAssassinoi(pankki, osanottaja);
    }

    @Override
    public String toString() {
        return super.getNimi();
    }

}
