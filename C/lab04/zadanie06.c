/*Napisz program, który dla podanej tablicy dwówymiarowej o wartościach 0 lub 1, wypisze na ekranie prostokąt o wymiarach zależnych od wymiarów tablicy. Na współrzędnych odpowiadającym wartości 1 umieść znak 'x'.*/

#include <stdio.h>

int main()
{
    int tab[3][4] = {{1,0,1,1},{0,0,1,0},{0,1,0,1}};

    printf("|----|\n");

    for (int i = 0; i < 3; i++) {
        printf("|");
        for (int j = 0; j < 4; j++) {
            if (tab[i][j] == 1) {
                printf("x");
            }
            else {
                printf(" ");
            }
        }
        printf("|\n");
    }

    printf("|----|\n");

    return 0;
}