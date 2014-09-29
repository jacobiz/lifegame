class Cell(val x: Int, val y: Int, val isAlive: Boolean) {}

object Cell {
  def apply(x: Int, y: Int, isAlive: Boolean) = 
    new Cell(x, y, isAlive)
}