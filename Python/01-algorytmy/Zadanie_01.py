# Napisz program, który wypisze na ekranie wszystkie liczby pierwsze z zadanego zakresu.

poczatek = int(input("podaj początek zakresu: "))
koniec = int(input("podaj koniec zakresu: "))

if poczatek < 2:
    poczatek = 2

for liczba in range(poczatek, koniec + 1):
    # dla każdej liczby sprawdzić, czy jest podzielna przez jakąkolwiek liczbę z zakresu od 2 do pierwiastka kwadratowego z liczby (+1)
    # ponieważ dzielniki większe od pierwiastka liczby są wielokrotnościami dzielników wcześniej obliczonych
    for dzielnik in range(2, int(liczba ** 0.5) + 1):
        if liczba % dzielnik == 0:
            break
    else:
        print(f"{liczba}", end=' ')  # zamienia znak nowej linii na spację
print()


przypadki = [
    (1, 10, [2, 3, 5, 7]),
    (50, 100, [53, 59, 61, 67, 71, 73, 79, 83, 89, 97]),
    (300, 400, [307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397]),
    (800, 1000, [809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997]),
    (-100, 1, [])
]

# pętla sprawdza każdy przypadek po kolei z listy, enumerate sprawdza każdy element listy i zwraca indeks
for idx, (poczatek, koniec, oczekiwane) in enumerate(przypadki, start=1):
    liczby_pierwsze = []

    if poczatek < 2:
        poczatek = 2

    for liczba in range(poczatek, koniec + 1):
        for dzielnik in range(2, int(liczba ** 0.5) + 1):
            if liczba % dzielnik == 0:
                break
        else:
            liczby_pierwsze.append(liczba)

    if liczby_pierwsze == oczekiwane:
        print(f"przypadek {idx}.: poprawnie")
    else:
        print(f"przypadek {idx}.: niepoprawnie")
        print(f"oczekiwane: {oczekiwane}")
        print(f"wynik: {liczby_pierwsze}")
