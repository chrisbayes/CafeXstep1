package cafeXMenu

import java.io.File
import org.junit.Test
import org.junit.Assert

/**
 * @author Chris
 *
 */
class MenuTest {
  // Menu file path
  val path = getClass.getResource("/menu.txt").getPath
  
  /**
   * Test that the correct number of items are loaded from file
   *
   */
  @Test def LoadTest {

    val amenu = Menu.fromFile(new File(path))

    Assert.assertTrue(amenu.size == 4)
  }
  /**
   * Test that an order can be placed
   *
   */
  @Test def OrderSTest {

    val amenu = Menu.fromFile(new File(path))
    val ocount = amenu.orderS(List("Cola", "Coffee", "Cheese Sandwich"))

    Assert.assertTrue(ocount == 3)
  }
  /**
   * Test that an order can be placed
   * Pass in a list of purchased items that produces a total bill.
   * e.g. [“Cola”, “Coffee”, “Cheese Sandwich”] returns 3.5
   *
   */
  @Test def OrderPTest {

    val amenu = Menu.fromFile(new File(path))
    val oprice = amenu.orderP(List("Cola", "Coffee", "Cheese Sandwich"))

    Assert.assertTrue(oprice == 3.50)
  }
  /**
   * Test that items not on the menu aren't ordered
   *
   */
  @Test def OrderRubbishTest {

    val amenu = Menu.fromFile(new File(path))
    val ocount = amenu.orderS(List("Cla", "Cffee", "Chese Sandwich", "Stk Sandwich"))

    Assert.assertTrue(ocount == 0)
  }
  /**
   * Test that the correct total is returned
   *
   */
  @Test def BillTest {

    val amenu = Menu.fromFile(new File(path))
    val ocount = amenu.orderS(List("Cola", "Coffee", "Cheese Sandwich"))

    val total = amenu.bill()
    amenu.printBill()
    
    Assert.assertTrue(total == 3.5)
  }
}
