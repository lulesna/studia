/*Napisz program, który poprosi użytkownika o odgadnięcie liczby z zakresu 1-5. Użytkownik ma cztery szanse na odgadnięcie liczby, ale złośliwy program powinien zawsze informować o odgadnięciu złej liczby. Po ostatniej próbie użytkownika program podaje jako poprawną odpowiedź liczbę z zakresu 1-5 różną od liczb podanych przez użytkownika.*/

#include <stdio.h>

int main() 
{
    int odp1, odp2, odp3, odp4, liczba;

    printf("Spróbuj odgadnąć liczbę z zakresu 1-5, masz cztery szanse: \n");
    scanf("%d", &odp1);
    printf("Niestety nie udało ci się, spróbuj jeszcze raz odgadnąć liczbę z zakresu 1-5: \n");
    scanf("%d", &odp2);
    printf("Niestety nie udało ci się, spróbuj jeszcze raz odgadnąć liczbę z zakresu 1-5: \n");
    scanf("%d", &odp3);
    printf("Niestety nie udało ci się, spróbuj jeszcze raz odgadnąć liczbę z zakresu 1-5: \n");
    scanf("%d", &odp4);

    if ((odp1 == 1) || (odp2 == 1) || (odp3 == 1) || (odp4 == 1)) {
        if ((odp1 == 2) || (odp2 == 2) || (odp3 == 2) || (odp4 == 2)) {
            if ((odp1 == 3) || (odp2 == 3) || (odp3 == 3) || (odp4 == 3)) {
                if ((odp1 == 4) || (odp2 == 4) || (odp3 == 4) || (odp4 == 4)) {
                    liczba = 5;
                }
                else {
                    liczba = 4;
                }
            }
            else {
                liczba = 3;
            }
        }
        else {
            liczba = 2;
        }
    }
    else {
        liczba = 1;
    }
    
    printf("Twoje szanse się skończyły. Poprawna odpowiedź to: %d.\n", liczba);
    
    return 0;
}