
package main;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class PeliTest {
    
    @Test
        public void test() {
                Peli tester = new Peli(1); // MyClass is tested
                assertEquals(tester.getPelaajamaara(), 1);
                
        }

    
}
