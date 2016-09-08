/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Random;

/**
 *
 * @author aleksisvuoksenmaa
 */
public class Vastustaja extends Osanottaja {
    private String nimi;
    private int kassa;
    private Korttikasi kasi;

    public Vastustaja() {
        Random r = new Random();
        if (r.nextDouble() > 0.5) {
            this.nimi = "Miia";
        }
        else this.nimi = "Jiia";
        kassa = 2;
        this.kasi = new Korttikasi();
    }
    
    
}
