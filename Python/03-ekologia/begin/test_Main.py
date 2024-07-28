import pytest
from World import World
from Position import Position
from Organisms.Grass import Grass
from Organisms.Sheep import Sheep
from Organisms.Lynx import Lynx
from Organisms.Antelope import Antelope
from Main import addNewOrganism


@pytest.mark.parametrize("inputSequence, organismClass, position", [
    (["0", "0", "G"], Grass, Position(xPosition=0, yPosition=0)),
    (["1", "1", "S"], Sheep, Position(xPosition=1, yPosition=1)),
    (["2", "2", "L"], Lynx, Position(xPosition=2, yPosition=2)),
    (["3", "3", "A"], Antelope, Position(xPosition=3, yPosition=3)),
])
def test_addNewOrganism(inputSequence, organismClass, position, mocker):
    mocker.patch('builtins.input', side_effect=inputSequence)
    mocker.patch('builtins.print')

    world = World(10, 10)

    addNewOrganism(world)

    addedOrganism = world.getOrganismFromPosition(position)

    assert isinstance(addedOrganism, organismClass)
    assert addedOrganism.position == position


@pytest.mark.parametrize("inputSequence", [(["0", "0", "Z"]), (["5", "5", "G"])])
def test_addNewOrganism_invalid_cases(inputSequence, mocker):
    mocker.patch('builtins.input', side_effect=inputSequence)
    mocker.patch('builtins.print')

    world = World(10, 10)
    existingOrganism = Grass(position=Position(xPosition=5, yPosition=5), world=world)
    world.addOrganism(existingOrganism)

    addNewOrganism(world)

    assert len(world.organisms) == 1
