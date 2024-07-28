SET client_encoding='utf-8';

-- dodanie użytkowników
INSERT INTO uzytkownik (login, haslo) VALUES ('_adam_', 'haslo123');
INSERT INTO uzytkownik (login, haslo) VALUES ('janek23', '42jhq1a3618');
INSERT INTO uzytkownik (login, haslo) VALUES ('kasia', 'aaaaa');
INSERT INTO uzytkownik (login, haslo) VALUES ('OleK', 'lwjwwx');
INSERT INTO uzytkownik (login, haslo) VALUES ('kiko', '12345');


-- dodanie klas i ich podstawowych statystyk
INSERT INTO klasa (nazwa, opis, sila, wytrzymalosc, inteligencja, charyzma, szczescie)
VALUES ('barbarzyńca', 'Zaciekły wojownik potrafiący wpaść w szał bitewny.', 65, 50, 5, 0, 20);
INSERT INTO klasa (nazwa, opis, wytrzymalosc, zrecznosc, inteligencja, charyzma, szczescie)
VALUES ('bard', 'Inspirujący czarodziej, którego moc rezonuje z muzyką.', 50, 20, 30, 25, 15);
INSERT INTO klasa (nazwa, opis, wytrzymalosc, inteligencja, charyzma, szczescie)
VALUES ('druid', 'Kapłan Starej Wiary, dzierżący moce natury i przyjmujący kształty zwierząt.', 40, 40, 20, 40);
INSERT INTO klasa (nazwa, opis, wytrzymalosc, zrecznosc, inteligencja, szczescie)
VALUES ('konstruktor', 'Mistrzowie inwencji, rzemieślnicy wykorzystujący pomysłowość i magię.', 40, 50, 30, 30);
INSERT INTO klasa (nazwa, opis, sila, wytrzymalosc, zrecznosc, szczescie)
VALUES ('łotr', 'Zawadiaka pokonujący przeciwników przy użyciu ukrywania się i podstępu.', 40, 5, 65, 20);
INSERT INTO klasa (nazwa, opis, sila, wytrzymalosc, zrecznosc, inteligencja, szczescie)
VALUES ('łowca', 'Wojownik wykorzystujący zdolności żołnierskie i magię natury.', 30, 20, 40, 20, 30);
INSERT INTO klasa (nazwa, opis, zrecznosc, inteligencja, szczescie)
VALUES ('mag', 'Uczony użytkownik magii, potrafiący manipulować rzeczywistością.', 30, 60, 30);
INSERT INTO klasa (nazwa, opis, sila, wytrzymalosc, charyzma)
VALUES ('paladyn', 'Natchniony wojownik związany ze świętymi ślubami.', 40, 50, 30);
INSERT INTO klasa (nazwa, opis, sila, zrecznosc, charyzma, szczescie)
VALUES ('skrytobójca', 'Mistrz wyprowadzania zabójczych ciosów i zadawania szybkiej śmierci.', 50, 40, 20, 20);

-- dodanie nazw miejsc
INSERT INTO miejsce (nazwa) VALUES ('miasto');
INSERT INTO miejsce (nazwa) VALUES ('las');
INSERT INTO miejsce (nazwa) VALUES ('wioska');
INSERT INTO miejsce (nazwa) VALUES ('zamek');
INSERT INTO miejsce (nazwa) VALUES ('góry');
INSERT INTO miejsce (nazwa) VALUES ('tawerna');
INSERT INTO miejsce (nazwa) VALUES ('ruiny');

-- dodanie zadań
INSERT INTO zadanie (nazwa, miejsce, pd, pieniadze, opis)
VALUES ('Zaginiony podróżnik', 'las', 150, 80, 'Odszukaj podróżnika, który zaginął w lesie.');
INSERT INTO zadanie (nazwa, miejsce, pd, pieniadze, opis)
VALUES ('Rzadkie zioła', NULL, 200, 100, 'Zbierz zioła, które rosną w różnych częściach mapy.');
INSERT INTO zadanie (nazwa, miejsce, pd, pieniadze, opis)
VALUES ('Ochrona konwoju', 'miasto', 280, 200, 'Ochroń konwój handlowy przed trolami i goblinami.');
INSERT INTO zadanie (nazwa, miejsce, pd, pieniadze, opis)
VALUES ('Badanie starożytnych ruin', 'ruiny', 250, 150, 'Zbadaj starożytne ruiny w poszukiwaniu skarbów i tajemniczej wiedzy.');
INSERT INTO zadanie (nazwa, miejsce, pd, pieniadze, opis)
VALUES ('Ratowanie uwięzionych jeńców', 'wioska', 220, 140, 'Uratuj uwięzionych jeńców z rąk okrutnych oprawców.');
INSERT INTO zadanie (nazwa, miejsce, pd, pieniadze, opis)
VALUES ('Ochrona wioski', 'wioska', 500, 300, 'Ochroń wioskę przed nadchodzącą inwazją potworów.');
INSERT INTO zadanie (nazwa, miejsce, pd, pieniadze, opis)
VALUES ('Złapanie smoka', 'góry', 350, 250, 'Złap i ujarzmij szalejącego smoka, który terroryzuje okoliczne tereny.');

-- dodanie potworów
INSERT INTO potwor (nazwa, zdrowie, atak) VALUES ('Goblin', 50, 10);
INSERT INTO potwor (nazwa, zdrowie, atak) VALUES ('Wilk', 60, 20);
INSERT INTO potwor (nazwa, zdrowie, atak) VALUES ('Ork', 70, 12);
INSERT INTO potwor (nazwa, zdrowie, atak) VALUES ('Wąż', 30, 8);
INSERT INTO potwor (nazwa, zdrowie, atak) VALUES ('Trol', 100, 20);
INSERT INTO potwor (nazwa, zdrowie, atak) VALUES ('Smok', 200, 60);
INSERT INTO potwor (nazwa, zdrowie, atak) VALUES ('Duch', 40, 30);

-- przypisanie potworów do zadań
INSERT INTO potwory_w_zadaniu (zadanie, potwor, liczba_potworow) VALUES (1, 2, 3);
INSERT INTO potwory_w_zadaniu (zadanie, potwor, liczba_potworow) VALUES (2, 4, 6);
INSERT INTO potwory_w_zadaniu (zadanie, potwor, liczba_potworow) VALUES (3, 1, 4);
INSERT INTO potwory_w_zadaniu (zadanie, potwor, liczba_potworow) VALUES (3, 5, 2);
INSERT INTO potwory_w_zadaniu (zadanie, potwor, liczba_potworow) VALUES (4, 4, 10);
INSERT INTO potwory_w_zadaniu (zadanie, potwor) VALUES (4, 7);
INSERT INTO potwory_w_zadaniu (zadanie, potwor, liczba_potworow) VALUES (5, 3, 5);
INSERT INTO potwory_w_zadaniu (zadanie, potwor, liczba_potworow) VALUES (6, 3, 4);
INSERT INTO potwory_w_zadaniu (zadanie, potwor, liczba_potworow) VALUES (6, 5, 6);
INSERT INTO potwory_w_zadaniu (zadanie, potwor) VALUES (7, 6);

-- dodanie postaci
INSERT INTO postac (nazwa, uzytkownik, klasa, miejsce)
VALUES ('Asmodeusz', '_adam_', 'łotr', 'tawerna');
INSERT INTO postac (nazwa, uzytkownik, klasa, miejsce)
VALUES ('Gajusz', 'janek23', 'druid', 'las');
INSERT INTO postac (nazwa, uzytkownik, status, klasa, miejsce)
VALUES ('Hypatia', 'kasia', 'false', 'druid', 'ruiny');
INSERT INTO postac (nazwa, uzytkownik, klasa, miejsce)
VALUES ('Stefan', 'OleK', 'bard', 'miasto');
INSERT INTO postac (nazwa, uzytkownik, klasa, miejsce)
VALUES ('Lucek', 'kiko', 'łowca', 'miasto');

-- przypisanie zadań do postaci
INSERT INTO postac_zadanie (postac, zadanie) VALUES (1, 3);
INSERT INTO postac_zadanie (postac, zadanie) VALUES (1, 6);
INSERT INTO postac_zadanie (postac, zadanie) VALUES (2, 2);
INSERT INTO postac_zadanie (postac, zadanie) VALUES (3, 2);
INSERT INTO postac_zadanie (postac, zadanie) VALUES (3, 4);
INSERT INTO postac_zadanie (postac, zadanie) VALUES (3, 7);
INSERT INTO postac_zadanie (postac, zadanie) VALUES (4, 3);

-- dodanie typów przedmiotów
INSERT INTO typ_przedmiotu (nazwa) VALUES ('amulet'), ('hełm'), ('narzędzie'), ('tarcza'), ('zbroja'), ('broń'), ('pierścień'), ('buty'), ('rękawice');

-- dodanie przedmiotów
INSERT INTO przedmiot (nazwa, typ, opis, sila, zrecznosc)
VALUES ('Miecz', 'broń', 'Standardowy miecz dla wojownika.', 10, 2);
INSERT INTO przedmiot (nazwa, typ, opis, inteligencja)
VALUES ('Księga zaklęć', 'narzędzie', 'Księga zawierająca zaklęcia dla maga.', 12);
INSERT INTO przedmiot (nazwa, typ, opis, sila)
VALUES ('Topór', 'broń', 'Potężny topór bojowy.', 15);
INSERT INTO przedmiot (nazwa, typ, opis, inteligencja)
VALUES ('Zwoje czarów', 'narzędzie', 'Zwoje z zaklęciami.', 25);
INSERT INTO przedmiot (nazwa, typ, opis, wytrzymalosc, zrecznosc, charyzma)
VALUES ('Skórzane rękawice', 'rękawice', 'Rękawice z lekkiej skóry.', 10, 5, 3);
INSERT INTO przedmiot (nazwa, typ, opis, szczescie)
VALUES ('Amulet szczęścia', 'amulet', NULL, 10);
INSERT INTO przedmiot (nazwa, typ, opis, sila, zrecznosc)
VALUES ('Łuk myśliwego', 'broń', 'Łuk doskonały dla łowców.', 10, 20);
INSERT INTO przedmiot (nazwa, typ, opis, wytrzymalosc, zrecznosc)
VALUES ('Drewniana tarcza', 'tarcza', NULL, 20, -5);

-- przypisanie przedmiotów do postaci
INSERT INTO postac_przedmiot (postac, przedmiot, typ_przedmiotu) VALUES (1, 1, 'broń');
INSERT INTO postac_przedmiot (postac, przedmiot, typ_przedmiotu) VALUES (1, 8, 'tarcza');
INSERT INTO postac_przedmiot (postac, przedmiot, typ_przedmiotu) VALUES (2, 2, 'narzędzie');
INSERT INTO postac_przedmiot (postac, przedmiot, typ_przedmiotu) VALUES (2, 5, 'rękawice');
INSERT INTO postac_przedmiot (postac, przedmiot, typ_przedmiotu) VALUES (3, 2, 'narzędzie');
INSERT INTO postac_przedmiot (postac, przedmiot, typ_przedmiotu) VALUES (3, 6, 'amulet');
INSERT INTO postac_przedmiot (postac, przedmiot, typ_przedmiotu) VALUES (3, 7, 'broń');
INSERT INTO postac_przedmiot (postac, przedmiot, typ_przedmiotu) VALUES (4, 6, 'amulet');
INSERT INTO postac_przedmiot (postac, przedmiot, typ_przedmiotu) VALUES (5, 7, 'broń');