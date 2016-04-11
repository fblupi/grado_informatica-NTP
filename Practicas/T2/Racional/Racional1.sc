class Racional(n: Int, d: Int) {

  require(d != 0)

  private val factor = mcd(n, d)
  val numerador: Int = n / factor
  val denominador: Int = d / factor

  def this(n: Int) = this(n, 1)

  override def toString =
    numerador + "/" + denominador

  override def equals(otro : Any) =
    otro.isInstanceOf[Racional] &&
      otro.asInstanceOf[Racional].numerador == numerador &&
      otro.asInstanceOf[Racional].denominador == denominador

  private def mcd(a: Int, b: Int): Int =
    if (b == 0) a
    else mcd(b, a % b)

  def sumar(otro: Racional) =
    new Racional(numerador * otro.denominador + otro.numerador * denominador,
      denominador * otro.denominador)

  def menorQue(otro: Racional) =
    numerador * otro.denominador < otro.numerador * denominador

  def max(otro: Racional) =
    if (this.menorQue(otro)) otro
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
val obj7 = new Racional(4)