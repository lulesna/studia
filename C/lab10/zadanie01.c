//Napisz program, który sprawdzi czy w katalogu domowym znajduje się plik plik.txt

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main()
{
    if (access("plik.txt", F_OK) == 0) { // F_OK - plik istnieje, R_OK - plik jest dozwolony do odczytu, W_OK - plik jest dozwolony do zapisu, X_OK - plik jest dozwolony do wykonywania programu
        printf("Plik plik.txt istnieje w katalogu domowym.\n");
    }
    return 0;
}