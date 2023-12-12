/*Napisz program, który wypisze trójkąt Pascala o wysokości 5.*/

#include <stdio.h>

int main()
{
    int wysokosc = 5;

    for (int wiersz = 0; wiersz < wysokosc; wiersz++) {
        for (int spacja = 0; spacja < wysokosc - wiersz - 1; spacja++) {
            printf(" ");
        }
        int liczba = 1;
        for (int i = 0; i <= wiersz; i++) {
            printf("%d ", liczba);
            liczba = liczba * (wiersz - i) / (i + 1);
        }
        printf("\n");
    }

    return 0;
}