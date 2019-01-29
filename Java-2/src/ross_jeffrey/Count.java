package ross_jeffrey;
/*************************************************************************//**
 * @file Count.java
 *
 * @author Jeff Ross
 *
 * @details
 * Handles the Count visitor class.
 *
 *****************************************************************************/
public class Count extends Visitor {

    private int numEmpty = 0;
    private int numGreenspace = 0;
    private int numRoad = 0;
    private int numBuilding = 0;

    /*********************************************************************//**
     * @name printTotals
     * @par Description:
     * Prints the counts for all the of the tiles types sent in the thus far
     ************************************************************************/
    public void printTotals()
    {
        System.out.println("Empty: " + numEmpty);
        System.out.println("Greenspaces: " + numGreenspace);
        System.out.println("Roads: " + numRoad);
        System.out.println("Buildings: " + numBuilding);
    }
    /*********************************************************************//**
     * @name visitBuilding
     * @par Description:
     * The visitor call for the building class. Increments building count
     *
     * param[in] building - the building
     ************************************************************************/
    public void visitBuilding(Building building)
    {
        numBuilding++;
    }

    /*********************************************************************//**
     * @name visitEmpty
     * @par Description:
     * The visitor call for the emptyclass. Increments empty count
     *
     * param[in] empty - the empty
     ************************************************************************/
    public void visitEmpty(Empty empty)
    {
        numEmpty++;
    }

    /*********************************************************************//**
     * @name visitGreenspace
     * @par Description:
     * The visitor call for the greenspace class. Increments greenspace count
     *
     * param[in] greenspace - the greenspace
     ************************************************************************/
    public void visitGreenspace(Greenspace greenspace)
    {
        numGreenspace++;
    }

    /*********************************************************************//**
     * @name visitRoad
     * @par Description:
     * The visitor call for the road class. Increments road count
     *
     * param[in] road - the road
     ************************************************************************/
    public void visitRoad(Road road)
    {
        numRoad++;
    }
}
