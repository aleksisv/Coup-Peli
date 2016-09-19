/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class KorttiTest {

    public KorttiTest() {
    }

    @Test
    public void vertaileekoKorttejaOikein1() {
        Kortti kortti1 = new Kortti("Contessa", false);
        Kortti kortti2 = new Kortti("Contessa", false);
        assertEquals(kortti1, kortti2);
    }

    @Test
    public void vertaileekoKorttejaOikein2() {
        Kortti kortti1 = new Kortti("Contessa", false);
        Kortti kortti2 = new Kortti("Contessa", true);
        assertEquals(kortti1, kortti2);
    }

    @Test
    public void vertaileekoKorttejaOikein3() {
        Kortti kortti1 = new Kortti("Contessa", false);
        Kortti kortti2 = new Kortti("Assassin", false);
        assertNotEquals(kortti1, kortti2);
    }
    @Test
    public void vertaileekoKorttejaOikein4() {
        Kortti kortti1 = new Kortti("Contessa", false);
        Pelaaja pelaaja1 = new Pelaaja("Aino");
        assertEquals(false, kortti1.equals(pelaaja1));
    }
    
    @Test
    public void vertaileekoKorttejaOikein5() {
        Pelaaja pelaaja = new Pelaaja("Anna");
        Kortti kortti = new Kortti("Contessa", false);
        assertNotEquals(pelaaja, kortti);
    }

    @Test
    public void onkoTulosteOikea1() {
        Kortti kortti = new Kortti("Assassin");
        assertEquals(kortti.toString(), "Assassin");
    }
    
    @Test
    public void onkoPaljastettuToimii1() {
        Kortti kortti = new Kortti("Assassin");
        assertEquals(false, kortti.onkoPaljastettu());
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
