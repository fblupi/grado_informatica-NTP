class Racional(n: Int, d: Int) {
  require(d != 0)
  val numerador: Int = n
  val denominador: Int = d
  override def toString = n + "/" + d
  def sumar(otro: Racional): Racional =
    new Racional(numerador * otro.denominador + otro.numerador * denominador,
      denominador * otro.denominador)
}

//val objFeo = new Racional(5, 0)
val obj1 = new Racional(2, 3)
val obj2 = new Racional(4, 2)
val obj3 = obj1.sumar(obj2)
