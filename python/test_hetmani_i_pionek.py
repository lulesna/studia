import pytest

from hetmani_i_pionek import (
    generowanie_mapy,
    indeks_na_pole,
    pole_na_indeks,
    weryfikacja_bicia,
    nowa_pozycja_pionka,
    usun_hetmana
)

mapa = [[" "] * 8 for _ in range(8)]


@pytest.mark.parametrize("k", [1, 2, 3, 4, 5])
def test_generowanie_mapy(k):
    wiersz_pionka, kolumna_pionka, mapa = generowanie_mapy(k)
    # sprawdzenie liczby hetmanów
    liczba_hetmanow = sum(wiersz.count("H") for wiersz in mapa)
    assert liczba_hetmanow == k
    # sprawdzenie pozycji pionka
    assert mapa[wiersz_pionka][kolumna_pionka] == "P"


@pytest.mark.parametrize("pole, wiersz, kolumna", [("a1", 7, 0), ("d7", 1, 3),  ("b2", 6, 1), ("h8", 0, 7)])
def test_indeks_na_pole(pole, wiersz, kolumna):
    assert indeks_na_pole(wiersz, kolumna) == pole


@pytest.mark.parametrize("pole, wiersz, kolumna", [("a1", 7, 0), ("g6", 2, 6),  ("c3", 5, 2), ("h8", 0, 7)])
def test_pole_na_indeks(pole, wiersz, kolumna):
    assert pole_na_indeks(pole) == (wiersz, kolumna)


def test_weryfikacja_bicia():
    mapa = [
        [" ", " ", " ", "H", " ", " ", " ", " "],
        [" ", " ", " ", " ", " ", " ", " ", " "],
        [" ", " ", " ", "H", " ", " ", " ", " "],
        [" ", " ", " ", "P", " ", " ", " ", "H"],
        [" ", " ", " ", " ", " ", " ", " ", " "],
        [" ", " ", " ", " ", " ", "H", " ", " "],
        ["H", " ", " ", " ", " ", " ", " ", " "],
        [" ", " ", " ", " ", " ", " ", " ", " "]
    ]
    assert weryfikacja_bicia(3, 3, mapa) == [(2, 3), (3, 7), (5, 5), (6, 0)]
    mapa = [
        [" ", " ", " ", " ", " ", " ", " ", " "],
        [" ", " ", " ", " ", " ", " ", " ", " "],
        [" ", " ", " ", " ", "H", "H", "H", " "],
        [" ", " ", " ", " ", "H", "P", "H", " "],
        [" ", " ", " ", " ", "H", "H", "H", " "],
        [" ", " ", " ", " ", " ", " ", " ", " "],
        [" ", " ", " ", " ", " ", " ", " ", " "],
        [" ", " ", " ", " ", " ", " ", " ", " "]
    ]
    assert weryfikacja_bicia(3, 5, mapa) == [(4, 5), (2, 5), (3, 6), (3, 4), (4, 6), (4, 4), (2, 6), (2, 4)]
    mapa = [
        [" ", " ", " ", " ", " ", " ", " ", " "],
        [" ", " ", "H", " ", " ", " ", " ", " "],
        [" ", " ", " ", " ", " ", " ", "H", " "],
        [" ", " ", " ", "P", " ", " ", " ", " "],
        [" ", " ", " ", " ", " ", " ", " ", " "],
        [" ", " ", " ", " ", " ", " ", " ", " "],
        [" ", " ", " ", " ", " ", " ", " ", " "],
        [" ", "H", " ", " ", " ", " ", " ", " "]
    ]
    assert weryfikacja_bicia(3, 3, mapa) == []


@pytest.mark.parametrize("stary_wiersz_pionka, stara_kolumna_pionka", [(3, 3), (0, 7), (1, 5)])
def test_nowa_pozycja_pionka(stary_wiersz_pionka, stara_kolumna_pionka):
    mapa[stary_wiersz_pionka][stara_kolumna_pionka] = "P"
    nowy_wiersz_pionka, nowa_kolumna_pionka = nowa_pozycja_pionka(mapa, stary_wiersz_pionka, stara_kolumna_pionka)
    # sprawdzenie, czy na starej pozycji pionka jest spacja
    assert mapa[stary_wiersz_pionka][stara_kolumna_pionka] == " "
    # sprawdzenie, czy na nowej pozycji pionka jest P
    assert mapa[nowy_wiersz_pionka][nowa_kolumna_pionka] == "P"


@pytest.mark.parametrize("wiersz, kolumna", [(3, 3), (0, 7), (1, 5)])
def test_usun_hetmana(wiersz, kolumna):
    mapa[wiersz][kolumna] = "H"
    nowa_mapa = usun_hetmana(mapa, wiersz, kolumna)
    # sprawdzenie, czy na starej pozycji hetmana jest spacja
    assert nowa_mapa[wiersz][kolumna] == " "
