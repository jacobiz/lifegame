object LifeGame extends App {
  
  println("開始")

  var map = CellMap(10, 10)

  while (map.generation < 10) {
    Thread.sleep(1000)
    println(map.toString)
    map = map.nextMap
  }

}
