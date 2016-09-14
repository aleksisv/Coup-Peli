
package main;

import java.util.ArrayList;
import java.util.Random;

public class Pelaaja extends Osanottaja {

    public Pelaaja(String nimi) {
        super(nimi);
    }
    
    public Korttikasi getKorttikasi(){
        return super.getKasi();
    }
    
    public void setRaha(int maara){
       super.setRaha(maara);
    }
    
    public void tapaKortti() {
        ArrayList<Kortti> kortit = this.getKorttikasi().getKortit();
        Random r = new Random();
        
        kortit.remove(r.nextInt(kortit.size()));
        
        
    }
    

    @Override
    public String toString() {
        return super.getNimi();
    }
    
    
    
    
   
}
