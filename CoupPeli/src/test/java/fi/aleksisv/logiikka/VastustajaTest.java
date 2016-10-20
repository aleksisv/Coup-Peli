package fi.aleksisv.logiikka;

import java.util.Random;
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
        v.paljastaKortti();
        assertEquals(v.getKorttikasi().getKorttikasi().get(0).onkoPaljastettu(), true);
        assertEquals(v.getKorttikasi().getKorttikasi().get(1).onkoPaljastettu(), false);
        v.paljastaKortti();
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
    public void perustuloToimii1() {
        Vastustaja v1 = new Vastustaja("Aino");
        Pankki pankki = new Pankki();
        v1.kaytaPerustulo(pankki);
        assertEquals(v1.getRaha(), 3);
        assertEquals(pankki.getRahamaara(), 99);
    }

    @Test
    public void naytaNakyvatKortitToimii1() {
        Vastustaja v1 = new Vastustaja("Aino");
        v1.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        v1.lisaaKorttiKorttipakkaan(new Kortti("Assassin"));
        v1.paljastaKortti();
        assertEquals(v1.naytaNakyvatKortit(), "Contessa");
        v1.paljastaKortti();
        assertEquals(v1.naytaNakyvatKortit(), "Contessa, Assassin");
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

    @Test
    public void haluaaTorjuaToimii1() {
        Vastustaja v1 = new Vastustaja("Aino");
        v1.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        assertEquals(v1.haluaaTorjua(v1, new Kortti("Contessa")), true);
        assertEquals(v1.haluaaTorjua(v1, new Kortti("Assassin")), false);
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
    public void torjuToimii1() {
        Vastustaja v1 = new Vastustaja("Azra");
        Vastustaja v2 = new Vastustaja("Hmm");
        v1.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        assertEquals(v1.torju(v2, new Kortti("Assassin")), false);
        assertEquals(v1.torju(v2, new Kortti("Contessa")), true);
    }

    @Test
    public void toStringToimii1() {
        Vastustaja v = new Vastustaja("Azra");
        assertEquals(v.toString(), "Nimi: " + "Azra" + "\nRahaa: " + v.getRaha()
                + "\nNäkyvät kortit: " + v.naytaNakyvatKortit() 
                + "\nStatus: " + " pelissä.");
    }
    
   


    @Test
    public void annaRahaaPankilleToimii1() {
        Vastustaja v = new Vastustaja("Azra");
        Pankki pankki = new Pankki();
        int pankinAlkurahat = pankki.getRahamaara();
        v.annaRahaaPankille(1, pankki);
        assertEquals(v.getRaha(), 1);
        assertEquals(pankki.getRahamaara(), 1 + pankinAlkurahat);
    }

    @Test
    public void otaRahaaPankiltaToimii1() {
        Vastustaja v = new Vastustaja("Azra");
        Pankki pankki = new Pankki();
        assertEquals(v.getRaha(), 2);
        v.otaRahaaPankilta(4, pankki);
        assertEquals(v.getRaha(), 6);
    }

    @Test
    public void kaytaPerustuloToimii1() {
        Vastustaja v = new Vastustaja("Azra");
        Pankki pankki = new Pankki();
        v.kaytaPerustulo(pankki);
        assertEquals(v.getRaha(), 3);
        v.kaytaPerustulo(pankki);
        assertNotEquals(v.getRaha(), 3);
    }

    @Test
    public void kaytaUlkomaanapuToimii1() {
        Vastustaja v = new Vastustaja("Azra");
        Pankki pankki = new Pankki();
        v.kaytaUlkomaanapu(pankki);
        assertEquals(v.getRaha(), 4);
        v.kaytaUlkomaanapu(pankki);
        assertNotEquals(v.getRaha(), 4);
    }

    @Test
    public void kaytaAssassinoiToimii1() {
        Vastustaja v = new Vastustaja("Azra");
        v.saaRahaa(10);
        Pankki pankki = new Pankki();
        Vastustaja kohde = new Vastustaja("OiEi");
        kohde.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        assertEquals(kohde.montakoNakyvaaKorttia(), 0);
        v.kaytaAssassinoi(pankki, kohde);
        assertEquals(kohde.montakoNakyvaaKorttia(), 1);
        assertEquals(v.getRaha(), 9);
        assertEquals(pankki.getRahamaara(), 103);
    }

    @Test
    public void kaytaVallankaappausToimii1() {
        Vastustaja v = new Vastustaja("Azra");
        v.saaRahaa(10);
        Pankki pankki = new Pankki();
        Vastustaja kohde = new Vastustaja("OiEi");
        kohde.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        assertEquals(kohde.montakoNakyvaaKorttia(), 0);
        v.kaytaVallankaappaus(pankki, kohde);
        assertEquals(kohde.montakoNakyvaaKorttia(), 1);
        assertEquals(v.getRaha(), 5);
        assertEquals(pankki.getRahamaara(), 107);
    }

    @Test
    public void kaytaVerotusToimii1() {
        Vastustaja v = new Vastustaja("Azra");
        Pankki pankki = new Pankki();
        v.kaytaVerotus(pankki);
        assertEquals(v.getRaha(), 5);
        assertEquals(pankki.getRahamaara(), 97);
    }

    @Test
    public void montakoNakyvaaKorttiaToimii1() {
        Vastustaja v = new Vastustaja("Azra");
        v.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        v.lisaaKorttiKorttipakkaan(new Kortti("Duke"));
        assertEquals(v.montakoNakyvaaKorttia(), 0);
        v.paljastaKortti();
        assertEquals(v.montakoNakyvaaKorttia(), 1);
        v.paljastaKortti();
        assertEquals(v.montakoNakyvaaKorttia(), 2);
        v.paljastaKortti();
        assertEquals(v.montakoNakyvaaKorttia(), 2);
    }

    @Test
    public void kaytaVarastaToimii1() {
        Vastustaja v = new Vastustaja("Azra");
        Osanottaja kohde = new Osanottaja("Hmh");
        Pankki pankki = new Pankki();
        kohde.saaRahaa(1);
        v.kaytaVarasta(pankki, kohde);
        assertEquals(v.getRaha(), 4);
        assertEquals(kohde.getRaha(), 1);
    }
    
    @Test
    public void kaytaAssassinateToimii1() {
        Vastustaja v = new Vastustaja("Azra");
        Osanottaja kohde = new Osanottaja("Voi harmi!");
        kohde.lisaaKorttiKorttipakkaan(new Kortti("Herttua"));
        Pankki pankki = new Pankki();
        v.saaRahaa(7);
        assertEquals(kohde.montakoNakyvaaKorttia(), 0);
        assertEquals(v.getRaha(), 9);
        v.kaytaAssassinoi(pankki, kohde);
        assertEquals(kohde.montakoNakyvaaKorttia(), 1);
        assertEquals(v.getRaha(), 6);
    }
    
    @Test
    public void valitseSiirtoToimii1() {
        Random r = new Random();
        Vastustaja v = new Vastustaja("Azra");
        int siirtoNumero = v.valitseSiirto(r);
        boolean kuuluukoValille = 1<=siirtoNumero && siirtoNumero <=6;
        assertEquals(true, kuuluukoValille);
        v.saaRahaa(5);
        siirtoNumero = v.valitseSiirto(new Random());
        assertEquals(siirtoNumero, 3);
        v.menetaRahaa(4);
        v.lisaaKorttiKorttipakkaan(new Kortti("Salamurhaaja"));
        siirtoNumero = v.valitseSiirto(r);
        assertEquals(siirtoNumero, 5);
        v.menetaRahaa(3);
        siirtoNumero = v.valitseSiirto(r);
        assertNotEquals(siirtoNumero, 5);
        assertNotEquals(siirtoNumero, 3);
    }
    
    @Test
    public void valitseSiirtoToimii2() {
        Random r = new Random();
        Vastustaja v = new Vastustaja("Azra");
        v.paljastaKortti();
        v.paljastaKortti();
        v.saaRahaa(1);
        
        assertNotEquals(v.valitseSiirto(r), 3);
    }
    
    @Test
    public void valitseKohdeToimii1() {
        Random r = new Random();
        Peli peli = new Peli(3, "Aleksis");
        peli.kaynnistaPeli();
        Vastustaja v = (Vastustaja) peli.getOsanottajajoukko().get(1);
        int kohde = v.valitseKohde(r, peli.getOsanottajamaara());
        System.out.println(kohde);
        assertNotEquals(kohde, 1);
    }
    
    
    @Test
    public void paljastaKorttiToimii1() {
        Vastustaja v = new Vastustaja("Azra");
        Korttipakka pakka = new Korttipakka();
        v.nostaSatunnainenKorttiPakasta(pakka);
        assertEquals(v.getKorttikasi().getKorttikasi().get(0).onkoPaljastettu(), false);
        v.paljastaKortti();
        assertEquals(v.getKorttikasi().getKorttikasi().get(0).onkoPaljastettu(), true);
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
