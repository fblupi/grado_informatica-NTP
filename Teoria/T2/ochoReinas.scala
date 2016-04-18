def enJaque(reina1 : (Int, Int), reina2 : (Int, Int)) : Boolean =
  reina1._1 == reina2._1 ||
  reina1._2 == reina2._2 ||
  (reina1._1 - reina1._2).abs == (reina2._1 - reina2._2).abs

def posicionCorrecta(reina : (Int, Int), solucionParcial : List[(Int, Int)]) : Boolean =
  solucionParcial forall(reinaPuesta => !enJaque(reina, reinaPuesta))

def solucionarProblemaReinas(n : Int) : List[List[(Int, Int)]] = {
  def posicionarReinas(fila : Int) : List[List[(Int, Int)]] =
    if (fila == 0) List(List())
    else for {
      solucion <- posicionarReinas(fila - 1)
      columna <- 1 to n
      reina = (fila, columna)
      if (posicionCorrecta(reina, solucion))
    } yield reina::solucion

  posicionarReinas(n)
}

println(solucionarProblemaReinas(8).mkString)
