package ross_jeffrey

/** ***********************************************************************//**
  * @file Accident.scala
  *
  * @author Jeff Ross
  *
  * @details
  * Handles the Accident class which inherits from info.
  *
  * ****************************************************************************/
import scala.collection.mutable
import scala.xml.{Node, Text}

class Accident(private[this] val date : String) extends Info{
  /** *******************************************************************//**
    * @name print
    * @par Description:
    * appends the contents of the accident onto the string passed in and
    * returns it
    *
    * param[in] level - signifies how many dashes to include
    * param[in] str - the string to add to
    * @returns the string after being appended to
    * ***********************************************************************/
  override def print(level : Int, str : String) : String = {
    val temp = str + "\n" + "---"*level + "Accident date: " + date
    temp
  }

    /** *******************************************************************//**
    * @name write
    * @par Description:
    * Takes the contents of accident, and puts it into a node and returns it
    *
    * @returns the accident node
    * ***********************************************************************/
  override def write(): Node = {
    val node = Text(date.toString)
    val temp = XMLHelper.makeNode("date",null,node)
    XMLHelper.makeNode("accident", null, temp)
  }

}
