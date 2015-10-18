bookMGR - Rakennekuvaus

bookmgr.repos:
Ohjelman logiikka on jaettu neljään repositorioon; kirjoja ja kirjailijoita hallinnoiva BookRepo, lainoja hallinnoiva RentRepo, Käyttäjiin liittyvää logiikkaa hoitava UserRepo sekä AdminRepo.

bookmgr.exceptions:
Exceptions-pakkauksessa on erilaisille spesifeille virhetilanteille omat Exception-luokkansa.

bookmgr.models:
Malliluokat mahdollistavat tietokanna taulujen rivien käsittelyn Olioina ja hoitavat kaikki CRUD-toiminnallisuudet. Jokaiselle tietokannan taululle löytyy siis oma malliluokka.

bookmgr.UI:
Graafisen käyttöliittymän luokat kutsuvat repositorioita.

bookmgr.main:
Pakkauksessa on ainoastaan Connection.luokka jota käyttöliittymä ja testit kutsuvat, sekä Main-luokka ohjelman ajamista varten.
