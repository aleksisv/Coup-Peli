
package fi.aleksisv.kayttoliittyma;

import fi.aleksisv.logiikka.*;
import java.awt.event.*;
import javax.swing.*;

public class PeliSiirtoKuuntelija implements ActionListener{
    ButtonGroup nappularyhma1, nappularyhma2;
    PelausIkkuna pelausIkkuna;
    GraafinenKayttoliittyma gkl;
    JTextArea huomioTekstit;
    PeliOhjaus peliOhjaus;

    public PeliSiirtoKuuntelija(PeliOhjaus peliOhjaus, PelausIkkuna pelausIkkuna,
            GraafinenKayttoliittyma gkl, ButtonGroup nappularyhma1,
            ButtonGroup nappularyhma2) {
        
        this.peliOhjaus = peliOhjaus;
        this.pelausIkkuna = pelausIkkuna;
        this.gkl = gkl;
        this.nappularyhma1 = nappularyhma1;
        this.nappularyhma2 = nappularyhma2;
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        int siirtoNumero = Integer.parseInt(nappularyhma1.getSelection().getActionCommand());
            if (this.peliOhjaus.onkoPelaajallaRahaa(siirtoNumero)) {
                yritaSiirtoa(Integer.parseInt(nappularyhma1.getSelection().getActionCommand()),
                        Integer.parseInt(nappularyhma2.getSelection().getActionCommand()));
            } else {
                pelausIkkuna.getHuomiotekstit().setText("Liian vähän rahaa siirtoon. Valitse uusi siirto.");
            }   
            SwingUtilities.updateComponentTreeUI(pelausIkkuna);
    }

    private void yritaSiirtoa(int siirtonumero, int kohde) {
        Pelaaja pelaaja = this.peliOhjaus.getPeli().getPelaaja();
        Vastustaja vastustaja = (Vastustaja) this.peliOhjaus.getPeli().getOsanottajajoukko().get(kohde);
        Kortti siirto = this.peliOhjaus.getPeli().getSiirtoNumerot().get(siirtonumero);

        if (vastustaja.haluaaEpailla(pelaaja, siirto)) {
            Epailyikkuna epailyIkkuna = new Epailyikkuna(true, vastustaja, peliOhjaus, siirtonumero, gkl);
            epailyIkkuna.luoKomponentit(epailyIkkuna.getContentPane());
            
            System.out.println("EPÄILYSSÄ!");
            
        } else if (vastustaja.haluaaTorjua(pelaaja, siirto)) {
            Torjuntaikkuna torjuntaikkuna = new Torjuntaikkuna(vastustaja, peliOhjaus, siirtonumero, gkl);
            torjuntaikkuna.luoKomponentit(torjuntaikkuna.getContentPane());
            
            System.out.println("TORJUNNASSA!");
            
        } else {
            this.peliOhjaus.suoritaSiirto(pelaaja, vastustaja, siirtonumero);
            this.pelausIkkuna.setVisible(false);
        }
        GraafinenKayttoliittyma gkl = this.gkl;
        gkl.getValiIkkuna().pelinSeurantapaneeli.paivitaTila(peliOhjaus.getPeli());
    }
    
    
    
}
