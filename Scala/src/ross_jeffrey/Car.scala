package ross_jeffrey
/** ***********************************************************************//**
  * @file Car.scala
  *
  * @author Jeff Ross
  *
  * @details
  * Handles the Car class which inherits from info.
  *
  * ****************************************************************************/
import java.text.DecimalFormat

import scala.collection.mutable
import scala.xml.Node

class Car(private[this] val make : String, private[this] val year : Int,
          private[this] val model : String,private[this] val value : Double) extends Info{
  /** *******************************************************************//**
    * @name print
    * @par Description:
    * appends the contents of the car onto the string passed in and
    * returns it
    *
    * param[in] level - signifies how many dashes to include
    * param[in] str - the string to add to
    * @returns the string after being appended to
    * ***********************************************************************/
  override def print(level : Int, str : String) : String = {
    var temp = str + "\n" + "---"*level + "Make: " + make.padTo(15," ").mkString("")
    temp += "Model: " + model.padTo(15," ").mkString("")
    temp += "Year: " + year.toString.padTo(15," ").mkString("")
    val df : DecimalFormat  = new DecimalFormat("#")
    temp += "Value: $" + df.format(value).mkString("").replace(".0","")
    return temp
  }
  /** *******************************************************************//**
    * @name write
    * @par Description:
    * Takes the contents of car, and puts it into a node and returns it
    *
    * @returns the accident node
    * ***********************************************************************/
  override def write(): Node = {
    val df : DecimalFormat  = new DecimalFormat("#")
    val valueVal = df.format(value).mkString("").replace(".0","").padTo(15," ").mkString("").replace(" ","")
    val attr: mutable.HashMap[String, String] = mutable.HashMap(("model", model),("value", valueVal),
      ("year",year.toString),("make",make.toString))
    return XMLHelper.makeNode("car", attr)
  }
}
