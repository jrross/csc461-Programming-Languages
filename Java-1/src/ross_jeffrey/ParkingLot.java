/*************************************************************************//**
 * @file ParkingLot.java
 *
 * @section course_section Course Information
 *
 * @author Jeff Ross
 *
 * @date 9/10/2018
 *
 * @par Course:
 *         CSC 461
 *
 * @par Language Version: Java 8
 *
 * @details
 * This program is used to simulate parking lots, containing three different
 * types of objects: parking lots, payed parking lots, and districts. Where
 * parking lots keep track of an average parking lot, payed parking lots keep
 * track of parking lots in which people need to pay in order to park there,
 * and keeps track of how much they owe, and districts, which keeps track of a
 * collection of paid and unpaid parking lots. All three objects run off of
 * car entry and car exit events.
 *
 *****************************************************************************/

package ross_jeffrey;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.text.DecimalFormat;
public class ParkingLot
{
    public class Car
    {
        public int ID;
        public int time;

        public Car(int i, int t) //structure used to keep track of the known cars in the lot
        {
            ID = i;
            time = t;
        }
    }

    private String name;
    private int limit;
    private int totCars = 0; //total number of cars in the lot
    private int timeOfClose = 0; //Time when the lot closed last
    private int timeClosed = 0; //Total time the lot has spent closed
    private int recentTime = 0; //Time of the most recent event to occur
    private int nextID = 0; //The ID the next car will be given
    public static float CLOSED_THRESHOLD = 80;
    public ArrayList<Car> vehicles = new ArrayList<>(1);

    /*********************************************************************//**
     * @par Description:
     * Default constructor
     *
     * @returns nothing
     ************************************************************************/
    public ParkingLot(){}

    /*********************************************************************//**
     * @par Description:
     * Constructor to operate with name and limit, setting them accordingly
     *
     * param[in] n - the name of the parking lot
     * param[in] l - the limit of the parking lot
     *
     * @returns nothing
     ************************************************************************/
    public ParkingLot(String n, int l)
    {
        limit = l;
        name = n;
    }

    /*********************************************************************//**
     * @par Description:
     * Constructor to operate with only a limit, defaults the name of the
     * lot to "test"
     *
     * param[in] l - the limit of the parking lot
     *
     * @returns nothing
     ************************************************************************/
    public ParkingLot(int l)
    {
        limit = l;
        name = "test";
    }

    /*********************************************************************//**
     * @par Description:
     * The function to handle the vehicle entry event. Will take a car coming
     * in and add it to the list of cars currently in the lot after checking
     * to make sure the lot has room and that the car hasn't come in backwards
     * in time. If the lot goes over the threshold, it will turn the closed
     * sign on.
     *
     * param[in] time - the time the car arrives.
     *
     * @returns the ID assigned to the car, -1 if error
     ************************************************************************/
    public int markVehicleEntry(int time)
    {
        if(totCars == limit) //If lot is full, do not let the car in
            return -1;
        if(time < recentTime) //If the car is in the past, return error
            return -1;
        recentTime = time; //update most recent action time
        Boolean closedPrev = isClosed(); //mark if the lot was closed before car arrived

        totCars++;

        if(isClosed() && !closedPrev) //if the lot went from open to closed
            timeOfClose = time; //update closed time
        nextID++;

        Car car = new Car(nextID, time); //add car to the list
        vehicles.add(car);

        return nextID;
    }

    /*********************************************************************//**
     * @par Description:
     * The function to handle the vehicle exit event. Will check to make sure
     * that the time makes sense chronologically and that the car was still in
     * the lot at the time the event is triggered. If it was, it will be
     * removed from the list and handled accordingly. If the lot falls below
     * the closed threshold, it will turn off the closed sign.
     *
     * param[in] time - the time the car leaves the lot
     * param[in] ID - the id of the car (if any)
     *
     * @returns -1 if error.
     ************************************************************************/
    public int markVehicleExit(int time, int ID)
    {
        Boolean exists = false; //marker for if the car was in the ArrayList
        if(time < recentTime) //return error if in the past
            return -1;
        recentTime = time; //update most recent event time
        for(int i = 0; i < vehicles.size(); i++)
        {
            if(vehicles.get(i).ID == ID) //If the car has been noted, remove and mark the vehicle was there
            {
                vehicles.remove(i);
                exists = true;
            }
        }
        if(!exists && ID != 0) //If the car with ID wasn't there, return error
            return -1;
        Boolean closedPrev = isClosed(); //mark if lot was closed before car left
        totCars--;
        if(closedPrev == true && isClosed() == false) //if lot opens after car leaves
        {
            timeClosed += (time - timeOfClose); //update total amount of time the lot has spent closed
            timeOfClose = 0;
        }
        return 0;
    }
    /*********************************************************************//**
     * @par Description:
     * Checks if the lot is closed
     *
     * @returns true if closed, false if not
     ************************************************************************/
    public Boolean isClosed()
    {
        if (totCars >= limit * (CLOSED_THRESHOLD / 100)) //If over the threshold
            return true;
        else
            return false;
    }

    /*********************************************************************//**
     * @par Description:
     * For simplicity all of the following are the "getters" for parking lot.
     * used to retrieve the values of the variables in the class.
     *
     * @returns the value requested.
     ************************************************************************/
    public String getName() {return name;}

    public int getLimit() {return limit;}

    public int getVehiclesInLot() {return totCars;}

    public int getClosedMinutes() {return timeClosed;}

    public int getTotCars() {return totCars;}

    public int getRecentTime() {return recentTime;}

    public int getTimeOfClose() {return timeOfClose;}

    public int getTimeClosed() {return timeClosed;}

    /*********************************************************************//**
     * @par Description:
     * For simplicity, all of the following are the "setters" for the parking
     * lot. Assigns the value given to the appropriate variable
     *
     * param[in] the value to be assigned.
     *
     * @returns nothing
     ************************************************************************/
    public void setLimit(int l) {limit = l;}

    public void setName(String n) {name = n;}

    public void setTotCars(int t) {totCars = t;}

    public void setRecentTime(int t) {recentTime = t;}

    public void setTimeOfClose(int t) {timeOfClose = t;}

    public void setTimeClosed(int t) {timeClosed = t;}

    /*********************************************************************//**
     * @par Description:
     * Function to create a string that is used to read the information of
     * the parking lot to be printed.
     *
     * @returns the string
     ************************************************************************/
    public String toString()
    {
        String temp;
        temp = "Status for " + name + " parking lot: " + totCars + " vehicles (";
        if (isClosed())
            temp += "CLOSED)";
        else
        {
            NumberFormat nf = new DecimalFormat("##.#");
            temp += nf.format(((double) totCars / limit) * 100) + "%)";
        }
        return temp;
    }
}