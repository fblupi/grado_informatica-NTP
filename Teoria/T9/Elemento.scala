/**
  * Clase abstracta para representar elementos de texto con
  * alto y ancho
  */
abstract class Elemento {
  /**
    * Metodo abstracto para representar el contenido textual
    * del elemento
    * @return
    */
  def contenido: Array[String]

  /**
    * Devuelve el alto del contenido textual del elemento
    * @return
    */
  def alto: Int = contenido.length

  /**
    * Devuelve el ancho del contenido textual del elemento
    * @return
    */
  def ancho: Int = if (alto == 0) 0 else contenido(0).length

  /**
    * Genera un nuevo elemento colocando this sobre el elemento
    * pasado como argumento
    * @param otro elemento con el que se hace la composicion
    * @return
    */
  def colocarSobre(otro: Elemento): Elemento = {
    // Se igualan los anchos de ambos elementos
    val este1= this ampliarAncho otro.ancho
    val otro1 = otro ampliarAncho this.ancho

    // Se crea el nuevo elemento usando el metodo factoria
    Elemento.crearElemento(este1.contenido ++ otro1.contenido)
  }

  /**
    * Genera un nuevo elemento colocando this junto al elemento
    * pasado como argumento
    * @param otro
    * @return
    */
  def colocarJunto(otro: Elemento): Elemento = {
    // Se igualan los altos de los elementos a combinar
    val este1 = this ampliarAlto otro.alto
    val otro1 = otro ampliarAlto this.alto

    // Se crea el elemento agregado
    Elemento.crearElemento(
      for ((linea1, linea2) <- este1.contenido zip otro1.contenido)
        yield linea1 + linea2
    )
  }

  /**
    * Genera un nuevo elemento ampliando el ancho del elemento sobre
    * el que se hace la llamada
    * @param ancho nuevo ancho que tendra el elemento
    * @return
    */
  def ampliarAncho(ancho: Int): Elemento =
    // Si ya tiene el ancho necesario, nada que hacer
    if (ancho <= this.ancho) this
    else {
      // En caso contrario, se determina la cadena de blancos a pegar
      // a derecha e izquierda
      val izquierda = Elemento.crearElemento(' ', (ancho - this.ancho) / 2, alto)
      val derecha = Elemento.crearElemento(' ', ancho - this.ancho - izquierda.ancho, alto)

      // Se genera un nuevo elemento uniendo el relleno a izquierda,
      // el elemento original y el relleno a derecha
      izquierda colocarJunto this colocarJunto derecha
    }

  /**
    * Genera un nuevo elemento ampliando el alto sobre el elemento
    * objeto de la llamada
    * @param alto nuevo alto del elemento a generar
    * @return
    */
  def ampliarAlto(alto:Int): Elemento =
    // Si el elemento ya tiene el alto adecuado, nada mas que hacer
    if (alto <= this.alto) this
    else{
      // Se determinan las cadenas de blancos que iran sobre y bajo el
      // contenido de this
      val sobre=Elemento.crearElemento(' ', ancho, (alto-this.alto)/2)
      val bajo = Elemento.crearElemento(' ', ancho, (alto - this.alto - sobre.alto))

      // Se genera el nuevo elemento, posicionado el relleno superior, el
      // elemento actual y el relleno inferior
      sobre colocarSobre this colocarSobre bajo
    }

  /**
    * Sobrecarga del metodo toString. Se genera una cadena con todo
    * el contenido del objeto, concatenando las cadenas con el
    * separador de salto de linea
    * @return
    */
  override def toString = contenido.mkString("\n")
}

/**
  * Objeto companion de Elemento para ubicar los metodos factoria
  * y las clases privadas con los tipos concretos de elemento
  */
object Elemento {

  /**
    * Clase para representar elementos que soportan varias lineas
    * de texto, una por elemento del array
    * @param contenido conjunto de cadenas que constituyen el contenido
    *                  del elemento
    */
  private class ElementoArray(val contenido: Array[String]) extends Elemento {
    // Se comprueba que todas las cadenas tienen igual
    // longitud para generar elementos correctos
    require(contenido forall (cadena => cadena.length == ancho))
  }

  /**
    * Clase para representar elementos de texto de una unica linea
    * @param texto contenido a almacenar en el elemento
    */
  private class ElementoLinea(texto: String) extends Elemento {
    /**
      * El contenido se almacena en un array
      */
    val contenido = Array(texto)

    /**
      * Sobrescritura del ancho
      * @return
      */
    override def ancho = texto.length

    /**
      * Sobrescritura de alto
      * @return
      */
    override def alto = 1
  }

  /**
    * Clase para representar elementos donde el contenido consiste en
    * la repeticion de un unico caracter un cierto numero de veces
    * @param caracter caracter base del contenido
    * @param ancho ancho de la cadena base a generar
    * @param alto numero de cadenas a generar, que se ubicaran en el
    *             array de cadenas representando lineas diferentes
    */
  private class ElementoUniforme(caracter: Char,
                                 override val ancho: Int,
                                 override val alto: Int) extends Elemento {
    // Se genera la linea base, repetiendo el caracter tantas veces como
    // indique ancho y generando una cadena
    private val lineaBase = caracter.toString * ancho

    /**
      * El contenido se obtiene creando un array con tantos elementos
      * (lineas) como indique alto y cada una de llas con el mismo
      * contenido: lineaBase
      * @return
      */
    def contenido = Array.fill(alto)(lineaBase)
  }

  /**
    * Metooo factoria que permite crear elementos con varias lineas
    * de texto
    * @param contenido
    * @return
    */
  def crearElemento(contenido: Array[String]): Elemento =
    new ElementoArray(contenido)

  /**
    * Metodo factoria que permite generar elementos a partir de un
    * caracter base, con un ancho y alto determinado
    * @param caracter
    * @param ancho
    * @param alto
    * @return
    */
  def crearElemento(caracter: Char, ancho: Int, alto: Int): Elemento =
    new ElementoUniforme(caracter, ancho, alto)

  /**
    * Metodo factoria para generar elementos cuyo contenido es una
    * simple linea
    * @param linea
    * @return
    */
  def crearElemento(linea: String): Elemento =
    new ElementoLinea(linea)
}
