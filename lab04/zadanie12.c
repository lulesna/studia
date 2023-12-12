/*Napisz program, który dla podanej liczby n wypisze trójkąt Pascala o n.*/

#include <stdio.h>

int main()
{
    int n;

    printf("Podaj wysokość trójkąta Pascala: ");
    scanf("%d", &n);

    for (int wiersz = 0; wiersz < n; wiersz++) {
        for (int spacja = 0; spacja < n - wiersz - 1; spacja++) {
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