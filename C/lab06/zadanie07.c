/*Napisz funkcję rekurencyjną która dla posortowanej tablicy liczb naturalnych i zadanej liczby sprawdzi, czy liczba jest elementem tej tablicy:

a) przeszukaj tablicę od lewej do prawej strony

b) skorzystaj z przeszukiwania binarnego*/

#include <stdio.h>
#include <stdbool.h>

int przeszukiwanie_od_lewej(int tablica[], int lewo, int prawo, int x)
{
    if (lewo < prawo) {
        return -1;
    }
    if (tablica[lewo] == x) {
        return lewo;
    }
    return przeszukiwanie_od_lewej(tablica, lewo + 1, prawo, x);
}

int przeszukiwanie_binarne (int tablica[], int lewo, int prawo, int x)
{
    if (prawo >= lewo) {
        int srodek = lewo + (prawo - lewo) / 2;
        if (tablica[srodek] == x) {
            return srodek;
        }
        if (tablica[srodek] > x) {
            return przeszukiwanie_binarne(tablica, lewo, srodek - 1, x);
        }
        return przeszukiwanie_binarne(tablica, srodek + 1, prawo, x);
    }
    return -1;
}

int main() 
{
    int tablica[] = {2, 3, 5, 8, 10, 11, 14, 15, 18};
    int n = sizeof(tablica) / sizeof(tablica[0]);
    int x;

    printf("Podaj liczbę: ");
    scanf("%d", &x);

    int wynik_przeszukiwania_od_lewej = przeszukiwanie_od_lewej(tablica, 0, n - 1, x);
    if (wynik_przeszukiwania_od_lewej == -1) {
        printf("Liczba nie istnieje w tablicy.\n");
    }
    else {
        printf("Liczba istnieje w tablicy na pozycji: %d\n", wynik_przeszukiwania_od_lewej);
    }
    
    int wynik_przeszukiwania_binarnego = przeszukiwanie_binarne(tablica, 0, n - 1, x);
    if (wynik_przeszukiwania_binarnego == -1) {
        printf("Liczba nie istnieje w tablicy.\n");
    }
    else {
        printf("Liczba istnieje w tablicy na pozycji: %d\n", wynik_przeszukiwania_binarnego);
    }

    return 0;
}