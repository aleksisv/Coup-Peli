
package main;

import java.util.ArrayList;

public class Korttikasi {
    private ArrayList<Kortti> kortit;

    public Korttikasi(ArrayList<Kortti> kortit) {
        this.kortit = kortit;
    }
    
    public Korttikasi() {
        this.kortit = new ArrayList<Kortti>();
    }

    public ArrayList<Kortti> getKortit() {
        return kortit;
    }
    
    public int koko() {
        return kortit.size();
    }
    
    public boolean sisaltyykoKortti(Kortti kortti){
        return kortit.contains(kortti);
    }
    
    
}
