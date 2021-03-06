package fi.aleksisv.logiikka;

import java.util.ArrayList;
import java.util.Random;

/**
 * Luokka vangitsee pelin sellaisen osanottajan, jota ohjaa äärimmäisen alkeellinen
 * tekoäly ja jota vastaan pelaaja pelaa.
 */
public class Vastustaja extends Osanottaja {
    /**
     * Luokan konstruktori.
     *
     * @param nimi Luotavan vastustajan nimi.
     */
    public Vastustaja(String nimi) {
        super(nimi);
    }
    

    @Override
    public void annaRahaaPankille(int maara, Pankki pankki) {
        super.annaRahaaPankille(maara, pankki);
    }

    @Override
    public boolean haluaaEpailla(Osanottaja osanottaja, Kortti mitaEiOle) {
        return super.haluaaEpailla(osanottaja, mitaEiOle);
    }

    @Override
    public boolean epaile(Osanottaja osanottaja, Kortti mitaEiOle) {
        return super.epaile(osanottaja, mitaEiOle);
    }

    @Override
    public Korttikasi getKorttikasi() {
        return super.getKorttikasi();
    }

    @Override
    public String getNimi() {
        return super.getNimi();
    }

    @Override
    public int getRaha() {
        return super.getRaha();
    }

    @Override
    public void kaytaPerustulo(Pankki pankki) {
        super.kaytaPerustulo(pankki);
    }

    @Override
    public void kaytaUlkomaanapu(Pankki pankki) {
        super.kaytaUlkomaanapu(pankki);
    }

    @Override
    public void lisaaKorttiKorttipakkaan(Kortti kortti) {
        super.lisaaKorttiKorttipakkaan(kortti);
    }

    @Override
    public void kaytaVarasta(Pankki pankki, Osanottaja osanottaja) {
        super.kaytaVarasta(pankki, osanottaja);
    }

    @Override
    public void kaytaVerotus(Pankki pankki) {
        super.kaytaVerotus(pankki);
    }

    @Override
    public String naytaNakyvatKortit() {
        return super.naytaNakyvatKortit();
    }

    @Override
    public boolean haluaaTorjua(Osanottaja osanottaja, Kortti kortti) {
        return super.haluaaTorjua(osanottaja, kortti);
    }

    @Override
    public void menetaRahaa(int maara) {
        super.menetaRahaa(maara);
    }

    @Override
    public void nostaSatunnainenKorttiPakasta(Korttipakka pakka) {
        super.nostaSatunnainenKorttiPakasta(pakka);
    }

    @Override
    public void otaRahaaPankilta(int maara, Pankki pankki) {
        super.otaRahaaPankilta(maara, pankki);
    }

    @Override
    public void saaRahaa(int maara) {
        super.saaRahaa(maara);
    }

    @Override
    public void setRaha(int raha) {
        super.setRaha(raha);
    }

    @Override
    public void paljastaKortti() {
        super.paljastaKortti();
    }

    @Override
    public String toString() {
        String status = "";
        if (super.montakoNakyvaaKorttia() == 2) {
            status = " poissa pelistä.";
        } else {
            status = " pelissä.";
        }
        return "Nimi: " + this.getNimi() + "\nRahaa: " + this.getRaha()
                + "\nNäkyvät kortit: " + this.naytaNakyvatKortit()
                + "\nStatus: " + status;
    }

    /**
     * Metodi valitsee vastustajalle siiron, minkä hän haluaa tehdä.
     * Vastustaja pyrkii aina hyökkäämään pelaajan korttia vastaan, mikäli hänellä
     * on tähän edellytykset. Jos näin ei ole, vastustaja arpoo minkä siirron tekee.
     * Useissa tapauksissa vastustaja bluffaa, eli valitsee siirron johon liittyvää korttia
     * hän ei edes omista.
     * 
     * @param r Random-olio.
     * 
     * @return Minkä numeroinen siirto.
     */
    public int valitseSiirto(Random r) {
        int siirtoEhdokas = 0;

        if (this.getRaha() >= 3) {
            if (this.getKorttikasi().sisaltyykoKortti(new Kortti("Salamurhaaja"))) {
                return 5;
            } else if (this.getRaha() >= 7) {
                return 3;
            }
        }

        while (true) {
            siirtoEhdokas = r.nextInt(6) + 1;
            if (siirtoEhdokas == 3) {
                if (this.getRaha() >= 7) {
                    break;
                }
            } else if (siirtoEhdokas == 5) {
                if (this.getRaha() >= 3) {
                    break;
                }
            } else {
                break;
            }
        }
        return siirtoEhdokas;
    }
    
    /**
     * Metodi valitsee mihin kohteeseen vastustaja haluaa tehdä siirron.
     * Vastustaja valitsee kohteen satunnaisesti.
     * 
     * @param r Random-olio.
     * @param pelaajaMaara Montako pelaajaa pelissä on mukana.
     * 
     * @return Minkä numeroinen siirto.
     */
    public int valitseKohde(Random r, int pelaajaMaara) {
        int kohdeEhdokas = 0;
        while (true) {
            kohdeEhdokas = r.nextInt(pelaajaMaara);
            if (!(this.peli.getOsanottajajoukko().get(kohdeEhdokas).equals(this))) {
                return kohdeEhdokas;
            }
        }
    }

}
