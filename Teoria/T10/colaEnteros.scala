import scala.collection.mutable.ArrayBuffer

abstract class ColaEnteros {
  def get(): Int
  def put(x: Int)
}

class ColaBasicaEnteros extends ColaEnteros {
  private val buffer = new ArrayBuffer[Int]
  def get() = buffer.remove(0)
  def put(x: Int): Unit = buffer += x
}

trait Doblar extends ColaEnteros {
  abstract override def put(x: Int): Unit = super.put(2 * x)
}

trait Incrementar extends ColaEnteros {
  abstract override def put(x: Int): Unit = super.put(x + 1)
}

trait FiltrarNegativos extends ColaEnteros {
  abstract override def put(x: Int): Unit = if (x >= 0) super.put(x)
}

object PruebaCambiosApilables extends App {
  class ColaDoblando extends ColaBasicaEnteros with Doblar
  val cola = new ColaDoblando
  cola.put(10)
  println("Cola doblando con 10, obtenido: " + cola.get())

  // También puede crearse un objetod de forma directa, sin declarar antes la clase
  val cola1 = new ColaBasicaEnteros with Incrementar with FiltrarNegativos
  cola1.put(-1)
  cola1.put(0)
  cola1.put(1)
  println("Cola con incremento y filtrado y -1, 0, y 1 extraemos " + cola1.get())

  // Incrementar        Filtrar negativos
  // super(x+1)         (x>=0) super(x)
  //
  // put(-1) ----- X
  // put(0)  ----- super(0) ----- super(0+1) ----- put(1)
  // put(1)  ----- super(1) ----- super(1+1) ----- put(2)

  // El orden es importante, porque el resultado variaría:
  //
  // Filtrar negativos  Incrementar
  // (x>=0) super(x)    super(x+1)
  //
  // put(-1) ----- super(-1+1) ----- super(0) ----- put(0)
  // put(0)  ----- super(0+1)  ----- super(1) ----- put(1)
  // put(1)  ----- super(1+1)  ----- super(2) ----- put(2)

}
