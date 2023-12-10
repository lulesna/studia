/*Napisz program w którym będzie zmienna globalna, w funkcji main stwórz zmienną lokalną i przypisz jej watrość zmiennej globlanej, po wypisz na ekran adressy obu zmiennych. Następnie napisz funkcję przyjmującą dwie liczby i wypisującą ich adressy i wywołaj ją na wcześniej utworzonych zmiennych. Przed uruchomieniem programu spróbuj przewidzieć jego działanie (ile różnych adresów zostanie wypisanych).*/

#include <stdio.h>

int zmienna_globalna;

int funkcja(int zmienna_globalna, int zmienna_lokalna)
{
    zmienna_globalna = 2;
    zmienna_lokalna = 3;

    printf("Adres zmiennej globalnej po przypisaniu wartości 2: %p\n", &zmienna_globalna);
    printf("Adres zmiennej lokalnej po przypisaniu wartości 3: %p\n", &zmienna_lokalna);
}

int main() 
{
    int zmienna_lokalna = zmienna_globalna;

    printf("Adres zmiennej globalnej: %p\n", &zmienna_globalna);
    printf("Adres zmiennej lokalnej: %p\n\n", &zmienna_lokalna);

    funkcja(zmienna_globalna, zmienna_lokalna);

    return 0;
}