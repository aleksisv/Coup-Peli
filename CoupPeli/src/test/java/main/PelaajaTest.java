/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

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
    public static void pelaajallaAlussaOikeaMaaraRahaa() {
        Pelaaja pelaaja = new Pelaaja("Pelaaja");
        assertEquals(pelaaja.getRaha(), 2);
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
