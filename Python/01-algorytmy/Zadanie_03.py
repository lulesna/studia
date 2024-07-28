# Problem Józefa Flawiusza.
# Napisz program rozwiązujący ten problen dla dowolnej liczby żołnierzy.

# ostani żołnierz:
# - jest zawsze nieparzysty
# - ma nr 1 jeśli n jest potęgą dwójki
# - jeśli n = 2^k + l, to ostatni = 2l + 1
# wzór z filmiku: https://www.youtube.com/watch?v=uCsD3ZGzMgE

while True:
    n = int(input("podaj liczbę żołnierzy: "))
    if n > 0:
        break
    else:
        print("liczba żołnierzy musi być dodatnia")

k = 0
# szukanie jak najmniejszej potęgi dwójki która będzie większa od n
while 2 ** k <= n:
    k += 1
# odjęcie jedynki od k, żeby znaleźć jak największą potęgę dwójki która bedzie mniejsza lub równa n
k -= 1

l = n - 2 ** k

ostatni = 2 * l + 1

print("numer ostatniego żołnierza to:", ostatni)


przypadki = [
    (1, 1),
    (2, 1),
    (3, 3),
    (4, 1),
    (5, 3),
    (6, 5),
    (7, 7),
    (8, 1),
    (9, 3),
    (10, 5),
    (11, 7),
    (12, 9),
    (13, 11),
    (14, 13),
    (15, 15),
    (16, 1),
    (17, 3),
    (18, 5),
    (19, 7),
    (20, 9)
]

for idx, (n, oczekiwany) in enumerate(przypadki, start=1):
    k = 0
    while 2 ** k <= n:
        k += 1
    k -= 1
    l = n - 2 ** k
    ostatni = 2 * l + 1

    if ostatni == oczekiwany:
        print(f"przypadek {idx}.: poprawnie")
    else:
        print(f"przypadek {idx}.: niepoprawnie")
        print(f"oczekiwane: {oczekiwany}")
        print(f"wynik: {ostatni}")
