object LifeGame {
  val usage = """
    Usage example: sbt "run -w <width> -h <height> -g <generation>"
  """

  def main(args: Array[String]) {
    val argList = args.toList
    type OptionIntMap = Map[Symbol, Int]

    def nextOption(map: OptionIntMap, list: List[String]): OptionIntMap = {
      list match {
        case Nil => map
        case "-w" :: value :: tail =>
          nextOption(map ++ Map('width -> value.toInt), tail)
        case "-h" :: value :: tail =>
          nextOption(map ++ Map('height -> value.toInt), tail)      
        case "-g" :: value :: tail =>
          nextOption(map ++ Map('generation -> value.toInt), tail)
        case string :: tail => 
          println(usage)
          exit(0)
      }
    }

    val options = nextOption(
      Map('width -> 10, 'height -> 10, 'generation -> 5), 
      argList
    )
    val width = options('width)
    val height = options('height)
    val generation = options('generation)

    println("Start")

    var map = CellMap(width, height)
    println(map.toString)

    while (map.generation < generation) {
      Thread.sleep(1000) //miliseconds
      map = map.nextMap
      println(map.toString)
    }
  }
}
