import scala.util.Random

class CellMap(val width: Int, val height: Int, val generation: Int, val cells: List[Cell]) {

  def nextMap: CellMap = {
    def roundCells(centerCell: Cell): List[Cell] = {
      cells.filter { cell =>
        val squaredDist = (cell.x - centerCell.x) * (cell.x - centerCell.x) + (cell.y - centerCell.y) * (cell.y - centerCell.y)
        (0 < squaredDist) && (squaredDist <= 2) //中央からの距離の自乗が2以下 <=> 周囲8Cell
      }
    }

    val nextCells = cells.map { cell =>
      val nextIsAlive = roundCells(cell).filter(_.isAlive).length match {
        case 3 => true
        case 2 => cell.isAlive
        case _ => false
      }
      Cell(cell.x, cell.y, nextIsAlive)
    }
    new CellMap(width, height, generation + 1, nextCells)
  }

  override def toString() = {
    val buf = new StringBuilder
    buf.append("第" + generation + "世代" + "\n")
    cells.foreach { cell =>
      val cellToString = if (cell.isAlive) "■" else "□"
      buf.append(if (cell.x == width) cellToString + "\n" else cellToString)
    }
    buf.toString
  }

}

object CellMap {

  def apply(width: Int, height: Int) = {
    val cells: List[Cell] = (
      for (y <- 1 to height; x <- 1 to width; rand = new Random)
      yield Cell(x, y, rand.nextBoolean)
    ).toList
    new CellMap(width, height, generation = 1, cells)
  }

}