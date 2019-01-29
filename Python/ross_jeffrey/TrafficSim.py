from ross_jeffrey.Street import Street
''' 
:file: TrafficSim.py
:section: M001
:author: Jeff Ross
:date: 10/20/2018
:course: CSC 461
:Language Version: python 3.7
:details: This file is used to hold the menu options to interact with the street
class that this program holds. The main purpose of this program is to to maintain
and modify a single street with multiple blocks. Each block has two lanes which can
hold multiple cars. Every block has a light between them which can cycle in multiples
of 30 seconds. Each block has it's own length and speed limit, but the length has a
minimum of half a mile. This menu allows the user to show the road, add a car, add a 
block, display all cars, and update the  entire road by 30 seconds, only allowing one 
car through a light in a direction per update
'''


def handleAddBlock(street):
    '''
    handleAddBlock
    handles the Add Block menu choice. Reads in a cycle time, block
    length, and speed limit. Then calls addBlock on the street to add it.
    :param street: street to add the block to
    :return: nothing
    '''
    try:
        cycle = int(input("Length of cycle: "))
        if cycle < 0:
            print("Invalid Option")
            return
    except ValueError:
        print("Invalid Option")
        return
    try:
        length = float(input("Length of block in miles: "))
        if length < 0.5:
            print("Invalid Option")
            return
    except ValueError:
        print("Invalid Option")
        return
    try:
        speed = int(input("Speed limit of block in mph: "))
        if speed < 0:
            print("Invalid Option")
            return
    except ValueError:
        print("Invalid Option")
        return
    street.addBlock(cycle, length, speed)

def handleAddCar(street):
    '''
    handleAddCar
    handles the menu choice to add a new car. Reads in end of the street
    to add to and the type of driver to add to the car. Then calls addCar on
    the street.
    :param street: street to add the car to
    :return: nothing
    '''
    try:
        end = int(input("Which end: 0) top 1) bottom: "))
        if end < 0 or end > 1:
            print("Invalid Option")
            return
    except ValueError:
        print("Invalid Option")
        return
    try:
        car = int(input("Which type: 0) slow 1) norm 2) fast: "))
        if car < 0 or car > 2:
            print("Invalid Option")
            return
    except ValueError:
        print("Invalid Option")
        return
    street.addCar(end, car)


choice = "0"
street = Street()
while choice != "5":
    print("0) show road\n1) update\n2) add car\n3) add block\n4) show cars\n5) quit\n")
    choice = input("Choice: ")

    if choice == "0":
        street.print()
    elif choice == "1":
        street.update()
    elif choice == "2":
        handleAddCar(street)
    elif choice == "3":
        handleAddBlock(street)
    elif choice == "4":
        street.showCars()
    elif choice == "5":
        print(5)
    else:
        print("Invalid Option")