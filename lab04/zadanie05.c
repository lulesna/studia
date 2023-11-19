/*Napisz program który dla podanej liczby n wczyta od użytkownika n liczb i stworzy z nich tablicę.*/

#include <stdio.h>

int main()
{
    int n;
    
    printf("Podaj rozmiar tablicy: ");
    scanf("%d", &n);

    int tab[n];

    printf("Podaj elementy tablicy:\n");
    for (int i = 0; i < n; i++) {
        scanf("%d", &tab[i]);
    }

    printf("Tablica tych liczb:\n");
    for (int i = 0; i < n; i++) {
        printf("%d\n", tab[i]);
    }

    return 0;
}