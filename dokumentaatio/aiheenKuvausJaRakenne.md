# Aihemäärittely
**Aihe**: Tietokonetoteutus vähemmän tunnetusta Coup-strategialautapelistä.
Coup on peli, jossa joukko osanottajia yrittää voittaa vallankumouksen.
Osanottajia on pelissä 2-5: 1 pelaaja ja 4 tekoäly-vastustajaa, jotka tekevät vuorollaan siirtoja.

Pelin alussa jokainen osanottaja saa käyttöönsä kaksi korttia ja kaksi kolikkoa.
Jokaiseen korttiin liittyy hahmo, jolla on tiettyjä erityisominaisuuksia. Kortit eivät näy muille pelaajille. Pelaajat voivat käyttää korttiensa erityisominaisuuksia tai yrittää huijata ja tällä tavoin voittaa muut pelaajat. Muut pelaajat voivat epäillä vuorossa olevan siirtoa, eli väittää että vastustajalla ei olekaan sitä korttia minkä tämä väittää omistavansa. Onnistunut epäily saa siirron tekijän paljastamaan yhden korttinsa, mutta epäonnistunut epäily johtaa oman kortin paljastumiseen. Pelaaja näkee jokaisen siirtonsa aikana sekä oman kätensä. pitää jokaisen siirtonsa aikana nähdä paitsi oma kätensä myös muiden pelaajien mahdollisesti paljastuneet kortit ja kentän tilanne.

**Käyttäjät**: Pelaaja.

**Pelaajan toiminnot**: 
* Aloita peli: Pelaaja voi valita nimensä ja vastustajien lukumäärän.
* Tee siirto: Pelaaja voi valita kohteen sekä siirron, jonka tekee.
* Epäile tai torju vastustaja siirto.
* Epäile vastustajan torjuntaa.


#Rakenne
Pelin logiikka-paketissa sijaitsevat itse pelin eri osatekijöitä kuvaavat oliot. Peli-luokka kuvaa
Coup-Peliä ja pitää sisällään pelaajan ja vastustajat, jotka laajentavat yläluokkaan Osanottaja,
korttipakan ja pankin. Osanottaja luokka kuvaa yleisellä tasolla yhtä pelin osanottajaa. Osanottajalla on korttikäsi, luokan Korttikäsi olio, joka sisältään pelikortteja kuvaavia Kortti-olioita. Osanottajalla on myös rahaa ja nimi. Luokkaa laajentavat luokat Pelaaja ja Vastustaja, joista
Pelaaja-luokan oliot kuvaavat pelin uniikkia pelaajaa ja vastustajat ovat pelissä muuten esiintyvät osanottajat.

Graafinen käyttöliittymä vastaa käyttäjän ja pelin vuorovaikutuksesta. Käyttöliittymä-paketissa on useita erilaisia graafisia luokkia laajentavia komponentteja ja näitä kuuntelevia toiminnankuuntelijoita.  Graafinen käyttöliittymä pyörittää peliä osittain luokan PeliOhjaus avulla. PeliOhjaus-luokan olio luo pelin ja vastaa esimerkiksi pelin siirtojen kutsumisesta.
 
###Luokkakaaviot

![Luokkakaavio](luokkakaavio.png)

# Käyttötapauksia
## Sekvenssikaaviot
### Pelin aloittaminen
![Aloita peli](AloitaPeliSekvenssikaavio.png)

### Siirron tekeminen
![Tee siirto](SiirronTekeminenSekvenssikaavio.png)
