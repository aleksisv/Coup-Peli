
package main;

public class Kortti {
    private int id;
    private String tyyppi;
    private boolean paljastettu;

    public Kortti(int id, String tyyppi, boolean paljastettu) {
        this.id = id;
        this.tyyppi = tyyppi;
        this.paljastettu = paljastettu;
    }
    
    public Kortti(int id, String tyyppi) {
        this.id = id;
        this.tyyppi = tyyppi;
        this.paljastettu = false;
    }
    
    
    
    
}
