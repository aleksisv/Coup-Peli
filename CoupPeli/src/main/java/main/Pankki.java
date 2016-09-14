
package main;

public class Pankki {
    private int rahamaara;

    public Pankki() {
        this.rahamaara = 100;
    }

    public int getRahamaara() {
        return rahamaara;
    }

    public void setRahamaara(int rahamaara) {
        this.rahamaara = rahamaara;
    }
    
    public void vahennaRahaa(int maara) {
        this.setRahamaara(this.getRahamaara() - maara);
    }
    
    public void lisaaRahaa(int maara) {
        this.setRahamaara(this.getRahamaara() + maara);
    }
    
    public int annaRahat(int maara) {
        if(maara <= this.rahamaara) {
            this.vahennaRahaa(maara);
            return maara;
        } else {
            int palautettava = this.getRahamaara();
            this.pankkiNolliin();
            return palautettava;
        }
    }
    
    public void pankkiNolliin() {
        this.rahamaara = 0;
    }

    @Override
    public String toString() {
        return "Pankilla on rahaa " + this.rahamaara + "Coup-yksikköä.";
    }
    
    
    
    
}
