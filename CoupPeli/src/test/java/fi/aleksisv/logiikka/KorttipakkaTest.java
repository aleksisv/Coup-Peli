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
        assertEquals(korttipakka.korttipakanKoko(), 12);
    }

    @Test
    public void onkoTamanNiminenKorttiPakassaToimiiOikein1() {
        Korttipakka korttipakka = new Korttipakka();
        boolean vastaus = korttipakka.onkoTamanNiminenKorttiPakassa("Kreivitär");
        assertEquals(vastaus, true);
    }

    @Test
    public void onkoTamanNiminenKorttiPakassaToimiiOikein2() {
        Korttipakka korttipakka = new Korttipakka();
        boolean vastaus = korttipakka.onkoTamanNiminenKorttiPakassa("KreiviTÄR");
        assertEquals(vastaus, false);
    }

    @Test
    public void korttienPoistaminenToimiiOikein1() {
        Korttipakka korttipakka = new Korttipakka();
        korttipakka.nostaPakastaTamanNiminenKortti("Kreivitär");
        korttipakka.nostaPakastaTamanNiminenKortti("Kreivitär");
        korttipakka.nostaPakastaTamanNiminenKortti("Kreivitär");
        boolean vastaus = korttipakka.onkoTamanNiminenKorttiPakassa("Kreivitär");
        assertEquals(vastaus, false);
    }
    
    @Test
    public void korttienPoistaminenToimiiOikein2() {
        Korttipakka korttipakka = new Korttipakka();
        Kortti kortti1 = korttipakka.nostaPakastaTassaKohdassaOleva(0);
        korttipakka.poistaPakastaTassaKohdassaOleva(0);
        korttipakka.poistaPakastaTassaKohdassaOleva(0);
        korttipakka.poistaPakastaTassaKohdassaOleva(0);
        assertNotEquals(kortti1, korttipakka.nostaPakastaTassaKohdassaOleva(0));
    }

    @Test
    public void nostaminenPakastaToimii1() {
        Korttipakka korttipakka = new Korttipakka();
        Kortti kreivitar = korttipakka.nostaPakastaTamanNiminenKortti("Kreivitär");
        assertEquals(new Kortti("Kreivitär"), kreivitar);
    }

    @Test
    public void nostaminenPakastaToimii2() {
        Korttipakka korttipakka = new Korttipakka();
        Kortti kortti = korttipakka.nostaPakastaTamanNiminenKortti("Mikko");
        assertEquals(null, kortti);
    }

    @Test
    public void nostaPakastaTassaKohdassaToimii1() {
        Korttipakka korttipakka = new Korttipakka();
        assertEquals(new Kortti("Kreivitär"), korttipakka.nostaPakastaTassaKohdassaOleva(0));
        assertEquals(new Kortti("Salamurhaaja"), korttipakka.nostaPakastaTassaKohdassaOleva(4));
    }

    @Test
    public void sisaltyykoToimii1() {
        Korttipakka korttipakka = new Korttipakka();
        assertEquals(korttipakka.sisaltyyko(new Kortti("Herttua")), true);
        assertEquals(korttipakka.sisaltyyko(new Kortti("Jasmin")), false);
    }

    @Test
    public void nostaPakastaSatunnainenKorttiToimii1() {
        Korttipakka korttipakka = new Korttipakka();
        korttipakka.nostaPakastaSatunnainenKortti();
        assertEquals(korttipakka.korttipakanKoko(), 11);
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
