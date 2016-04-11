class Racional(n: Int, d: Int) {

  require(d != 0)

  val numerador: Int = n
  val denominador: Int = d

  override def toString =
    n + "/" + d

  override def equals(otro : Any) =
    otro.isInstanceOf[Racional] &&
      otro.asInstanceOf[Racional].numerador == numerador &&
      otro.asInstanceOf[Racional].denominador == denominador

  def sumar(otro: Racional): Racional =
    new Racional(numerador * otro.denominador + otro.numerador * denominador,
      denominador * otro.denominador)

  def menorQue(otro: Racional) =
    numerador * otro.denominador < otro.numerador * denominador

  def max(otro: Racional): Racional =
    if (menorQue(otro)) otro
    else this
}

//val objFeo = new Racional(5, 0)
val obj1 = new Racional(2, 3)
val obj2 = new Racional(4, 2)
val obj3 = obj1.sumar(obj2)
val obj4 = new Racional(2, 3)
obj1.equals(obj4)
obj1.equals(obj2)
obj1.menorQue(obj2)
obj2.menorQue(obj1)
val obj5 = obj1.max(obj2)
val obj6 = obj2.max(obj1)