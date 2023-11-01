/*Napisz program który pobierze od użytkownika sześć liczb (dzień1, miesiąc1, rok1, dzień2, miesiąc2, rok2) i obliczy ile lat, miesięcy i dni różni reprezentowanie przez nie daty.*/

#include <stdio.h>
#include <stdlib.h>

int main()
{
    int dzien1, miesiac1, rok1, dzien2, miesiac2, rok2;

    printf("podaj pierwszą datę (dzień, miesiąc i rok):\n");
    scanf ("%d %d %d", &dzien1, &miesiac1, &rok1);

    printf("podaj drugą datę (dzień, miesiąc i rok):\n");
    scanf ("%d %d %d", &dzien2, &miesiac2, &rok2);

    int roznicadni = abs(dzien1 - dzien2);
    int roznicamiesiecy = abs(miesiac1 - miesiac2);
    int roznicalat = abs(rok1 - rok2);

    if ((miesiac1 >=1 && miesiac1 <= 12) && (miesiac2 >=1 && miesiac2 <= 12)) {
        if (miesiac1 == 1 || miesiac1 == 3 || miesiac1 == 5 || miesiac1 == 7 || miesiac1 == 8 || miesiac1 == 10 || miesiac1 == 12) {
            if (dzien1 <= 1 && dzien1 >= 31) {
                printf("taki dzień nie istnieje\n");
            }
        }
        else if (miesiac2 == 1 || miesiac2 == 3 || miesiac2 == 5 || miesiac2 == 7 || miesiac2 == 8 || miesiac2 == 10 || miesiac2 == 12) {
            if (dzien2 <= 1 && dzien2 >= 31) {
                printf("taki dzień nie istnieje\n");
            }
        }
        else if (miesiac1 == 2) {
            if (((rok1 % 4 == 0 && rok1 % 100 != 0) || (rok1 % 400 == 0)) && (dzien1 <= 1 && dzien1 >= 29)) {
                printf("taki dzień nie istnieje\n");
            }
            else if ((dzien1 <= 1 && dzien1 >= 28)) {
                printf("taki dzień nie istnieje\n");
            }
        }
        else if (miesiac2 == 2) {
            if (((rok2 % 4 == 0 && rok2 % 100 != 0) || (rok2 % 400 == 0)) && (dzien2 <= 1 && dzien2 >= 29)) {
                printf("taki dzień nie istnieje\n");
            }
            else if ((dzien2 <= 1 && dzien2 >= 28)) {
                printf("taki dzień nie istnieje\n");
            }
        }
        else if ((dzien1 <= 1 && dzien1 >= 30) || (dzien2 <= 1 && dzien2 >= 30)) {
                printf("taki dzień nie istnieje\n");
            }
        else {
            printf ("różnica dni wynosi: %d\nróżnica miesięcy wynosi: %d\nróżnica lat wynosi: %d\n", roznicadni, roznicamiesiecy, roznicalat);
        }
    }
    else {
        if ((miesiac1 <= 1 || miesiac1 >= 12) && (miesiac2 <=1 || miesiac2 >=12)) {
            printf("oba podane miesiące są niepoprawne\n");
        }
        else if (miesiac1 <=1 || miesiac1 >=12) {
            printf("miesiąc z pierwszej daty jest niepoprawny\n");
        }
        else {
            printf("miesiąc z drugiej daty jest niepoprawny\n");
        }   
    }

    return 0;
}