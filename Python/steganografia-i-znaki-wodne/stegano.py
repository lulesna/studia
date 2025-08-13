import sys
import re

# autorka kodu: Łucja Leśna

def hex_na_bity(ciag_hex):
    # 1 znak -> 4 bity
    bity = ""
    for znak in ciag_hex.strip():
        if znak in "0123456789abcdef":
            bity += format(int(znak, 16), '04b')
    return bity


def bity_na_hex(bity):
    # 4 bity -> 1 znak
    ciag_hex = ""
    for i in range(0, len(bity), 4):
        if i + 4 <= len(bity):
            czworka = bity[i:i + 4]
            hex_znak = format(int(czworka, 2), 'x')
            ciag_hex += hex_znak
    return ciag_hex


def usun_koncowe_zera(bity):
    ostatnia_jedynka = bity.rfind('1')

    if ostatnia_jedynka == -1:
        # same zera
        return ''

    # zwraca bity do ostatniej jedynki włącznie
    # zaokrągla w górę do pełnej grupy 4 bitów
    koniec = ostatnia_jedynka + 1
    if koniec % 4 != 0:
        koniec = ((koniec // 4) + 1) * 4

    return bity[:koniec]


# opcja -e -1: dodatkowa spacja na końcu wiersza = bit 1
def metoda_1_zanurzanie(nosnik, bity_wiadomosci):
    # każdy bit ukrywanej wiadomości będzie przekazywany jako dodatkowa spacja na końcu każdego wiersza
    wiersze = nosnik.split('\n')

    # usunięcie wszystkich spacji na końcach wierszy
    for i in range(len(wiersze)):
        wiersze[i] = wiersze[i].rstrip()

    # ukrywana wiadomość może mieć najwyżej tyle bitów ile wierszy w nośniku
    if len(bity_wiadomosci) > len(wiersze):
        raise ValueError("Ukrywana wiadomość jest za długa.")

    # dodanie spacji według bitów wiadomości
    for i in range(len(bity_wiadomosci)):
        if bity_wiadomosci[i] == '1':
            wiersze[i] += ' '

    return '\n'.join(wiersze)


# opcja -d -1: wyodrębnianie, spacja na końcu = bit 1
def metoda_1_wyodrebnianie(znak_wodny):
    wiersze = znak_wodny.split('\n')
    bity = ""

    for wiersz in wiersze:
        if len(wiersz) > 0 and wiersz[-1] == ' ':
            bity += '1'
        else:
            bity += '0'

    return usun_koncowe_zera(bity)


# opcja -e -2: pojedyczna spacja = bit 0, podwójna spacja = bit 1
def metoda_2_zanurzanie(nosnik, bity_wiadomosci):
    # znalezienie wszystkich spacji (nie na końcach wierszy)
    wiersze = nosnik.split('\n')

    # usunięcie podwójnych spacji
    wynik = []
    for wiersz in wiersze:
        wiersz = wiersz.rstrip()  # usunięcie spacji z końca
        while '  ' in wiersz:
            wiersz = wiersz.replace('  ', ' ')
        wynik.append(wiersz)

    # ukrywana wiadomość może być co najwyżej długości równej liczbie spacji
    liczba_spacji = 0
    for wiersz in wynik:
        liczba_spacji += wiersz.count(' ')

    if len(bity_wiadomosci) > liczba_spacji:
        raise ValueError("Ukrywana wiadomość jest za długa.")

    # zakodowanie wiadomości
    indeks_bitu = 0
    koncowe_wiersze = []

    for wiersz in wynik:
        nowy_wiersz = ""
        i = 0
        while i < len(wiersz):
            if wiersz[i] == ' ' and indeks_bitu < len(bity_wiadomosci):
                if bity_wiadomosci[indeks_bitu] == '1':
                    nowy_wiersz += '  '  # podwójna spacja
                else:
                    nowy_wiersz += ' '  # pojedyncza spacja
                indeks_bitu += 1
            else:
                nowy_wiersz += wiersz[i]
            i += 1
        koncowe_wiersze.append(nowy_wiersz)

    return '\n'.join(koncowe_wiersze)


# opcja -d -2: wyodrębnianie - pojedyncza = 0, podwójna = 1
def metoda_2_wyodrebnianie(znak_wodny):
    wiersze = znak_wodny.split('\n')
    bity = ""

    for wiersz in wiersze:
        wiersz = wiersz.rstrip()  # usunięcie spacji z końca
        i = 0
        while i < len(wiersz):
            if i < len(wiersz) - 1 and wiersz[i] == ' ' and wiersz[i + 1] == ' ':
                bity += '1'
                i += 2
            elif wiersz[i] == ' ':
                bity += '0'
                i += 1
            else:
                i += 1

    return usun_koncowe_zera(bity)


# opcja -e -3: fałszywe literówki w atrybutach
def metoda_3_zanurzanie(nosnik, bity_wiadomosci):
    # znalezienie wszystkich znaczników <p>
    wzorzec_p = r'<p\s*>'
    znaczniki_p = re.findall(wzorzec_p, nosnik)

    if len(bity_wiadomosci) > len(znaczniki_p):
        raise ValueError("Ukrywana wiadomość jest za długa.")

    # usunięcie istniejących styli z <p>
    nosnik = re.sub(r'<p\s+[^>]*>', '<p>', nosnik)

    # dodanie atrybutów do <p>
    # zero jako błąd w margin-bottom -> margin-botom
    # jeden jako błąd w line-height -> lineheight
    wynik = nosnik
    for i in range(len(bity_wiadomosci)):
        if bity_wiadomosci[i] == '0':
            nowy_znacznik = '<p style="margin-botom: 0cm; line-height: 100%">'
        else:
            nowy_znacznik = '<p style="margin-bottom: 0cm; lineheight: 100%">'

        wynik = wynik.replace('<p>', nowy_znacznik, 1)

    return wynik


# opcja -d -3: wyodrębnianie z literówek
def metoda_3_wyodrebnianie(znak_wodny):
    bity = ""

    # znalezienie wszystkich znaczników <p> ze stylem
    wzorzec_p = r'<p\s+style="[^"]*">'
    znaczniki_p = re.findall(wzorzec_p, znak_wodny)

    for i, znacznik in enumerate(znaczniki_p):
        if 'margin-botom' in znacznik:
            bity += '0'
        elif 'lineheight' in znacznik:
            bity += '1'

    return usun_koncowe_zera(bity)


# opcja -e -4: puste pary znaczników font
def metoda_4_zanurzanie(nosnik, bity_wiadomosci):
    # usunięcie wszystkich pustych par <font></font>
    while '<font></font>' in nosnik:
        nosnik = nosnik.replace('<font></font>', '')

    # znalezienie wszystkich <font> ... </font>
    import re

    # znajdź pary <font>...</font>
    wzorzec = r'(<font[^>]*>)(.*?)(</font>)'
    dopasowania = list(re.finditer(wzorzec, nosnik))

    # wiadomość ukrywana nie może mieć więcej bitów niż wystąpień tego znacznika w nośniku
    if len(bity_wiadomosci) > len(dopasowania):
        raise ValueError("Ukrywana wiadomość jest za długa.")

    # przetwarzaj od końca, żeby nie zmieniać indeksów
    wynik = nosnik
    przesuniecie = 0

    for i in range(len(bity_wiadomosci)):
        if i < len(dopasowania):
            dopasowanie = dopasowania[i]
            poczatek = dopasowanie.start() + przesuniecie
            koniec = dopasowanie.end() + przesuniecie

            otwarcie = dopasowanie.group(1)  # <font...>
            zawartosc = dopasowanie.group(2)  # treść
            zamkniecie = dopasowanie.group(3)  # </font>

            if bity_wiadomosci[i] == '1':
                # bit 1: dodanie </font><font> przed <font>
                nowy_fragment = '<font></font>' + otwarcie + zawartosc + zamkniecie
            else:
                # bit 0: dodanie <font></font> po </font>
                nowy_fragment = otwarcie + zawartosc + zamkniecie + '<font></font>'

            wynik = wynik[:poczatek] + nowy_fragment + wynik[koniec:]
            przesuniecie += len(nowy_fragment) - (koniec - poczatek)

    return wynik


# opcja -d -4: wyodrębnianie z pustych par
def metoda_4_wyodrebnianie(znak_wodny):
    bity = ""

    # znalezienie wszystkich miejsc gdzie może być zakodowany bit
    i = 0
    while True:
        # szukanie <font></font><font - bit 1
        pos1 = znak_wodny.find('<font></font><font', i)
        # szukanie </font><font></font> - bit 0
        pos0 = znak_wodny.find('</font><font></font>', i)

        # znalezienie, które wystąpienie jest pierwsze
        if pos1 == -1 and pos0 == -1:
            break
        elif pos1 == -1:
            bity += '0'
            i = pos0 + 1
        elif pos0 == -1:
            bity += '1'
            i = pos1 + 1
        elif pos1 < pos0:
            bity += '1'
            i = pos1 + 1
        else:
            bity += '0'
            i = pos0 + 1

    return usun_koncowe_zera(bity)


def zanurzanie(plik_nosnika, plik_wiadomosci, plik_wyjsciowy, metoda):
    with open(plik_nosnika, 'r', encoding='utf-8') as f:
        nosnik = f.read()

    with open(plik_wiadomosci, 'r') as f:
        wiadomosc_hex = f.read().strip()

    bity_wiadomosci = hex_na_bity(wiadomosc_hex)

    if metoda == 1:
        znak_wodny = metoda_1_zanurzanie(nosnik, bity_wiadomosci)
    elif metoda == 2:
        znak_wodny = metoda_2_zanurzanie(nosnik, bity_wiadomosci)
    elif metoda == 3:
        znak_wodny = metoda_3_zanurzanie(nosnik, bity_wiadomosci)
    elif metoda == 4:
        znak_wodny = metoda_4_zanurzanie(nosnik, bity_wiadomosci)
    else:
        raise ValueError("Nieznana metoda")

    with open(plik_wyjsciowy, 'w', encoding='utf-8') as f:
        f.write(znak_wodny)


def wyodrebnianie(plik_znaku_wodnego, plik_wyjsciowy, metoda):
    with open(plik_znaku_wodnego, 'r', encoding='utf-8') as f:
        znak_wodny = f.read()

    if metoda == 1:
        bity = metoda_1_wyodrebnianie(znak_wodny)
    elif metoda == 2:
        bity = metoda_2_wyodrebnianie(znak_wodny)
    elif metoda == 3:
        bity = metoda_3_wyodrebnianie(znak_wodny)
    elif metoda == 4:
        bity = metoda_4_wyodrebnianie(znak_wodny)
    else:
        raise ValueError("Nieznana metoda")

    wiadomosc_hex = bity_na_hex(bity)

    with open(plik_wyjsciowy, 'w') as f:
        f.write(wiadomosc_hex)


def main():
    if len(sys.argv) < 3:
        print("Nieprawidłowa liczba argumentów, powinno być: stegano -e|-d -1|-2|-3|-4")
        exit()

    # określenie metody zanurzania/wyodrębniania
    if "-1" in sys.argv:
        metoda = 1
    elif "-2" in sys.argv:
        metoda = 2
    elif "-3" in sys.argv:
        metoda = 3
    elif "-4" in sys.argv:
        metoda = 4
    else:
        print("Nie podano metody: -1, -2, -3 lub -4")
        exit()

    try:
        if "-e" in sys.argv:
            zanurzanie('cover.html', 'mess.txt', 'watermark.html', metoda)
        elif "-d" in sys.argv:
            wyodrebnianie('watermark.html', 'detect.txt', metoda)
        else:
            print("Nie podano opcji: -e (zanurzanie) lub -d (wyodrębnianie)")
            exit()
    except Exception as e:
        print(f"{e}")
        exit()


if __name__ == "__main__":
    main()
