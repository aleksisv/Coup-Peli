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
    
    @Test
    public void epailyToimii1() {
        Osanottaja osanottaja1 = new Osanottaja("Aino");
        Osanottaja osanottaja2 = new Osanottaja("Anna");
        osanottaja1.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        osanottaja2.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        boolean epaily = osanottaja1.epaile(osanottaja2, new Kortti("Contessa"));
        assertEquals(epaily, false);
    }
    
    @Test
    public void epailyToimii2() {
        Osanottaja osanottaja1 = new Osanottaja("Aino");
        Osanottaja osanottaja2 = new Osanottaja("Anna");
        osanottaja1.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        osanottaja2.lisaaKorttiKorttipakkaan(new Kortti("Assassin"));
        boolean epaily = osanottaja1.epaile(osanottaja2, new Kortti("Contessa"));
        assertEquals(epaily, true);
    }
    
    @Test
    public void epailyToimii3() {
        Osanottaja osanottaja1 = new Osanottaja("Aino");
        Osanottaja osanottaja2 = new Osanottaja("Anna");
        osanottaja1.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        osanottaja2.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        boolean epaily = osanottaja1.epaile(osanottaja2, new Kortti("Contessa"));
        assertEquals(epaily, false);
    }
    
    @Test
    public void epailyToimii4() {
        Osanottaja osanottaja1 = new Osanottaja("Aino");
        Osanottaja osanottaja2 = new Osanottaja("Anna");
        osanottaja1.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        osanottaja2.lisaaKorttiKorttipakkaan(new Kortti("Assassin"));
        boolean epaily = osanottaja1.epaile(osanottaja2, new Kortti("Contessa"));
        assertEquals(epaily, true);
    }
    
    @Test
    public void basicIncomeToimii1() {
        Osanottaja osanottaja1 = new Osanottaja("Aino");
        Pankki pankki = new Pankki();
        osanottaja1.kaytaBasicIncome(pankki);
        assertEquals(osanottaja1.getRaha(), 3);
    }
    
    @Test
    public void basicIncomeToimii2() {
        Osanottaja osanottaja1 = new Osanottaja("Aino");
        Pankki pankki = new Pankki();
        osanottaja1.kaytaBasicIncome(pankki);
        assertEquals(pankki.getRahamaara(), 99);
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
