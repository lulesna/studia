import random

# zmienne do zmiany koloru terminalu
bialy = "\33[0m"
pogrubiony = "\33[1m"
czerwony = "\33[1;91m"
zielony = "\33[1;92m"


def liczba_hetmanow():
    while True:
        k = int(input("\nPodaj liczbę hetmanów (od 1 do 5): "))
        if 1 <= k <= 5:
            break
        else:
            print("Podana liczba hetmanów jest niepoprawna.")
    return k


def generowanie_mapy(k):
    # dwuwymiarowa lista 8x8 wypełniona spacjami
    mapa = [[" "] * 8 for _ in range(8)]

    # rozmieszczenie hetmanów
    for _ in range(k):
        while True:
            wiersz = random.randint(0, 7)
            kolumna = random.randint(0, 7)
            if mapa[wiersz][kolumna] == " ":
                mapa[wiersz][kolumna] = "H"
                break

    # rozmieszczenie pionka
    while True:
        wiersz_pionka = random.randint(0, 7)
        kolumna_pionka = random.randint(0, 7)
        if mapa[wiersz_pionka][kolumna_pionka] == " ":
            mapa[wiersz_pionka][kolumna_pionka] = "P"
            break

    return wiersz_pionka, kolumna_pionka, mapa


def wyswietlenie_mapy(mapa):
    # oznaczenia kolumn a-h
    print(f"\n{pogrubiony}    a   b   c   d   e   f   g   h")
    # iteracja po wierszach mapy
    for nr_wiersza, wiersz in enumerate(mapa):
        # numer wiersza 8-1
        print(f"{pogrubiony}{8 - nr_wiersza}{bialy}", end=' ')
        caly_wiersz = "|"
        for pole in wiersz:
            if pole == " ": caly_wiersz += f"_{pole}_|"
            if pole == "P": caly_wiersz += f"_{zielony}{pole}{bialy}_|"
            if pole == "H": caly_wiersz += f"_{czerwony}{pole}{bialy}_|"
        print(caly_wiersz)


def indeks_na_pole(wiersz, kolumna):
    litera = chr(97 + kolumna)  # chr(kod ASCII) -> znak
    cyfra = str(8 - wiersz)
    return f"{litera}{cyfra}"


def pole_na_indeks(pole_szachowe):
    kolumna = ord(pole_szachowe[0]) - 97  # ord(znak) -> kod ASCII
    wiersz = 8 - int(pole_szachowe[1])
    return wiersz, kolumna


def wyswietlenie_weryfikacji_bicia(hetmani_ktorzy_moga_zbic):
    if hetmani_ktorzy_moga_zbic:
        print("\nHetmani, którzy mogą zbić pionka:", end=' ')
        for hetman in hetmani_ktorzy_moga_zbic:
            pole_szachowe = indeks_na_pole(hetman[0], hetman[1])
            print(czerwony, pole_szachowe, bialy, sep='', end=' ')
        print()
    else:
        print("\nPionek jest bezpieczny.")


def weryfikacja_bicia(wiersz_pionka, kolumna_pionka, mapa):
    hetmani_ktorzy_moga_zbic = []
    for x, y in ((1, 0), (-1, 0), (0, 1), (0, -1), (1, 1), (1, -1), (-1, 1), (-1, -1)):
        wiersz, kolumna = wiersz_pionka, kolumna_pionka
        while True:
            wiersz += x
            kolumna += y
            if wiersz < 0 or wiersz >= 8 or kolumna < 0 or kolumna >= 8:
                break
            if mapa[wiersz][kolumna] == "H":
                hetmani_ktorzy_moga_zbic.append((wiersz, kolumna))
                break
    return hetmani_ktorzy_moga_zbic


def nowa_pozycja_pionka(mapa, stary_wiersz_pionka, stara_kolumna_pionka):
    # zmiana "P" na " "
    mapa[stary_wiersz_pionka][stara_kolumna_pionka] = " "
    # wylosowanie nowego pola dla pionka
    while True:
        nowy_wiersz_pionka = random.randint(0, 7)
        nowa_kolumna_pionka = random.randint(0, 7)
        # pod warunkiem, że nowa pozycja jest różna od starej, i że nie stoi tam hetman
        if (nowy_wiersz_pionka, nowa_kolumna_pionka) != (stary_wiersz_pionka, stara_kolumna_pionka) and \
                mapa[nowy_wiersz_pionka][nowa_kolumna_pionka] == " ":
            mapa[nowy_wiersz_pionka][nowa_kolumna_pionka] = "P"
            return nowy_wiersz_pionka, nowa_kolumna_pionka


def usun_hetmana(mapa, wiersz, kolumna):
    # zmiana "H" na " "
    mapa[wiersz][kolumna] = " "
    return mapa

def opcje(wiersz_pionka, kolumna_pionka, mapa, k):
    while True:
        print(f"\n{pogrubiony}Co chcesz zrobić?{bialy}")
        print("1. Wylosuj nową pozycję dla pionka.")
        print("2. Usuń hetmana.")
        print("3. Ponowna weryfikacja bicia.")
        print("4. Wygeneruj nową mapę.")
        print("5. Zakończ program.")
        wybor = input(f"{pogrubiony}Wybierz opcję: {bialy}")

        if wybor == "1":
            stary_wiersz_pionka = wiersz_pionka
            stara_kolumna_pionka = kolumna_pionka
            wiersz_pionka, kolumna_pionka = nowa_pozycja_pionka(mapa, stary_wiersz_pionka, stara_kolumna_pionka)
            wyswietlenie_mapy(mapa)
        elif wybor == "2":
            pole_szachowe = input("\nPodaj pole, na którym znajduje się hetman do usunięcia: ")
            wiersz, kolumna = pole_na_indeks(pole_szachowe)
            if mapa[wiersz][kolumna] == "H":
                mapa = usun_hetmana(mapa, wiersz, kolumna)
                wyswietlenie_mapy(mapa)
            else:
                print("Na podanym polu nie ma hetmana.\n")
        elif wybor == "3":
            wyswietlenie_weryfikacji_bicia(weryfikacja_bicia(wiersz_pionka, kolumna_pionka, mapa))
        elif wybor == "4":
            wiersz_pionka, kolumna_pionka, mapa = generowanie_mapy(k)
            wyswietlenie_mapy(mapa)
        elif wybor == "5":
            print("\nKoniec programu.")
            break
        else:
            print("Podano niepoprawną opcję.")

def main():
    k = liczba_hetmanow()
    wiersz_pionka, kolumna_pionka, mapa = generowanie_mapy(k)
    wyswietlenie_mapy(mapa)
    wyswietlenie_weryfikacji_bicia(weryfikacja_bicia(wiersz_pionka, kolumna_pionka, mapa))
    opcje(wiersz_pionka, kolumna_pionka, mapa, k)

# sprawdza, czy plik jest uruchamiany bezpośrednio, a nie importowany jako moduł
if __name__ == "__main__":
    main()
