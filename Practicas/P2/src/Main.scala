
/**
  * Objeto singleton para probar la funcionalidad del triangulo
  * de Pascal
  */
object Main {

  /**
    * Metodo main: en realidad no es necesario porque el desarrollo
    * deberia guiarse por los tests de prueba
    *
    * @param args
    */
  def main(args: Array[String]) {
    println("................... Triangulo de Pascal ...................")
    // Se muestran 10 filas del trinagulo de Pascal
    for (row <- 0 to 10) {
      // Se muestran 10 10 columnas
      for (col <- 0 to row)
        print(calcularValorTrianguloPascal(col, row) + " ")
      // Salto de linea final para mejorar la presentacion
      println()
    }
    // Se muestra el valor que debe ocupar la columna 5 en la fila 10
    println(calcularValorTrianguloPascal(10, 15))
    println(calcularValorTrianguloPascal(0, 0))
  }

  /**
    * Ejercicio 1: funcion para generar el triangulo de Pascal
    *
    * @param columna
    * @param fila
    * @return
    */
  def calcularValorTrianguloPascal(columna: Int, fila: Int): Int = {
    if (columna == 0 || columna == fila) 1
    else calcularValorTrianguloPascal(columna - 1, fila - 1) + calcularValorTrianguloPascal(columna, fila - 1)
  }

  /**
    * Ejercicio 2: funcion para chequear el balance de parentesis
    *
    * @param cadena cadena a analizar
    * @return valor booleano con el resultado de la operacion
    */
  def chequearBalance(cadena: List[Char]): Boolean = {
    def contarParentesis(cadena: List[Char], contador: Int): Int = {
      if (cadena.isEmpty) contador
      else {
        val acumulador = cadena.head match {
          case ')' => contador - 1
          case '(' => contador + 1
          case _   => contador
        }
        if (acumulador < 0) acumulador
        else contarParentesis(cadena.tail, acumulador)
      }
    }
    contarParentesis(cadena, 0) == 0
  }

  /**
    * Ejercicio 3: funcion para determinar las posibles formas de devolver el
    * cambio de una determinada cantidad con un conjunto de monedas
    *
    * @param cantidad
    * @param monedas
    * @return contador de numero de vueltas posibles
    */
  def contarCambiosPosibles(cantidad: Int, monedas: List[Int]): Int = {
    if (cantidad == 0) 1
    else if (cantidad < 0 || monedas.isEmpty) 0
    else contarCambiosPosibles(cantidad - monedas.head, monedas) + contarCambiosPosibles(cantidad, monedas.tail)
  }
}
