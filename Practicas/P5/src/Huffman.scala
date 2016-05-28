object Huffman {
  /**
    * Recibe como argumento un nodo y devuelve el peso asociado calculando los pesos de los nodos inferiores, desde las
    * hojas hasta sus hijos
    *
    * @param nodo
    * @return
    */
  def calcularPeso(nodo: Nodo) : Integer = nodo match {
    case NodoHoja(_, peso) => peso
    case NodoIntermedio(nodoIzda, nodoDcha, _, _) => calcularPeso(nodoIzda) + calcularPeso(nodoDcha)
  }

  /**
    * Recibe como argumento un árbol de codificación (un nodo, su raíz) y devuelve la lista de caracteres que
    * representa considerando todos los nodos inferiores
    *
    * @param nodo
    * @return
    */
  def obtenerCaracteres(nodo: Nodo) : List[Char] = nodo match {
    case NodoHoja(caracter, _) => List(caracter)
    case NodoIntermedio(nodoIzda, nodoDcha, _, _) => obtenerCaracteres(nodoIzda) ::: obtenerCaracteres(nodoDcha)
  }

  /**
    * Recibe como argumento los subárboles a izquierda y derecha y genera un nuevo árbol a partir de ellos
    *
    * @param nodoIzda
    * @param nodoDcha
    * @return
    */
  def generarArbol(nodoIzda: Nodo, nodoDcha: Nodo): Nodo =
    NodoIntermedio(nodoIzda, nodoDcha,
      obtenerCaracteres(nodoIzda) ::: obtenerCaracteres(nodoDcha), calcularPeso(nodoIzda) + calcularPeso(nodoDcha))

  /**
    * Dado un texto, calcula y construye un árbol de codificación analizando sus caracteres y contadores de ocurrencia
    *
    * @param caracteres
    * @return
    */
  def generarArbolCodificacion(caracteres: List[Char]) : Nodo = ???

  /**
    * Calcula la frecuencia de aparición de cada carácter en el texto a analizar
    *
    * @param caracteres
    * @return
    */
  def obtenerTuplasOcurrencias(caracteres: List[Char]) : List[(Char, Integer)] = ???

  /**
    * Genera una lista con toidos los nodos hoja del árbol de codificación. Esta lista de nodos terminales está
    * ordenada por pesos de forma ascendente
    *
    * @param ocurrencias
    * @return
    */
  def generarListHojasOrdenadas(ocurrencias: List[(Char, Integer)]) : List[NodoHoja] = ???

  /**
    * Comprueba si una lista de nodos contiene un único elemento
    *
    * @param nodos
    * @return
    */
  def singleton(nodos: List[Nodo]) : Boolean = ???

  /**
    * Combina todos los nodos terminales
    *
    * @param nodos
    * @return
    */
  def combinar(nodos: List[Nodo]) : List[Nodo] = ???

  def hasta = ???

  /**
    * Pasar de cadena de texto normal a lista de caracteres
    *
    * @param cadena
    * @return
    */
  def stringAListaCaracteres(cadena: String) :List[Char] = cadena.toList
}