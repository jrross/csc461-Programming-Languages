package ross_jeffrey;
import java.util.Scanner;
/*************************************************************************//**
 * @file CityProgram.java
 *
 * @section M001
 *
 * @author Jeff Ross
 *
 * @date 9/25/2018
 *
 * @par Course:
 *         CSC 461
 *
 * @par Language Version: Java 8
 *
 * @details
 * This program is used to maintain a city with a 5x5 grid of various tiles.
 * These tiles can either be empty, green space, road, or a building. This grid
 * and the tiles within it, are all modified via the City class, which houses
 * them. Information on the tiles can be accessed via a visitor pattern. The
 * visitors used are Color, Count, Fix, and IsRoad. This program runs off a main
 * menu which gives the options to generate a default city, set tiles, remove
 * tiles, count the number of each type of tile, set the tile color for a type
 * of tile, fix the roads such that they face each other with their ASCII char,
 * and to exit the program.
 *
 *****************************************************************************/
public class CityProgram {
    /*********************************************************************//**
     * @name handleRemoveTile
     * @par Description:
     * Handles the option to remove a tile from the city, by using the setTile
     * function in the City class. Handles any possible errors in the user
     * input.
     *
     * param[in] city - The class that holds the grid to remove the tile from.
     * param[in] in - the scanner used in main to keep a constant stream
     ************************************************************************/
    private static void handleRemoveTile(City city, Scanner in)
    {
        System.out.printf("Input location (x y): ");
        int x,y;
        try
        {
            x = Integer.parseInt(in.next());
            y = Integer.parseInt(in.next());
        }
        catch(NumberFormatException e)
        {
            System.out.println("Invalid option");
            return;
        }
        if(x > 4 || y > 4)
        {
            System.out.println("Invalid option");
            return;
        }
        city.setTile(x,y, City.TileType.EMPTY);
    }
    /*********************************************************************//**
     * @name handleSetColor
     * @par Description:
     * Handles the option to change the color of all the tiles of a type by
     * taking user input and enforcing proper values, and then setting the
     * color of the tiles with the setColor function in the City class.
     *
     * param[in] city - the city to change the color of the tiles in.
     ************************************************************************/
    private static void handleSetColor(City city)
    {
        Scanner in = new Scanner(System.in);
        System.out.printf("Input tile type 1) greenspace 2) building 3) road #) empty: ");
        String temp = in.nextLine();
        int tileType;
        try
        {
            tileType = Integer.parseInt(temp);
        }
        catch(NumberFormatException e)
        {
            System.out.println("Invalid option");
            return;
        }

        System.out.printf("Input color 1) red 2) yellow 3) blue 4) green #) black: : ");
        temp = in.nextLine();
        int colorNum;
        try
        {
            colorNum = Integer.parseInt(temp);
        }
        catch(NumberFormatException e)
        {
            System.out.println("Invalid Option");
            return;
        }
        ColorText.Color color;
        switch(colorNum)
        {
            case 1:
                color = ColorText.Color.RED;
                break;
            case 2:
                color = ColorText.Color.YELLOW;
                break;
            case 3:
                color = ColorText.Color.BLUE;
                break;
            case 4:
                color = ColorText.Color.GREEN;
                break;
            default:
                color = ColorText.Color.BLACK;
        }
        switch(tileType)
        {
            case 1:
                city.setColor(City.TileType.GREENSPACE, color);
                break;
            case 2:
                city.setColor(City.TileType.BUILDING, color);
                break;
            case 3:
                city.setColor(City.TileType.ROAD, color);
                break;
            default:
                city.setColor(City.TileType.EMPTY, color);
                break;
        }
    }
    /*********************************************************************//**
     * @name handleSetTile
     * @par Description:
     * Takes the input from the user for the option to set a tile in the grid.
     * Validates the input and the calls the setTile function from the city to
     * set the tile to the appropriate type at the appropriate coordinate.
     *
     * param[in] city - the City in which the tile should be set
     * param[in] in - the scanner used in main to keep a constant stream
     ************************************************************************/
    private static void handleSetTile(City city, Scanner in)
    {
        System.out.printf("Input tile type 1) greenspace 2) building 3) road #) empty: ");
        String temp = in.next();
        int tileType;
        try
        {
            tileType = Integer.parseInt(temp);
        }
        catch(NumberFormatException e)
        {
            System.out.println("Invalid option");
            return;
        }
        System.out.printf("Input location (x y): ");
        int x,y;
        try
        {
            x = Integer.parseInt(in.next());
            y = Integer.parseInt(in.next());
            if(x > 4 || y > 4)
            {
                System.out.println("Invalid option");
                return;
            }
        }
        catch(NumberFormatException e)
        {
            System.out.println("Invalid option");
            return;
        }
        switch(tileType)
        {
            case 1:
                city.setTile(x,y, City.TileType.GREENSPACE);
                break;
            case 2:
                city.setTile(x,y, City.TileType.BUILDING);
                break;
            case 3:
                city.setTile(x,y, City.TileType.ROAD);
                break;
            default:
                city.setTile(x,y, City.TileType.EMPTY);
                break;
        }

    }
    /*********************************************************************//**
     * @name main
     * @par Description: the main entry point of the program. Instantiates the
     * city and then runs in an infinite loop, displaying the options and
     * calling the corresponding functions of the options when selected.
     *
     *
     * param[in] args - the commandline arguments
     ************************************************************************/
    public static void main(String args[])
    {
        City city = new City();
        Scanner in = new Scanner(System.in);
        int choice;
        while(true)
        {
            choice = -1; //signals an invalid entry was used
            city.printCity();
            System.out.printf("\n0) Make default City\n1) Set Tile\n2) Remove tile\n3) Count Zones\n");
            System.out.printf("4) Set tile color\n5) Fix roads\n6) Quit\n\nChoice: ");
            String s = in.next();
            try
            {
                choice = Integer.parseInt(s);
                if(choice > 6)
                    System.out.println("Invalid option");
            }
            catch(NumberFormatException e)
            {
                System.out.println("Invalid option");
            }

            switch(choice)
            {
                case 0:
                    city.makeDefaultCity();
                    break;
                case 1:
                    handleSetTile(city,in);
                    break;
                case 2:
                    handleRemoveTile(city,in);
                    break;
                case 3:
                    city.countTile();
                    break;
                case 4:
                    handleSetColor(city);
                    break;
                case 5:
                    city.fixRoads();
                    break;
                case 6:
                    return;
            }
        }
    }
}
