import struct
import hashlib
import os

# autorka programu: Łucja Leśna

def wczytaj_dane_obrazu(plik, szerokosc, wysokosc, glebia):
    bajty_na_piksel = glebia // 8
    padding = (4 - (szerokosc * bajty_na_piksel) % 4) % 4
    dane_obrazu = []

    for _ in range(wysokosc):
        wiersz = []
        for _ in range(szerokosc):
            if glebia == 24:
                b, g, r = struct.unpack('BBB', plik.read(3))
                # konwersja do odcienia szarości
                piksel = int(0.299 * r + 0.587 * g + 0.114 * b)
                wiersz.append(piksel)
            else:  # głębia_bitowa == 8 (skala szarości)
                piksel = struct.unpack('B', plik.read(1))[0]
                wiersz.append(piksel)

        plik.read(padding)
        dane_obrazu.append(wiersz)

    return dane_obrazu


def wczytaj_bmp(nazwa_pliku):
    try:
        with open(nazwa_pliku, 'rb') as plik:
            naglowek = plik.read(14)  # wczytanie 14-bajtowego nagłówka

            # sprawdzenie formatu pliku
            if naglowek[:2] != b'BM':
                print(f"Błąd: {nazwa_pliku} nie jest prawidłowym plikiem BMP")
                exit()

            # odczyt offsetu danych pikseli
            offset_pikseli = struct.unpack('<I', naglowek[10:14])[0]  # '<I' oznacza: little-endian, 4-bajtowa liczba całkowita

            # wczytanie 40-bajtowego nagłówka
            info_naglowek = plik.read(40)  # format BITMAPINFOHEADER
            szerokosc = struct.unpack('<i', info_naglowek[4:8])[0]
            wysokosc = struct.unpack('<i', info_naglowek[8:12])[0]
            glebia = struct.unpack('<H', info_naglowek[14:16])[0]  # bity na piksel

            if glebia != 8 and glebia != 24:
                print(f"Błąd: Obsługiwane są tylko obrazy 8-bitowe lub 24-bitowe, ten obraz ma {glebia} bitów")
                exit()

            plik.seek(offset_pikseli)  # przejście do początku danych pikseli

            # jeśli wysokość jest dodatnia, to dane idą od dołu do góry
            odwroc = wysokosc > 0
            wysokosc = abs(wysokosc)
            dane_obrazu = wczytaj_dane_obrazu(plik, szerokosc, wysokosc, glebia)

            # odwórcenie wierszy, jeśli potrzeba
            if odwroc:
                dane_obrazu.reverse()

            return dane_obrazu, szerokosc, wysokosc, naglowek, info_naglowek, glebia

    except FileNotFoundError:
        print(f"Nie znaleziono pliku: {nazwa_pliku}")
        exit()
    except Exception as e:
        print(f"Błąd przy odczycie pliku {nazwa_pliku}: {e}")
        exit()


def zapisz_dane_pikseli(plik, dane_obrazu, szerokosc, glebia):
    bajty_na_piksel = glebia // 8
    padding = (4 - (szerokosc * bajty_na_piksel) % 4) % 4
    padding_bajty = b'\x00' * padding

    # zapisywanie danych pikseli (od dołu do góry)
    for wiersz in reversed(dane_obrazu):
        for piksel in wiersz:
            if glebia == 24:  # dla kolorowych
                plik.write(struct.pack('BBB', piksel, piksel, piksel))
            else:  # dla 8-bitowych
                plik.write(struct.pack('B', piksel))

        plik.write(padding_bajty)


def zapisz_bmp(dane_obrazu, szerokosc, naglowek, info_naglowek, glebia, nazwa_pliku):
    try:
        with open(nazwa_pliku, 'wb') as plik:
            plik.write(naglowek)
            plik.write(info_naglowek)

            if glebia == 8:
                # 256 odcieni szarości
                for i in range(256):
                    plik.write(struct.pack('BBBB', i, i, i, 0))

            zapisz_dane_pikseli(plik, dane_obrazu, szerokosc, glebia)

    except Exception as e:
        print(f"Błąd przy zapisie do pliku {nazwa_pliku}: {e}")
        exit()


def wczytaj_klucz(nazwa_pliku):
    if os.path.exists(nazwa_pliku):
        try:
            with open(nazwa_pliku, 'r') as plik:
                klucz = plik.read().strip()
                if not klucz:
                    print(f"Plik {nazwa_pliku} jest pusty - użycie klucza domyślnego")
                    return "klucz"
                return klucz
        except Exception as e:
            print(f"Błąd przy odczycie pliku {nazwa_pliku}: {e} - użycie klucza domyślnego")
            return "klucz"
    else:
        print(f"Plik {nazwa_pliku} nie istnieje - użycie klucza domyślnego")
        return "klucz"


def string_na_bajty(tekst):
    bajty = bytearray()
    for znak in tekst:
        bajty.append(ord(znak))
    return bajty


def szyfruj_blok(blok, klucz):
    # konwersja bloku do ciągu bajtów
    dane = bytearray()
    for wiersz in blok:
        for piksel in wiersz:
            dane.append(piksel)

    dane_z_kluczem = dane + string_na_bajty(klucz)

    skrot_md5 = hashlib.md5(dane_z_kluczem).digest()

    # rozciągniecie skrótu do 64 wartości (dla bloku 8x8)
    wynik_plaski = []
    while len(wynik_plaski) < 64:
        for bajt in skrot_md5:
            wynik_plaski.append(bajt)
            if len(wynik_plaski) >= 64:
                break

        # ponowne haszowanie, jeśli potrzeba
        if len(wynik_plaski) < 64:
            skrot_md5 = hashlib.md5(skrot_md5 + string_na_bajty(klucz)).digest()

    # zmiana płaskiego wyniku do bloku 2D 8x8
    zaszyfrowany_blok = []
    for i in range(8):
        wiersz = []
        for j in range(8):
            wiersz.append(wynik_plaski[i * 8 + j])
        zaszyfrowany_blok.append(wiersz)

    return zaszyfrowany_blok


def xor_blokow(blok1, blok2):
    wynik = []
    for i in range(len(blok1)):
        wiersz = []
        for j in range(len(blok1[i])):
            wiersz.append(blok1[i][j] ^ blok2[i][j])
        wynik.append(wiersz)
    return wynik


# generowanie wektora inicjalizacyjnego (IV) na podstawie klucza
def generuj_IV(klucz):
    iv = []
    for i in range(8):
        wiersz = []
        for j in range(8):
            wartosc = (ord(klucz[i % len(klucz)]) + i * 3 + j * 7) % 256
            wiersz.append(wartosc)
        iv.append(wiersz)
    return iv


def main():
    klucz = wczytaj_klucz("key.txt")

    (dane_obrazu, szerokosc, wysokosc, naglowek, info_naglowek, glebia_bitowa) = wczytaj_bmp("plain.bmp")

    rozmiar_bloku = 8

    liczba_blokow_x = szerokosc // rozmiar_bloku
    liczba_blokow_y = wysokosc // rozmiar_bloku

    # przycięcie obrazka do wielokrotności rozmiaru bloku
    nowa_szerokosc = liczba_blokow_x * rozmiar_bloku
    nowa_wysokosc = liczba_blokow_y * rozmiar_bloku

    if nowa_szerokosc != szerokosc or nowa_wysokosc != wysokosc:
        print(f"Obraz zostanie przycięty do wymiarów {nowa_szerokosc}x{nowa_wysokosc}")

        nowe_dane_obrazu = []
        for y in range(nowa_wysokosc):
            wiersz = []
            for x in range(nowa_szerokosc):
                wiersz.append(dane_obrazu[y][x])
            nowe_dane_obrazu.append(wiersz)

        dane_obrazu = nowe_dane_obrazu
        szerokosc = nowa_szerokosc
        wysokosc = nowa_wysokosc

        info_naglowek = bytearray(info_naglowek)
        struct.pack_into('<i', info_naglowek, 4, szerokosc)
        struct.pack_into('<i', info_naglowek, 8, wysokosc)
        info_naglowek = bytes(info_naglowek)

    ecb_dane = []
    cbc_dane = []
    for _ in range(wysokosc):
        ecb_dane.append([0] * szerokosc)
        cbc_dane.append([0] * szerokosc)

    # przygotowanie wektora inicjalizującego dla CBC
    wektor_iv = generuj_IV(klucz)
    poprzedni_blok_cbc = wektor_iv

    for y in range(liczba_blokow_y):
        for x in range(liczba_blokow_x):
            # wydzielenie bloku
            blok = []
            for i in range(rozmiar_bloku):
                wiersz = []
                for j in range(rozmiar_bloku):
                    wiersz.append(dane_obrazu[y * rozmiar_bloku + i][x * rozmiar_bloku + j])
                blok.append(wiersz)

            # szyfrowanie ECB: każdy blok szyfrowany niezależnie
            zaszyfrowany_blok_ecb = szyfruj_blok(blok, klucz)

            # szyfrowanie CBC: XOR z poprzednim blokiem -> szyfrowanie
            xorowany_blok = xor_blokow(blok, poprzedni_blok_cbc)
            zaszyfrowany_blok_cbc = szyfruj_blok(xorowany_blok, klucz)
            poprzedni_blok_cbc = zaszyfrowany_blok_cbc

            for i in range(rozmiar_bloku):
                for j in range(rozmiar_bloku):
                    ecb_dane[y * rozmiar_bloku + i][x * rozmiar_bloku + j] = zaszyfrowany_blok_ecb[i][j]
                    cbc_dane[y * rozmiar_bloku + i][x * rozmiar_bloku + j] = zaszyfrowany_blok_cbc[i][j]

    zapisz_bmp(ecb_dane, szerokosc, naglowek, info_naglowek, glebia_bitowa, "ecb_crypto.bmp")
    zapisz_bmp(cbc_dane, szerokosc, naglowek, info_naglowek, glebia_bitowa, "cbc_crypto.bmp")


if __name__ == "__main__":
    main()
