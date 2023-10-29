#include <stdio.h>

int main() {

    char znak;
    int kod, odpowiedz;

    printf("Jeśli chcesz podać znak ASCII, wpisz '1', jeśli chcesz podać kod ASCII, wpisz '2': ");
    scanf ("%d", &odpowiedz);

    if (odpowiedz == 1) {

        printf("podaj znak ASCII: ");
        scanf (" %c", &znak);

        if ((znak >= 'a' && znak <= 'z') || (znak >= 'A' && znak <= 'Z')) {
            printf("znak jest literą alfabetu\n");
        }
        else {
            printf("znak nie jest literą alfabetu\n");
        }
    }

    else if (odpowiedz == 2) {

        printf("podaj kod ASCII: ");
        scanf ("%d", &kod);

        if ((kod >= 65 && kod <= 90) || (kod >= 97 && kod <= 122)) {
            printf("znak jest literą alfabetu\n");
        }
        else {
            printf("znak nie jest literą alfabetu\n");
        }
    }

    else {
        printf("niepoprawna odpowiedź");
    }

return 0;
}