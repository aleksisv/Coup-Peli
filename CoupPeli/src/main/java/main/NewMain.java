
package main;

import java.util.ArrayList;

public class NewMain {

    public static void main(String[] args) {
        Peli peli = new Peli(3);
        ArrayList<Integer> testi = new ArrayList();
        testi.add(0);
        testi.add(1);
        System.out.println(testi.toString());
        testi.remove(0);
        System.out.println(testi.toString());
        peli.kaynnistaPeli();
    }
    
}
