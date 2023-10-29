#include <stdio.h>

int main() {

    int dzien, miesiac, rok;

    printf("podaj dzień: ");
    scanf ("%d", &dzien);
    
    printf("podaj miesiąc: ");
    scanf ("%d", &dzien);
    
    printf("podaj rok: ");
    scanf ("%d", &dzien);

    if ((miesiac >=1 && miesiac <= 12)) {
        
        if (miesiac == 1 || miesiac == 3 || miesiac == 5 || miesiac == 7 || miesiac == 8 || miesiac == 10 || miesiac == 12) {
            if (dzien >= 1 && dzien <= 31) {
                printf("data %d.%d.%d jest poprawna\n", dzien, miesiac, rok);
            }   
            else {
                printf("%d. dzień nie istnieje\n", dzien);
            }
        }
        else if (miesiac == 2) {
            if (dzien >= 1 && dzien <= 28) {
                printf("data %d.%d.%d jest poprawna\n", dzien, miesiac, rok);
            }   
            else {
                printf("%d. dzień nie istnieje\n", dzien);
            }
        }
        else {
            if (dzien >= 1 && dzien <= 30) {
                printf("data %d.%d.%d jest poprawna\n", dzien, miesiac, rok);
            }   
            else {
                printf("%d. dzień nie istnieje\n", dzien);
            }
        }

    }
    else {
        printf("%d. miesiąc nie istnieje\n", miesiac);
    }

return 0;
}