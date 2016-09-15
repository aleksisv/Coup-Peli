
package fi.aleksisv.logiikka;

import java.util.ArrayList;

public class Korttikasi {
    private ArrayList<Kortti> kortit;

    public Korttikasi(ArrayList<Kortti> kortit) {
        this.kortit = kortit;
    }
    
    public Korttikasi() {
        this.kortit = new ArrayList();
    }
    
    public int koko() {
        return kortit.size();
    }
    
    public boolean sisaltyykoKortti(Kortti kortti){
        for (Kortti kortit1 : kortit) {
            if (kortit1.equals(kortti)) {
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Kortti> getKorttikasi() {
        return this.kortit;
    }

    public void setKortit(ArrayList<Kortti> kortit) {
        this.kortit = kortit;
    }

    public void poistaPakastaTassaKohdassaOleva(int tapettavanKohta) {
        this.kortit.remove(tapettavanKohta);
    }
    
}
