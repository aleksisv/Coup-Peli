package fi.aleksisv.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KorttikasiTest {

    public KorttikasiTest() {
    }

    @Test
    public void luokoKorttikadenOikein1() {
        Korttikasi korttikasi = new Korttikasi();
        assertEquals(korttikasi.koko(), 0);
    }

    @Test
    public void toimiikoSatunnaistenKorttienLisaaminenOikein1() {
        Korttikasi korttikasi = new Korttikasi();
        Korttipakka pakka = new Korttipakka();
        korttikasi.lisaaKaksiSatunnaistaKorttia(pakka);
        assertEquals(korttikasi.koko(), 2);
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
