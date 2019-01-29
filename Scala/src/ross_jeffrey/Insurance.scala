package ross_jeffrey
/** ***********************************************************************//**
  * @file Accident.scala
  *
  * @author Jeff Ross
  *
  * @details
  * Handles the Insurance class which acts as the entry point to all the data.
  * The insurance class is the starting point for a all RDP chain of responsiblity
  *
  * ****************************************************************************/
import java.io.FileNotFoundException
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.xml._


class Insurance {
  private var zips = ArrayBuffer[Zip]()

  def removeZip() = {}
  def findShop() = {}

  /** *******************************************************************//**
    * @name print
    * @par Description:
    * Starts the RDP chain for adding new items to the XML. Beings by reading
    * in for a new zip, if it exists, send it off to that zip. If it does not
    * exist, add it to the zip collection.
    *
    * @returns nothing
    * ***********************************************************************/
  def add()= {
    printf("What zip code: ")
    val choice = scala.io.StdIn.readInt()
    var found : Boolean = false
    for(zip <- zips){
      if(zip.getCode() == choice){
        zip.add()
        found = true
      }
    }
    if(!found){
      zips += new Zip(choice)
      printf("Added zip code")
    }
  }
  /** *******************************************************************//**
    * @name getString
    * @par Description:
    * Starts the RDP chain for getting a printable string from all the classes.
    * starts by going through all the zips and calling their print functions
    * and pending their results to a string, before returning it.
    *
    * @returns the string after being appended to
    * ***********************************************************************/
  def getString() : String = {
    var temp : String = ""
    if(zips.nonEmpty) {
      for (zip <- zips) {
        temp += zip.print(0, "")
      }
    }
    return temp
  }
  /** *******************************************************************//**
    * @name loadXML
    * @par Description:
    * loads in the XML by taking a path and reading the item into a new Node.
    * Does checking on the file in question to make sure it is valid, before
    * adding any zips in it to the structure, before passing it on to the zips
    * themselves. This starts the RDP chain.
    *
    * param[in] path - path to the xml file to read in from
    * @returns nothing
    * ***********************************************************************/
  def loadXML(path : String) = {
    var opened: Boolean = true
    var xml: Node = <Blank></Blank>
    try {
      xml = XML.loadFile(path)
      opened = true
    }
    catch {
      case e: FileNotFoundException => println(("Could not open file: " + e).replace("java.io.FileNotFoundException: ", ""))
        opened = false
    }
    if (opened) {
      if (xml.label != "insurance") {
        println("Invalid xml file. Needs to be an insurance xml file")
      }
      else {
        for (zip <- xml.child) {
          if (zip.label == "zip") {
            val temp = new Zip((zip \ "@code").toString().toInt)
            zips += temp
            temp.read(zip)
          }
        }
      }
    }
  }

  /** *******************************************************************//**
    * @name removeZip
    * @par Description:
    * Removes a zip from the structure
    *
    * param[in] zip - the value of the zip in a string
    * @returns nothing
    * ***********************************************************************/
  def removeZip(zipR : String) = {
    for(zip <- zips) {
      if(zip.getCode() == zipR.toInt) {
        zips -= zip
        println("Removed " + zipR.toString)
      }
    }
  }
  /** *******************************************************************//**
    * @name writeXML
    * @par Description:
    * Begins the RDP chain for writing xml to a file. Calls for all the nodes
    * in all the zips in the structure and then writes those nodes to a file.
    *
    * param[in] path - the path to the file to write to
    * @returns nothing
    * ***********************************************************************/
  def writeXML(path : String) = {
    var zipHold : Seq[Node] = Seq()
    for(zip <- zips) {
      zipHold = zipHold ++ zip.write()
    }
    val finXML = XMLHelper.makeNode("insurance", null,zipHold)
    XML.save(path, finXML, "UTF-8", true, null)
  }
}