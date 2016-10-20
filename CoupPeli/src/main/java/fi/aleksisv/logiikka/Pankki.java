package fi.aleksisv.logiikka;

/**
 * Luokka kuvaa pelin rahavarastoa.
 */
public class Pankki {
    
    /** Pankin rahamäärä.*/
    private int rahamaara;
    
    /**
     * Luokan konstruktori.
     */
    public Pankki() {
        this.rahamaara = 100;
    }

    public int getRahamaara() {
        return rahamaara;
    }
    
    public void setRahamaara(int rahamaara) {
        this.rahamaara = rahamaara;
    }
    
    /**
     * Metodin avulla pankin kassasta vähennetään rahaa.
     * 
     * @param maara Rahamäärä, joka pankista vähennetään.
     */
    public void vahennaRahaa(int maara) {
        this.setRahamaara(this.getRahamaara() - maara);
    }
    
    /**
     * Metodin avulla pankkin kassaan lisätään rahaa.
     * 
     * @param maara Rahamäärä, joka pankkiin lisätään.
     */
    public void lisaaRahaa(int maara) {
        this.setRahamaara(this.getRahamaara() + maara);
    }
    
    /**
     * Metodin avulla pankki antaa rahaa.
     * 
     * @param maara Rahamäärä, jonka pankki antaa.
     * 
     * @return Annettavat rahat.
     */
    public int annaRahat(int maara) {
        if (maara <= this.rahamaara) {
            this.vahennaRahaa(maara);
            return maara;
        } else {
            int palautettava = this.getRahamaara();
            this.pankkiNolliin();
            return palautettava;
        }
    }
    
    /**
     * Metodin asettaa pankin rahamäärän nolliin.
     * 
     */
    public void pankkiNolliin() {
        this.rahamaara = 0;
    }

    @Override
    public String toString() {
        return "Pankilla on rahaa " + this.rahamaara + " yksikköä.";
    }

}
