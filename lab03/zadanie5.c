/*Napisz program który pobierze od użytkownika znak ASCII (char) lub kod ASCII (int) i sprawdzi czy jest on literą alfabetu.*/

#include <stdio.h>

int main() 
{
    char znak;
    int kod, odpowiedz;

    printf("Jeśli chcesz podać znak, wpisz '1', jeśli chcesz podać kod ASCII, wpisz '2': ");
    scanf("%d", &odpowiedz);

    if (odpowiedz == 1) {
        printf("Podaj znak ASCII: ");
        scanf(" %c", &znak);

        if ((znak >= 'a' && znak <= 'z') || (znak >= 'A' && znak <= 'Z')) {
            printf("Podany znak jest literą alfabetu.\n");
        }
        else {
            printf("Podany znak nie jest literą alfabetu.\n");
        }
    }

    else if (odpowiedz == 2) {
        printf("Podaj kod ASCII: ");
        scanf("%d", &kod);

        if ((kod >= 65 && kod <= 90) || (kod >= 97 && kod <= 122)) {
            printf("Podany kod jest literą alfabetu.\n");
        }
        else {
            printf("Podany kod nie jest literą alfabetu.\n");
        }
    }

    else {
        printf("Niepoprawna odpowiedź.\n");
    }

    return 0;
}