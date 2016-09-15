
package main;

import java.util.ArrayList;

public class Korttikasi {
    private Korttipakka korttipakka;

    public Korttikasi(Korttipakka kortit) {
        this.korttipakka = kortit;
    }
    
    public Korttikasi() {
        this.korttipakka = new Korttipakka();
    }

    public Korttipakka getKorttipakka() {
        return this.korttipakka;
    }
    
    public int koko() {
        return korttipakka.getPakka().size();
    }
    
    public boolean sisaltyykoKortti(Kortti kortti){
        return korttipakka.sisaltyyko(kortti);
    }
    
    
}
