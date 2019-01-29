package ross_jeffrey;
/*************************************************************************//**
 * @file Empty.java
 *
 * @author Jeff Ross
 *
 * @details
 * Handles the Empty class which inherits from tile.
 *
 *****************************************************************************/
public class Empty extends Tile
{
    /*********************************************************************//**
     * @name Accept
     * @par Description:
     * Visitor for the Empty class
     *
     * param[in] visitor - the visitor to accept
     ************************************************************************/
    public void Accept(Visitor v)
    {
        v.visitEmpty(this);
    }
    /*********************************************************************//**
 * @name Empty
 * @par Description:
 * Constructor for the empty class
 * Sets symbol to a square, and color to black
 ************************************************************************/
    public Empty()
    {
        setSymbol('▫');
        setColor(ColorText.Color.BLACK);
    }
}
