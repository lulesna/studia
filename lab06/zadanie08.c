/*Napisz funkcję rekurencyjną sprawdzającą czy podane słowo jest palindromem.*/

#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool palindrom(char* slowo, int pierwsza, int ostania)
{
    if (pierwsza >= ostania) {
        return true;
    }
    if (slowo[pierwsza] != slowo[ostania]) {
        return false;
    }
    return palindrom(slowo, pierwsza + 1, ostania - 1);
}

int main() 
{
    char slowo[20];

    printf("Podaj słowo: ");
    scanf("%s", slowo);

    int dlugosc = strlen(slowo);

    if (palindrom(slowo, 0, dlugosc - 1)) {
        printf("Słowo %s jest palindromem.\n", slowo);
    }
    else {
        printf("Słowo %s nie jest palindromem.\n", slowo);
    }

    return 0;
}