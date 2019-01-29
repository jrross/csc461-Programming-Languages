package ross_jeffrey;
/*************************************************************************//**
 * @file Building.java
 *
 * @author Jeff Ross
 *
 * @details
 * Handles the Building class which inherits from tile.
 *
 *****************************************************************************/
public class Building extends Tile {
    /*********************************************************************//**
     * @name Accept
     * @par Description:
     * Visitor for the Building class
     *
     * param[in] visitor - the visitor to accept
     ************************************************************************/
    public void Accept(Visitor v)
    {
        v.visitBuilding(this);
    }
    /*********************************************************************//**
     * @name Building
     * @par Description:
     * Constructor for the Building class
     * Sets symbol to a house, and color to black
     ************************************************************************/
    public Building()
    {
        setSymbol('⌂');
        setColor(ColorText.Color.BLACK);
    }
}
