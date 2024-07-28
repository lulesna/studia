# Liczby zaprzyjaźnione to dwie liczby naturalne, gdzie każda z nich jest równa sumie dzielników właściwych drugiej liczby.
# Napisz program wypisujący liczby zaprzyjaźnione z zadanego zakresu.

poczatek = int(input("podaj początek zakresu: "))
koniec = int(input("podaj koniec zakresu: "))

for liczba in range(poczatek, koniec + 1):
    suma1 = 1  # suma dzielników pierwszej liczby
    for dzielnik in range(2, liczba):
        # jeśli liczba jest podzielna przez dzielnik, dodaj dzielnik do sumy1
        if liczba % dzielnik == 0:
            suma1 += dzielnik

    # liczba2 = suma1

    # liczba2 musi być większa od liczby1 i mieścić się w zakresie
    if liczba < suma1 <= koniec:
        suma2 = 1  # suma dzielników drugiej liczby
        for dzielnik in range(2, suma1):
            # jeśli liczba2 jest podzielna przez dzielnik, dodaj dzielnik do sumy2
            if suma1 % dzielnik == 0:
                suma2 += dzielnik

        if suma2 == liczba:
            print(liczba, suma1)


przypadki = [
    (1, 1000, [(220, 284)]),
    (1000, 10000, [(1184, 1210), (2620, 2924), (5020, 5564), (6232, 6368)]),
    (-200, 200, [])
]

for idx, (poczatek, koniec, oczekiwane) in enumerate(przypadki, start=1):
    liczby_zaprzyjaznione = []

    for liczba in range(poczatek, koniec + 1):
        suma1 = 1
        for dzielnik in range(2, liczba):
            if liczba % dzielnik == 0:
                suma1 += dzielnik
        if liczba < suma1 <= koniec:
            suma2 = 1
            for dzielnik in range(2, suma1):
                if suma1 % dzielnik == 0:
                    suma2 += dzielnik
            if suma2 == liczba:
                liczby_zaprzyjaznione.append((liczba, suma1))

    if liczby_zaprzyjaznione == oczekiwane:
        print(f"przypadek {idx}.: poprawnie")
    else:
        print(f"przypadek {idx}.: niepoprawnie")
        print(f"oczekiwane: {oczekiwane}")
        print(f"wynik: {liczby_zaprzyjaznione}")
