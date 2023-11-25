/*Zmodyfikuj rozwiązanie zadania 9 tak aby piłeczka mogła "odbijać" się od krawędzi planszy.*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main() {
    int a1, b1, a2, b2, da, db;

    printf("Podaj początkową pozycję piłeczki (dwie liczby od 1 do 10 oddzielone spacją): ");
    scanf("%d %d", &a1, &b1);

    if (a1 < 1 || a1 > 10 || b1 < 1 || b1 > 10) {
        printf("Błędne dane.\n");
        return 1;
    }

    printf("Podaj nową pozycję piłeczki (dwie liczby od 1 do 10 oddzielone spacją): ");
    scanf("%d %d", &a2, &b2);

    if (a2 < 1 || a2 > 10 || b2 < 1 || b2 > 10) {
        printf("Błędne dane.\n");
        return 1;
    }

    da = a2 - a1;
    db = b2 - b1;
    
    while (1) {
        
        system("clear");
        
        for (int i = 1; i <= 10; ++i)
        {    
            for (int j = 1; j <= 10; ++j) 
            {    
                if (i == a1 && j == b1) {
                    printf("x ");
                } else {
                    printf("- ");
                }
            }
            printf("\n");
        }
        
        a1 += da;
        b1 += db;
        sleep(1);
        
        if (a1 < 1) {
            a1 = 1;
            da = -da;
        }
        if (a1 > 10) {
            a1 = 10;
            da = -da;
        }
        if (b1 < 1) {
            b1 = 1;
            db = -db;
        }
        if (b1 > 10) {
            b1 = 10;
            db = -db;
        }
    }

    return 0;
}
