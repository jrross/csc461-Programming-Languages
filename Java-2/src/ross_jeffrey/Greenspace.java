package ross_jeffrey;
/*************************************************************************//**
 * @file Greenspace.java
 *
 * @author Jeff Ross
 *
 * @details
 * Handles the Greenspace class which inherits from tile.
 *
 *****************************************************************************/
public class Greenspace extends Tile{
    /*********************************************************************//**
     * @name Accept
     * @par Description:
     * Visitor for the Greenspace class
     *
     * param[in] visitor - the visitor to accept
     ************************************************************************/
    public void Accept(Visitor v)
    {
        v.visitGreenspace(this);
    }

    /*********************************************************************//**
     * @name Greenspace
     * @par Description:
     * Constructor for the greenspace class
     * Sets symbol to a tree, and color to black
     ************************************************************************/
    public Greenspace()
    {
        setSymbol('⚵');
        setColor(ColorText.Color.BLACK);
    }
}
