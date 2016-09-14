
package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Peli extends AbstraktiPeli {
    
    private int osanottajamaara;
    private ArrayList<Osanottaja> osanottajajoukko;
    private boolean pysayta;
    private int vuoronumero;
    private Pelaaja pelaaja;
    private Scanner lukija;

    public Peli(int pelaajamaara) {
        this.osanottajamaara = pelaajamaara;
        this.osanottajajoukko = new ArrayList<Osanottaja>();
        this.vuoronumero = 0;
        this.pelaaja = new Pelaaja("A");
        this.lukija = new Scanner(System.in);
    }
    
    public void kaynnistaPeli() {
        Random r = new Random();
        luoOsanottajat();
        this.vuoronumero = r.nextInt(this.osanottajamaara);
        
        while (this.osanottajajoukko.size() > 1) {
            pelaaVuoro(this.osanottajajoukko.get(vuoronumero % osanottajajoukko.size()));
            this.vuoronumero++;
        }
        System.out.println("Voittaja: " + this.osanottajajoukko.get(0).toString());

    }

    public int getPelaajamaara() {
        return osanottajamaara;
    }
    
    private void luoOsanottajat() {
        osanottajajoukko.add(new Pelaaja("X"));
        for (int i = 0; i < osanottajamaara - 1; i++) {
            osanottajajoukko.add(new Vastustaja("i"));
        }
        for (Osanottaja osanottajajoukko1 : osanottajajoukko) {
            System.out.println(osanottajajoukko1);
        }
    }
    
    private void pelaaVuoro(Osanottaja osanottaja) {
        if (osanottaja.equals(this.pelaaja)) {
            pelaajanVuoro((Pelaaja) osanottaja);
        } 
//        else { 
//            vastustajanVuoro((Vastustaja) osanottaja); 
//        }
    }
    
    private void pelaajanVuoro(Pelaaja pelaaja) {
        System.out.println("Minkä siirron haluat tehdä?\n");
        lukija.nextLine();
    }
    
    
    private void vastustajanVuoro(Vastustaja osanottaja) {
       
    }
    
    
    
}
