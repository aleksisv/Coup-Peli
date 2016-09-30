/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.aleksisv.logiikka;

import fi.aleksisv.logiikka.Pelaaja;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelaajaTest {

    public PelaajaTest() {
    }

    @Test
    public void pelaajallaAlussaOikeaMaaraRahaa() {
        Pelaaja pelaaja = new Pelaaja("Pelaaja");
        assertEquals(pelaaja.getRaha(), 2);
    }

    @Test
    public void pelaajanNimiOikea() {
        Pelaaja pelaaja = new Pelaaja("Pelaaja");
        assertEquals(pelaaja.getNimi(), "Pelaaja");
    }

    @Test
    public void getKorttikasiToimii1() {
        Pelaaja pelaaja = new Pelaaja("Pelaaja");
        assertEquals(pelaaja.getKorttikasi().getClass(), Korttikasi.class);
    }

    @Test
    public void pelaajallaAlussaOikeaMaaraKortteja() {
        Pelaaja pelaaja = new Pelaaja("Pelaaja");
        assertEquals(pelaaja.getKorttikasi().koko(), 0);
    }

    @Test
    public void korttienTappaminenToimii1() {
        Pelaaja pelaaja = new Pelaaja("Pelaaja");
        pelaaja.getKorttikasi().lisaaKorttikateen(new Kortti("Contessa"));
        pelaaja.paljastaKortti();
        assertEquals(pelaaja.getKorttikasi().koko(), 1);
    }

    @Test
    public void korttienPaljastaminenToimii() {
        Pelaaja pelaaja = new Pelaaja("Pelaaja");
        pelaaja.getKorttikasi().lisaaKorttikateen(new Kortti("Contessa"));
        pelaaja.paljastaKortti();
        assertEquals(pelaaja.getKorttikasi().paljastettujenKorttienLukumaara(), 1);
    }

    @Test
    public void epailyToimii1() {
        Pelaaja pelaaja1 = new Pelaaja("Aino");
        Pelaaja pelaaja2 = new Pelaaja("Anna");
        pelaaja1.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        pelaaja2.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        boolean epaily = pelaaja1.epaile(pelaaja2, new Kortti("Contessa"));
        assertEquals(epaily, false);
    }

    @Test
    public void epailyToimii2() {
        Pelaaja pelaaja1 = new Pelaaja("Aino");
        Pelaaja pelaaja2 = new Pelaaja("Anna");
        pelaaja1.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        pelaaja2.lisaaKorttiKorttipakkaan(new Kortti("Assassin"));
        boolean epaily = pelaaja1.epaile(pelaaja2, new Kortti("Contessa"));
        assertEquals(epaily, true);
    }

    @Test
    public void epailyToimii3() {
        Pelaaja pelaaja1 = new Pelaaja("Aino");
        Pelaaja pelaaja2 = new Pelaaja("Anna");
        pelaaja1.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        pelaaja2.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        boolean epaily = pelaaja1.epaile(pelaaja2, new Kortti("Contessa"));
        assertEquals(epaily, false);
    }

    @Test
    public void epailyToimii4() {
        Pelaaja pelaaja1 = new Pelaaja("Aino");
        Pelaaja pelaaja2 = new Pelaaja("Anna");
        pelaaja1.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        pelaaja2.lisaaKorttiKorttipakkaan(new Kortti("Assassin"));
        boolean epaily = pelaaja1.epaile(pelaaja2, new Kortti("Contessa"));
        assertEquals(epaily, true);
    }

    @Test
    public void PerustuloToimii1() {
        Pelaaja p1 = new Pelaaja("Aino");
        Pankki pankki = new Pankki();
        p1.kaytaPerustulo(pankki);
        assertEquals(p1.getRaha(), 3);
        assertEquals(pankki.getRahamaara(), 99);
    }

    @Test
    public void naytaNakyvatKortitToimii1() {
        Pelaaja p1 = new Pelaaja("Aino");
        p1.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        p1.lisaaKorttiKorttipakkaan(new Kortti("Assassin"));
        p1.paljastaKortti();
        assertEquals(p1.naytaNakyvatKortit(), "Contessa");
        p1.paljastaKortti();
        assertEquals(p1.naytaNakyvatKortit(), "Contessa, Assassin");
        p1.paljastaKortti();
        assertEquals(p1.naytaNakyvatKortit(), "Contessa, Assassin");
    }

    @Test
    public void saaRahaaToimii1() {
        Pelaaja p1 = new Pelaaja("Aino");
        p1.saaRahaa(2);
        assertEquals(p1.getRaha(), 4);
    }

    @Test
    public void menetaRahaaToimii1() {
        Pelaaja p1 = new Pelaaja("Aino");
        p1.menetaRahaa(2);
        assertEquals(p1.getRaha(), 0);
        p1.menetaRahaa(1);
        assertEquals(p1.getRaha(), 0);
        p1.saaRahaa(5);
        p1.menetaRahaa(6);
        assertEquals(p1.getRaha(), 0);
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
        Pelaaja p = new Pelaaja("Azra");
        Pankki pankki = new Pankki();
        int pankinAlkurahat = pankki.getRahamaara();
        p.annaRahaaPankille(1, pankki);
        assertEquals(p.getRaha(), 1);
        assertEquals(pankki.getRahamaara(), 1 + pankinAlkurahat);
    }

    @Test
    public void otaRahaaPankiltaToimii1() {
        Pelaaja p = new Pelaaja("Azra");
        Pankki pankki = new Pankki();
        assertEquals(p.getRaha(), 2);
        p.otaRahaaPankilta(4, pankki);
        assertEquals(p.getRaha(), 6);
    }

    @Test
    public void kaytaPerustuloToimii1() {
        Pelaaja p = new Pelaaja("Azra");
        Pankki pankki = new Pankki();
        p.kaytaPerustulo(pankki);
        assertEquals(p.getRaha(), 3);
        p.kaytaPerustulo(pankki);
        assertNotEquals(p.getRaha(), 3);
    }

    @Test
    public void kaytaUlkomaanapuToimii1() {
        Pelaaja p = new Pelaaja("Azra");
        Pankki pankki = new Pankki();
        p.kaytaUlkomaanapu(pankki);
        assertEquals(p.getRaha(), 4);
        p.kaytaUlkomaanapu(pankki);
        assertNotEquals(p.getRaha(), 4);
    }

    @Test
    public void kaytaAssassinoiToimii1() {
        Pelaaja p = new Pelaaja("Azra");
        p.saaRahaa(10);
        Pankki pankki = new Pankki();
        Osanottaja kohde = new Osanottaja("OiEi");
        kohde.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        assertEquals(kohde.montakoNakyvaaKorttia(), 0);
        p.kaytaAssassinoi(pankki, kohde);
        assertEquals(kohde.montakoNakyvaaKorttia(), 1);
        assertEquals(p.getRaha(), 9);
        assertEquals(pankki.getRahamaara(), 103);
    }

    @Test
    public void VallankaappausToimii1() {
        Pelaaja p = new Pelaaja("Azra");
        p.saaRahaa(10);
        Pankki pankki = new Pankki();
        Osanottaja kohde = new Osanottaja("OiEi");
        kohde.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        assertEquals(kohde.montakoNakyvaaKorttia(), 0);
        p.kaytaVallankaappaus(pankki, kohde);
        assertEquals(kohde.montakoNakyvaaKorttia(), 1);
        assertEquals(p.getRaha(), 5);
        assertEquals(pankki.getRahamaara(), 107);
    }

    @Test
    public void kaytaVerotusToimii1() {
        Pelaaja p = new Pelaaja("Azra");
        Pankki pankki = new Pankki();
        p.kaytaVerotus(pankki);
        assertEquals(p.getRaha(), 5);
        assertEquals(pankki.getRahamaara(), 97);
    }

    @Test
    public void montakoNakyvaaKorttiaToimii1() {
        Pelaaja p = new Pelaaja("Azra");
        p.lisaaKorttiKorttipakkaan(new Kortti("Contessa"));
        p.lisaaKorttiKorttipakkaan(new Kortti("Duke"));
        assertEquals(p.montakoNakyvaaKorttia(), 0);
        p.paljastaKortti();
        assertEquals(p.montakoNakyvaaKorttia(), 1);
        p.paljastaKortti();
        assertEquals(p.montakoNakyvaaKorttia(), 2);
        p.paljastaKortti();
        assertEquals(p.montakoNakyvaaKorttia(), 2);
    }

    @Test
    public void kaytaVarastaToimii1() {
        Pelaaja p = new Pelaaja("Azra");
        Osanottaja kohde = new Osanottaja("Hmh");
        Pankki pankki = new Pankki();
        kohde.saaRahaa(1);
        p.kaytaVarasta(pankki, kohde);
        assertEquals(p.getRaha(), 4);
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
