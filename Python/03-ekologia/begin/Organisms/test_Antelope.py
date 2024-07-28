import pytest
from Position import Position
from World import World
from ActionEnum import ActionEnum
from Action import Action
from Organisms.Antelope import Antelope
from Organisms.Lynx import Lynx


@pytest.mark.parametrize("lynxPosition", [
    Position(xPosition=1, yPosition=1), Position(xPosition=2, yPosition=1),
    Position(xPosition=3, yPosition=1), Position(xPosition=1, yPosition=2),
    Position(xPosition=1, yPosition=3), Position(xPosition=2, yPosition=3),
    Position(xPosition=3, yPosition=3), Position(xPosition=3, yPosition=2)
])
def test_getLynxPosition(lynxPosition):
    world = World(4, 4)
    antelope = Antelope(position=Position(xPosition=2, yPosition=2), world=world)
    lynx = Lynx(position=lynxPosition, world=world)

    world.addOrganism(antelope)
    world.addOrganism(lynx)

    assert antelope.getLynxPosition() == lynxPosition


@pytest.mark.parametrize("lynxPosition, escapeCoordinates", [
    (Position(xPosition=1, yPosition=1), Position(xPosition=4, yPosition=4)),
    (Position(xPosition=2, yPosition=1), Position(xPosition=2, yPosition=4)),
    (Position(xPosition=3, yPosition=1), Position(xPosition=0, yPosition=4)),
    (Position(xPosition=1, yPosition=2), Position(xPosition=4, yPosition=2)),
    (Position(xPosition=1, yPosition=3), Position(xPosition=4, yPosition=0)),
    (Position(xPosition=2, yPosition=3), Position(xPosition=2, yPosition=0)),
    (Position(xPosition=3, yPosition=3), Position(xPosition=0, yPosition=0)),
    (Position(xPosition=3, yPosition=2), Position(xPosition=0, yPosition=2))
])
def test_getEscapePosition(lynxPosition, escapeCoordinates):
    world = World(5, 5)
    antelope = Antelope(position=Position(xPosition=2, yPosition=2), world=world)
    lynx = Lynx(position=lynxPosition, world=world)

    world.addOrganism(antelope)
    world.addOrganism(lynx)
    escapePosition = antelope.getEscapePosition(lynxPosition)

    assert escapePosition == escapeCoordinates


def test_action_escape():
    world = World(5, 5)
    antelope = Antelope(position=Position(xPosition=2, yPosition=2), world=world)
    lynx = Lynx(position=Position(xPosition=1, yPosition=1), world=world)
    world.addOrganism(antelope)
    world.addOrganism(lynx)

    actionResult = antelope.action()

    expectedAction = Action(ActionEnum.A_MOVE, Position(xPosition=4, yPosition=4), 0, antelope)

    assert len(actionResult) == 1
    assert actionResult[0].action == expectedAction.action
    assert actionResult[0].position == expectedAction.position
    assert actionResult[0].value == expectedAction.value
    assert actionResult[0].organism == expectedAction.organism


def test_action_attack():
    world = World(5, 5)
    antelope1 = Antelope(position=Position(xPosition=2, yPosition=2), world=world)
    antelope2 = Antelope(position=Position(xPosition=2, yPosition=4), world=world)
    lynx = Lynx(position=Position(xPosition=2, yPosition=1), world=world)
    world.addOrganism(antelope1)
    world.addOrganism(antelope2)
    world.addOrganism(lynx)

    actionResult = antelope1.action()
    expectedAction = antelope1.consequences(lynx)

    assert len(actionResult) == len(expectedAction)
    for result, expected in zip(actionResult, expectedAction):
        assert result.action == expected.action
        assert result.position == expected.position
        assert result.value == expected.value
        assert result.organism == expected.organism


def test_action_super():
    world = World(5, 5)
    antelope = Antelope(position=Position(xPosition=2, yPosition=2), world=world)
    lynx = Lynx(position=Position(xPosition=0, yPosition=0), world=world)
    world.addOrganism(antelope)
    world.addOrganism(lynx)

    actionResult = antelope.action()

    assert actionResult == super(Antelope, antelope).action()
