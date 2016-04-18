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

imprimirTablasFor
