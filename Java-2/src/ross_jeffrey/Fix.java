package ross_jeffrey;
/*************************************************************************//**
 * @file Fix.java
 *
 * @author Jeff Ross
 *
 * @details
 * Handles the Fix visitor class.
 *
 *****************************************************************************/
public class Fix extends Visitor
{
    private boolean left,right,top,bot;

    /*********************************************************************//**
 * @name Fix
 * @par Description:
 * Constructor. sets the left, right, top, and bottom booleans
 *
 * param[in] l - left boolean
 * param[in] r - right boolean
 * param[in] t - top boolean
 * param[in] b - bottom boolean
 ************************************************************************/
    public Fix(boolean l, boolean r, boolean t, boolean b)
    {
        left = l;
        right = r;
        top = t;
        bot = b;
    }
    /*********************************************************************//**
 * @name visitRoad
 * @par Description:
 * Visitor to change the symbol for a road. Uses provided code to calculate
 * the symbol based on the booleans passed in the constructor
 *
 * param[in] road - the Road to change
 ************************************************************************/
    public void visitRoad(Road road)
    {
        // Create the adjacency code
        int code = (left ? 1 : 0) | (top ? 2 : 0) | (bot ? 4 : 0) | (right ? 8 : 0);

        //unicode list
        char symbols[] = {
                '━',      // 0 right
                '━',      // 1 right
                '┃',      // 2 ud
                '┛',      // 3 lu
                '┃',      // 4 ud
                '┓',      // 5 ld
                '┃',      // 6 ud
                '┫',     // 7 lud
                '━',      // 8 right
                '━',      // 9 right
                '┗',      // 10 top
                '┻',     // 11 lur
                '┏',      // 12 dr
                '┳',    // 13 ldr
                '┣',     // 14 udr
                '╋'    // 15 ludr
        };

        // symbols[code] now is the correct symbol
        road.setSymbol(symbols[code]);
    }
}
