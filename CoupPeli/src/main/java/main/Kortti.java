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

}
