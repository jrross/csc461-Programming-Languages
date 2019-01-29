/*************************************************************************//**
 * @file District.java
 *
 * @author Jeff Ross
 *
 * @details
 * Handles the District class.
 *
 *****************************************************************************/
package ross_jeffrey;
import java.util.ArrayList;

public class District {
    ArrayList<ParkingLot> lots = new ArrayList<>(1);
    private int timeOfClose = 0;
    private int timeClosed = 0;

    /*********************************************************************//**
     * @par Description:
     * Adds a new parking lot to the list
     *
     * param[in] parking - the lot to be added
     *
     * @returns the index of the lot in the list
     ************************************************************************/
    public int add(ParkingLot parking)
    {
        lots.add(parking);
        return lots.size() - 1;
    }

    /*********************************************************************//**
     * @par Description:
     * Gets all number of vehicles parked in all the lots in the district.
     *
     * @returns the value
     ************************************************************************/
    public int getVehiclesParkedInDistrict()
    {
        int size =0;
        for(int i = 0; i < lots.size(); i++)
            size +=lots.get(i).getVehiclesInLot();
        return size;
    }

    /*********************************************************************//**
     * @par Description:
     * Returns the total number of minutes in which all the lots were closed
     * at the same time
     *
     * @returns the number of minutes
     ************************************************************************/
    public int getClosedMinutes()
    {
        return timeClosed;
    }

    /*********************************************************************//**
     * @par Description:
     * Gets the total amount of money collected from all the lots
     * (if applicable)
     *
     * @returns the value
     ************************************************************************/
    public double getTotalMoneyCollected()
    {
        double sum = 0;
        for (ParkingLot l : lots)
            if (l instanceof PayParkingLot)
                sum += ((PayParkingLot) l).getProfit();
        return sum;
    }

    /*********************************************************************//**
     * @par Description:
     * Returns the parking lot class of the specified index in the list
     *
     * param[in] parking - the index of the lot
     *
     * @returns the lot
     ************************************************************************/
    public ParkingLot getLot(int parking)
    {
        return lots.get(parking);
    }

    /*********************************************************************//**
     * @par Description:
     * Tells whether or not all the parking lots are closed in the district
     *
     * @returns true if all are closed, false otherwise
     ************************************************************************/
    public Boolean isClosed()
    {
        for(int i =0; i < lots.size(); i++) //go through to check if all lots are closed at the same time
        {
            if(!lots.get(i).isClosed())
                return false;
        }
        return true;
    }

    /*********************************************************************//**
     * @par Description:
     * Puts the information of the district into a string to be printed.
     *
     * @returns the string
     ************************************************************************/
    public String toString()
    {
        String string = "District status:\n";
        for(int i = 0; i < lots.size(); i++)
            string += lots.get(i).toString() + "\n";
        return string;
    }

    /*********************************************************************//**
     * @par Description:
     * The function to handle the event where a car enters the parking lot.
     * Calls the function to enter the car into a lot. If all the lots becomes
     * closed, the district is marked as such
     *
     * param[in] parking - the index of which parking lot is being entered
     * param[in] time - the time when the car enters the parking lot
     *
     * @returns the ID of the car in the lot, -1 if error
     ************************************************************************/
    public int markVehicleEntry(int parking, int time)
    {
        Boolean closedPrev = isClosed(); //indicates if closed before car enters
        int result = lots.get(parking).markVehicleEntry(time);
        if(isClosed() && !closedPrev) //if lot closes as car enters
            timeOfClose = time;
        return result;
    }

    /*********************************************************************//**
     * @par Description:
     * The function to handle the event where a car leaves the parking lot.
     * Calls the function to remove the car into a lot. If all the lots are no
     * longer closed, the district is marked as such
     *
     * param[in] parking - the index of the lot in the district
     * param[in] time - the time the car left
     * param[in] ID - the ID of the car in the specified lot
     *
     * @returns -1 if error
     ************************************************************************/
    public int markVehicleExit(int parking, int time, int ID)
    {
        Boolean closedPrev = isClosed(); //indicates if closed before car leaves
        int result = lots.get(parking).markVehicleExit(time,ID);
        if(closedPrev == true && isClosed() == false) //if lot opens as car leaves
        {
            timeClosed += (time - timeOfClose);
            timeOfClose = 0;
        }
        return result;
    }
}
