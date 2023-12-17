/*W oparciu o podany pseudokod przeprowadź symulację na małej tablicy i zaimplementuj algorytm przeszukiwania liniowego.

    WEJŚCIE: Tablica TAB[1..N], element n
    WYJŚCIE: True jeśli n znajduje się w TAB, False w przeciwnym przypadku

    index = 1
    wynik = False
    Dopóki index <= N wykonuj:
        Jeśli TAB[index] == n to:
            wynik = True
        index = index + 1
    Wypisz wynik*/

#include <stdio.h>
#include <stdbool.h>

bool przeszukiwanie_liniowe(int *TAB, int n, int N)
{
    int index = 0;
    bool wynik = false;
    while (index <= N) {
        if (TAB[index] == n) {
            wynik = true;
        }
        index++;
    }
    return wynik;
}

int main() 
{
    int TAB[] = {3, 4, 6, 7, 10, 18}, n;
    int N = sizeof(TAB) / sizeof(TAB[0]);

    printf("Podaj n: ");
    scanf("%d", &n);

    bool wynik = przeszukiwanie_liniowe(TAB, n, N);

    if (wynik) {
        printf("n znaduje się w tablicy\n");
    }
    else {
        printf("n nie znaduje się w tablicy\n");
    }

    return 0;
}