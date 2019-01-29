package ross_jeffrey;
/*************************************************************************//**
 * @file Tile.java
 *
 * @author Jeff Ross
 *
 * @details
 * Handles the abstract base class tile.
 *
 *****************************************************************************/
public abstract class Tile {

    private char symbol;
    private ColorText.Color color;

    /*********************************************************************//**
     * @name Accept
     * @par Description:
     * Abstract visitor
     ************************************************************************/
    public void Accept(Visitor v)
    {
        return;
    }
    /*********************************************************************//**
     * @name getColor
     * @par Description:
     * gets the color of the tile
     *
     * @returns the color
     ************************************************************************/
    public ColorText.Color getColor()
    {
        return color;
    }

    /*********************************************************************//**
     * @name getSymbol
     * @par Description:
     * gets the symbol of the tile
     *
     * @returns the symbol
     ************************************************************************/
    public char getSymbol()
    {
        return symbol;
    }

    /*********************************************************************//**
     * @name setColor
     * @par Description:
     * sets the color of the tile to a certain color from the enum
     *
     * param[in] col - the color to set to
     ************************************************************************/
    public void setColor(ColorText.Color col)
    {
        color = col;
    }

    /*********************************************************************//**
     * @name setSymbol
     * @par Description:
     * sets the symbol to a new char
     *
     * param[in] sym - the symbol to change to
     ************************************************************************/
    public void setSymbol(char sym)
    {
        symbol = sym;
    }

    /*********************************************************************//**
     * @name print
     * @par Description:
     * Prints the symbol of the tile in the color of the tile
     ************************************************************************/
    public void print()
    {
        String toPrint = ColorText.colorString(Character.toString(symbol),color);
        System.out.printf("%s",toPrint);
    }
}
