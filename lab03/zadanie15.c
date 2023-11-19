/*Napisz program który poprosi użytkownika o odgadnięcie liczby z zakresu 1-5. Liczba powinna być na stałe wpisana do programu. Użytkownik ma trzy szanse na odgadnięcie liczby, jeśli użytkownik odgadnie prawidłową liczbę przed trzecią szansą program powinien się zakończyć. Podanie liczby z poza zakresu powinno skutkować dodatkową informacją o zmarnowanej szansie.*/

#include <stdio.h>

int main() 
{
    int odp, liczba = 1;

    for (int i = 1; i <=3; i++) {
        printf("Spróbuj odgadnąć liczbę z zakresu 1-5, masz trzy szanse: \n");
        scanf("%d", &odp);
        if (odp < 1 || odp > 5) {
            printf("Zmarnowałeś/aś swoją szansę.\n");
            if (i < 3) {
                printf("Wpisz poprawnie liczbę od 1 do 5.\n");
            }
        }
        else if (odp == liczba) {
            printf("Gratulacje, udało ci się odgadnąć liczbę.\n");
            return 1;
        }
        else {
            printf("Nie udało ci się odganąć liczby.");
        }
    }
    
    printf(" Twoje szanse się skończyły. Poprawna odpowiedź to: %d.\n", liczba);
    
    return 0;
}