package fi.aleksisv.logiikka;

public class Kortti {

    private String tyyppi;
    private boolean onkoPaljastettu;

    public Kortti(String tyyppi, boolean onkoPaljastettu) {
        this.tyyppi = tyyppi;
        this.onkoPaljastettu = onkoPaljastettu;
    }

    public Kortti(String tyyppi) {
        this.tyyppi = tyyppi;
        this.onkoPaljastettu = false;
    }

    public String getTyyppi() {
        return tyyppi;
    }

    public boolean onkoPaljastettu() {
        return onkoPaljastettu;
    }

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