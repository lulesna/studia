def equilateral(sides): # równoboczny
    if 0 < sides[0] < sides[1] + sides[2] and 0 < sides[1] < sides[0] + sides[2] and 0 < sides[2] < sides[0] + sides[1]:
        if sides[0] == sides[1] == sides[2]:
            return True
    return False


def isosceles(sides): # równoramienny
    if 0 < sides[0] < sides[1] + sides[2] and 0 < sides[1] < sides[0] + sides[2] and 0 < sides[2] < sides[0] + sides[1]:
        if sides[0] == sides[1] or sides[0] == sides[2] or sides[1] == sides[2]:
            return True
    return False


def scalene(sides): # różnoboczny
    if 0 < sides[0] < sides[1] + sides[2] and 0 < sides[1] < sides[0] + sides[2] and 0 < sides[2] < sides[0] + sides[1]:
        if sides[0] != sides[1] != sides[2] != sides[0]:
            return True
    return False
