def steps(number):
    steps = 0
    if number < 1:
        raise ValueError("Only positive integers are allowed")
    while number != 1:
        if number % 2 == 1:
            number = 3 * number + 1 
        else:
            number = number // 2
        steps += 1
    return steps
