def is_armstrong_number(number):
    str_number = str(number)
    sum = 0 # suma potęg cyfr liczby
    for digit in str_number: # pętla przechodzi przez każdą cyfrę po kolei
        sum += int(digit) ** len(str_number) # dodaje do sumy (cyfra ^ liczba cyfr) 
    return sum == number
