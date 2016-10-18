/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

class VastustajanVuoroAlaTeeMitaanKuuntelija implements ActionListener {

    GraafinenKayttoliittyma gkl;
    PeliOhjaus peliOhjaus;
    PelausIkkuna pelausIkkuna;
    Vastustaja vastustaja;
    int siirto;
    Osanottaja kohde;

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
        this.gkl.paivitaPelinseuranta(this.gkl.getPelinSeurantapaneeli());
        this.pelausIkkuna.setVisible(false);
        SwingUtilities.updateComponentTreeUI(this.gkl.getValiIkkuna());
    }

}
