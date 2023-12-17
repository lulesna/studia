/*W oparciu o podany pseudokod przeprowadź symulację na małej tablicy i zaimplementuj algorytm sortowania bąbelkowego.

    WEJŚCIE: Tablica A[1..n]
    WYJŚCIE: Posortowana rosnąco tablica A[1..n]
    i = n
    Dopóki i != 0 wykonuj:
        j = 0
        Dopóki j < i wykonuj
            Jeśli A[j+1] < A[j] to zamień miejscami A[j+1] z A[j]
            j = j + 1
        i = i - 1*/

#include <stdio.h>
#include <string.h>

void sortowanie_babelkowe(int *A, int n)
{
    int i = n, j;
    while (i != 0) {
        j = 0;
        while (j < i) {
            if (A[j+1] < A[j]) {
                int temp = A[j];
                A[j] = A[j+1];
                A[j+1] = temp;
            }
            j++;
        }
        i--;
        }
}

int main() 
{
    int A[] = {3, 6, 4, 7, 18, 10};
    int n = sizeof(A) / sizeof(A[0]);

    sortowanie_babelkowe(A, n);

    int posortowana_A[n];

    memcpy(posortowana_A, A, sizeof(A));

    for (int i = 0; i < n; ++i)
    {
        printf("%d ", posortowana_A[i]);
    }

    return 0;
}