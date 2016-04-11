class Racional(n: Int, d: Int) {
  require(d != 0)
  override def toString = n + "/" + d
}

val obj = new Racional(2, 3)
val objFeo = new Racional(5, 0)