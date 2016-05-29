/**
  * Clase para generar las espirales en base a crear elementos de
  * la libreria de elementos de texto
  */
object Espiral {
  /**
    * Miembro para representar espacios en blanco
    */
  val espacio = Elemento.crearElemento(" ")

  /**
    * Miembro para representar elementos que representaran las
    * esquinas de la espiral
    */
  val esquina = Elemento.crearElemento("+")

  /**
    * Metodo recursivo de generacion de la espiral
    *
    * @param tamLado   tamaño del lado de la espiral
    * @param direccion direccion de completado (inicialmente sera 0)
    * @return
    */
  def construirEspiral(tamLado: Int, direccion: Int): Elemento = {

    // Caso base: el numero de lados es 1: se devuelve el centro
    // de la espiral, un elemento con un unico caracter: '+'
    if (tamLado == 1)
      esquina
    else {
      // Se generan las espirales de orden inferior. La direccion se va
      // cambiando en la siguiente sucesion: 0, 3, 2, 1, 0, 3, 2, 1, ....
      val espiralPrevia = construirEspiral(tamLado - 1, (direccion + 3) % 4)

      // La espiral de orden inferior recien creada se compone con los
      // elementos necesarios para completarla. Las barras vertical y
      // horizontal tendran las mismas dimensiones que la espiral previa.
      // Asi, la barra vertical se basa en el caracter '|', con un ancho
      // de 1 y tanta altura como la espiral previa. Por su parte, la
      // barra horizontal se base en el caracter '-', con el ancho de la
      // espiral previa y 1 de alto
      def barraVertical = Elemento.crearElemento('|', 1, espiralPrevia.alto)
      def barraHorizontal = Elemento.crearElemento('-', espiralPrevia.ancho, 1)

      // Se complementa la espiral previa segun la direccion
      direccion match {
        case 0 => (esquina colocarJunto barraHorizontal) colocarSobre
          (espiralPrevia colocarJunto espacio)
        case 1 => (espiralPrevia colocarSobre espacio) colocarJunto
          (esquina colocarSobre barraVertical)
        case 2 => (espacio colocarJunto espiralPrevia) colocarSobre
          (barraHorizontal colocarJunto esquina)
        case 3 => (barraVertical colocarSobre esquina) colocarJunto
          (espacio colocarSobre espiralPrevia)
      }
    }
  }

  /**
    * Metodo main para probar el funcionamaiento
    *
    * @param args el argumento es el tamaño de lado deseado
    */
  def main(args: Array[String]): Unit = {
    val tamLado = args(0).toInt
    println(construirEspiral(tamLado, 0))
  }
}
