/*Napisz program, który pobierze od użytkownika liczbę i sprawdzi, czy jest ona podzielna przez liczby z zakresu 2-6, jeśli tak, program powinien zwrócić pierwszą z tych liczb, jeśli podana liczba nie dzieli się przez żadną z tych liczb, program powinien zwrócić stosowny komunikat.*/

#include <stdio.h>

int main() 
{
    int liczba;

    printf("Podaj liczbę:\n");
    scanf("%d", &liczba);

    if (liczba % 2 == 0) {
        printf("Liczba %d jest podzielna przez 2.\n", liczba);
    }
    else if (liczba % 3 == 0) {
        printf("Liczba %d jest podzielna przez 3.\n", liczba);
    }
    else if (liczba % 4 == 0) {
        printf("Liczba %d jest podzielna przez 4.\n", liczba);
    }
    else if (liczba % 5 == 0) {
        printf("Liczba %d jest podzielna przez 5.\n", liczba);
    }
    else if (liczba % 6 == 0) {
        printf("Liczba %d jest podzielna przez 6.\n", liczba);
    }
    else {
        printf("Liczba %d nie jest podzielna przez żadną z liczb od 2 do 6.\n", liczba);
    }

    return 0;
}