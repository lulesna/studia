/*Napisz program, który sprawdza czy podane przez użytkownika słowo jest palindromem. Wykorzystaj rozwiązania zadań 2,3.*/

#include <stdio.h>
#include <string.h>

void funkcja(char s[], int i) {
    printf("%c\n", s[i]);
}

int main() 
{
    char napis[20];
    int i, dlugosc;

    printf("Podaj napis: ");
    scanf("%s", napis);

    dlugosc = strlen(napis);

    for (i = 0; i < dlugosc / 2; i++) {
        if (napis[i] != napis[dlugosc - i -1]) {
            printf("Podany napis nie jest palindromem.\n");
            return 1;
        }
    }
    printf("Podany napis jest palindromem.\n");

    return 0;
}