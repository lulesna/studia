from World import World
from Position import Position
from Organisms.Grass import Grass
from Organisms.Sheep import Sheep
from Organisms.Lynx import Lynx
from Organisms.Antelope import Antelope
import os

def addNewOrganism(world):
    print("Na które pole chcesz dodać organizm?")
    x = int(input("Podaj współrzedną x: "))
    y = int(input("Podaj współrzędną y: "))
    position = Position(xPosition=x, yPosition=y)
    if world.positionOnBoard(position) and world.getOrganismFromPosition(position) is None:
        print("Wybierz organizm: [A] Antelope, [G] Grass, [L] Lynx, [S] Sheep")
        organism = input().upper()
        if organism == "A":
            world.addOrganism(Antelope(position=position, world=world))
        elif organism == "G":
            world.addOrganism(Grass(position=position, world=world))
        elif organism == "L":
            world.addOrganism(Lynx(position=position, world=world))
        elif organism == "S":
            world.addOrganism(Sheep(position=position, world=world))
        else:
            print("Podano niepoprawny organizm.")
    else:
        print("Podano niepoprawne lub zajęte pole.")

if __name__ == '__main__':
    pyWorld = World(10, 10)

    newOrg = Grass(position=Position(xPosition=9, yPosition=9), world=pyWorld)
    pyWorld.addOrganism(newOrg)

    newOrg = Grass(position=Position(xPosition=1, yPosition=1), world=pyWorld)
    pyWorld.addOrganism(newOrg)

    newOrg = Sheep(position=Position(xPosition=2, yPosition=2), world=pyWorld)
    pyWorld.addOrganism(newOrg)

    newOrg = Lynx(position=Position(xPosition=4, yPosition=4), world=pyWorld)
    pyWorld.addOrganism(newOrg)

    newOrg = Antelope(position=Position(xPosition=7, yPosition=7), world=pyWorld)
    pyWorld.addOrganism(newOrg)

    print(pyWorld)
    print("Następna runda: [Enter]\nDodaj nowy organizm: [O]\nTryb plagi na 2 rundy: [P]")

    for _ in range(0, 50):
        choice = input().upper()
        if choice == 'O':
            addNewOrganism(pyWorld)
        elif choice == 'P':
            for _ in range(2):
                os.system('cls')
                print("Tryb plagi włączony: wszystkie organizmy tracą połowę życia.")
                pyWorld.plague()
                pyWorld.makeTurn()
                pyWorld.natureProtection()
                print(pyWorld)
        else:
            os.system('cls')
            pyWorld.makeTurn()
            pyWorld.natureProtection()
            print(pyWorld)
