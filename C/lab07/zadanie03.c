/*Napisz funkcję typu void która przyjmuje adressy dwóch zmiennych i zamienia miejscami wartości znajdujące się pod tymi adresami. W programie głównym stwórz dwie zmienne o różnych wartościach i wypisz je przed i po wywołaniu funkcji.*/

#include <stdio.h>

void funkcja(int *adres1, int *adres2)
{
    int temp = *adres1;
    *adres1 = *adres2;
    *adres2 = temp;
}

int main() 
{
    int zmienna1 = 3, zmienna2 = 6;

    printf("Przed wywołaniem funkcji: %d, %d\n", zmienna1, zmienna2);
    
    funkcja(&zmienna1, &zmienna2);

    printf("Po wywołaniu funkcji: %d, %d\n", zmienna1, zmienna2);

    return 0;
}