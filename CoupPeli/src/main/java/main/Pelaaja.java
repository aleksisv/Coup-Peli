
package main;

public class Pelaaja extends Osanottaja {
    private Korttikasi kasi;

    public Pelaaja(String nimi) {
        super(nimi);
    }

    @Override
    public String toString() {
        return super.getNimi();
    }
    
    
    
   
}
