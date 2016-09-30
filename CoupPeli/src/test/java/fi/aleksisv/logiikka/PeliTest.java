package fi.aleksisv.logiikka;


import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PeliTest {

    private int vakio1;
    private int vakio2;

    public PeliTest() {
        this.vakio1 = 3;
        this.vakio2 = 4;
    }

    @BeforeClass
    public static void setUpClass() {
        int vakio1 = 100;
    }

    @Test
    public void pelaajaMaaratOikein1() {
        Peli testipeli = new Peli(1);
        assertEquals(testipeli.getOsanottajamaara(), 1);
    }

    @Test
    public void pelaajaMaaratOikein2() {
        Peli testipeli = new Peli(4);
        assertEquals(testipeli.getOsanottajamaara(), 4);
    }

    @Test
    public void pelaajaMaaratOikein3() {
        Peli testipeli = new Peli(vakio1);
        assertEquals(testipeli.getOsanottajamaara(), 3);
    }

    @Test
    public void pudotaVastustajaOikein() {
        Peli testipeli = new Peli(3);
        Vastustaja vastustaja = new Vastustaja("Jaa");
        testipeli.lisaaVastustaja(vastustaja);
        testipeli.pudotaVastustaja(vastustaja);
        assertEquals(false, testipeli.getOsanottajamaara() == 3);
//        testipeli.pudotaPelaaja(pelaaja);

    }

    @Test
    public void lisaaVastustajaOikein() {
        Peli testipeli = new Peli(4);
        Vastustaja vastustaja = new Vastustaja("Vastustaja");
        testipeli.lisaaVastustaja(vastustaja);
        assertEquals(1, testipeli.getOsanottajajoukko().size());
    }

    @Test
    public void luoOsanottajatToimii1() {
        Peli peli1 = new Peli(4);
        Peli peli2 = new Peli(5);
        peli1.luoOsanottajat();
        peli2.luoOsanottajat();
        assertEquals(peli1.getOsanottajajoukko().size(), 4);
        assertEquals(peli2.getOsanottajajoukko().size(), 5);
    }

    @Test
    public void annaKortitToimii1() {
        Pelaaja pelaaja = new Pelaaja("JA");
        Peli peli = new Peli(4);
        assertEquals(pelaaja.getKorttikasi().koko(), 0);
        peli.annaKortit(pelaaja);
        assertEquals(pelaaja.getKorttikasi().koko(), 2);
        boolean sisaltyykoEdesJokuOikeistaKorteista = pelaaja.getKorttikasi().sisaltyykoKortti(new Kortti("Contessa"))
                || pelaaja.getKorttikasi().sisaltyykoKortti(new Kortti("Duke"))
                || pelaaja.getKorttikasi().sisaltyykoKortti(new Kortti("Assassin"))
                || pelaaja.getKorttikasi().sisaltyykoKortti(new Kortti("Captain"))
                || pelaaja.getKorttikasi().sisaltyykoKortti(new Kortti("Ambassador"));
        assertEquals(sisaltyykoEdesJokuOikeistaKorteista, true);
        assertNotEquals(pelaaja.getKorttikasi().koko(), 0);
    }

    @Test
    public void pelissaOlevatOsanottajatToimii1() {
        Peli peli = new Peli(4);
        peli.kaynnistaPeli();
        assertEquals(peli.pelissaOlevatOsanottajat(), 4);
        peli.getOsanottajajoukko().get(0).paljastaKortti();
        assertEquals(peli.pelissaOlevatOsanottajat(), 4);
        peli.getOsanottajajoukko().get(0).paljastaKortti();
        assertEquals(peli.pelissaOlevatOsanottajat(), 3);
    }
//    @Test
//    public void kaynnistaaPelinOikein1() {
//        Peli testipeli = new Peli(4);
//        assertEquals(testipeli.getVuoronumero(), 0);
//        testipeli.kaynnistaPeli();
//        assertNotEquals(testipeli.getVuoronumero(), 0);
//    }

}
