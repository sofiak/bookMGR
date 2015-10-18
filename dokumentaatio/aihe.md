Aihe: Kirjaston käyttöön tarkoitettu kirjojen ja lainojen hallintajärjestelmä. Ohjelma on tarkoitus asentaa kirjaston tietokoneelle jota voivat käyttää sekä kirjastonhoitajat että asiakkaat. Ohjelma käyttää activeJDBCtä tietokannan kanssa kommunikoimiseen ja CRUD-toimintoihin. Ohjelmalla on kahden tyyppisiä käyttäjiä, peruskäyttäjiä ja adminkäyttäjiä.

Kirjoista tallennetaan ISBN, kirjailija, teoksen nimi, julkaisuvuosi, kappalemäärä, sekä lyhyt kuvaus.

Peruskäyttäjät (kirjaston asiakkaat) voivat
- lainata ja palauttaa kirjoja
- vaihtaa salasanansa


Adminkäyttäjät (kirjastonhoitajat) voivat 
- lisätä, poistaa, muokata kirjoja 
- lisätä ja poistaa kirjailijoita
- tulostaa raportteja tietyn käyttäjän lainoista, kaikista lainoista (vanhoista tai nykyisistä)
- selata tietyn kirjailijan teoksia
- lisätä, muokata ja poistaa käyttäjiä
- ottaa vastaa käyttäjän myöhästymismaksut

