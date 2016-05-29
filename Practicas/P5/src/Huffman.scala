object Huffman {
  type TablaCodigo = List[(Char, List[Int])]

  /**
    * Pasar de cadena de texto normal a lista de caracteres
    *
    * @param cadena
    * @return
    */
  def stringAListaCaracteres(cadena: String): List[Char] = cadena.toList

  /**
    * Recibe como argumento un nodo y devuelve el peso asociado calculando los pesos de los nodos inferiores, desde las
    * hojas hasta sus hijos
    *
    * @param nodo
    * @return
    */
  def calcularPeso(nodo: Nodo): Int = nodo match {
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
  def obtenerCaracteres(nodo: Nodo): List[Char] = nodo match {
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
    * @param texto
    * @return
    */
  def generarArbolCodificacion(texto: List[Char]): Nodo =
    hasta(singleton, combinar)(generarListHojasOrdenadas(obtenerTuplasOcurrencias(texto)))  // llamada a hasta sobre el texto
      .head                                                                                 // obtiene el primer (y único) elemento

  /**
    * Calcula la frecuencia de aparición de cada carácter en el texto a analizar
    *
    * @param texto
    * @return
    */
  def obtenerTuplasOcurrencias(texto: List[Char]): List[(Char, Int)] =
    texto
      .map(char => (char, texto.count(otherChar => otherChar == char))) // para cada elemento mapea con sus ocurrencias
      .distinct                                                         // elimina repetidos

  /**
    * Genera una lista con toidos los nodos hoja del árbol de codificación. Esta lista de nodos terminales está
    * ordenada por pesos de forma ascendente
    *
    * @param ocurrencias
    * @return
    */
  def generarListHojasOrdenadas(ocurrencias: List[(Char, Int)]): List[NodoHoja] =
    ocurrencias
      .sortWith((ocurr1, ocurr2) => ocurr1._2 < ocurr2._2)  // ordena de forma ascendente por peso
      .map(ocurr => NodoHoja(ocurr._1, ocurr._2))           // mapea el resultado en una lista de nodos hoja

  /**
    * Comprueba si una lista de nodos contiene un único elemento
    *
    * @param nodos
    * @return
    */
  def singleton(nodos: List[Nodo]): Boolean = nodos.size == 1  // comprueba que solo hay un nodo

  /**
    * Combina todos los nodos terminales
    * Pasos:
    *  - elimina de la lista de nodos los dos con menos peso (n-2)
    *  - los combina para formar un nodo intermedio con ellos
    *  - inserta ese nodo en la lista de nodos a combinar insertando de forma que se preserve el orden (n-1)
    *  - función hasta
    *
    * @param nodos
    * @return
    */
  def combinar(nodos: List[Nodo]): List[Nodo] = {
    (generarArbol(nodos.head, nodos.tail.head) :: nodos.tail.tail)            // inserta nodo intermedio al principio
      .sortWith((nodo1, nodo2) => calcularPeso(nodo1) < calcularPeso(nodo2))  // ordena la lista
  }

  /**
    * Hace llamadas a las funciones definidas en pasos anteriores hasta que la lista de nodos contenga un único elemento
    *
    * @param pred
    * @param func
    * @param nodos
    * @return
    */
  def hasta(pred: List[Nodo] => Boolean, func: List[Nodo] => List[Nodo])(nodos: List[Nodo]): List[Nodo] =
    if (pred(nodos)) nodos              // solo queda un elemento
    else hasta(pred, func)(func(nodos)) // llamada recursiva sobre la combinación en los nodos actuales

  /**
    * Codifica un texto siguiendo un código Huffman
    *
    * @param arbol
    * @param texto
    * @return
    */
  def codificar(arbol: Nodo, texto: List[Char]): List[Int] = {
    def codificar0(nodo: Nodo, texto: List[Char]): List[Int] =
      nodo match {                                                                        // nodo actual
        case NodoHoja(_, _) =>                                                            // es nodo hoja
          if (texto.tail.isEmpty) List()                                                  // último caracter => criterio de parada
          else codificar0(arbol, texto.tail)                                              // no último caracter => continúa con el siguiente carácter
        case NodoIntermedio(izda, dcha, caracteres, _) =>                                 // es nodo intermedio
          if (obtenerCaracteres(izda).contains(texto.head)) 0 :: codificar0(izda, texto)  // el carácter está a la izquierda => añade un 0
          else 1 :: codificar0(dcha, texto)                                               // el carácter está a la derecha => añade un 1
      }
    codificar0(arbol, texto)  // comienza desde la raiz con el texto completo
  }

  /**
    * Decodifica un texto siguiendo un código Huffman
    *
    * @param arbol
    * @param textoCodificado
    * @return
    */
  def decodificar(arbol: Nodo, textoCodificado: List[Int]): List[Char] = {
    def decodificar0(nodo: Nodo, textoCodificado: List[Int]): List[Char] =
      nodo match {                                                                // nodo actual
        case NodoHoja(caracter, _) =>                                             // es nodo hoja
          if (textoCodificado.isEmpty) List(caracter)                             // último bit => criterio de parada
          else caracter :: decodificar0(arbol, textoCodificado)                   // no último bit => guarda el caracter y continúa
        case NodoIntermedio(izda, dcha, _, _) =>                                  // es nodo intermedio
          if (textoCodificado.head == 0) decodificar0(izda, textoCodificado.tail) // bit == 0 => recursividad por la izda con el resto de bits
          else decodificar0(dcha, textoCodificado.tail)                           // bit == 1 => recursividad por la dcha con el resto de bits
      }
    decodificar0(arbol, textoCodificado) // comienza desde la raiz con el texto codificado completo
  }

  /**
    * Codifica un texto siguiendo un código Huffman usando una tabla para no tener que recorrer el árbol
    *
    * @param tabla
    * @param caracter
    * @return
    */
  def codificarConTabla(tabla: TablaCodigo)(caracter: Char): List[Int] =
    tabla
      .filter(codigo => codigo._1 == caracter)  // filtra para buscar la entrada con el carácter
      .head._2                                  // devuelve el carácter encriptado

  /**
    * Crear tabla visitando el arbol de codificación
    *
    * @param arbol
    * @return
    */
  def convertirArbolTabla(arbol: Nodo) : TablaCodigo = {
    def convertirArbolTabla0(nodo: Nodo, lista: List[Int]): TablaCodigo = {
      nodo match {                                                                          // nodo actual
        case NodoHoja(caracter, _) => List((caracter, lista))                               // es nodo hoja => devuelve el caracter con la lista hasta llegar a él
        case NodoIntermedio(izda, dcha, _, _) =>                                            // es nodo intermedio => recursividad a izda y dcha agregando un 0 y 1
          convertirArbolTabla0(izda, lista :+ 0) ::: convertirArbolTabla0(dcha, lista :+ 1) //                       a la lista respectivamente
      }
    }
    convertirArbolTabla0(arbol, List()) // comienza con el arbol completo y una lista vacía
  }

  /**
    * Codifica un texto siguiendo un código Huffman usando una tabla para no tener que recorrer el árbol
    *
    * @param arbol
    * @param texto
    * @return
    */
  def codificacionRapida(arbol: Nodo)(texto: List[Char]): List[Int] = {
    val tablaCodigo = convertirArbolTabla(arbol)                            // crea la tabla a partir del árbol
    (for(caracter <- texto) yield codificarConTabla(tablaCodigo)(caracter)) // obtiene lista para cada caracter del texto
      .flatten                                                              // convierte el List[List[Int]] a List[Int]
  }
}