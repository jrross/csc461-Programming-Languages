package ross_jeffrey
/** ***********************************************************************//**
  * @file Owner.scala
  *
  * @author Jeff Ross
  *
  * @details
  * Handles the Owner class which inherits from info.
  *
  * ****************************************************************************/
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.xml.Node

class Owner(name : String) extends Info{
  private var accidents = ArrayBuffer[Accident]()
  private var cars = ArrayBuffer[Car]()
  /** *******************************************************************//**
    * @name add
    * @par Description:
    * provides a prompt to either add a new accident or new car and then adds
    * that item to the class.
    *
    * @returns nothing
    * ***********************************************************************/
  override def add()= {
    printf("Car or accident (0 or 1): ")
    val choice = scala.io.StdIn.readInt()
    if (choice == 0) {
      printf("Make: ")
      val make = scala.io.StdIn.readLine()
      printf("Model: ")
      val model = scala.io.StdIn.readLine()
      printf("Year: ")
      val year = scala.io.StdIn.readInt()
      printf("Value: ")
      val value = scala.io.StdIn.readDouble()
      cars += new Car(make,year,model,value)
    }
    if (choice == 1) {
      printf("Date: ")
      val date = scala.io.StdIn.readLine()
      accidents += new Accident(date)
    }
  }
  /** *******************************************************************//**
    * @name getName
    * @par Description:
    * gets the name of the owner
    *
    * @returns name of the owner
    * ***********************************************************************/
  def getName() : String = {
    return name
  }
  /** *******************************************************************//**
    * @name print
    * @par Description:
    * appends the contents of the owner onto the string passed in and
    * returns it
    *
    * param[in] level - signifies how many dashes to include
    * param[in] str - the string to add to
    * @returns the string after being appended to
    * ***********************************************************************/
  override def print(level : Int, str : String) : String = {
    var temp = str + "\n" + "---" * (level) + "Name: " + name
    if(cars.nonEmpty) {
      temp += "\n" + "---" * (level+1) + "Cars:"
      for (car <- cars) {
        temp = car.print(level + 2, temp)
      }
    }
    if(accidents.nonEmpty){
      temp += "\n" + "---" * (level+1) + "Accidents:"
      for (accident <- accidents){
        temp = accident.print(level + 2, temp)
      }
    }
    return temp
  }
  /** *******************************************************************//**
    * @name read
    * @par Description:
    * Takes a node and reads through all the children in the node to find if
    * there are any cars or accidents present. if there are, they are added
    * to the owner.
    *
    * param[in] xml - the node to look through
    * @returns nothing
    * ***********************************************************************/
  override def read(xml : Node) = {
    for(child <- xml.child){
      if(child.label == "car") {
        val temp = new Car((child \ "@make").toString(),(child \ "@year").toString().toInt,
          (child \ "@model").toString(), (child \ "@value").toString().toDouble)
        cars += temp
      }
      if(child.label == "accident") {
        val temp = new Accident((child \\ "date").text)
        accidents += temp
      }
    }
  }
  /** *******************************************************************//**
    * @name write
    * @par Description:
    * Grabs the information for the Owner, and combines it with information
    * from all the accidents and cars underneath to create a node, which it
    * returns.
    *
    * @returns the node to be written
    * ***********************************************************************/
  override def write(): Node = {
    val attr: mutable.HashMap[String, String] = mutable.HashMap(("name", name))
    var valHold : Seq[Node] = Seq()
    for(car <- cars) {
      valHold = valHold ++ car.write()
    }
    for(accident <- accidents) {
      valHold = valHold ++ accident.write()
    }
    return XMLHelper.makeNode("owner", attr, valHold)
  }
}
