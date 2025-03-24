import sys

# autorka programu: Łucja Leśna


# wektor częstotliwości występowania liter w języku angielskim
W = [82, 15, 28, 43, 127, 22, 20, 61, 70, 2, 8, 40, 24, 67, 75, 29, 1, 60, 63, 91, 28, 10, 23, 1, 20, 1]


# przygotowanie tekstu jawnego do szyfrowania (tylko małe litery alfabetu)
def przygotuj_tekst(tekst):
    nowy_tekst = ""

    for znak in tekst:
        if znak.isalpha():
            nowy_tekst += znak.lower()

    return nowy_tekst


# wczytanie klucza Vigenère'a - układ liczb k=<k0,k1,...,k(n−1)> zapamiętywany raczej w postaci liter
def wczytaj_klucz_vigenere(nazwa_pliku):
    zawartosc = odczyt_pliku(nazwa_pliku)
    klucz = zawartosc.strip().lower()

    if not klucz or not all(znak.isalpha() for znak in klucz):
        print(f"Błąd w pliku: {nazwa_pliku} - klucz musi składać się tylko z liter")
        exit()

    klucz = przygotuj_tekst(klucz)

    # zamiana liter na liczby: a=0, b=1, ..., z=25
    klucz_liczby = []
    for znak in klucz:
        liczba = ord(znak) - ord('a')  # obliczamy różnicę
        klucz_liczby.append(liczba)  # dodajemy wynik do listy

    return klucz_liczby


def klucz_liczby_na_litery(klucz_liczby):
    klucz = ""
    for liczba in klucz_liczby:
        kod_znaku = liczba + ord('a')  # obliczamy kod ASCII odpowiadający tej liczbie
        znak = chr(kod_znaku)  # zamieniamy kod ASCII na znak
        klucz += znak
    return klucz


# x - tekst jawny, k - klucz jako liczby (0–25)
def szyfruj_vigenere(x, k):
    n = len(k)
    y = ""

    for m in range(len(x)):
        j = m % n
        xm = ord(x[m]) - ord('a')  # zamiana litery na liczbę 0-25
        E = (xm + k[j]) % 26  # E(k, x) = x + k[j]
        y += chr(E + ord('a'))  # zamiana liczby z powrotem na literę

    return y


# y - kryptogram, k - klucz jako liczby (0–25)
def deszyfruj_vigenere(y, k):
    n = len(k)
    x = ""

    for m in range(len(y)):
        j = m % n
        ym = ord(y[m]) - ord('a')  # zamiana litery na liczbę 0-25
        D = (ym - k[j]) % 26  # D(k, y) = y - kj
        x += chr(D + ord('a'))  # zamiana liczby z powrotem na literę

    return x


# porównuje oryginalny tekst z jego przesuniętą wersją i liczy ile liter jest identycznych
def oblicz_koincydencje(tekst, j):
    liczba_koincydencji = 0
    for i in range(len(tekst) - j):
        if tekst[i] == tekst[i + j]:
            liczba_koincydencji += 1

    # zwraca prawdopodobieństwo koincydencji
    return liczba_koincydencji / (len(tekst) - j)


# V - wektor częstotliwości
def oblicz_czestotliwosci(tekst):
    V = [0] * 26
    calkowita_liczba_liter = 0

    for znak in tekst:
        indeks = ord(znak) - ord('a')
        V[indeks] += 1
        calkowita_liczba_liter += 1

    # przeliczenie na promile
    suma = max(calkowita_liczba_liter, 1)
    V = [1000 * c / suma for c in V]
    return V


# iloczyn skalarny dwóch wektorów V oraz U to suma V[0]*U[0]+V[1]*U[1]+...+V[n-1]*U[n-1].
def iloczyn_skalarny(V, U):
    suma = 0.0
    for i in range(len(V)):
        suma += V[i] * U[i]
    return suma


# przesuwa wektor V o j pozycji, aby uzyskać Vj
def przesun_wektor(V, j):
    n = len(V)
    Vj = [0.0] * n
    for i in range(n):
        Vj[i] = V[(i - j) % n]
    return Vj


# j - przesunięcie
def znajdz_dlugosc_klucza(y):
    min_dlugosc = 4
    max_dlugosc = 20

    najlepsze_przesuniecie = 1
    max_koincydencja = 0.0

    for j in range(min_dlugosc, max_dlugosc + 1):
        koincydencja = oblicz_koincydencje(y, j)
        # szukanie przesunięcia, dla którego koincydencja jest znacząco większa
        if koincydencja > max_koincydencja and koincydencja > 0.06:
            max_koincydencja = koincydencja
            najlepsze_przesuniecie = j

    return najlepsze_przesuniecie


# y - kryptogram
def kryptoanaliza_vigenere(y):
    # Krok 1: trzeba znaleźć długość klucza
    n = znajdz_dlugosc_klucza(y)

    # Krok 2: Dla każdej pozycji klucza wydziel odpowiedni podciąg
    klucz = []
    for i in range(n):
        # wydzielenie części kryptogramu na pozycjach o numerach równych i modulo n
        podciag = y[i::n]
        # wektor częstotliwości dla podciągu
        V = oblicz_czestotliwosci(podciag)

        max_iloczyn = -1
        ki = 0

        for j in range(26):
            # wektor częstotliwości dla przesunięcia j
            Wj = przesun_wektor(W, j)

            iloczyn = iloczyn_skalarny(V, Wj)

            # szukanie j, dla którego iloczyn skalarny jest największy
            if iloczyn > max_iloczyn:
                max_iloczyn = iloczyn
                ki = j

        klucz.append(ki)

    # zmiana postaci klucza: liczby -> litery
    klucz_litery = klucz_liczby_na_litery(klucz)

    x = deszyfruj_vigenere(y, klucz)
    return klucz_litery, x


def odczyt_pliku(nazwa_pliku):
    try:
        plik = open(nazwa_pliku, "r", encoding="utf-8")
        tekst = plik.read()
        if not tekst.strip():
            print(f"Plik {nazwa_pliku} jest pusty")
            plik.close()
            exit()
        plik.close()
    except FileNotFoundError:
        raise FileNotFoundError(f"Nie znaleziono pliku: {nazwa_pliku}")
    except Exception as e:
        raise Exception(f"Błąd przy odczycie pliku {nazwa_pliku}: {e}")
    return tekst


def zapis_do_pliku(nazwa_pliku, tekst):
    try:
        plik = open(nazwa_pliku, "w", encoding="utf-8")
        plik.write(tekst)
        plik.close()
    except Exception as e:
        raise Exception(f"Błąd przy zapisie do pliku {nazwa_pliku}: {e}")


def main():
    if len(sys.argv) != 2:
        print("Nieprawidłowa liczba argumentów, powinno być: vigenere.py -p/-e/-d/-k")
        exit()

    opcja = sys.argv[1]
    if opcja not in ["-p", "-e", "-d", "-k"]:
        print("Nie podano opcji:\n-p - przygotowanie tekstu jawnego,\n-e - szyfrowanie,\n-d - odszyfrowywanie,\n-k - kryptoanaliza wyłącznie w oparciu o kryptogram")
        exit()

    # przygotowanie tekstu jawnego do szyfrowania
    if opcja == "-p":
        tekst = odczyt_pliku("orig.txt")
        tekst_przygotowany = przygotuj_tekst(tekst)
        zapis_do_pliku("plain.txt", tekst_przygotowany)

    # szyfrowanie
    if opcja == "-e":
        x = odczyt_pliku("plain.txt")
        k = wczytaj_klucz_vigenere("key.txt")
        y = szyfruj_vigenere(x, k)
        zapis_do_pliku("crypto.txt", y)

    # odszyfrowywanie
    if opcja == "-d":
        y = odczyt_pliku("crypto.txt")
        k = wczytaj_klucz_vigenere("key.txt")
        x_odszyfrowany = deszyfruj_vigenere(y, k)
        zapis_do_pliku("decrypt.txt", x_odszyfrowany)

    # kryptoanaliza wyłącznie w oparciu o kryptogram
    if opcja == "-k":
        y = odczyt_pliku("crypto.txt")
        znaleziony_klucz, odszyfrowany_tekst = kryptoanaliza_vigenere(y)
        zapis_do_pliku("key-found.txt", znaleziony_klucz)
        zapis_do_pliku("decrypt.txt", odszyfrowany_tekst)


if __name__ == "__main__":
    main()
