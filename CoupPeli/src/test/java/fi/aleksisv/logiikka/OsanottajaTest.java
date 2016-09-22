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
        assertEquals(osanottaja.getRaha(), 2);
        assertEquals(osanottaja.getKorttikasi().koko(), 0);
        Korttipakka korttipakka = new Korttipakka();
        osanottaja.nostaSatunnainenKorttiPakasta(korttipakka);
        assertEquals(osanottaja.getKorttikasi().koko(), 1);
    }
    
    @Test
    public void tapaKorttiToimii1() {
        Osanottaja o = new Osanottaja("Aino");
        o.lisaaKorttiKorttipakkaan(new Kortti("Assassin"));
        o.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        o.tapaKortti();
        assertEquals(o.getKorttikasi().getKorttikasi().get(0).onkoPaljastettu(), true);
        assertEquals(o.getKorttikasi().getKorttikasi().get(1).onkoPaljastettu(), false);
        o.tapaKortti();
        assertEquals(o.getKorttikasi().getKorttikasi().get(1).onkoPaljastettu(), true);
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
        assertEquals(pankki.getRahamaara(), 99);
    }
  
    @Test
    public void naytaNakyvatKortitToimii1() {
        Osanottaja osanottaja1 = new Osanottaja("Aino");
        osanottaja1.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        osanottaja1.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        osanottaja1.tapaKortti();
        osanottaja1.tapaKortti();
        assertEquals(osanottaja1.naytaNakyvatKortit(), "Contessa, Contessa");
    }
    
    @Test
    public void saaRahaaToimii1() {
        Osanottaja osanottaja1 = new Osanottaja("Aino");
        osanottaja1.saaRahaa(2);
        assertEquals(osanottaja1.getRaha(), 4);
    }
    
    @Test
    public void haluaaEpaillaToimii1() {
        Osanottaja osanottaja1 = new Osanottaja("Aino");
        osanottaja1.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        assertEquals(osanottaja1.haluaaEpailla(osanottaja1, new Kortti("Contessa")), true);
        assertEquals(osanottaja1.haluaaEpailla(osanottaja1, new Kortti("Assassin")), false);
    }
    
    @Test public void haluaaBlokataToimii1() {
        Osanottaja osanottaja1 = new Osanottaja("Aino");
        osanottaja1.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        assertEquals(osanottaja1.haluaaBlokata(osanottaja1, new Kortti("Contessa")), true);
        assertEquals(osanottaja1.haluaaBlokata(osanottaja1, new Kortti("Assassin")), false);
    }
    
    @Test
    public void menetaRahaaToimii1() {
        Osanottaja osanottaja1 = new Osanottaja("Aino");
        osanottaja1.menetaRahaa(2);
        assertEquals(osanottaja1.getRaha(), 0);
        osanottaja1.menetaRahaa(1);
        assertEquals(osanottaja1.getRaha(), 0);
        osanottaja1.saaRahaa(5);
        osanottaja1.menetaRahaa(6);
        assertEquals(osanottaja1.getRaha(), 0);
    }
    
    @Test
    public void blokkaaToimii1() {
        Osanottaja o1 = new Osanottaja("Azra");
        Osanottaja o2 = new Osanottaja("Hmm");
        o2.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        assertEquals(o1.blokkaa(o2, new Kortti("Assassin")), true);
        assertEquals(o1.blokkaa(o2, new Kortti("Contessa")), false);
    }
    
    @Test
    public void toStringToimii1() {
        Osanottaja o = new Osanottaja("Azra");
        assertEquals(o.toString(), "Azra");
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

}
