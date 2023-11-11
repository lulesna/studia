/*Napisz program który dla zadanej tablicy liczb stworzy i zwróci tablicę kwadratów tych liczb.*/

#include <stdio.h>

int main()
{
    int tab[6];

    tab[0] = 1;
    tab[1] = 2;
    tab[2] = 3;
    tab[3] = 4;
    tab[4] = 5;
    tab[5] = 6;
    tab[6] = 7;
    
    for (int i = 0; i <= 6; i++) {
        tab[i] *= tab[i];
    }

    printf("Tablica kwadratów:\n");
    for (int i = 0; i <= 6; i++) {
        printf("%d\n", tab[i]);
    }

    return 0;
}