/*Napisz program, który wczyta od użytkownika dwie liczby i zwróci wynik jednego z czterech działań (dodawanie, odejmowanie, mnożenie, dzielenie), za wykonanie każdego z działań powinna odpowiadać osobna funkcja. Za pomocą instrukcji case/switch stwórz menu które pozwoli użytkownikowi wybrać działanie.*/

#include <stdio.h>

int main() 
{
    float liczba1, liczba2, wynik;
    char operacja;

    printf("Podaj pierwszą liczbę: ");
    scanf("%f", &liczba1);
    printf("Podaj drugą liczbę: ");
    scanf("%f", &liczba2);

    printf("Wpisz odpowiednią cyfrę, aby wybrać działanie:\n");
    printf("1 - dodawanie\n");
    printf("2 - odejmowanie\n");
    printf("3 - mnożenie\n");
    printf("4 - dzielenie\n");
    scanf(" %c", &operacja);

    switch (operacja) {
        case '1':
            wynik = liczba1 + liczba2;
            printf("%.2f + %.2f = %.2f\n", liczba1, liczba2, wynik);
            break;
        case '2':
            wynik = liczba1 - liczba2;
            printf("%.2f - %.2f = %.2f\n", liczba1, liczba2, wynik);
            break;
        case '3':
            wynik = liczba1 * liczba2;
            printf("%.2f * %.2f = %.2f\n", liczba1, liczba2, wynik);
            break;
        case '4':
            if (liczba2 != 0) {
                wynik = liczba1 / liczba2;
                printf("%.2f / %.2f = %.2f\n", liczba1, liczba2, wynik);
            }
            else {
                printf("Nie można dzielić przez 0.\n");
            }
            break;
    }

    return 0;
}