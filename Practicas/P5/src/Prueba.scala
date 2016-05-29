/**
  * Objeto para probar la funcionalidad requerida
  */
object Prueba extends App {
  import Huffman._

  /**
    * Código Huffman para el lenguaje francés, obtenido a partir de la página
    * web http://fr.wikipedia.org/wiki/Fr%C3%A9quence_d%27apparition_des_lettres_en_fran%C3%A7ais
    */
  val codigoHuffmanFrances: Nodo = NodoIntermedio(
    NodoIntermedio(
      NodoIntermedio(
        NodoHoja('s', 121895),
        NodoIntermedio(
          NodoHoja('d', 56269),
          NodoIntermedio(
            NodoIntermedio(
              NodoIntermedio(
                NodoHoja('x', 5928),
                NodoHoja('j', 8351),
                List('x', 'j'), 14279),
              NodoHoja('f', 16351),
              List('x', 'j', 'f'), 30630),
            NodoIntermedio(
              NodoIntermedio(
                NodoIntermedio(
                  NodoIntermedio(
                    NodoHoja('z', 2093),
                    NodoIntermedio(
                      NodoHoja('k', 745),
                      NodoHoja('w', 1747),
                      List('k', 'w'), 2492),
                    List('z', 'k', 'w'), 4585),
                  NodoHoja('y', 4725),
                  List('z', 'k', 'w', 'y'), 9310),
                NodoHoja('h', 11298),
                List('z', 'k', 'w', 'y', 'h'), 20608),
              NodoHoja('q', 20889),
              List('z', 'k', 'w', 'y', 'h', 'q'), 41497),
            List('x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 72127),
          List('d', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 128396),
        List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 250291),
      NodoIntermedio(
        NodoIntermedio(
          NodoHoja('o', 82762),
          NodoHoja('l', 83668),
          List('o', 'l'), 166430),
        NodoIntermedio(
          NodoIntermedio(
            NodoHoja('m', 45521),
            NodoHoja('p', 46335),
            List('m', 'p'), 91856),
          NodoHoja('u', 96785),
          List('m', 'p', 'u'), 188641),
        List('o', 'l', 'm', 'p', 'u'), 355071),
      List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q', 'o', 'l', 'm', 'p', 'u'), 605362),
    NodoIntermedio(
      NodoIntermedio(
        NodoIntermedio(
          NodoHoja('r', 100500),
          NodoIntermedio(
            NodoHoja('c', 50003),
            NodoIntermedio(
              NodoHoja('v', 24975),
              NodoIntermedio(
                NodoHoja('g', 13288),
                NodoHoja('b', 13822),
                List('g', 'b'), 27110),
              List('v', 'g', 'b'), 52085),
            List('c', 'v', 'g', 'b'), 102088),
          List('r', 'c', 'v', 'g', 'b'), 202588),
        NodoIntermedio(
          NodoHoja('n', 108812),
          NodoHoja('t', 111103),
          List('n', 't'), 219915),
        List('r', 'c', 'v', 'g', 'b', 'n', 't'), 422503),
      NodoIntermedio(
        NodoHoja('e', 225947),
        NodoIntermedio(
          NodoHoja('i', 115465),
          NodoHoja('a', 117110),
          List('i', 'a'), 232575),
        List('e', 'i', 'a'), 458522),
      List('r', 'c', 'v', 'g', 'b', 'n', 't', 'e', 'i', 'a'), 881025),
    List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q', 'o', 'l', 'm', 'p', 'u', 'r', 'c', 'v', 'g', 'b', 'n', 't', 'e', 'i', 'a'), 1486387)

  /**
    * Mensaje secreto a decodificar
    */
  val mensajeSecreto: List[Int] = List(0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1)

  /**
    * Se decodifica el mensaje
    */
  val mensajeDecodificado: List[Char] = decodificar(codigoHuffmanFrances, mensajeSecreto)

  println("Mensaje decodificado: " + mensajeDecodificado.mkString)

  // Se intenta lo mismo con la tabla
  val codificacionTabla = codificacionRapida(codigoHuffmanFrances)(mensajeDecodificado)

  // Debe ser igual a mensajeSecreto
  println("Mensaje original == mensaje codificado: " + (mensajeSecreto == codificacionTabla))

  /**
    * ******************************************************************************************************************
    */

  /**
    * Árbol original
    */
  val arbolOriginal = NodoIntermedio(
    NodoHoja('a', 8),
    NodoIntermedio(
      NodoIntermedio(
        NodoIntermedio(
          NodoHoja('g', 1),
          NodoHoja('h', 1),
          List('g','h'), 2),
        NodoIntermedio(
          NodoHoja('e', 1),
          NodoHoja('f', 1),
          List('e','f'), 2),
        List('g','h','e','f'), 4),
      NodoIntermedio(
        NodoIntermedio(
          NodoHoja('c', 1),
          NodoHoja('d', 1),
          List('c', 'd'), 2),
        NodoHoja('b', 3),
        List('c','d','b'), 5),
      List('g','h','e','f','c','d','b'), 9),
    List('a','g','h','e','f','c','d','b'), 17)

  /**
    * Cadena con la que construir el árbol
    */
  val chars = stringAListaCaracteres("aaaaaaaabbbcdefgh")

  // Árbol generado
  val arbolGenerado = generarArbolCodificacion(chars)

  // Debe ser igual a árbol original
  println("Árbol original == Árbol generado: " + (arbolOriginal == arbolGenerado))
}
