package fi.aleksisv.logiikka;

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
    public void epaile(Pelaaja pelaaja, Kortti mitaEiOle) {
        super.epaile(pelaaja, mitaEiOle);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public Korttikasi getKasi() {
        return kasi;
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
    public void lisaaKorttiKorttipakkaan(Kortti kortti) {
        super.lisaaKorttiKorttipakkaan(kortti);
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
    public void tapaKortti() {
        super.tapaKortti();
    }
    
    

    @Override
    public String toString() {
        return super.toString();
    }

}
