package ross_jeffrey
/** ***********************************************************************//**
  * @file Accident.scala
  *
  * @author Jeff Ross
  *
  * @details
  * Handles the Zip class which inherits from info.
  *
  * ****************************************************************************/
import scala.collection.mutable.ArrayBuffer
import scala.xml._
import scala.collection.mutable
/** *******************************************************************//**
  * @name Zip
  * @par Description:
  * Constructor
  *
  * param[in] code - the code to give the zip
  * @returns nothing
  * ***********************************************************************/
class Zip(private[this] val code : Int) extends Info{
  private var owners = ArrayBuffer[Owner]()

  def getCode() : Int = {
    return code
  }
  /** *******************************************************************//**
    * @name add
    * @par Description:
    * provides a prompt to find what owner the user would like to add. If the
    * owner already exists, pass off to the owner, otherwise, add the owner
    * to the zip
    *
    * @returns nothing
    * ***********************************************************************/
  override def add()= {
    printf("What owner: ")
    val choice = scala.io.StdIn.readLine()
    var found : Boolean = false
    for(owner <- owners){
      if(owner.getName().toLowerCase() == choice.toLowerCase()){
        owner.add()
        found = true
      }
    }
    if(!found){
      owners += new Owner(choice)
      printf("Added owner")
    }
  }
  /** *******************************************************************//**
    * @name print
    * @par Description:
    * appends the contents of the zip onto the string passed in and
    * returns it
    *
    * param[in] level - signifies how many dashes to include
    * param[in] str - the string to add to
    * @returns the string after being appended to
    * ***********************************************************************/
  override def print(level : Int, str : String) : String = {
    var temp = ""
    temp = str + "\n" + "---" * level + "Zip Code: " + code
    if (owners.nonEmpty) {
      for (owner <- owners) {
        temp = owner.print(level + 1, temp)
      }
    }
    return temp
  }
  /** *******************************************************************//**
    * @name read
    * @par Description:
    * takes a node and finds all the owners in in the children of the node
    * which are an owner and adds them to the zip, and sends control to the
    * owners.
    *
    * param[in] node - the node to be read over
    * @returns nothing
    * ***********************************************************************/
  override def read(xml : Node) = {
    for(owner <- xml.child){
      if(owner.label == "owner") {
        val temp = new Owner((owner \ "@name").toString())
        temp.read(owner)
        owners += temp

      }
    }
  }
  /** *******************************************************************//**
    * @name write
    * @par Description:
    * First sends off control to all the owners in the zip, and then combines the
    * nodes they return with the information for the zip to create a node, and
    * returns this node
    *
    * @returns none
    * ***********************************************************************/
  override def write(): Node = {
    val attr: mutable.HashMap[String, String] = mutable.HashMap(("code", code.toString))
    var ownerHold : Seq[Node] = Seq()
    for(owner <- owners) {
      ownerHold = ownerHold ++ owner.write()
    }
    return XMLHelper.makeNode("zip", attr, ownerHold)
  }
}
