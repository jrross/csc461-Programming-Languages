package ross_jeffrey
/** ***********************************************************************//**
  * @file InsuranceProgram.scala
  *
  * @section M001
  *
  * @author Jeff Ross
  *
  * @date 12/5/2018
  *
  * @par Course:
  * CSC 461
  *
  * @par Language Version: Scala 2.12.7
  *
  * @details
  * This program is used to load, and modify XML files pertaining to car insurance
  * items, including Zips, owners, cars, and accidents. Other features were
  * planned to be implemented, but were not implemented due to time issues.
  *
  * @bugs
  * Only the first 5 tiers are functional, anything after that will just output
  * a number when selected.
  *
  * @RDP-chain-of-responsibility
  * the main locations for the recursive descent parsers are shown below:
  *
  * tier2:
  * insurance: line 77 (loadXML)
  * zip: line 82 (read)
  * owner: line 92 (read)
  * insurance: 129 (writeXML)
  * zip: 101 (write)
  * owner: 114 (write)
  *
  * tier3:
  * insurance: line 57 (getString)
  * zip: line 62 (print)
  * owner: line 66 (print)
  *
  * tier4:
  * insurance: line 33 (add)
  * zip: line 37 (add)
  * owner: line 26 (add)
  *
  * ****************************************************************************/
object InsuranceProgram{
  /** *******************************************************************//**
    * @name main
    * @par Description:
    * the entry point of the program, handles an infinite loop that the user can
    * call menu items from.
    *
    * param[in] args - commandline arguments
    * @returns nothing
    * ***********************************************************************/
  def main(args: Array[String]) = {
    var choice = 1
    var insurance = new Insurance
    while(choice != 0){
      print("\n0) Quit\n1) Load XML\n2) Write XML\n3) Display data\n4) Add data\n5) Remove zip code\n6) Find service\n7) Total value insured\n8) Insurance for\nChoice: ")
      try
        choice = scala.io.StdIn.readInt()

      catch {
        case e: NumberFormatException => println("Invalid input")
      }
      choice match {
        case 0 => ;
        case 1 => print("File name: ")
          val file = scala.io.StdIn.readLine()
          insurance.loadXML(file)
        case 2 => print("File name: ")
          val file = scala.io.StdIn.readLine()
          insurance.writeXML(file)
        case 3 => println(insurance.getString())
        case 4 => insurance.add()
        case 5 => print("What zip code: ")
          val zip = scala.io.StdIn.readLine()
          insurance.removeZip(zip)
        case 6 => println(6)
        case 7 => println(7)
        case 8 => println(8)
       // catch the default with a variable so you can print it
        case default => println("Invalid input")
      }
    }
  }
}
