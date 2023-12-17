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

bool przeszukiwanie_liniowe(int *TAB, int n)
{
    int index = 1;
    bool wynik = false;
    while (index <= 18) {
        if (TAB[index] == n) {
            wynik = true;
        }
        index++;
    }

    if (wynik) {
        printf("n znaduje się w tablicy\n");
    }
    else {
        printf("n nie znaduje się w tablicy\n");
    }
}

int main() 
{
    int TAB[] = {3, 4, 6, 7, 10, 18}, n;

    printf("Podaj n: ");
    scanf("%d", &n);

    przeszukiwanie_liniowe(TAB, n);

    return 0;
}