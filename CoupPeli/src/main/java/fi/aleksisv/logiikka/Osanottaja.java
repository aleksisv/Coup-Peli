package fi.aleksisv.logiikka;

import java.util.ArrayList;
import java.util.Random;

public class Osanottaja {

    private String nimi;
    private int raha;
    private Korttikasi korttikasi;

    public Osanottaja(String nimi) {
        this.nimi = nimi;
        this.raha = 2;
        this.korttikasi = new Korttikasi();
    }

    public Korttikasi getKorttikasi() {
        return this.korttikasi;
    }
    
    //Surkea tekoäly.
    public boolean haluaaEpailla(Osanottaja osanottaja, Kortti mitaEiOle) {
        if (this.korttikasi.sisaltyykoKortti(mitaEiOle)) {
            return true;
        }
        return false;
    }

    public boolean epaile(Osanottaja osanottaja, Kortti mitaEiOle) {
        if (!osanottaja.getKorttikasi().sisaltyykoKortti(mitaEiOle)) {
            osanottaja.tapaKortti();
            return true;
        } else {
            this.tapaKortti();
            return false;
        }
    }
    
    public String naytaNakyvatKortit() {
        StringBuilder merkkijono = new StringBuilder();
        for (Kortti kortti : this.korttikasi.getKorttikasi()) {
            if(kortti.onkoPaljastettu()) {
                merkkijono.append(kortti.toString() + ", ");
            }
        }
        String palautettava = merkkijono.toString();
        return palautettava.replaceAll(", $", "");
    }

    public String getNimi() {
        return nimi;
    }

    public void setRaha(int raha) {
        this.raha = raha;
    }

    public void setKasi(Korttikasi kasi) {
        this.korttikasi = kasi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public int getRaha() {
        return raha;
    }

    public void tapaKortti() {
        Korttikasi korttikasi = this.getKorttikasi();
        Random r = new Random();
        int tapettavanKohta = r.nextInt(korttikasi.koko());
        System.out.println(tapettavanKohta);
        korttikasi.paljastaPakastaTassaKohdassaOleva(tapettavanKohta);
    }

    public void saaRahaa(int maara) {
        this.setRaha(this.getRaha() + maara);
    }

    public void nostaSatunnainenKorttiPakasta(Korttipakka pakka) {
        Random r = new Random();
        int i = r.nextInt(pakka.korttipakanKoko());
        Kortti apu = pakka.nostaPakastaTassaKohdassaOleva(i);
        this.lisaaKorttiKorttipakkaan(apu);
    }

    public void lisaaKorttiKorttipakkaan(Kortti kortti) {
        this.getKorttikasi().lisaaKorttikateen(kortti);
    }
    
    
    public boolean haluaaBlokata(Osanottaja osanottaja, Kortti kortti) {
        if (this.korttikasi.sisaltyykoKortti(kortti)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean blokkaa(Osanottaja osanottaja, Kortti kortti) {
        if (osanottaja.korttikasi.sisaltyykoKortti(kortti)) {
            return false;
        } return true;
    }

    public void menetaRahaa(int maara) {
        this.setRaha(this.getRaha() - maara);
    }

    public void annaRahaaPankille(int maara, Pankki pankki) {
        if (this.getRaha() >= maara) {
            pankki.lisaaRahaa(maara);
            this.menetaRahaa(maara);
        } else {
            pankki.lisaaRahaa(this.getRaha());
            this.menetaRahaa(this.getRaha());
        }
    }

    public void otaRahaaPankilta(int maara, Pankki pankki) {
        this.setRaha(pankki.annaRahat(maara));
    }

    public void kaytaBasicIncome(Pankki pankki) {
        this.saaRahaa(pankki.annaRahat(1));
    }
    
    public void kaytaAssassinate(Pankki pankki, Osanottaja osanottaja) {
        this.menetaRahaa(3);
        pankki.lisaaRahaa(3);
        osanottaja.tapaKortti();
    }
    
    public void kaytaCoup(Pankki pankki, Osanottaja osanottaja) {
        this.menetaRahaa(7);
        pankki.lisaaRahaa(7);
        osanottaja.tapaKortti();
    }
    
    public void kaytaTaxes(Pankki pankki) {
        this.saaRahaa(pankki.annaRahat(3));
    }

    @Override
    public String toString() {
        return this.nimi;
    }

}
