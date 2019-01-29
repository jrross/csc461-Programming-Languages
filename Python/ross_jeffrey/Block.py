from ross_jeffrey.Light import Light
from ross_jeffrey.Car import Car

class Block:
    '''
    :file: Block.py
    :author: Jeff Ross
    :details: Handles the Block class which holds all the cars
    in two lanes with a light on each end.
    '''
    def __init__(self, l, s):
        '''
        __init__
        the constructor for the Block class, setting a length and speed
        and initializing two lanes and two null Lights
        :param l: the length to be set in miles
        :param s: the speed to be set in mph
        '''
        self.length = l
        self.speed = s
        self.upCars = []
        self.downCars = []
        self.upLight = Light(-1)
        self.downLight = Light(-1)

    def addCar(self, end, car):
        '''
        addCar
        adds a car to the specified end of the block
        :param end: the end to add to, 0 for down, 1 for up
        :param car: the car to add
        :return: nothing
        '''
        car.setLocation(0)
        #top
        if end == 0:
            self.downCars.append(car)
        #bottom
        elif end == 1:
            self.upCars.append(car)

    def displayCars(self):
        '''
        displayCars
        displays all the cars in the block, separated by lane
        :return: nothing
        '''
        print("Up----")
        for val in self.upCars:
            print(str(val))
        print("Down---")
        for val in self.downCars:
            print(str(val))

    def getCounts(self):
        '''
        getCounts
        Gets the total number of cars in each lane
        :return: count of cars in up lane, count of cars in down lane
        '''
        upcount = downcount = 0
        for temp in self.upCars:
            upcount += 1
        for temp in self.downCars:
            downcount += 1
        return upcount, downcount

    def getLength(self):
        '''
        getLength
        Gets the length of the block
        :return: the length of the block
        '''
        return self.length

    def removeCar(self,id):
        '''
        removeCar
        Removes a car from the block
        :param id: the id of the car to be removed
        :return: nothing
        '''
        for car in self.upCars:
            if car.getID() == id:
                self.upCars.remove(car)
                return

        for car in self.downCars:
            if car.getID() == id:
                self.downCars.remove(car)
                return

    def setDownLight(self, light):
        '''
        setDownLight
        sets the light at the bottom of the block
        :param light: the light to set the light to
        :return: nothing
        '''
        self.downLight = light

    def setUpLight(self, light):
        '''
        setUpLight
        sets the light at the top of the block
        :param light: the light to set the light to
        :return: nothing
        '''
        self.upLight = light

    def update(self, ind):
        '''
        update
        updates the Block to movee cars forward. First by updating the light then by
        checking if any cars were waiting at the light in the order they have arrived.
        If one is ready and the light is turned on, let it through. Then move all cars
        forward by 30s, and if any go PAST the length of the block, allow it through if
        the light is currently on. Only one car is allowed through per update, and this
        function only handles one lane at a time
        :param ind: indication of the lane to handle 1 for up, 0 for down
        :return: any car that may have passed, car with id of -1 if none was ready
        '''
        heldCar = Car(-1, 0)  # denotes car to be returned, -1 if not yet found
        if ind == 1: # up
            self.upLight.update()
            light = self.upLight.isOn()
            # go through and check in order if any cars are ready to move forward
            for car in self.upCars:
                if car.getLocation() == self.length and light and heldCar.getID() == -1:
                    heldCar = car
                    self.removeCar(car.getID())
            # go through and advance cars forward, move through light if they go past
            for car in self.upCars:
                car.update(30, self.speed)
                if car.getLocation() > self.length:
                    if light and heldCar.getID() == -1:
                        heldCar = car
                    else:
                        car.setLocation(self.length)
        if ind == 0: # down
            self.downLight.update()
            light = self.downLight.isOn()
            # go through and check in order if any cars are ready to move forward
            for car in self.downCars:
                if car.getLocation() == self.length and light and heldCar.getID() == -1:
                    heldCar = car
                    self.removeCar(car.getID())
            # go through and advance cars forward, move through light if they go past
            for car in self.downCars:
                car.update(30,self.speed)
                if car.getLocation() > self.length:
                    if light and heldCar.getID() == -1:
                        heldCar = car
                    else:
                        car.setLocation(self.length)
        self.removeCar(heldCar.getID())
        return heldCar

    def upLightOn(self):
        '''
        upLightOn
        indicates if the light at the top of the block is on
        :return: true if light is on, false if off
        '''
        return self.upLight.isOn()