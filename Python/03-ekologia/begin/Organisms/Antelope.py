from Position import Position
from Action import Action
from ActionEnum import ActionEnum
from Animal import Animal
from Lynx import Lynx


class Antelope(Animal):

    def __init__(self, antelope=None, position=None, world=None):
        super(Antelope, self).__init__(antelope, position, world)

    def clone(self):
        return Antelope(self, None, None)

    def initParams(self):
        self.power = 4
        self.initiative = 3
        self.liveLength = 11
        self.powerToReproduce = 5
        self.sign = 'A'

    def getLynxPosition(self):
        neighboringPositions = self.world.getNeighboringPositions(self.position)
        for position in neighboringPositions:
            organism = self.world.getOrganismFromPosition(position)
            if isinstance(organism, Lynx):
                return position
        return None

    def getEscapePosition(self, lynxPosition):
        oppositeX = self.position.x + 2 * (self.position.x - lynxPosition.x)
        oppositeY = self.position.y + 2 * (self.position.y - lynxPosition.y)
        escapePosition = Position(xPosition=oppositeX, yPosition=oppositeY)
        if self.world.positionOnBoard(escapePosition) and self.world.getOrganismFromPosition(escapePosition) is None:
            return escapePosition
        return None

    def action(self):
        lynxPosition = self.getLynxPosition()
        if lynxPosition:
            escapePosition = self.getEscapePosition(lynxPosition)
            if escapePosition:
                print("Antelope flees from Lynx")
                return [Action(ActionEnum.A_MOVE, escapePosition, 0, self)]
            else:
                print(f"Antelope attacks Lynx on: {lynxPosition}")
                lynx = self.world.getOrganismFromPosition(lynxPosition)
                return self.consequences(lynx)
        else:
            return super(Antelope, self).action()
