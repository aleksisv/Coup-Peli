package fi.aleksisv.logiikka;

/**
 * Luokka kuvaa pelin yhtä korttia.
 */
public class Kortti {
    
    /** Kortin tyyppi.*/
    private String tyyppi;
    
    /** Onko kortti paljastettu vai ei.*/
    private boolean onkoPaljastettu;

    /**
     * Luokan konstruktori.
     *
     * @param tyyppi Minkä tyyppinen kortti halutaan luoda.
     * @param onkoPaljastettu Onko luotava kortti aluksi paljastettu vai ei.
     */
    public Kortti(String tyyppi, boolean onkoPaljastettu) {
        this.tyyppi = tyyppi;
        this.onkoPaljastettu = onkoPaljastettu;
    }
    
    /**
     * Luokan konstruktori.
     * @param tyyppi Minkä tyyppinen kortti halutaan luoda.
     */
    public Kortti(String tyyppi) {
        this.tyyppi = tyyppi;
        this.onkoPaljastettu = false;
    }

    public String getTyyppi() {
        return tyyppi;
    }

    /**
     * Metodi tarkastaa, onko kortti paljastettu, eli pitääkö sen näkyä muille
     * pelaajille.
     *
     * @return Totuusarvo.
     */
    public boolean onkoPaljastettu() {
        return onkoPaljastettu;
    }

    /**
     * Metodi tarkastaa, ovatko kaksi korttia samat. Kortit ovat samat, jos
     * niillä on sama tyyppi.
     *
     * @param objekti Kortti, johon kutsuvaa korttia halutaan verrata.
     *
     *
     * @return Totuusarvo.
     */
    @Override
    public boolean equals(Object objekti) {
        if (objekti == null) {
            return false;
        }
        if (!(objekti instanceof Kortti)) {
            return false;
        }
        Kortti toinenKortti = (Kortti) objekti;
        if ((this.tyyppi == null) ? (toinenKortti.tyyppi != null) : !this.tyyppi.equals(toinenKortti.tyyppi)) {
            return false;
        }

        return true;
    }

    public void setOnkoPaljastettu(boolean onkoPaljastettu) {
        this.onkoPaljastettu = onkoPaljastettu;
    }

    @Override
    public String toString() {
        return this.tyyppi;
    }

}
