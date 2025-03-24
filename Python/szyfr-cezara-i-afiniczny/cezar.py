import sys

# autorka programu: Łucja Leśna


# zmienia polskie znaki na łacińskie
def usun_polskie_znaki(x):
    polskie_znaki = "ąćęłńóśźżĄĆĘŁŃÓŚŹŻ"
    odpowiedniki = "acelnoszzACELNOSZZ"
    nowy_tekst = ""

    for znak in x:
        if znak in polskie_znaki:
            nowy_tekst += odpowiedniki[polskie_znaki.index(znak)]
        else:
            nowy_tekst += znak

    return nowy_tekst

# k - klucz
def sprawdz_klucz_cezar(k):
    if k < 1 or k > 25:
        print("Klucz dla szyfru Cezara (k) musi znajdować się w przedziale od 1 do 25")
        exit()


def NWD(a, b):
    while a != b:
        if a > b:
            a -= b
        else:
            b -= a
    return a


def odwrotnosc_modulo_26(a):
    for a1 in range(1, 26):
        if (a * a1) % 26 == 1:
            return a1
    print(f"Nie można znaleźć odwrotności modulo 26 dla liczby a={a}")
    exit()


# (a, b) - para liczb tworząca klucz
def sprawdz_klucz_afiniczny(a, b):
    if a < 1 or a > 25 or b < 0 or b > 25:
        print("Wartości klucza dla szyfru afinicznego są nieprawidłowe: 1 <= a <= 25 i 0 <= b <= 25")
        exit()

    # NWD(a,26) musi być równe 1
    if NWD(a, 26) != 1:
        print(f"Wartość a={a} nie jest względnie pierwsza z 26")
        exit()

    # sprawdzenie, czy istnieje odwrotność mod 26 z a
    odwrotnosc_modulo_26(a)


# x - tekst jawny
# y - kryptogram
# funkcja szyfrująca: E(k,x)=x+k (mod 26) dla szyfru Cezara (k=b, a=1)
# funkcja szyfrująca: E(a,b,x)=a·x+b (mod 26) dla szyfru afinicznego
def szyfruj(x, a, b):
    if a == 1:
        sprawdz_klucz_cezar(b)
    else:
        sprawdz_klucz_afiniczny(a, b)

    y = ""
    for znak in x:
        if znak.isalpha():
            kod_ascii = ord(znak)
            if znak.islower():
                liczba = kod_ascii - ord('a')  # liczba, za pomocą której jest zakodowana litera (od 0 do 25)
                E = (a * liczba + b) % 26
                zaszyfrowany_znak = chr(E + ord('a'))
            else:
                liczba = kod_ascii - ord('A')
                E = (a * liczba + b) % 26
                zaszyfrowany_znak = chr(E + ord('A'))
            y += zaszyfrowany_znak
        else:
            y += znak
    return y


# funkcja deszyfrująca: D(k,y)=1·(y-k) (mod 26) dla szyfru Cezara (k=b, a=1)
# funkcja deszyfrująca: D(a,b,y)=a'·(y-b) (mod 26) dla szyfru afinicznego
# a1 = a' - odwrotność modulo 26
def deszyfruj(y, a, b):
    if a == 1:
        sprawdz_klucz_cezar(b)
    else:
        sprawdz_klucz_afiniczny(a, b)

    a1 = odwrotnosc_modulo_26(a)

    x = ""
    for znak in y:
        if znak.isalpha():
            kod_ascii = ord(znak)
            if znak.islower():
                liczba = kod_ascii - ord('a')
                D = (a1 * (liczba - b)) % 26
                odszyfrowany_znak = chr(D + ord('a'))
            else:
                liczba = kod_ascii - ord('A')
                D = (a1 * (liczba - b)) % 26
                odszyfrowany_znak = chr(D + ord('A'))
            x += odszyfrowany_znak
        else:
            x += znak
    return x


def sprawdz_tekst_pomocniczy(y, x_pomocniczy, szyfr):
    if len(y) < len(x_pomocniczy):
        print("Kryptogram jest krótszy niż tekst pomocniczy")
        exit()

    if szyfr == 'c':
        if len(x_pomocniczy) < 1:
            # jedna litera pary tekst jawny+zaszyfrowany wystarczy
            print("Tekst pomocniczy musi zawierać co najmniej 1 literę")
            exit()

    if szyfr == 'a':
        if len(x_pomocniczy) < 2:
            # dwie litery tekstu jawnego+zaszyfrowanego często wystarczą, kilka par prawie na pewno
            print("Tekst pomocniczy musi zawierać co najmniej 2 litery")
            exit()


# y - kryptogram
# x_pomocniczy - fragment tekstu jawnego (co najmniej jedna litera)
# k - klucz
def kryptoanaliza_tekst_pomocniczy_cezar(y, x_pomocniczy):
    szyfr = 'c'
    sprawdz_tekst_pomocniczy(y, x_pomocniczy, szyfr)

    for i in range(len(x_pomocniczy)):
        if y[i].isalpha() and x_pomocniczy[i].isalpha():
            if x_pomocniczy[i].islower():
                x_liczba = ord(x_pomocniczy[i]) - ord('a')
                y_liczba = ord(y[i]) - ord('a')
            else:
                x_liczba = ord(x_pomocniczy[i]) - ord('A')
                y_liczba = ord(y[i]) - ord('A')

            k = (y_liczba - x_liczba) % 26  # z przekształcenia wzoru szyfrowania

            x = deszyfruj(y, 1, k)
            return k, x

    print("Nie można obliczyć klucza")
    exit()


# y - kryptogram
# k - potencjalny klucz
def kryptoanaliza_kryptogram_cezar(y):
    kandydaci = []

    for k in range(1, 26):
        x_kandydat = deszyfruj(y, 1, k)
        kandydaci.append((k, x_kandydat))

    return kandydaci


# y - kryptogram
# x_pomocniczy - fragment tekstu jawnego (co najmniej dwie litery)
# (a, b) - klucz
def kryptoanaliza_tekst_pomocniczy_afiniczny(y, x_pomocniczy):
    szyfr = 'a'
    sprawdz_tekst_pomocniczy(y, x_pomocniczy, szyfr)

    pary_liter = []

    for i in range(len(x_pomocniczy)):
        if y[i].isalpha() and x_pomocniczy[i].isalpha():
            if x_pomocniczy[i].islower():
                x_liczba = ord(x_pomocniczy[i]) - ord('a')
                y_liczba = ord(y[i]) - ord('a')
            else:
                x_liczba = ord(x_pomocniczy[i]) - ord('A')
                y_liczba = ord(y[i]) - ord('A')
            pary_liter.append((x_liczba, y_liczba))

    if len(pary_liter) < 2:
        print("Nie znaleziono dwóch par liter do obliczenia klucza")
        exit()

    for a in range(1, 26):
        x1, y1 = pary_liter[0]
        b = (y1 - a * x1) % 26  # z przekształcenia wzoru szyfrowania

        # upewnienie się, że znaleziony klucz pasuje do pozostałych par liter
        klucz_pasuje = True
        for x_liczba, y_liczba in pary_liter[1:]:
            if (a * x_liczba + b) % 26 != y_liczba:
                klucz_pasuje = False
                break
        if klucz_pasuje:
            x = deszyfruj(y, a, b)
            return a, b, x

    print("Nie znaleziono pasującego klucza")
    exit()


# y - kryptogram
# (a, b) - potencjalny klucz
def kryptoanaliza_kryptogram_afiniczny(y):
    kandydaci = []

    for a in range(1, 26):
        if NWD(a, 26) == 1:
            for b in range(0, 26):
                if a == 1 and b == 0:
                    continue
                x_kandydat = deszyfruj(y, a, b)
                kandydaci.append((a, b, x_kandydat))

    return kandydaci


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


def wczytaj_klucz(nazwa_pliku):
    zawartosc = odczyt_pliku(nazwa_pliku)
    klucze = zawartosc.split(" ")

    if len(klucze) != 2:
        print(f"Błąd w pliku: {nazwa_pliku} - oczekiwano dwóch liczb oddzielonych spacją")
        exit()

    try:
        b = int(klucze[0])  # przesunięcie
        a = int(klucze[1])  # współczynnik dla szyfru afinicznego
    except TypeError:
        raise TypeError(f"Błąd w pliku: {nazwa_pliku} - nie udało się przekonwertować na liczby całkowite")

    return a, b


def main():
    if len(sys.argv) != 3:
        print("Nieprawidłowa liczba argumentów, powinno być: cezar.py -c/-a -e/-d/-j/-k")
        exit()

    if "-c" not in sys.argv and "-a" not in sys.argv:
        print("Nie podano rodzaju szyfru:\n-c - szyfr Cezar,\n-a - szyfr afiniczny")
        exit()

    if "-e" not in sys.argv and "-d" not in sys.argv and "-j" not in sys.argv and "-k" not in sys.argv:
        print("Nie podano opcji szyfru:\n-e - szyfrowanie,\n-d - odszyfrowywanie,\n-j - kryptoanaliza z tekstem jawnym,\n-k - kryptoanaliza wyłącznie w oparciu o kryptogram")
        exit()

    # szyfrowanie
    if "-e" in sys.argv:
        x = odczyt_pliku("plain.txt")
        x = usun_polskie_znaki(x)
        a, b = wczytaj_klucz("key.txt")
        if "-c" in sys.argv:
            a = 1
        y = szyfruj(x, a, b)
        zapis_do_pliku("crypto.txt", y)

    # odszyfrowywanie
    if "-d" in sys.argv:
        y = odczyt_pliku("crypto.txt")
        a, b = wczytaj_klucz("key.txt")
        if "-c" in sys.argv:
            a = 1
        x_odszyfrowany = deszyfruj(y, a, b)
        zapis_do_pliku("decrypt.txt", x_odszyfrowany)

    # kryptoanaliza z tekstem jawnym
    if "-j" in sys.argv:
        y = odczyt_pliku("crypto.txt")
        x_pomocniczy = odczyt_pliku("extra.txt")
        x_pomocniczy = usun_polskie_znaki(x_pomocniczy)
        if "-c" in sys.argv:
            nowy_k, x_kryptoanaliza_tekst_jawny = kryptoanaliza_tekst_pomocniczy_cezar(y, x_pomocniczy)
            zapis_do_pliku("key-found.txt", str(nowy_k))
            zapis_do_pliku("decrypt.txt", x_kryptoanaliza_tekst_jawny)
        elif "-a" in sys.argv:
            nowe_a, nowe_b, x_kryptoanaliza_tekst_jawny = kryptoanaliza_tekst_pomocniczy_afiniczny(y, x_pomocniczy)
            nowy_k = str(nowe_b) + " " + str(nowe_a)
            zapis_do_pliku("key-found.txt", nowy_k)
            zapis_do_pliku("decrypt.txt", x_kryptoanaliza_tekst_jawny)

    # kryptoanaliza wyłącznie w oparciu o kryptogram
    if "-k" in sys.argv:
        y = odczyt_pliku("crypto.txt")
        if "-c" in sys.argv:
            kandydaci = kryptoanaliza_kryptogram_cezar(y)
            potencjalne_x = ""
            for i, (k, x_kandydat) in enumerate(kandydaci):
                potencjalne_x += f"k={k}: {x_kandydat}"
                if i < len(kandydaci) - 1:
                    potencjalne_x += "\n"
            zapis_do_pliku("decrypt.txt", potencjalne_x)
        elif "-a" in sys.argv:
            kandydaci = kryptoanaliza_kryptogram_afiniczny(y)
            potencjalne_x = ""
            for i, (a, b, x_kandydat) in enumerate(kandydaci):
                potencjalne_x += f"a={a}, b={b}: {x_kandydat}"
                if i < len(kandydaci) - 1:
                    potencjalne_x += "\n"
            zapis_do_pliku("decrypt.txt", potencjalne_x)


if __name__ == "__main__":
    main()
