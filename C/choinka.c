#include <stdio.h>

#define czerwony "\x1b[31m"
#define zolty "\x1b[33m"
#define zielony "\x1b[32m"
#define reset "\x1b[0m"

void rysuj_galezie(int galezie, int wysokosc)
{   
    printf("\n");

    for (int i = 1; i <= galezie; i++)
    {
        for (int j = 1; j <= wysokosc; j++)
        {
                //rysowanie spacji
                for (int k = 1; k <= (wysokosc + galezie * 2) - j - i * 2; k++)
                {
                    printf(" ");
                }

                //zmiana koloru
                if (i == 1 && j == 1) {
                    printf(zolty); //żółty kolor dla czubka choinki
                }
                else {
                    printf(zielony); //zielony kolor dla reszty choinki
                }
                
                //rysowanie gwiazdek
                for (int k = 1; k <= 2 * j - 1 + 2 * (i - 1) * 2; k++)
                {
                    printf("*");
                }

                printf(reset);
                printf("\n");
        }
    }
}

void rysuj_pien(int galezie, int wysokosc)
{
    for (int i = 1; i <= (2 * wysokosc - 1 + 2 * (galezie - 1) * 2) / 2 - 1; i++)
        {
            printf(" ");
        }

    printf(czerwony);
    printf("|||\n\n");
    printf(reset);
}

int main()
{
    unsigned int galezie, wysokosc;
    
    printf("Liczba gałęzi: ");
    scanf("%u", &galezie);

    printf("Wysokość gałęzi: ");
    scanf("%u", &wysokosc);
    
    rysuj_galezie(galezie, wysokosc);

    rysuj_pien(galezie, wysokosc);

    return 0;
}