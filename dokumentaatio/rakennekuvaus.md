bookMGR - Rakennekuvaus

bookmgr.repos:
Ohjelman logiikka on jaettu nelj��n repositorioon; kirjoja ja kirjailijoita hallinnoiva BookRepo, lainoja hallinnoiva RentRepo, K�ytt�jiin liittyv�� logiikkaa hoitava UserRepo sek� AdminRepo.

bookmgr.exceptions:
Exceptions-pakkauksessa on erilaisille spesifeille virhetilanteille omat Exception-luokkansa.

bookmgr.models:
Malliluokat mahdollistavat tietokanna taulujen rivien k�sittelyn Olioina ja hoitavat kaikki CRUD-toiminnallisuudet. Jokaiselle tietokannan taululle l�ytyy siis oma malliluokka.

bookmgr.UI:
Graafisen k�ytt�liittym�n luokat kutsuvat repositorioita.

bookmgr.main:
Pakkauksessa on ainoastaan Connection.luokka jota k�ytt�liittym� ja testit kutsuvat, sek� Main-luokka ohjelman ajamista varten.
