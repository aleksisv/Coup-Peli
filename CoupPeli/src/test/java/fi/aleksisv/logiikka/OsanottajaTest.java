package fi.aleksisv.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class OsanottajaTest {

    public OsanottajaTest() {

    }

    @Test
    public void luoOsanottajanOikein1() {
        Osanottaja osanottaja = new Osanottaja("Aino");
        assertEquals(osanottaja.getNimi(), "Aino");
    }

    @Test
    public void luoOsanottajanOikein2() {
        Osanottaja osanottaja = new Osanottaja("Aino");
        assertEquals(osanottaja.getRaha(), 2);
    }

    @Test
    public void luoOsanottajanOikein3() {
        Osanottaja osanottaja = new Osanottaja("Aino");
        assertEquals(osanottaja.getKorttikasi().koko(), 0);
    }

    @Test
    public void pakastaNostoToimii1() {
        Osanottaja osanottaja = new Osanottaja("Aino");
        Korttipakka korttipakka = new Korttipakka();
        osanottaja.nostaSatunnainenKorttiPakasta(korttipakka);
        assertEquals(osanottaja.getKorttikasi().koko(), 1);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
