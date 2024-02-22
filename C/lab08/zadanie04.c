/*W oparciu o podany pseudokod przeprowadź symulację na małej tablicy i zaimplementuj algorytm sortowania przez wstawianie.

    WEJŚCIE: Tablica A[1..n]
    WYJŚCIE: Posortowana rosnąco tablica A[1..n]
    i = 1
    Dopóki i <= n wykonuj
        element = A[i]
        k = i - 1
        Dopóki k >= 1 oraz A[k] > element):
            A[k+1] = A[k]
            k = k - 1
        A[k+1] = element
        i = i + 1*/

#include <stdio.h>
#define n 6

void sortowanie_babelkowe(int *A)
{
    int i = 1, k, element;
    while (i <= n) {
        element = A[i]; // element = 6
        k = i - 1; // k = 1
        while (k >= i && A[k] > element) {
            A[k+1] = A[k];
            k--;
        }
        A[k+1] = element; //A[1] = 6
        i++;
    }
}

int main() 
{
    int A[n] = {3, 6, 4, 7, 18, 10};
    
    sortowanie_babelkowe(A);

    int posortowana_A[n];

    memcpy(posortowana_A, A, sizeof(A));

    for (int i = 0; i < n; ++i)
    {
        printf("%d\n", posortowana_A[i]);
    }

    return 0;
}