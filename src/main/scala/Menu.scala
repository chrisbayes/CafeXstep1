/**
 * @author Chris
 *
 */
package cafeXMenu

import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import java.io.File

/**
 * Class for handling Cafe X Menu
 *
 *
 */
class Menu {

  /**
   * Collection of MenuItems
   */
  private val items: ArrayBuffer[MenuItem] = ArrayBuffer()

  /**
   * Add MenuItem to collection
   * @param item
   * @return void
   */
  def add(item: MenuItem) = items += item

  /**
   * Get collection size
   * @return collection size
   */
  def size: Int = items.size

  /**
   * Find menu items by name
   * @param name
   * @return matching menu items
   */
  def findByName(name: String): Seq[MenuItem] =
    items.filter(_.name == name)

  /**
   * Place an order
   * @param names of items in order i.e. ["Cola", "Coffee", "Cheese Sandwich"]
   * @return count of items added
   */
  def orderS(names: List[String]) =
    items.filter(i => names.contains(i.name)).map({ i => i.quantity += 1
      i }).size
      
  /**
   * Place an order
   * @param names of items in order i.e. ["Cola", "Coffee", "Cheese Sandwich"]
   * @return price of items added
   */
  def orderP(names: List[String]) =
    items.filter(i => names.contains(i.name)).map({ i => i.quantity += 1
      i }).map(i => i.price * i.quantity).sum

  /**
   * Calculate the total price of ordered items
   * @return price of ordered items
   */
  def bill() =
    items.map(i => i.price * i.quantity).sum

  /**
   * Print menu
   *
   */
  def printAll() =
    items.map(i => println(s"${i.id}\t${i.name}\t ${i.price}\t ${i.ttype}\t ${i.quantity}"))
  /**
   * Print bill
   *
   */
  def printBill() = {
    items.filter(_.quantity > 0).map(i => println(s"${i.id}\t${i.name}\t ${i.price}\t ${i.ttype}\t ${i.quantity}"))
    println(s"Total\t${this.bill()}")
  }
}

/**
 * Menu instance
 *
 *
 */
object Menu {

  /**
   * Populate menu collection from file
   * @param f text file
   * @return a new menu
   */
  def fromFile(file: File) = {
    val newMenu = new Menu
    val itemRegex = "(.*)?[,](.*)?[,](.*)?[,](.*)?".r

    Source.fromFile(file).getLines() foreach { line =>
      line match {
        case itemRegex(id, name, tp, price) =>
          newMenu.add(new MenuItem(id.toInt, name, tp, price.toDouble))
        case _ =>
          println("not matched: " + line)
      }
    }
    newMenu
  }
}
