package fi.aleksisv.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PeliTest {

    private int vakio1;
    private int vakio2;

    public PeliTest() {
        this.vakio1 = 3;
        this.vakio2 = 4;
    }

    @BeforeClass
    public static void setUpClass() {
        int vakio1 = 100;
    }

    @Test
    public void pelaajaMaaratOikein1() {
        Peli testipeli = new Peli(1);
        assertEquals(testipeli.getOsanottajamaara(), 1);
    }

    @Test
    public void pelaajaMaaratOikein2() {
        Peli testipeli = new Peli(4);
        assertEquals(testipeli.getOsanottajamaara(), 4);
    }

    @Test
    public void pelaajaMaaratOikein3() {
        Peli testipeli = new Peli(vakio1);
        assertEquals(testipeli.getOsanottajamaara(), 3);
    }

    @Test
    public void pudotaVastustajaOikein() {
        Peli testipeli = new Peli(3);
        Vastustaja vastustaja = new Vastustaja("Jaa");
        testipeli.lisaaVastustaja(vastustaja);
        testipeli.pudotaVastustaja(vastustaja);
        assertEquals(false, testipeli.getOsanottajamaara() == 3);
//        testipeli.pudotaPelaaja(pelaaja);

    }

    public void lisaaVastustajaOikein() {
        Peli testipeli = new Peli(3);
        Vastustaja vastustaja = new Vastustaja("Vastustaja");
        testipeli.lisaaVastustaja(vastustaja);
        assertEquals(true, testipeli.getOsanottajamaara() == 4);
    }

}
