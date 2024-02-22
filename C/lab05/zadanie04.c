/*Napisz program, który sprawdza czy podane przez użytkownika słowo jest palindromem. Wykorzystaj rozwiązania zadań 2,3.*/

#include <stdio.h>
#include <string.h>

void funkcja(char s[], int i) {
    printf("%c\n", s[i]);
}

int main() 
{
    char slowo[20];
    int i, dlugosc;

    printf("Podaj słowo: ");
    scanf("%s", slowo);

    dlugosc = strlen(slowo);

    for (i = 0; i < dlugosc / 2; i++) {
        if (slowo[i] != slowo[dlugosc - i -1]) {
            printf("Podane słowo nie jest palindromem.\n");
            return 1;
        }
    }
    printf("Podane słowo jest palindromem.\n");

    return 0;
}