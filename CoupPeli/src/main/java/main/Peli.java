
package main;

import java.util.ArrayList;
import java.util.Set;

public class Peli extends AbstraktiPeli {
    
    private int osanottajamaara;
    private ArrayList<Osanottaja> osanottajajoukko;

    public Peli(int pelaajamaara) {
        this.osanottajamaara = pelaajamaara;
        this.osanottajajoukko = new ArrayList<Osanottaja>();
        luoOsanottajat();
        
    }

    public int getPelaajamaara() {
        return osanottajamaara;
    }
    
    private void luoOsanottajat(){
        osanottajajoukko.add(new Pelaaja("X"));
        for (int i = 0; i < osanottajamaara - 1; i++) {
            osanottajajoukko.add(new Vastustaja("i"));
        }
    }
    
    
    
}
