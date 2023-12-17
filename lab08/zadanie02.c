/*W oparciu o podany pseudokod przeprowadź symulację na małej tablicy i zaimplementuj algorytm przeszukiwania binarnego.

    WEJŚCIE: Posortowana tablica TAB[1..N], element n
    WYJŚCIE: True jeśli n znajduje się w TAB, False w przeciwnym przypadku

    left = 1
    right = N
    result = False
    Dopóki left <= right:
        middle := floor((left + right) / 2)
        if Tab[middle] < n:
            left := middle + 1
        else if Tab[middle] > n:
            right := middle - 1
        else:
            result = True
    Wypisz result*/

#include <stdio.h>
#include <stdbool.h>
#include <math.h>

bool przeszukiwanie_binarne(int *TAB, int N, int n)
{
    int left = 0, right = N, middle;
    bool result = false;
    while (left <= right) {
        middle = floor((left + right) / 2);
        if (TAB[middle] < n) {
            left = middle + 1;
        }
        else if (TAB[middle] > n) {
            right = middle - 1;
        }
        else {
            result = true;
            break;
        }
    }
    return result;
}

int main() 
{
    int TAB[] = {3, 4, 6, 7, 10, 18}, n;
    int N = sizeof(TAB) / sizeof(TAB[0]);

    printf("Podaj n: ");
    scanf("%d", &n);

    bool result = przeszukiwanie_binarne(TAB, N, n);

    if (result) {
        printf("n znaduje się w tablicy\n");
    }
    else {
        printf("n nie znaduje się w tablicy\n");
    }

    return 0;
}