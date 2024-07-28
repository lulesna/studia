-- wyświetlenie opisów wszystkich zadań,
-- których nazwa nie zaczyna się od 'Ochrona' lub 'Ratowanie'
SELECT opis
FROM zadanie
WHERE nazwa NOT LIKE 'Ochrona%' AND nazwa NOT LIKE 'Ratowanie%';


-- wyświetlenie nazw i opisów klas, które nie zostały przypisane do żadnej postaci
SELECT nazwa, opis
FROM klasa k
WHERE NOT EXISTS
          (select *
           FROM postac p
           WHERE p.klasa = k.nazwa)
ORDER BY k.nazwa;


-- wyświetlenie przypisania potworów do zadań, ale
-- tylko te rekordy, w których liczba potworów jest w przedziale od 2 do 8,
-- oraz w których nazwa potwora nie składa się z dokładnie trzech liter
SELECT z.nazwa AS zadanie, p.nazwa AS potwor, pwz.liczba_potworow
FROM potwory_w_zadaniu pwz, zadanie z, potwor p
WHERE pwz.zadanie = z.id
  AND pwz.potwor = p.id
  AND p.nazwa NOT LIKE '___'
  AND pwz.liczba_potworow BETWEEN 2 AND 8 
ORDER BY z.id, liczba_potworow;


-- wyświetlenie przedmiotów, które posiadają opis
SELECT * FROM przedmiot
WHERE opis IS NOT NULL 
ORDER BY typ;


-- wyświetlenie przypisania przedmiotów do postaci,
-- które aktualnie używają więcej niż jednego przedmiotu
SELECT postac.nazwa AS postac, przedmiot.nazwa AS przedmiot
FROM (postac_przedmiot INNER JOIN postac ON postac_przedmiot.postac = postac.id)
      INNER JOIN przedmiot ON postac_przedmiot.przedmiot = przedmiot.id
WHERE postac IN
      (SELECT postac
       FROM postac_przedmiot
       GROUP BY postac
       HAVING COUNT(postac) > 1);


-- stworzenie widoku: wyświetlenie postaci, obliczenie ich punktów doświadczenia i pieniędzy
-- na podstawie wykonanych zadań, obliczenie poziomu postaci
CREATE VIEW postac_informacje AS
SELECT p.*,
       COALESCE(suma.pd, 0) AS pd,
       COALESCE(suma.pieniadze, 0) AS pieniadze,
       COALESCE(suma.pd/120, 0) AS poziom
FROM postac p
LEFT JOIN
    (SELECT pz.postac,
            SUM(zadanie.pd) AS pd,
            SUM(zadanie.pieniadze) AS pieniadze
     FROM postac_zadanie pz
     INNER JOIN zadanie ON pz.zadanie = zadanie.id
     GROUP BY pz.postac)
AS suma ON p.id = suma.postac;


-- wyświetlenie postaci z widoku postac_informacje,
-- ale z wyjątkiem postaci, które walczyły ze smokiem
SELECT * FROM postac_informacje
EXCEPT
SELECT * FROM postac_informacje
WHERE id IN (
    SELECT pz.postac
    FROM postac_zadanie pz
    INNER JOIN potwory_w_zadaniu ON pz.zadanie = potwory_w_zadaniu.zadanie
    WHERE potwory_w_zadaniu.potwor = 7)
ORDER BY id;


-- zwiększenie inteligencji o 20% i zmniejszenie wytrzymałości o 5 punktów
-- w przedmiotach, które są narzędziami
UPDATE przedmiot
SET inteligencja = ROUND(inteligencja*1.2, 0), wytrzymalosc = wytrzymalosc-5
WHERE typ = 'narzędzie'; 


-- dodanie zdania 'Bardzo trudne zadanie.' do opisu zadań,
-- za które można dostać ponad 250 punktów doświadczenia i przynajmniej 200 pieniędzy
UPDATE zadanie
SET opis = opis||' Bardzo trudne zadanie.'
WHERE pd > 250 AND pieniadze >= 200;


-- dodanie po jednym potworze więcej do zadań
-- wykonanych przez postacie, które aktualnie nie żyją
UPDATE potwory_w_zadaniu
SET liczba_potworow = liczba_potworow+1
WHERE zadanie IN (
    SELECT zadanie 
    FROM postac_zadanie 
    WHERE postac = (
        SELECT id 
        FROM postac 
        WHERE status = false
    )
);


-- usunięcie przedmiotów postaciom, które aktualnie przebywają w tawernie
DELETE FROM postac_przedmiot
WHERE postac IN (SELECT id FROM postac WHERE miejsce = 'tawerna');


-- usunięcie potworów w zadaniach, które nie mają określonego miejsca
DELETE FROM potwory_w_zadaniu
WHERE zadanie IN (SELECT id FROM zadanie WHERE miejsce IS NULL);