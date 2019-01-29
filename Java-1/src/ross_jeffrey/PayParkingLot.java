/*************************************************************************//**
 * @file PayParkingLot.java
 *
 * @author Jeff Ross
 *
 * @details
 * Handles the Paid Parking lot class.
 *
 *****************************************************************************/
package ross_jeffrey;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class PayParkingLot extends ParkingLot
{
    double price;
    double profit;
    /*********************************************************************//**
     * @par Description:
     * Constructor for the name, limit, and price of the parking lot,
     * assigning all three to the proper variables to be handled.
     *
     * param[in] n - the name of the parking lot
     * param[in] l - the limit of the parking lot
     * param[in] p - the price of the parking lot
     *
     * @returns nothing
     ************************************************************************/
    public PayParkingLot(String n, int l, double p)
    {
        price = p;
        setLimit(l);
        setName(n);
    }

    /*********************************************************************//**
     * @par Description:
     * Constructor for when provided a name and a limit. Sets the variables
     * accordingly and sets the price to $1.00.
     *
     * param[in] n - the name of the parking lot
     * param[in] l - the limit of hte parking lot
     *
     * @returns nothing
     ************************************************************************/
    public PayParkingLot(String n, int l)
    {
        setLimit(l);
        setName(n);
        price = 1.00;
    }

    /*********************************************************************//**
     * @par Description:
     * Returns the amount of money made thus far by the parking lot
     *
     * @returns the amount
     ************************************************************************/
    public double getProfit()
    {
        return profit;
    }

    /*********************************************************************//**
     * @par Description:
     * Puts the information of the class into a string that is readable for
     * printing.
     *
     * @returns the string
     ************************************************************************/
    public String toString()
    {
        String temp;
        temp = "Status for " + getName() + " parking lot: " + getTotCars() + " vehicles (";
        if (isClosed())
            temp += "CLOSED)";
        else
        {
            NumberFormat nf = new DecimalFormat("##.#");
            temp += nf.format(((double)getTotCars() / getLimit()) * 100) + "%)";
        }
        NumberFormat nf = new DecimalFormat("######.##");
        temp += " Money collected: $" + nf.format(getProfit());
        return temp;
    }

    /*********************************************************************//**
     * @par Description:
     * Function to handle the vehicle exit event. Charges the vehicle for how
     * long it was in the parking lot. If the ID of the car was not in the lot
     * or the event happened backwards in time, an error will be returned. the
     * latter scenario will be charged however. Then updates information on the
     * lot, removing the car from the list, and turns on the closed sign if
     * needed.
     *
     * param[in] time - the time the car leaves
     * param[in] ID - the ID of the car leaving the lot
     * @returns nothing
     ************************************************************************/
    public int markVehicleExit(int time, int ID)
    {
        Boolean exists = false; //to tell if was in ArrayList
        Boolean backwards = false; //to tell if backwards in time
        if(time < getRecentTime())
            backwards = true; //mark to not allow car to leave
        setRecentTime(time); //update most recent time
        for(int i = 0; i < vehicles.size(); i++)
        {
            if(vehicles.get(i).ID == ID)
            {
                profit += (((double)time - vehicles.get(i).time)/60) * price;
                if(!backwards)
                    vehicles.remove(i);
                exists = true;
            }
        }
        if((!exists && ID != 0) || backwards) //return error if car already left or if backwards in time
            return -1;
        Boolean closedPrev = isClosed(); //indicates if lot was closed before car left
        setTotCars(getTotCars() - 1);
        if(closedPrev == true && isClosed() == false) //if lot just opened, update times
        {
            setTimeClosed(getTimeClosed() + (time - getTimeOfClose()));
            setTimeOfClose(0);
        }
        return 0;
    }
}
