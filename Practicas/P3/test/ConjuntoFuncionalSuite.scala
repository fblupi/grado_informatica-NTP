
import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner])
class ConjuntoFuncionalSuite extends FunSuite {

  // Se importan las declaraciones  en ConjuntoFuncional
  import ConjuntoFuncional._

  // Se crea un trait incluyendo tres conjuntos, que se
  // usan en cada test
  trait TestSets {
    val s0 = conjuntoUnElemento(0)
    val sMenos5 = conjuntoUnElemento(-5)
    val s14 = conjuntoUnElemento(14)
  }

  /**
    * Test para conjuntoUnElemento y contiene
    */
  test("test de conjunto de un elemento y contiene") {
    new TestSets {
      assert(contiene(s0, 0), "fallo: s0 no contiene a 0")
      assert(contiene(sMenos5, -5), "fallo: sMenos5 no contiene a -5")
      assert(contiene(s14, 14), "fallo: s14 no contiene a 14")
      assert(!contiene(s14, -5), "fallo: s14 contiene a -5")
    }
  }

  /**
    * Test para la union
    */
  test("test de union") {
    new TestSets {
      // Conjunto union: enteros mayores de 3
      val s = union(s0, s14)

      // 0 y 14 pertenecen a al union
      assert(contiene(s, 0), "fallo: s no contiene a 0")
      assert(contiene(s, 14), "fallo: s no contiene a 14")

      // -5 no pertenece a la union
      assert(!contiene(s, -5), "fallo: s contiene a -5")

      // Dos conjuntos, uno de mayores que 0 y otro de menores que 0
      val conjunto1 = (x: Int) => x > 0
      val conjunto2 = (x: Int) => x < 0

      // Conjunto union: enteros mayores de 3
      val conjuntoUnion = union(conjunto1, conjunto2)

      // 4, 5, 6 y 7  pertenecen a la union
      assert(contiene(conjuntoUnion, 1), "fallo: conjuntoUnion no contiene a 1")
      assert(contiene(conjuntoUnion, 36), "fallo: conjuntoUnion no contiene a 36")
      assert(contiene(conjuntoUnion, -4), "fallo: conjuntoUnion no contiene a -4")
      assert(contiene(conjuntoUnion, 99), "fallo: conjuntoUnion no contiene a 99")

      // 0 no pertenece a la union
      assert(!contiene(conjuntoUnion, 0), "fallo: conjuntoUnion contiene a 0")
    }
  }

  /**
    * Test para interseccion
    */
  test("test de interseccion") {
    val conjunto1 = (x: Int) => x > 7
    val conjunto2 = (x: Int) => x >= 14

    // Formacion de la interseccion: solo a partir de 5
    val conjuntoInterseccion = interseccion(conjunto1, conjunto2)

    // 14 y 99 pertenecen
    assert(contiene(conjuntoInterseccion, 14), "fallo: conjuntoInterseccion no contiene a 14")
    assert(contiene(conjuntoInterseccion, 99), "fallo: conjuntoInterseccion no contiene a 99")

    // ni 7 ni 13 pertenecen
    assert(!contiene(conjuntoInterseccion, 7), "fallo: conjuntoInterseccion contiene a 7")
    assert(!contiene(conjuntoInterseccion, 13), "fallo: conjuntoInterseccion contiene a 13")
  }

  /**
    * Test de diferencia
    */
  test("test de diferencia") {
    val conjunto1 = (x: Int) => x >= 14
    val conjunto2 = (x: Int) => x > 15

    // Diferencia: mayores o iguales a 14 pero no mayores de 15
    val conjuntoDiferencia = diferencia(conjunto1, conjunto2)

    // 14 y 15 pertenecen
    assert(contiene(conjuntoDiferencia, 14), "fallo: conjuntoDiferencia no contiene a 14")
    assert(contiene(conjuntoDiferencia, 15), "fallo: conjuntoDiferencia no contiene a 15")

    // ni 13 ni 16 pertenecen
    assert(!contiene(conjuntoDiferencia, 13), "fallo: conjuntoDiferencia contiene a 13")
    assert(!contiene(conjuntoDiferencia, 16), "fallo: conjuntoDiferencia contiene a 16")
  }

  /**
    * Test de filtrado
    */
  test("Test de filtrado") {
    val conjunto1 = (x: Int) => x > 3
    val conjunto2 = (x: Int) => x < 10

    // Deja en conjunto1 los elementos de conjunto2
    val conjuntoFiltrado = filter(conjunto1, conjunto2)

    // 6 debe pertenecer y 11 no
    assert(contiene(conjuntoFiltrado, 6))
    assert(!contiene(conjuntoFiltrado, 11))
  }

  /**
    * Test de forall
    */
  test("Test de paraTodo") {
    val conjunto = (x: Int) => x < 10

    // No todos los elementos del conjunto son > 0
    assert(!paraTodo(conjunto, x => x > 0))

    // Si que todos son menores de 15
    assert(paraTodo(conjunto, x => x < 15))
  }

  /**
    * Test para existe
    */
  test("Test para existe") {
    val conjunto = (x: Int) => x < 10

    // No existe en el conjunto ningun elemento mayor de 10
    assert(!existe(conjunto, x => x > 10))

    // Si existe en el conjunto algun elemento menor de 15
    assert(existe(conjunto, x => x < 15))
  }

  /**
    * Test de map
    */
  test("Test de map") {
    // Definicion del conjunto
    val conjunto = (x: Int) => x < 10

    // Mapeo: sumar 25 a todos los elementos del conjunto
    val resultado = map(conjunto, (x => x + 25))

    // 30 y 31 pertenecen al conjunto resultado, ya que
    // 5 y 6 pertenecen al conjunto de partida
    assert(contiene(resultado, 30))
    assert(contiene(resultado, 31))

    // 125 no pertenece, porque 100 no esa en el conjunto
    // de partida
    assert(!contiene(resultado, 125))
  }

}
