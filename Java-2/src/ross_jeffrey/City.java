package ross_jeffrey;
/*************************************************************************//**
 * @file City.java
 *
 * @author Jeff Ross
 *
 * @details
 * Handles the City class. Maintains the 5x5 grid for the program.
 *
 *****************************************************************************/
public class City {
    /*! Enum to identify which type of tile is present*/
    public enum TileType{
        EMPTY, GREENSPACE, ROAD, BUILDING
    }

    private Tile[][] grid = new Tile[5][5];

    /*********************************************************************//**
     * @name countTile
     * @par Description:
     * The function used to count the number of each tile in the city and
     * print them all to the screen. Counts and outputs by using the Count
     * visitor
     ************************************************************************/
    public void countTile()
    {
        Count visitor = new Count();

        for(int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                grid[i][j].Accept(visitor);
            }
        }
        visitor.printTotals(); //prints all values for all subclasses
    }
    /*********************************************************************//**
     * @name City
     * @par Description:
     * Constructor for the City class. Goes through the grid tile by tile and
     * sets all of the tiles to empty.
     ************************************************************************/
    public City()
    {
        for(int i = 0; i < 5; i++)
            for(int j = 0; j <5; j++)
                this.setTile(i,j,TileType.EMPTY);

    }
    /*********************************************************************//**
     * @name fixRoads
     * @par Description:
     * Function to be called to change the symbols for the roads such that
     * they face each other. This is done by finding out which of the tiles
     * tiles it touches (no diagonals) are roads using the isRoad visitor,
     * and passing that information to the Fix visitor.
     ************************************************************************/
    public void fixRoads()
    {
        IsRoad visitor = new IsRoad();
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
            {
                grid[i][j].Accept(visitor);
                if(visitor.getResult())
                {
                    boolean left = false;
                    boolean right = false;
                    boolean top = false;
                    boolean bot = false;
                    if(j != 0)//won't try to access non-existing memory
                    {
                        grid[i][j-1].Accept(visitor);
                        left = visitor.getResult();
                    }
                    if(j != 4)//won't try to access non-existing memory
                    {
                        grid[i][j+1].Accept(visitor);
                        right = visitor.getResult();
                    }
                    if(i != 0)//won't try to access non-existing memory
                    {
                        grid[i-1][j].Accept(visitor);
                        top = visitor.getResult();
                    }
                    if(i != 4)//won't try to access non-existing memory
                    {
                        grid[i+1][j].Accept(visitor);
                        bot = visitor.getResult();
                    }
                    Fix fix = new Fix(left,right,top,bot);
                    grid[i][j].Accept(fix);
                }
            }
    }
    /*********************************************************************//**
     * @name makeDefaultCity
     * @par Description:
     * Fills the grid with the pre-specified default values for the city.
     ************************************************************************/
    public void makeDefaultCity()
    {
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                //road for y < 3, empty for y >= 3
                if(i < 3)
                    this.setTile(i,j,TileType.ROAD);
                else
                    this.setTile(i,j,TileType.EMPTY);
            }
        }
        this.setTile(0,3,TileType.GREENSPACE);
        this.setTile(0,4,TileType.GREENSPACE);
        this.setTile(1,3,TileType.GREENSPACE);
        this.setTile(1,4,TileType.EMPTY);
        this.setTile(2,3,TileType.BUILDING);
        this.setTile(2,4,TileType.EMPTY);
        this.setTile(3,3,TileType.BUILDING);
        this.setTile(3,4,TileType.EMPTY);
        this.setTile(4,3,TileType.EMPTY);
        this.setTile(4,4,TileType.GREENSPACE);
    }
    /*********************************************************************//**
     * @name printCity
     * @par Description:
     * Prints the Tiles in grid in according to how they sit in the 5x5 space
     ************************************************************************/
    public void printCity()
    {
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                grid[i][j].print();
            }
            System.out.printf("%n");
        }
    }
    /*********************************************************************//**
     * @name setColor
     * @par Description:
     * Sets the color of a specified tile type using the Color visitor
     *
     * param[in] type - the type of tile from the TileType enum
     * param[in] color - the color to change from from the Color Enum
     ************************************************************************/
    public void setColor(TileType type, ColorText.Color color)
    {
        Color visitor = new Color(type,color);
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                grid[i][j].Accept(visitor);
    }
    /*********************************************************************//**
     * @name setTile
     * @par Description:
     * Sets the tile at a specified point in the grid to be changed into a
     * different object according to the TileType
     *
     * param[in] x - the x coordinate for the tile
     * param[in] y - the y coordinate for the tile
     * param[in] type - the type of tile to change to specified by the
     *                  TileType enum
     ************************************************************************/
    public void setTile(int x, int y, TileType type)
    {
        Tile tile;
        switch(type)
        {
            case EMPTY:
                tile = new Empty();
                break;
            case GREENSPACE:
                tile = new Greenspace();
                break;
            case ROAD:
                tile = new Road();
                break;
            case BUILDING:
                tile = new Building();
                break;
            default:
                tile = new Empty();
                break;
        }
        grid[x][y] = tile;
    }
}
