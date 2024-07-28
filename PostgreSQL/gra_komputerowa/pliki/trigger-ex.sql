SET client_encoding TO 'UTF-8';

-- utworzenie testowej postaci
INSERT INTO uzytkownik (login, haslo) VALUES ('test', 'test');
INSERT INTO postac (nazwa, uzytkownik, klasa, miejsce) VALUES ('tEStowA POStac', 'test', 'mag', 'miasto');

-- przykład wyzwalaczy: 'popraw_nazwe_postaci' i 'dodaj_date_utworzenia'
SELECT * FROM postac;

-- przykład wyzwalacza: 'usunieta_postac_log'
DELETE FROM postac WHERE nazwa = 'Testowa Postac';
SELECT * FROM usunieta_postac;