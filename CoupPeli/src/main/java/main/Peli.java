
package main;

import java.util.ArrayList;
import java.util.Arrays;
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
        this.pelaaja = new Pelaaja("Pelaaja");
        this.lukija = new Scanner(System.in);
    }
    
    public void kaynnistaPeli() {
        Random r = new Random();
        luoOsanottajat();
        this.vuoronumero = 0;
//        this.vuoronumero = r.nextInt(this.osanottajamaara);
        System.out.println("Vuoronumero: " + this.vuoronumero);
        
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
        osanottajajoukko.add(this.pelaaja);
        for (int i = 0; i < osanottajamaara - 1; i++) {
            osanottajajoukko.add(new Vastustaja(Integer.toString(i)));
        }
        for (Osanottaja osanottajat : osanottajajoukko) {
            System.out.print(osanottajat);
            System.out.print(" ");
        }
        System.out.println("");
    }
    
    private void pelaaVuoro(Osanottaja osanottaja) {
        if (osanottaja.equals(this.pelaaja)) {
            pelaajanVuoro((Pelaaja) osanottaja);
        }
        
        for (Osanottaja o : osanottajajoukko) {
            if(osanottaja.getKasi().koko() < 1) {
                osanottajajoukko.remove(o);
                this.osanottajamaara--;
            }
        }
        
        if(!osanottajajoukko.contains(this.pelaaja)) {
            System.out.println(Arrays.toString(osanottajajoukko.toArray()));
            System.out.println("Hävisit pelin");
        }
    }
    
    private void pelaajanVuoro(Pelaaja pelaaja) {
        System.out.println("Minkä siirron haluat tehdä?\n");
        System.out.println("Vaihtoehdot: (1) tapa vastustajan kortti \n");
        
//        int vastaus1 = Integer.parseInt(lukija.nextLine());
        int vastaus1 = 1;
        
        if(vastaus1 == 1) {
            System.out.println("Valitse pelaaja, jonka haluat tappaa. Vaihtoehdot: ");
            for (Osanottaja o : osanottajajoukko) {
                if(!o.equals(this.pelaaja)) {
                    System.out.println(o);
                }
            }
            //int vastaus2 = Integer.parseInt(lukija.nextLine());
            this.osanottajajoukko.remove(1);
            
        }
    }
    
    
    private void vastustajanVuoro(Vastustaja osanottaja) {
       
    }
    
    
    
}
