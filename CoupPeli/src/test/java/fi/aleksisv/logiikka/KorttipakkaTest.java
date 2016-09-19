package fi.aleksisv.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KorttipakkaTest {

    public KorttipakkaTest() {
    }

    @Test
    public void alussaPakassaOikeaMaaraKortteja() {
        Korttipakka korttipakka = new Korttipakka();
        assertEquals(korttipakka.korttipakanKoko(), 15);
    }

    @Test
    public void onkoTamanNiminenKorttiPakassaToimiiOikein1() {
        Korttipakka korttipakka = new Korttipakka();
        boolean vastaus = korttipakka.onkoTamanNiminenKorttiPakassa("Contessa");
        assertEquals(vastaus, true);
    }

    @Test
    public void onkoTamanNiminenKorttiPakassaToimiiOikein2() {
        Korttipakka korttipakka = new Korttipakka();
        boolean vastaus = korttipakka.onkoTamanNiminenKorttiPakassa("ContessA");
        assertEquals(vastaus, false);
    }

    @Test
    public void korttienPoistaminenToimiiOikein1() {
        Korttipakka korttipakka = new Korttipakka();
        korttipakka.nostaPakastaTamanNiminenKortti("Contessa");
        korttipakka.nostaPakastaTamanNiminenKortti("Contessa");
        korttipakka.nostaPakastaTamanNiminenKortti("Contessa");
        boolean vastaus = korttipakka.onkoTamanNiminenKorttiPakassa("Contessa");
        assertEquals(vastaus, false);
    }
    
    @Test
    public void nostaminenPakastaToimii1() {
        Korttipakka korttipakka = new Korttipakka();
        Kortti contessa = korttipakka.nostaPakastaTamanNiminenKortti("Contessa");
        assertEquals(new Kortti("Contessa"), contessa);
    }
    
    @Test
    public void nostaminenPakastaToimii2() {
        Korttipakka korttipakka = new Korttipakka();
        Kortti kortti = korttipakka.nostaPakastaTamanNiminenKortti("Mikko");
        assertEquals(null, kortti);
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

}
