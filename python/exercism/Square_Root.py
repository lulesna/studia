def square_root(number):
    if number >= 0:
        return round(number ** 0.5)
    else:
        raise ValueError("Invalid number")
