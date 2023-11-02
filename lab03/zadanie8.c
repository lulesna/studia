/*Napisz program który pobierze od użytkownika sześć liczb (dzień1, miesiąc1, rok1, dzień2, miesiąc2, rok2) i obliczy ile lat, miesięcy i dni różni reprezentowanie przez nie daty.*/

#include <stdio.h>
#include <stdlib.h>

int main()
{
    int dzien1, miesiac1, rok1, dzien2, miesiac2, rok2;

    printf("Podaj pierwszą datę (dzień, miesiąc i rok):\n");
    scanf ("%d %d %d", &dzien1, &miesiac1, &rok1);

    printf("Podaj drugą datę (dzień, miesiąc i rok):\n");
    scanf ("%d %d %d", &dzien2, &miesiac2, &rok2);

    if (miesiac1 < 1 || miesiac1 > 12 || miesiac2 < 1 || miesiac2 > 12) {
        printf("Podano niepoprawny miesiąc.\n");
        return 1;
    }

    if (dzien1 < 1 || dzien1 > 31 || dzien2 < 1 || dzien2 > 31) {
        printf("Podano niepoprawny dzień.\n");
        return 1;
    }

    if (miesiac1 == 2) {
        if ((rok1 % 4 == 0 && rok1 % 100 != 0) || (rok1 % 400 == 0)) {
            if (dzien1 < 1 || dzien1 > 29) {
                printf("Podano niepoprawny dzień.\n");
                return 1;
            }
        }
        else {
            if (dzien1 < 1 || dzien1 > 28) {
                printf("Podano niepoprawny dzień.\n");
                return 1;
            }
        }
    }

    if (miesiac2 == 2) {
        if ((rok2 % 4 == 0 && rok2 % 100 != 0) || (rok2 % 400 == 0)) {
            if (dzien2 < 1 || dzien2 > 29) {
                printf("Podano niepoprawny dzień.\n");
                return 1;
            }
        }
        else {
            if (dzien2 < 1 || dzien2 > 28) {
                printf("Podano niepoprawny dzień.\n");
               return 1;
            }
        }
    }

    if ((miesiac1 == 4 || miesiac1 == 6 || miesiac1 == 9 || miesiac1 == 11) && (dzien1 < 1 || dzien1 > 30)) {
        printf("Podano niepoprawny dzień.\n");
        return 1;
    }

    if ((miesiac2 == 4 || miesiac2 == 6 || miesiac2 == 9 || miesiac2 == 11) && (dzien2 < 1 || dzien2 > 30)) {
        printf("Podano niepoprawny dzień.\n");
        return 1;
    }

    int roznicadni = abs(dzien1 - dzien2);
    int roznicamiesiecy = abs(miesiac1 - miesiac2);
    int roznicalat = abs(rok1 - rok2); 

    printf ("Różnica dni wynosi: %d\nRóżnica miesięcy wynosi: %d\nRóżnica lat wynosi: %d\n", roznicadni, roznicamiesiecy, roznicalat);

    return 0;
}