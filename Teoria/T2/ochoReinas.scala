def enJaque(reina1 : (Int, Int), reina2 : (Int, Int)) : Boolean =
  reina1._1 == reina2._1 ||
  reina1._2 == reina2._2 ||
  (reina1._1 - reina1._2).abs == (reina2._1 - reina2._2).abs

def posicionCorrecta(reina : (Int, Int), solucionParcial : List[(Int, Int)]) : Boolean =
  solucionParcial forall(reinaPuesta => !enJaque(reina, reinaPuesta))
