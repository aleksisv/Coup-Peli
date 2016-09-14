package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PankkiTest {

    public PankkiTest() {
    }

    @Test
    public void alussaOikeaMaaraRahaa() {
        Pankki pankki = new Pankki();
        assertEquals(pankki.getRahamaara(),100);
    }
    
    @Test
    public void rahaVaheneeOikein() {
        int loppumaara = 70;
        int vahennettava = 30;
        Pankki pankki = new Pankki();
        pankki.vahennaRahaa(vahennettava);
        assertEquals(pankki.getRahamaara(),loppumaara);
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
