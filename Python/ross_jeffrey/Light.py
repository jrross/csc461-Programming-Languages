class Light:
    '''
    :file: Light.py
    :author: Jeff Ross
    :details: Handles the Light class to represent the lights on the ends
    of the roads
    '''
    def __init__(self, cycle):
        '''
        __init__
        Constructor for Light class to set the cycle
        :param cycle: the amount of time before the light changes
        '''
        self.cycleTime = cycle
        self.timer = cycle
        if cycle == -1:
            self.on = True
        else:
            self.on = False

    def changeLight(self):
        '''
        changeLight
        Changes the light to be on if it is off, and changes the light to
        off if it is on.
        :return: nothing
        '''
        if self.on:
            self.on = False
        else:
            self.on = True

    def getCycle(self):
        '''
        getCycle
        gets the cycle time for the light
        :return: cycle time
        '''
        return self.cycleTime

    def isOn(self):
        '''
        isOn
        Indicates whether or not the light is currently on
        :return: true if on, false if off
        '''
        return self.on

    def resetTimer(self):
        '''
        resetTimer
        Resets the timer to the cycle time of the light.
        :return: nothing
        '''
        self.timer = self.cycleTime

    def update(self):
        '''
        update
        Updates the light, if the light is null (end of road) will do nothing.
        Otherwise, decrements the timer, if the timer runs out, resets the timer
        and changes the light.
        :return: nothing
        '''
        if self.cycleTime == -1:
            return
        self.timer = self.timer - 1
        if self.timer < -1:
            self.resetTimer()
            self.changeLight()
