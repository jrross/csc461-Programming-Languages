package ross_jeffrey;
/*************************************************************************//**
 * @file Color.java
 *
 * @author Jeff Ross
 *
 * @details
 * Handles the Color visitor class.
 *
 *****************************************************************************/
public class Color extends Visitor
{
    private ColorText.Color color;
    private City.TileType type;
    /*********************************************************************//**
    * @name Color
    * @par Description:
    * Constructor. Sets the TileType and Color to be used in the operation
    *
    * param[in] t - tile type
    * param[in] c - color
    ************************************************************************/
    public Color( City.TileType t, ColorText.Color c)
    {
        type = t;
        color = c;
    }
    /*********************************************************************//**
 * @name visitBuilding
 * @par Description:
 * The visitor call for the building class. If the specified tile type is
 * Building, it will change the color of the building being sent in.
 *
 * param[in] building - the building to color
 ************************************************************************/
    public void visitBuilding(Building building)
    {
        if(type == City.TileType.BUILDING)
            building.setColor(color);
    }

    /*********************************************************************//**
 * @name visitEmpty
 * @par Description:
 * The visitor call for the empty class. If the specified tile type is
 * Empty, it will change the color of the empty tile being sent in.
 *
 * param[in] empty - the empty space to color
 ************************************************************************/
    public void visitEmpty(Empty empty)
    {
        if(type == City.TileType.EMPTY)
            empty.setColor(color);
    }
    /*********************************************************************//**
 * @name visitGreenspace
 * @par Description:
 * The visitor call for the greenspace class. If the specified tile type is
 * Greenspace, it will change the color of the greenspace being sent in.
 *
 * param[in] greenspace - the greenspace to color
 ************************************************************************/
    public void visitGreenspace(Greenspace greenspace)
    {
        if(type == City.TileType.GREENSPACE)
            greenspace.setColor(color);
    }
    /*********************************************************************//**
 * @name visitRoad
 * @par Description:
 * The visitor call for the road class. If the specified tile type is
 * road, it will change the color of the road being sent in.
 *
 * param[in] road - the road to color
 ************************************************************************/
    public void visitRoad(Road road)
    {
        if(type == City.TileType.ROAD)
            road.setColor(color);
    }
}
