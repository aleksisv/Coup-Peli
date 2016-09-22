
package fi.aleksisv.logiikka;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aleksisvuoksenmaa
 */
public class VastustajaTest {
    
    public VastustajaTest() {
        
    }
    
    @Test
    public void luoVastustajanOikein1() {
        Vastustaja v = new Vastustaja("Aino");
        assertEquals(v.getNimi(), "Aino");
        assertEquals(v.getRaha(), 2);
        assertEquals(v.getKorttikasi().koko(), 0);
        Korttipakka korttipakka = new Korttipakka();
        v.nostaSatunnainenKorttiPakasta(korttipakka);
        assertEquals(v.getKorttikasi().koko(), 1);
    }
    
    @Test
    public void tapaKorttiToimii1() {
        Vastustaja v = new Vastustaja("Aino");
        v.lisaaKorttiKorttipakkaan(new Kortti("Assassin"));
        v.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        v.tapaKortti();
        assertEquals(v.getKorttikasi().getKorttikasi().get(0).onkoPaljastettu(), true);
        assertEquals(v.getKorttikasi().getKorttikasi().get(1).onkoPaljastettu(), false);
        v.tapaKortti();
        assertEquals(v.getKorttikasi().getKorttikasi().get(1).onkoPaljastettu(), true);
    }
    
    @Test
    public void epailyToimii1() {
        Vastustaja v1 = new Vastustaja("Aino");
        Vastustaja v2 = new Vastustaja("Anna");
        v1.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        v2.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        boolean epaily = v1.epaile(v2, new Kortti("Contessa"));
        assertEquals(epaily, false);
    }
    
    @Test
    public void epailyToimii2() {
        Vastustaja v1 = new Vastustaja("Aino");
        Vastustaja v2 = new Vastustaja("Anna");
        v1.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        v2.lisaaKorttiKorttipakkaan(new Kortti("Assassin"));
        boolean epaily = v1.epaile(v2, new Kortti("Contessa"));
        assertEquals(epaily, true);
    }
    
    @Test
    public void epailyToimii3() {
        Vastustaja v1 = new Vastustaja("Aino");
        Vastustaja v2 = new Vastustaja("Anna");
        v1.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        v2.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        boolean epaily = v1.epaile(v2, new Kortti("Contessa"));
        assertEquals(epaily, false);
    }
    
    @Test
    public void epailyToimii4() {
        Vastustaja v1 = new Vastustaja("Aino");
        Vastustaja v2 = new Vastustaja("Anna");
        v1.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        v2.lisaaKorttiKorttipakkaan(new Kortti("Assassin"));
        boolean epaily = v1.epaile(v2, new Kortti("Contessa"));
        assertEquals(epaily, true);
    }
    
    @Test
    public void basicIncomeToimii1() {
        Vastustaja v1 = new Vastustaja("Aino");
        Pankki pankki = new Pankki();
        v1.kaytaBasicIncome(pankki);
        assertEquals(v1.getRaha(), 3);
        assertEquals(pankki.getRahamaara(), 99);
    }
  
    @Test
    public void naytaNakyvatKortitToimii1() {
        Vastustaja v1 = new Vastustaja("Aino");
        v1.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        v1.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        v1.tapaKortti();
        v1.tapaKortti();
        assertEquals(v1.naytaNakyvatKortit(), "Contessa, Contessa");
    }
    
    @Test
    public void saaRahaaToimii1() {
        Vastustaja v1 = new Vastustaja("Aino");
        v1.saaRahaa(2);
        assertEquals(v1.getRaha(), 4);
    }
    
    @Test
    public void haluaaEpaillaToimii1() {
        Vastustaja v1 = new Vastustaja("Aino");
        v1.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        assertEquals(v1.haluaaEpailla(v1, new Kortti("Contessa")), true);
        assertEquals(v1.haluaaEpailla(v1, new Kortti("Assassin")), false);
    }
    
    @Test public void haluaaBlokataToimii1() {
        Vastustaja v1 = new Vastustaja("Aino");
        v1.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        assertEquals(v1.haluaaBlokata(v1, new Kortti("Contessa")), true);
        assertEquals(v1.haluaaBlokata(v1, new Kortti("Assassin")), false);
    }
    
    @Test
    public void menetaRahaaToimii1() {
        Vastustaja v1 = new Vastustaja("Aino");
        v1.menetaRahaa(2);
        assertEquals(v1.getRaha(), 0);
        v1.menetaRahaa(1);
        assertEquals(v1.getRaha(), 0);
        v1.saaRahaa(5);
        v1.menetaRahaa(6);
        assertEquals(v1.getRaha(), 0);
    }
    
    @Test
    public void blokkaaToimii1() {
        Vastustaja v1 = new Vastustaja("Azra");
        Vastustaja v2 = new Vastustaja("Hmm");
        v2.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        assertEquals(v1.blokkaa(v2, new Kortti("Assassin")), true);
        assertEquals(v1.blokkaa(v2, new Kortti("Contessa")), false);
    }
    
    @Test
    public void toStringToimii1() {
        Vastustaja v = new Vastustaja("Azra");
        assertEquals(v.toString(), "Azra");
    }
    
    @Test
    public void annaRahaaPankilleToimii1() {
        Osanottaja o = new Osanottaja("Azra");
        Pankki pankki = new Pankki();
        int pankinAlkurahat = pankki.getRahamaara();
        o.annaRahaaPankille(1, pankki);
        assertEquals(o.getRaha(), 1);
        assertEquals(pankki.getRahamaara(), 1 + pankinAlkurahat);
    }
    
    @Test
    public void otaRahaaPankiltaToimii1() {
        Osanottaja o = new Osanottaja("Azra");
        Pankki pankki = new Pankki();
        assertEquals(o.getRaha(), 2);
        o.otaRahaaPankilta(4, pankki);
        assertEquals(o.getRaha(), 6);
    }
    
    @Test
    public void kaytaBasicIncomeToimii1() {
        Osanottaja o = new Osanottaja("Azra");
        Pankki pankki = new Pankki();
        o.kaytaBasicIncome(pankki);
        assertEquals(o.getRaha(), 3);
        o.kaytaBasicIncome(pankki);
        assertNotEquals(o.getRaha(), 3);
    }
    
    @Test
    public void kaytaForeignAidToimii1() {
        Osanottaja o = new Osanottaja("Azra");
        Pankki pankki = new Pankki();
        o.kaytaForeignAid(pankki);
        assertEquals(o.getRaha(), 4);
        o.kaytaForeignAid(pankki);
        assertNotEquals(o.getRaha(), 4);
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
