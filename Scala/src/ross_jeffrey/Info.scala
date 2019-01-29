package ross_jeffrey
/** ***********************************************************************//**
  * @file Car.scala
  *
  * @author Jeff Ross
  *
  * @details
  * Handles the Info class which other classes inherit from.
  *
  * ****************************************************************************/
import scala.xml.Node

class Info {
  /** *******************************************************************//**
    * @name read
    * @par Description:
    * Used to read in items from xml
    *
    * param[in] xml - the xml node to get information from
    * @returns nothing
    * ***********************************************************************/
  def read(xml : Node) = {}
  /** *******************************************************************//**
    * @name write
    * @par Description:
    * used to create notes to write to xml
    *
    * @returns the node to be used for writing
    * ***********************************************************************/
  def write() : Node = {return XMLHelper.makeNode("temp")}
  /** *******************************************************************//**
    * @name write
    * @par Description:
    * Used to add new items to the classes
    *
    * @returns nothing
    * ***********************************************************************/
  def add() = {}
  /** *******************************************************************//**
    * @name print
    * @par Description:
    * Takes a string being passed in, adds its contents and returns it
    *
    * param[in] level - how many dashes to add in front
    * param[in] str - the string to add to
    * @returns the appended string
    * ***********************************************************************/
  def print(level : Int, str : String) : String = {
    return ""
  }

}
