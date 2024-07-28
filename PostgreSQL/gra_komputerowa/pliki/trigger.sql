SET client_encoding TO 'UTF-8';
-- CREATE LANGUAGE plpgsql;

-- poprawienie nazwy postaci, aby każde słowo zaczynało się od wielkiej litery, a reszta liter ma być mała
CREATE FUNCTION popraw_nazwe_postaci()
    RETURNS TRIGGER AS $$
DECLARE
    poprawiona_nazwa varchar;
BEGIN
    poprawiona_nazwa := INITCAP(LOWER(NEW.nazwa));
    new.nazwa := poprawiona_nazwa;
    RETURN new;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER popraw_nazwe_postaci
    BEFORE INSERT OR UPDATE ON postac
    FOR EACH ROW
    EXECUTE PROCEDURE popraw_nazwe_postaci();


-- dodanie kolumny do tabeli postac z datą i czasem jej utworzenia
ALTER TABLE postac ADD COLUMN data_utworzenia timestamp;

CREATE FUNCTION dodaj_date_utworzenia()
    RETURNS TRIGGER AS $$
BEGIN
    new.data_utworzenia = current_timestamp;
    RETURN new;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER dodaj_date_utworzenia
    BEFORE INSERT ON postac
    FOR EACH ROW
    EXECUTE PROCEDURE dodaj_date_utworzenia();


-- dodanie tabeli, która będzie przechowywać informacje o usuniętych postaciach
CREATE TABLE usunieta_postac (
    id                serial,
    postac_id         int,
    nazwa             varchar(32),
    uzytkownik        varchar(20),
    data_utworzenia   timestamp,
    data_usuniecia    timestamp,
    CONSTRAINT        usunieta_postac_id_pk PRIMARY KEY(id)
);

CREATE FUNCTION usunieta_postac_log() 
    RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO usunieta_postac (postac_id, nazwa, uzytkownik, data_utworzenia, data_usuniecia)
    VALUES (old.id, old.nazwa, old.uzytkownik, old.data_utworzenia, current_timestamp);
    RETURN old;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER usunieta_postac_log
    AFTER DELETE ON postac
    FOR EACH ROW
    EXECUTE PROCEDURE usunieta_postac_log();


