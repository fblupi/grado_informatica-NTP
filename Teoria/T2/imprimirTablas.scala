def imprimirTablasFor =
  for (i <- 1 to 10) {
    for (j <- 1 to 10) {
      val prod = (i * j).toString
      for (k <- prod.length until 4)
        print(" ")
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

def generarTablaNumero(fila : Int) =
  for (columna <- 1 to 10) yield {
    val prod = (fila * columna).toString
    val blank = " " * (4 - prod.length)
    blank + prod
  }

def imprimirTablas = {
  val tablas = for (fila <- 1 to 10) yield
    generarTablaNumero(fila).mkString
  tablas.mkString("\n")
}

/******************************************************************************/

println("Usando While (No funcional)")
imprimirTablasWhile
println
println("Usando For (No funcional)")
imprimirTablasFor
println
println("Usando For (Funcional)")
println(imprimirTablas)
