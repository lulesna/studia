SET client_encoding='utf-8';

-- tabela z użytkownikami
CREATE TABLE uzytkownik
(
    login              varchar(20)         not null,
    haslo              varchar(32)         not null,
    CONSTRAINT         uzytkownik_login_pk PRIMARY KEY(login)
);

-- tabela z klasami i ich podstawowymi statystykami
CREATE TABLE klasa
(
    nazwa              varchar(20)         not null  ,
    opis               text                not null  ,
    sila               int                 default 10,
    wytrzymalosc       int                 default 10,
    zrecznosc          int                 default 10,
    inteligencja       int                 default 10,
    charyzma           int                 default 10,
    szczescie          int                 default 10,
    CONSTRAINT         klasa_nazwa_pk PRIMARY KEY(nazwa)
);

-- tabela z nazwami miejsc
CREATE TABLE miejsce
(
    nazwa              varchar(32)          not null,
    CONSTRAINT         miejsce_nazwa_pk PRIMARY KEY(nazwa)
);

-- tabela z zadaniami, miejscami zadań i nagrodami za nie
CREATE TABLE zadanie
(
    id                 serial,
    nazwa              varchar(50)          not null UNIQUE,
    miejsce            varchar(20)                         ,
    pd                 int                  not null       ,
    pieniadze          int                  not null       ,
    opis               text                 not null       ,
    CONSTRAINT         zadanie_id_pk PRIMARY KEY(id),
    CONSTRAINT         miejsce_fk FOREIGN KEY(miejsce)
                           REFERENCES miejsce(nazwa)
                           ON UPDATE CASCADE ON DELETE RESTRICT
);

-- tabela z potworami i ich statystykami
CREATE TABLE potwor
(
    id                 serial                              ,
    nazwa              varchar(20)          not null UNIQUE,
    atak               int                  not null       ,
    zdrowie            int                  not null       ,
    CONSTRAINT         potwor_id_pk PRIMARY KEY(id)
);

-- tabela przypisująca potwory do zadań i liczbę, w jakiej występują w zadaniu
CREATE TABLE potwory_w_zadaniu
(
    zadanie            int                  not null ,
    potwor             int                  not null ,
    liczba_potworow    int                  default 1,
    CONSTRAINT         zadanie_fk FOREIGN KEY(zadanie)
                           REFERENCES zadanie(id)
                           ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT         potwor_fk FOREIGN KEY(potwor)
                           REFERENCES potwor(id)
                           ON UPDATE CASCADE,
    CONSTRAINT        potwory_w_zadaniu_un UNIQUE(zadanie, potwor)
);

-- tabela z postaciami
CREATE TABLE postac
(
    id                serial                              ,
    nazwa             varchar(32)          not null       ,
    uzytkownik        varchar(20)          not null UNIQUE,
    status            boolean              default TRUE   ,
    klasa             varchar(20)          not null       ,
    miejsce           varchar(20)          not null       ,
    CONSTRAINT        postac_id_pk PRIMARY KEY(id),
    CONSTRAINT        uzytkownik_fk FOREIGN KEY(uzytkownik)
                          REFERENCES uzytkownik(login)
                          ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT        klasa_fk FOREIGN KEY(klasa)
                          REFERENCES klasa(nazwa)
                          ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT        miejsce_fk FOREIGN KEY(miejsce)
                          REFERENCES miejsce(nazwa)
                          ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT        postac_un UNIQUE(nazwa,uzytkownik)
);

-- tabela przypisująca wykonane zadania do postaci
CREATE TABLE postac_zadanie
(
    postac            int                  not null,
    zadanie           int                  not null,
    CONSTRAINT        postac_fk FOREIGN KEY(postac)
                          REFERENCES postac(id)
                          ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT        zadanie_fk FOREIGN KEY(zadanie)
                          REFERENCES zadanie(id)
                          ON UPDATE RESTRICT ON DELETE RESTRICT,
    CONSTRAINT        postac_zadanie_un UNIQUE(postac,zadanie)
);

-- tabela z typami przedmiotów
CREATE TABLE typ_przedmiotu
( 
    nazwa              varchar(32)          not null,
    CONSTRAINT         typ_przedmiotu_nazwa_pk PRIMARY KEY(nazwa)
);

-- tabela z przedmiotami
CREATE TABLE przedmiot
(
    id                serial                        ,
    nazwa             varchar(32)          not null ,
    typ               varchar(20)          not null ,
    opis              text                          ,
    sila              int                  default 0,
    wytrzymalosc      int                  default 0,
    zrecznosc         int                  default 0,
    inteligencja      int                  default 0,
    charyzma          int                  default 0,
    szczescie         int                  default 0,
    CONSTRAINT        przedmiot_id_pk PRIMARY KEY(id),
    CONSTRAINT        typ_fk FOREIGN KEY(typ)
                          REFERENCES typ_przedmiotu(nazwa)
                          ON UPDATE CASCADE ON DELETE CASCADE
);

-- tabela z przedmiotami, których aktualnie używa postać
CREATE TABLE postac_przedmiot
(
    postac            int                  not null,
    przedmiot         int                  not null,
    typ_przedmiotu    varchar(20)          not null,
    CONSTRAINT        postac_fk FOREIGN KEY(postac)
                          REFERENCES postac(id)
                          ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT        przedmiot_fk FOREIGN KEY(przedmiot)
                          REFERENCES przedmiot(id)
                          ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT        typ_przedmiotu_fk FOREIGN KEY(typ_przedmiotu)
                          REFERENCES typ_przedmiotu(nazwa)
                          ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT        postac_typ_przedmiotu_un UNIQUE(postac,typ_przedmiotu)
);