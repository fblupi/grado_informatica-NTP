def imprimirTablasFor =
  for (i <- 1 to 10) {
    for (j <- 1 to 10) {
      val prod = (i * j).toString
      for (k <- prod.length until 4) {
        print(" ")
      }
      print(prod)
    }
    println
  }

def imprimirTablasWhile = {
  var i = 1
  while (i <= 10) {
    var j = 1
    while (j <= 10) {
      val prod = (i * j).toString
      var k = prod.length
      while (k < 4) {
        print(" ")
        k += 1
      }
      print(prod)
      j += 1
    }
    println
    i += 1
  }
}

imprimirTablasWhile
