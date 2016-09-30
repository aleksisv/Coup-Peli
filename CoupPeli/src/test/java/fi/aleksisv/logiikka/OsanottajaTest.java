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
    public void paljastaKorttiToimii1() {
        Osanottaja o = new Osanottaja("Aino");
        o.lisaaKorttiKorttipakkaan(new Kortti("Assassin"));
        o.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        o.paljastaKortti();
        assertEquals(o.getKorttikasi().getKorttikasi().get(0).onkoPaljastettu(), true);
        assertEquals(o.getKorttikasi().getKorttikasi().get(1).onkoPaljastettu(), false);
        o.paljastaKortti();
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
    public void perustuloToimii1() {
        Osanottaja osanottaja1 = new Osanottaja("Aino");
        Pankki pankki = new Pankki();
        osanottaja1.kaytaPerustulo(pankki);
        assertEquals(osanottaja1.getRaha(), 3);
        assertEquals(pankki.getRahamaara(), 99);
    }

    @Test
    public void naytaNakyvatKortitToimii1() {
        Osanottaja osanottaja1 = new Osanottaja("Aino");
        osanottaja1.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        osanottaja1.lisaaKorttiKorttipakkaan(new Kortti("Assassin"));
        osanottaja1.paljastaKortti();
        assertEquals(osanottaja1.naytaNakyvatKortit(), "Contessa");
        osanottaja1.paljastaKortti();
        assertEquals(osanottaja1.naytaNakyvatKortit(), "Contessa, Assassin");
        osanottaja1.paljastaKortti();
        assertEquals(osanottaja1.naytaNakyvatKortit(), "Contessa, Assassin");
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

    @Test
    public void haluaaTorjuaToimii1() {
        Osanottaja osanottaja1 = new Osanottaja("Aino");
        osanottaja1.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        assertEquals(osanottaja1.haluaaTorjua(osanottaja1, new Kortti("Contessa")), true);
        assertEquals(osanottaja1.haluaaTorjua(osanottaja1, new Kortti("Assassin")), false);
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
    public void torjuToimii1() {
        Osanottaja o1 = new Osanottaja("Azra");
        Osanottaja o2 = new Osanottaja("Hmm");
        o1.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        assertEquals(o1.torju(o2, new Kortti("Assassin")), false);
        assertEquals(o1.torju(o2, new Kortti("Contessa")), true);
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
        o.annaRahaaPankille(2, pankki);
        assertEquals(o.getRaha(), 0);
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
    public void kaytaPerustuloToimii1() {
        Osanottaja o = new Osanottaja("Azra");
        Pankki pankki = new Pankki();
        o.kaytaPerustulo(pankki);
        assertEquals(o.getRaha(), 3);
        o.kaytaPerustulo(pankki);
        assertNotEquals(o.getRaha(), 3);
    }

    @Test
    public void kaytaUlkomaanapuToimii1() {
        Osanottaja o = new Osanottaja("Azra");
        Pankki pankki = new Pankki();
        o.kaytaUlkomaanapu(pankki);
        assertEquals(o.getRaha(), 4);
        o.kaytaUlkomaanapu(pankki);
        assertNotEquals(o.getRaha(), 4);
    }

    @Test
    public void kaytaAssassinoiToimii1() {
        Osanottaja o = new Osanottaja("Azra");
        o.saaRahaa(10);
        Pankki pankki = new Pankki();
        Osanottaja kohde = new Osanottaja("OiEi");
        kohde.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        assertEquals(kohde.montakoNakyvaaKorttia(), 0);
        o.kaytaAssassinoi(pankki, kohde);
        assertEquals(kohde.montakoNakyvaaKorttia(), 1);
        assertEquals(o.getRaha(), 9);
        assertEquals(pankki.getRahamaara(), 103);
    }

    @Test
    public void kaytaVallankaappausToimii1() {
        Osanottaja o = new Osanottaja("Azra");
        o.saaRahaa(10);
        Pankki pankki = new Pankki();
        Osanottaja kohde = new Osanottaja("OiEi");
        kohde.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        assertEquals(kohde.montakoNakyvaaKorttia(), 0);
        o.kaytaVallankaappaus(pankki, kohde);
        assertEquals(kohde.montakoNakyvaaKorttia(), 1);
        assertEquals(o.getRaha(), 5);
        assertEquals(pankki.getRahamaara(), 107);
    }

    @Test
    public void kaytaVerotusToimii1() {
        Osanottaja o = new Osanottaja("Azra");
        Pankki pankki = new Pankki();
        o.kaytaVerotus(pankki);
        assertEquals(o.getRaha(), 5);
        assertEquals(pankki.getRahamaara(), 97);
    }

    @Test
    public void montakoNakyvaaKorttiaToimii1() {
        Osanottaja o = new Osanottaja("Azra");
        o.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        o.lisaaKorttiKorttipakkaan(new Kortti("Duke"));
        assertEquals(o.montakoNakyvaaKorttia(), 0);
        o.paljastaKortti();
        assertEquals(o.montakoNakyvaaKorttia(), 1);
        o.paljastaKortti();
        assertEquals(o.montakoNakyvaaKorttia(), 2);
        o.paljastaKortti();
        assertEquals(o.montakoNakyvaaKorttia(), 2);
    }

    @Test
    public void kaytaVarastaToimii1() {
        Osanottaja o = new Osanottaja("Azra");
        Osanottaja kohde = new Osanottaja("Hmh");
        Pankki pankki = new Pankki();
        kohde.saaRahaa(1);
        o.kaytaVarasta(pankki, kohde);
        assertEquals(o.getRaha(), 4);
        assertEquals(kohde.getRaha(), 1);
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
