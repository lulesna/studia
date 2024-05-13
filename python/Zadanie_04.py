# Napisz program, który posortuje n dat.
# Zdefiniuj i wykorzystaj słownik, która będzie posiadał klucze zawierające informacje na temat dnia, miesiąca i roku.
# Następnie posortuje dowolnym algorytmem daty rosnąco.
# Uwaga: Napisz algorytm sortujący od podstaw. Nie korzystaj z gotowych rozwiązań dostępnych w Pythonie.

daty = []
while True:
    n = int(input("podaj liczbę dat: "))
    if n >= 0:
        break
    else:
        print("liczba dat nie może być ujemna")

# pobieranie i dodawanie dat do listy
for i in range(n):
    # sprawdzenie poprawności dnia - uproszczone
    while True:
        dzien = int(input("podaj dzień: "))
        if 0 < dzien < 32:
            break
    else:
        print("podano niepoprawny dzień")
    # sprawdzenie poprawności miesiąca
    while True:
        miesiac = int(input("podaj miesiąc: "))
        if 0 < miesiac < 13:
            break
    else:
        print("podano niepoprawny miesiąc")

    rok = int(input("podaj rok: "))

    # przypisywanie wartości do kluczy, utworzenie słownika
    daty.append({"dzien": dzien, "miesiac": miesiac, "rok": rok})

# sortowanie bąbelkowe
for i in range(n):
    # iteracja od początku do elementu, który jest już posortowany
    for j in range(0, n-i-1):
        # porównanie roku w dwóch datach obok siebie
        if daty[j]['rok'] > daty[j + 1]['rok']:
            # jeśli pierwsza data ma większy rok, następuje zamiana miejsc tych dat
            daty[j], daty[j + 1] = daty[j + 1], daty[j]
        # jeśli rok jest ten sam w obu datach, to sprawdzamy, gdzie jest większy miesiąc
        elif daty[j]['rok'] == daty[j + 1]['rok']:
            if daty[j]['miesiac'] > daty[j + 1]['miesiac']:
                daty[j], daty[j + 1] = daty[j + 1], daty[j]
            # jeśli miesiąc jest ten sam, to porównujemy dzień
            elif daty[j]['miesiac'] == daty[j + 1]['miesiac']:
                if daty[j]['dzien'] > daty[j + 1]['dzien']:
                    daty[j], daty[j + 1] = daty[j + 1], daty[j]

print("posortowane daty:")
# iteracja przez wszystkie słowniki w liście 'daty'
for data in daty:
    # d - liczba całkowita, 02 - wyświetlona na dwóch cyfrach
    dzien, miesiac, rok = data['dzien'], data['miesiac'], data['rok']
    print(f"{dzien:02d}.{miesiac:02d}.{rok}")


przypadki = [
    ([
        {'dzien': 12, 'miesiac': 5, 'rok': 2021},
        {'dzien': 23, 'miesiac': 8, 'rok': 2020},
        {'dzien': 5, 'miesiac': 10, 'rok': 2022},
        {'dzien': 8, 'miesiac': 3, 'rok': 2021}
     ], [
        {'dzien': 23, 'miesiac': 8, 'rok': 2020},
        {'dzien': 8, 'miesiac': 3, 'rok': 2021},
        {'dzien': 12, 'miesiac': 5, 'rok': 2021},
        {'dzien': 5, 'miesiac': 10, 'rok': 2022}
     ]),
    ([
        {'dzien': 1, 'miesiac': 1, 'rok': 2021},
        {'dzien': 2, 'miesiac': 1, 'rok': 2021},
        {'dzien': 1, 'miesiac': 2, 'rok': 2021},
        {'dzien': 1, 'miesiac': 1, 'rok': 2022}
     ], [
        {'dzien': 1, 'miesiac': 1, 'rok': 2021},
        {'dzien': 2, 'miesiac': 1, 'rok': 2021},
        {'dzien': 1, 'miesiac': 2, 'rok': 2021},
        {'dzien': 1, 'miesiac': 1, 'rok': 2022}
     ])
]

for idx, (daty, oczekiwane) in enumerate(przypadki, start=1):
    n = len(daty)

    for i in range(n):
        for j in range(0, n - i - 1):
            if daty[j]['rok'] > daty[j + 1]['rok']:
                daty[j], daty[j + 1] = daty[j + 1], daty[j]
            elif daty[j]['rok'] == daty[j + 1]['rok']:
                if daty[j]['miesiac'] > daty[j + 1]['miesiac']:
                    daty[j], daty[j + 1] = daty[j + 1], daty[j]
                elif daty[j]['miesiac'] == daty[j + 1]['miesiac']:
                    if daty[j]['dzien'] > daty[j + 1]['dzien']:
                        daty[j], daty[j + 1] = daty[j + 1], daty[j]

    if daty == oczekiwane:
        print(f"przypadek {idx}.: poprawnie")
    else:
        print(f"przypadek {idx}.: niepoprawnie")
        print("oczekiwane:")
        for data in oczekiwane:
            dzien, miesiac, rok = data['dzien'], data['miesiac'], data['rok']
            print(f"{dzien:02d}.{miesiac:02d}.{rok}")
        print("wynik:")
        for data in daty:
            dzien, miesiac, rok = data['dzien'], data['miesiac'], data['rok']
            print(f"{dzien:02d}.{miesiac:02d}.{rok}")
