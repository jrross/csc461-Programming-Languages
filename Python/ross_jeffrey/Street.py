from ross_jeffrey.Block import Block
from ross_jeffrey.Car import Car
from ross_jeffrey.Light import Light
from ross_jeffrey.Iterator import Iterator
from ross_jeffrey.ReverseIterator import ReverseIterator

class Street:
    '''
    :file: Street.py
    :author: Jeff Ross
    :details: Handles the street class to represent the entire street of
    the system, holding multiple blocks
    '''
    def __init__(self):
        '''
        __init__
        The constructor for the street class, creating a default block with a
        length of 0.5, and a speed limit of 30
        '''
        ''
        block = Block(0.5, 30)
        self.blocks = []
        self.blocks.append(block)
        self.currID = 1

    def addBlock(self, cycle, length, speed):
        '''
        addBlock
        adds a new block to the street, and appends a light to the
        :param cycle: The amount of time it takes for a light to change
        :param length: The length of the block in miles
        :param speed: The speed limit of the block in mph
        :return: nothing
        '''
        light1 = Light(cycle)
        light2 = Light(cycle)
        self.blocks[len(self.blocks)-1].setDownLight(light1)
        block = Block(length, speed)
        block.setUpLight(light2)
        self.blocks.append(block)

    def addCar(self, end, type):
        '''
        addCar
        Adds a car to one of the end blocks on the street (top or bottom) of
        one of three types (nervous, normal, and aggressive)
        :param end: the end to add the car to
        :param type: the type
        :return: nothing
        '''
        #top
        if end == 0:
            car = Car(self.currID, type)
            self.blocks[0].addCar(end, car)
            self.currID = self.currID + 1
        #bottom
        elif end == 1:
            car = Car(self.currID, type)
            self.blocks[len(self.blocks) - 1].addCar(end, car)
            self.currID = self.currID + 1

    def print(self):
        '''
        print
        Prints out the current status of the street, containing how many
        cars are in each lane in each block, and the status of the lights
        that sit between them
        :return: nothing
        '''
        print("Entry\Exit")
        for i, block in Iterator(self.blocks):
            if i != 0:
                if block.upLightOn():
                    print("On")
                else:
                    print("Off")
            up, down = block.getCounts()
            print("Up: " + str(up) + " Down: " + str(down))
        print("Entry\Exit")


    def removeCar(self, id):
        '''
        removeCar
        removes a car from the block that it is in
        :param id: the id of the car to be removed
        :return: nothing
        '''
        for i, block in self.blocks:
            block.removeCar(id)

    def showCars(self):
        '''
        Displays all the cars id's and positions per block in the street
        :return: nothing
        '''
        for i, block in Iterator(self.blocks):
            print("Block " + str(i) + "-------------------")
            block.displayCars()
            print("")

    def update(self):
        '''
        update
        Goes through all the blcoks and updates them to allow cars to pass
        between blocks. But only one car between an intersection per update
        with priority for the cars that arrive first. And prints out the
        street afterwards.
        :return: nothing
        '''
        prevCar = Car(-1, 0)
        currCar = Car(-1, 0)
        for i, block in Iterator(self.blocks):
            currCar = block.update(0)
            if prevCar.getID() != -1:
                block.addCar(0, prevCar)
            prevCar = currCar
        prevCar = Car(-1,0)
        for i, block in ReverseIterator(self.blocks):
            #print("Block: " + str(i))
            currCar = block.update(1)
            if prevCar.getID() != -1:
                block.addCar(1, prevCar)
            prevCar = currCar
        self.print()
