/*Napisz program który pobierze od użytkownika trzy liczby (dzień, miesiąc, rok) i sprawdzi czy tworzą poprawną datę.*/

#include <stdio.h>

int main()
{
    int dzien, miesiac, rok;

    printf("Podaj dzień: ");
    scanf ("%d", &dzien);
    
    printf("Podaj miesiąc: ");
    scanf ("%d", &miesiac);
    
    printf("Podaj rok: ");
    scanf ("%d", &rok);

    if (miesiac >=1 && miesiac <= 12) {
        
        if (miesiac == 1 || miesiac == 3 || miesiac == 5 || miesiac == 7 || miesiac == 8 || miesiac == 10 || miesiac == 12) {
            if (dzien >= 1 && dzien <= 31) {
                printf("Data %d.%d.%d jest poprawna.\n", dzien, miesiac, rok);
            }   
            else {
                printf("Podano niepoprawny dzień.\n");
            }
        }
        else if (miesiac == 2) {
            if (((rok % 4 == 0 && rok % 100 != 0) || (rok % 400 == 0)) && (dzien >= 1 && dzien <= 29)) {
                printf("Data %d.%d.%d jest poprawna.\n", dzien, miesiac, rok);
            }
            else if ((dzien >= 1 && dzien <= 28)) {
                printf("Data %d.%d.%d jest poprawna.\n", dzien, miesiac, rok);
            } 
            else {
                printf("Podano niepoprawny dzień.\n");
            }
        }
        else {
            if (dzien >= 1 && dzien <= 30) {
                printf("Data %d.%d.%d jest poprawna.\n", dzien, miesiac, rok);
            }   
            else {
                printf("Podano niepoprawny dzień.\n");
            }
        }

    }
    else {
        printf("Podano niepoprawny miesiąc.\n");
    }

    return 0;
}