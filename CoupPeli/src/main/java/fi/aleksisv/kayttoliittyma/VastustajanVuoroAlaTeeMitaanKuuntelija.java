/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.*;
import java.awt.event.*;
import javax.swing.SwingUtilities;

/**
 * Luokka seuraa tilannetta, jossa pelaaja ei halua tehdä mitään vastustajan
 * siirron aikana.
 */
public class VastustajanVuoroAlaTeeMitaanKuuntelija implements ActionListener {

    /**
     * Graafinen käyttöliittymä.
     */
    GraafinenKayttoliittyma gkl;
    /**
     * Peliä pyörittävä taho.
     */
    PeliOhjaus peliOhjaus;
    /**
     * Tilanteeseen liittyvä ikkuna.
     */
    PelausIkkuna pelausIkkuna;
    /**
     * Vastustaja.
     */
    Vastustaja vastustaja;
    /**
     * Siirtonumero.
     */
    int siirto;
    /**
     * Keneen siirto kohdistuu.
     */
    Osanottaja kohde;

    /**
     * Luokan konstruktori.
     * 
     * @param gkl Graafinen käyttöliittymä.
     * @param peliOhjaus Peliä pyörittävä taho.
     * @param pelausIkkuna Tilanteeseen liittyvä ikkuna.
     * @param vastustaja Kuka vastustaja on vuorossa
     * @param siirto Mikä siirto on kyseessä.
     * @param kohde Siirron kohde.
     */
    public VastustajanVuoroAlaTeeMitaanKuuntelija(GraafinenKayttoliittyma gkl, PeliOhjaus peliOhjaus, PelausIkkuna pelausIkkuna, Vastustaja vastustaja, int siirto, Osanottaja kohde) {
        this.gkl = gkl;
        this.peliOhjaus = peliOhjaus;
        this.pelausIkkuna = pelausIkkuna;
        this.vastustaja = vastustaja;
        this.siirto = siirto;
        this.kohde = kohde;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.peliOhjaus.suoritaSiirto(vastustaja, kohde, siirto);
        
        this.gkl.getValiIkkuna().huomioTekstit.setText(vuoroteksti());
        
        this.gkl.paivitaPelinseuranta(this.gkl.getPelinSeurantapaneeli());
        this.pelausIkkuna.setVisible(false);
        SwingUtilities.updateComponentTreeUI(this.gkl.getValiIkkuna());
    }
    
    private String vuoroteksti() {
        String siirronNimi = this.peliOhjaus.getPeli().getSiirtoNimet().get(siirto);
        
        if (siirto == 3 || siirto == 5 || siirto == 6) {
            return "Vastustaja onnistui tekemään siirron " + siirronNimi + "\nosanottajaa " + this.kohde.getNimi() + " vastaan.";
        } else {
            return "Vastustaja onnistui tekemään siirron " + siirronNimi + ".";
        }
    }

}
