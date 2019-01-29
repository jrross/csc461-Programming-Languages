class Car:
    '''
    :file: Car.py
    :author: Jeff Ross
    :details: Handles the car class to represent the cars on the road
    '''

    def __init__(self, i, type):
        '''
        __init__
        constructor for the Car class
        :param i: id to be used for the car
        :param type: the type to determine which function to use to
        calculate the distance traveled for the Car.
        '''
        self.id = i
        self.location = 0.0
        if type == 0:
            self.calcDrive = nervous
        if type == 1:
            self.calcDrive = normal
        if type == 2:
            self.calcDrive = aggressive

    def __str__(self):
        '''
        __str__
        Overrided function to print the Car class
        :return: the id and location within a string
        '''
        return str(self.id) + ": " + str(self.location)

    def calcDrive(self, time, speed):
        '''
        calcDrive
        Abstract function used with strategy pattern to calculate distance
        traveled over a time span.
        :param time: the amount of time the car will drive
        :param speed: the speed limit of the road
        :return: 0
        '''
        return 0.0

    def getID(self):
        '''
        getID
        gets the id of the Car class
        :return: Car id
        '''
        return self.id

    def getLocation(self):
        '''
        getLocation
        gets the location of the Car class on the block
        :return: Car location
        '''
        return self.location

    def setLocation(self, loc):
        '''
        setLocation
        sets the location of the Car class on the block
        :param loc: the location to be set
        :return: nothing
        '''
        self.location = loc

    def update(self, time, speed):
        '''
        update
        updates the position of the Car using the calcDrive function
        :param time: the amount of time the car drives
        :param speed: the speed of the road the car is driving on
        :return: nothing
        '''
        self.setLocation(self.getLocation() + self.calcDrive(time, speed))

def nervous(time, speed):
    '''
    nervous
    Strategy for a nervous driver
    :param time: the amount of time the car drives
    :param speed: the speed limit of the road the car is driving on
    :return: the distance traveled
    '''
    return time * ((speed - 5)/3600)

def normal(time, speed):
    '''
    normal
    Strategy for a normal driver
    :param time:  the amount of time the car drives
    :param speed:  the speed limit of the road the car is driving on
    :return: the distance traveled
    '''
    return time * (speed/3600)

def aggressive(time, speed):
    '''
    aggressive
    Strategy for an aggressive driver
    :param time: the amount of time the car drives
    :param speed: the speed limit of the road the car is driving on
    :return: the distance traveled
    '''
    return time * ((speed + 5)/3600)