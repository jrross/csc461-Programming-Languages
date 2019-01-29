package ross_jeffrey;
/*************************************************************************//**
 * @file Road.java
 *
 * @author Jeff Ross
 *
 * @details
 * Handles the Road class which inherits from tile.
 *
 *****************************************************************************/
public class Road extends Tile {

    /*********************************************************************//**
     * @name Accept
     * @par Description:
     * Visitor for the Building class
     *
     * param[in] visitor - the visitor to accept
     ************************************************************************/
    public void Accept(Visitor v)
    {
        v.visitRoad(this);
    }

    /*********************************************************************//**
     * @name Road
     * @par Description:
     * Constructor for the road class
     * Sets symbol to a line, and color to black
     ************************************************************************/
    public Road()
    {
        setSymbol('━');
        setColor(ColorText.Color.BLACK);
    }
}
