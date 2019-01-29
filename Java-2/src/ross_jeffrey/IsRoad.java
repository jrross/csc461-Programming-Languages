package ross_jeffrey;
/*************************************************************************//**
 * @file IsRoad.java
 *
 * @author Jeff Ross
 *
 * @details
 * Handles the IsRoad visitor class.
 *
 *****************************************************************************/
public class IsRoad extends Visitor
{
    private boolean result;
    /*********************************************************************//**
 * @name getResult
 * @par Description:
 * Gets the result of the visit (whether or not tile is a road)
 *
 * @returns boolean result
 ************************************************************************/
    public boolean getResult()
    {
        return result;
    }
    /*********************************************************************//**
 * @name visitBuilding
 * @par Description:
 * The visitor call for the building class. Marks tile as false.
 *
 * param[in] building - the building
 ************************************************************************/
    public void visitBuilding(Building building)
    {
        result = false;
        return;
    }
    /*********************************************************************//**
 * @name visitEmpty
 * @par Description:
 * The visitor call for the empty class. Marks tile as false.
 *
 * param[in] empty - the empty
 ************************************************************************/
    public void visitEmpty(Empty empty)
    {
        result = false;
        return;
    }
    /*********************************************************************//**
 * @name visitGreenspace
 * @par Description:
 * The visitor call for the greenspace class. Marks tile as false
 *
 * param[in] greenspace - the greenspace
 ************************************************************************/
    public void visitGreenspace(Greenspace greenspace)
    {
        result = false;
        return;
    }
    /*********************************************************************//**
 * @name visitRoad
 * @par Description:
 * The visitor call for the road class. Marks tile as true
 *
 * param[in] road - the road
 ************************************************************************/
    public void visitRoad(Road road)
    {
        result = true;
        return;
    }
}
