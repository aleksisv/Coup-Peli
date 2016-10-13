package fi.aleksisv.logiikka;

import java.util.ArrayList;
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
    public void konstruktoriToimiiOikein1() {
        ArrayList<Kortti> plaa = new ArrayList();
        Korttikasi kasi = new Korttikasi(plaa);
        assertEquals(kasi.getKorttikasi(), plaa);
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
    
    @Test
    public void lisaaKorttikateenToimii1() {
        Korttikasi korttikasi = new Korttikasi();
        Korttipakka pakka = new Korttipakka();
        assertEquals(korttikasi.koko(), 0);
        korttikasi.lisaaKorttikateen(pakka.getPakka().get(0));
        assertEquals(korttikasi.koko(), 1);
        korttikasi.lisaaKorttikateen(pakka.getPakka().get(0));
        assertEquals(korttikasi.koko(), 2);
        korttikasi.lisaaKorttikateen(pakka.getPakka().get(0));
        assertEquals(korttikasi.koko(), 2);
    }
    
    @Test
    public void setKortitToimii1() {
        Korttikasi k = new Korttikasi();
        assertEquals(k.koko(), 0);
        Kortti k1 = new Kortti("A");
        Kortti k2 = new Kortti("B");
        
        ArrayList<Kortti> kortit = new ArrayList();
        k.setKortit(kortit);
        kortit.add(k1);
        kortit.add(k2);
        assertEquals(k.koko(), 2);
        
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
