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
}