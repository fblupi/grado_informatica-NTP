def imprimirTablasFor = {
  for (i <- 1 to 10) {
    for (j <- 1 to 10) {
      val prod = (i * j).toString
      val blank = prod.length
      for (k <- blank until 4) {
        print(" ")
      }
      print(prod)
    }
    print("\n")
  }
}

def imprimirTablasWhile = {
  var i = 1
  while (i <= 10) {
    var j = 1
    while (j <= 10) {
      val prod = (i * j).toString
      var blank = prod.length
      while (blank < 4) {
        print(" ")
        blank = blank + 1
      }
      print(prod)
      j = j + 1
    }
    print("\n")
    i = i + 1
  }
}

imprimirTablasWhile
