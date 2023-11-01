#include <stdio.h>

int main() 
{
    int liczba1, liczba2, liczba3;

    printf("podaj pierwszą liczbę: ");
    scanf ("%d", &liczba1);
    printf("podaj drugą liczbę: ");
    scanf ("%d", &liczba2);
    printf("podaj trzecią liczbę: ");
    scanf ("%d", &liczba3);

    if (liczba1 > liczba2 && liczba1 > liczba3) {
        printf("największa jest pierwsza liczba: %d\n", liczba1);
    }
    else if (liczba2 > liczba3) {
        printf("największa jest druga liczba: %d\n", liczba2);
    }
    else {
        printf("największa jest trzecia liczba: %d\n", liczba3);
    }

    return 0;
}