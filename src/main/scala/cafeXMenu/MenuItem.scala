package cafeXMenu

/** MenuItem class
 * @param i id of item
 * @param n name of item
 * @param t type of item
 * @param p price of item
 *
 */
class MenuItem(i: Int, n: String, t: String, p: Double) {
  val id = i
  val name = n
  val ttype = t
  val price = p
  var quantity = 0
}