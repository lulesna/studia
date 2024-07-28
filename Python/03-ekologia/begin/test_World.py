import pytest
from Position import Position
from World import World
from Organisms.Grass import Grass
from Organisms.Sheep import Sheep
from Organisms.Lynx import Lynx
from Organisms.Antelope import Antelope


def test_plague():
    world = World(4, 4)
    grass = Grass(position=Position(xPosition=0, yPosition=0), world=world)
    sheep = Sheep(position=Position(xPosition=0, yPosition=1), world=world)
    lynx = Lynx(position=Position(xPosition=0, yPosition=2), world=world)
    antelope = Antelope(position=Position(xPosition=0, yPosition=3), world=world)

    world.addOrganism(grass)
    world.addOrganism(sheep)
    world.addOrganism(lynx)
    world.addOrganism(antelope)

    world.plague()

    assert grass.liveLength == 3
    assert sheep.liveLength == 5
    assert lynx.liveLength == 9
    assert antelope.liveLength == 5.5


def test_getAllPositions():
    world = World(3, 3)
    expectedPositions = [
        Position(xPosition=0, yPosition=0), Position(xPosition=0, yPosition=1), Position(xPosition=0, yPosition=2),
        Position(xPosition=1, yPosition=0), Position(xPosition=1, yPosition=1), Position(xPosition=1, yPosition=2),
        Position(xPosition=2, yPosition=0), Position(xPosition=2, yPosition=1), Position(xPosition=2, yPosition=2)
    ]

    positions = world.getAllPositions()

    assert positions == expectedPositions


def test_natureProtection():
    world = World(4, 4)
    grass1 = Grass(position=Position(xPosition=0, yPosition=0), world=world)
    grass2 = Grass(position=Position(xPosition=0, yPosition=1), world=world)
    grass3 = Grass(position=Position(xPosition=0, yPosition=2), world=world)
    grass4 = Grass(position=Position(xPosition=0, yPosition=3), world=world)
    grass5 = Grass(position=Position(xPosition=1, yPosition=0), world=world)
    grass6 = Grass(position=Position(xPosition=1, yPosition=1), world=world)
    grass7 = Grass(position=Position(xPosition=1, yPosition=2), world=world)
    grass8 = Grass(position=Position(xPosition=1, yPosition=3), world=world)
    grass9 = Grass(position=Position(xPosition=2, yPosition=0), world=world)
    world.addOrganism(grass1)
    world.addOrganism(grass2)
    world.addOrganism(grass3)
    world.addOrganism(grass4)
    world.addOrganism(grass5)
    world.addOrganism(grass6)
    world.addOrganism(grass7)
    world.addOrganism(grass8)
    world.addOrganism(grass9)

    world.natureProtection()

    assert sum(isinstance(o, Grass) for o in world.organisms) <= 8
    assert any(isinstance(o, Sheep) for o in world.organisms)
    assert any(isinstance(o, Lynx) for o in world.organisms)
    assert any(isinstance(o, Antelope) for o in world.organisms)
