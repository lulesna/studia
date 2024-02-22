/*Napisz program który będzie służył do wykonywania różnych operacji na pewnej tablicy znaków.

W programie powinna znajdować się zmienna globalna n informująca ile znaków znajduje się w tablicy.

Sama tablica powinna być zadeklarowana dla 100 znaków, ale nie powinna być globalna.

Głównym elementem funkcji main powinna być nieskończona pętla w której za pomocą istrukcji case/switch program prosi użytkownika o wybranie liczby odpowiadającej następnej akcji.

Jeśli użytkownik wybierze 0 program powinien zakończyć działanie.

Jeśli użytkownik wybierze 1 program powinien wywołać funkcję typu void która wypisze elementy tablicy, lub zwróci komunikat błędu jeśli n==0.

Jeśli użytkownik wybierze 2 program powinien wywołać funkcję typu void która sprawdzi czy w tablicy jest miejsce na nowy element. Jeśli tak to program poprosi użytkownika o podanie cyfry i wstawi ją do tablicy, jeśli nie ma miejsca lub znak podany przez użytkownika nie jest cyfrą program powinien zwrócić odpowiedni komunikat.

Jeśli użytkownik wybierze 3 program powninien wywołać funkcję typu void która poprosi o indeks elementu i usunie go z tablicy oraz zmniejszy zmienną n o jeden, jednocześnie przesuwając pozostałe elementy tak, aby tablica po wywołaniu zawierała wszystkie pozostałe elementy znajdowały się na n pierwszych miejscach zachowując oryginalną kolejność.

Jeśli użytkownik wybierze 4 program powinien wywołać funkcję typu int która zwróci sumę cyfr reprezentowanych przez znaki w tablicy, program główny powinien wypisać tę sumę na ekran. Jeśli funkcja zostanie wywołana dla kiedy tablica posiada 0 znaków funkcja powinna zwrócić liczbę która pozwoli programowi głównemu zwrócić odpowiedni komunikat zamiast Suma=0.

Jeśli użytkownik wybierze 5 program powinien wywołać funkcję typu void która wypisze elementy listy jako drzewo binarne, tzn. w pierwszym wierszu powinien być wypisany 1 element, w drugim 2, w trzecim 4 itd. pierwyszy wiersz powinien być wyśrodkowany na jedynym elemencie, pozostałe powinny być wypisane tak aby zachować struktórę przypominającą trójkąt równoboczny.

Jeśli użytkownik wybierze coś innego niż przewidziano w powyższych krokach program powinien poinformować go o błędnym wyborze i ponownie przedstawić dostępne opcje.*/

#include <stdio.h>

int n;

void wypisz_tablice(int tablica[]) 
{
    if (n != 0) {
        for (int i = 0; i < n; i++) {
            printf("%d ", tablica[i]);
        }
        printf("\n"); 
    }
    else {
        printf("Błąd: tablica jest pusta.\n");
    }
}

void czy_jest_miejsce(int tablica[]) 
{

}

int main() 
{
    int tablica[100];

    while(true) {
        int opcja;
        printf("Podaj, co chcesz zrobić:\n");
        printf("0 - zakończenie działania programu\n");
        printf("1 - wypisanie elementów tablicy\n");
        printf("2 - wpisanie nowego elementu do tablicy\n");
        printf("3 - usunięcie elementu z tablicy\n");
        printf("4 - sumowanie cyfr reprezentowanych przez znaki w tablicy\n");
        printf("5 - wypisanie listy jako drzewo binarne\n");
        scanf("%d", &opcja);

        switch (opcja) {
            case 0:
                printf("Koniec programu.\n");
                return 0;
            case 1:
                wypisz_tablice(&tablica);
                break;
            case 2:
                
                break;
            default:
                printf("Nieprawidłowy wybór. Spróbuj ponownie.\n");
                break;
        }
    }

    return 0;
}