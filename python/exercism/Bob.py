def response(hey_bob):
    if (hey_bob.strip()).endswith("?"):
        if hey_bob.isupper():
            return "Calm down, I know what I'm doing!"
        return 'Sure.'
    elif hey_bob.isupper():
        return 'Whoa, chill out!'
    elif not hey_bob or hey_bob.isspace():
        return 'Fine. Be that way!'
    return 'Whatever.'
