
package fi.aleksisv.logiikka;

import java.util.Random;

public class Pelaaja extends Osanottaja {

    public Pelaaja(String nimi) {
        super(nimi);
    }
    
    public Korttikasi getKorttikasi(){
        return super.getKasi();
    }
    
    @Override
    public void setRaha(int maara){
       super.setRaha(maara);
    }
    
    public void menetaRahaa(int maara){
        this.setRaha(this.getRaha() - maara);
    }
    
    @Override
    public void saaRahaa(int maara){
        this.setRaha(this.getRaha() + maara);
    }
    
    public void annaRahaaPankille(int maara, Pankki pankki){
        if(this.getRaha() >= maara) {
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
    
    public void tapaKortti() {
        Korttikasi korttikasi = this.getKorttikasi();
        Random r = new Random();
        
        int tapettavanKohta = r.nextInt(korttikasi.koko());
        
        korttikasi.poistaPakastaTassaKohdassaOleva(tapettavanKohta);
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
