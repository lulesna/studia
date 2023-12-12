/*Napisz program, który pobierze od użytkownika sześć liczb (dzień1, miesiąc1, rok1, dzień2, miesiąc2, rok2) i obliczy, ile lat, miesięcy i dni różni reprezentowanie przez nie daty.*/

#include <stdio.h>
#include <stdlib.h>

int sprawdzenie_poprawnosci_daty(int dzien, int miesiac, int rok)
{
    if (miesiac < 1 || miesiac > 12) {
        printf("Podano niepoprawny miesiąc.\n");
        return 1;
    }

    if (dzien < 1 || dzien > 31) {
        printf("Podano niepoprawny dzień.\n");
        return 1;
    }

    if (miesiac == 2) {
        if ((rok % 4 == 0 && rok % 100 != 0) || (rok % 400 == 0)) {
            if (dzien < 1 || dzien > 29) {
                printf("Podano niepoprawny dzień.\n");
                return 1;
            }
        }
        else {
            if (dzien < 1 || dzien > 28) {
                printf("Podano niepoprawny dzień.\n");
                return 1;
            }
        }
    }

    if ((miesiac == 4 || miesiac == 6 || miesiac == 9 || miesiac == 11) && (dzien < 1 || dzien > 30)) {
        printf("Podano niepoprawny dzień.\n");
        return 1;
    }

    return 0;
}

int main()
{
    int dzien1, miesiac1, rok1, dzien2, miesiac2, rok2;

    printf("Podaj pierwszą datę (dzień.miesiąc.rok):\n");
    scanf("%d.%d.%d", &dzien1, &miesiac1, &rok1);

    if (sprawdzenie_poprawnosci_daty(dzien1, miesiac1, rok1) == 1) {
        return 1;
    }

    printf("Podaj drugą datę (dzień.miesiąc.rok):\n");
    scanf("%d.%d.%d", &dzien2, &miesiac2, &rok2);

    if (sprawdzenie_poprawnosci_daty(dzien2, miesiac2, rok2) == 1) {
        return 1;
    }

    int roznicadni = abs(dzien1 - dzien2);
    int roznicamiesiecy = abs(miesiac1 - miesiac2);
    int roznicalat = abs(rok1 - rok2); 

    printf("Różnica dni wynosi: %d\nRóżnica miesięcy wynosi: %d\nRóżnica lat wynosi: %d\n", roznicadni, roznicamiesiecy, roznicalat);

    return 0;
}