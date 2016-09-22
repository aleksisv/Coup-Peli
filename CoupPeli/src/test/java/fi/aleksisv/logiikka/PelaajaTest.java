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
        pelaaja.tapaKortti();
        assertEquals(pelaaja.getKorttikasi().koko(), 1);
    }
    
    @Test
    public void korttienPaljastaminenToimii() {
        Pelaaja pelaaja = new Pelaaja("Pelaaja");
        pelaaja.getKorttikasi().lisaaKorttikateen(new Kortti("Contessa"));
        pelaaja.tapaKortti();
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
