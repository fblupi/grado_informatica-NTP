
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
    * Test de conjunto de un elemento y contiene
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
    * Test de union
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
    * Test de interseccion
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
  test("test de filtrado") {
    val conjunto = (x: Int) => x >= -2

    // Deja en conjunto los elementos pares
    val conjuntoFiltrado = filter(conjunto, (x: Int) => x % 2 == 0)

    // -2, 0, 14 y 56 pertenecen
    assert(contiene(conjuntoFiltrado, -2), "fallo: conjuntoFiltrado no contiene a -2")
    assert(contiene(conjuntoFiltrado, 0), "fallo: conjuntoFiltrado no contiene a 0")
    assert(contiene(conjuntoFiltrado, 14), "fallo: conjuntoFiltrado no contiene a 14")
    assert(contiene(conjuntoFiltrado, 56), "fallo: conjuntoFiltrado no contiene a 56")

    // -4, 7 y 99 no pertenecen
    assert(!contiene(conjuntoFiltrado, -4), "fallo: conjuntoFiltrado contiene a -4")
    assert(!contiene(conjuntoFiltrado, 7), "fallo: conjuntoFiltrado contiene a 7")
    assert(!contiene(conjuntoFiltrado, 99), "fallo: conjuntoFiltrado contiene a 99")
  }

  /**
    * Test de para todo
    */
  test("test de para todo") {
    // Conjunto de mayores que 14
    val conjunto = (x: Int) => x > 14

    // Todos son mayores que 7
    assert(paraTodo(conjunto, x => x > 7), "fallo: no todos los elementos del conjunto son mayores que 7")

    // No todos los elementos del conjunto son pares
    assert(!paraTodo(conjunto, x => x % 2 == 0), "fallo: todos los elementos del conjunto son pares")
  }

  /**
    * Test para existe
    */
  test("test para existe") {
    // Conjunto de múltiplos de 7
    val conjunto = (x: Int) => x % 7 == 0

    // Existen pares y negativos en el conjunto
    assert(existe(conjunto, x => x % 2 == 0), "fallo: no existen pares en el conjunto")
    assert(existe(conjunto, x => x < 0), "fallo: no existen negativos en el conjunto")

    // No existe en el conjunto el 8
    assert(!existe(conjunto, x => x == 8), "fallo: 8 pertenece al conjunto")
  }

  /**
    * Test de map
    */
  test("test de map") {
    // Definicion del conjunto
    val conjunto = (x: Int) => x % 2 == 0

    // Mapeo: sumar 1 a todos los elementos del conjunto
    val resultado = map(conjunto, (x => x + 1))

    // El conjunto pasa de ser par a ser impar por lo que 1, 7 y 53 pertenecen
    assert(contiene(resultado, 1), "fallo: 1 no pertenece al conjunto")
    assert(contiene(resultado, 7), "fallo: 7 no pertenece al conjunto")
    assert(contiene(resultado, 53), "fallo: 53 no pertenece al conjunto")

    // En cambio 0, 2 y 14 no pertenecen
    assert(!contiene(resultado, 0), "fallo: 0 pertenece al conjunto")
    assert(!contiene(resultado, 2), "fallo: 2 pertenece al conjunto")
    assert(!contiene(resultado, 14), "fallo: 14 pertenece al conjunto")

    // En definitiva, no hay numeros pares
    assert(!existe(resultado, (x => x % 2 == 0)), "fallo: existe un número par")
  }

}
