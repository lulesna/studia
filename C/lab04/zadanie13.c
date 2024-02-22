#include <stdio.h>

main()
{
    int n;

    printf("Podaj liczbę kolumn: ");
    scanf("%d", &n);

    if (n < 1) {
        printf("Niepoprawna liczba kolumn.\n");
        return 1;
    }

    printf("Tabela ASCII w %d kolumnach:\n", n);
    
    int liczba = 0;
    for (int i = 0; i < 128; i++) {
        
    }

    return 0;
}