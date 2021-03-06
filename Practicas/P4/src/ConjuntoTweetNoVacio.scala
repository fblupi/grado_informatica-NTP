/**
  * Clase para representar conjuntos no vacios
  *
  * @param raiz      elemento de la raiz
  * @param izquierda conjunto a la izquierda
  * @param derecha   conjunto a la derecha
  */
class ConjuntoTweetNoVacio(raiz: Tweet, izquierda: ConjuntoTweet, derecha: ConjuntoTweet) extends ConjuntoTweet {
  /**
    * Metodo auxiliar para filtrar
    *
    * @param predicado
    * @param conjunto
    * @return
    */
  def filtrar0(predicado: Tweet => Boolean, conjunto: ConjuntoTweet): ConjuntoTweet = {
    // se obtiene un conjunto con el resultado parcial en la raiz:
    // se incluye la raiz si cumple el predicado o es igual que el recibido si no se cumple  o ya esta incluido
    // recursividad a la dcha. y a la izda.
    val conjuntoNuevo = if (predicado(raiz) && !conjunto.contiene(raiz)) conjunto.incluir(raiz) else conjunto
    derecha.filtrar0(predicado, izquierda.filtrar0(predicado, conjuntoNuevo))
  }

  /**
    * Metodo que devuelve la union del conjunto de tweets actual con otro
    *
    * @param otro
    * @return
    */
  def union(otro: ConjuntoTweet): ConjuntoTweet =
    filtrar0(tweet => !otro.contiene(tweet), otro) // filtra para quitar comunes iniciando con todos los del otro
    // ALTERNATIVA:
    //if (otro.estaVacio) this              // otro vacio: devolver this
    //else if (!contiene(otro.head)) {      // this no contiene mensaje cabeza:
    //  val nuevo = this.incluir(otro.head) // nuevo <- incluir mensaje de cabeza en this
    //  nuevo.union(otro.tail)              // realizar union de nuevo con resto de mensajes de otro
    //} else union(otro.tail)               // en caso contrario: union de this con resto de mensajes de otro

  /**
    * Metodo que devuelve la interseccion del conjunto de tweets actual con otro
    *
    * @param otro
    * @return
    */
  def interseccion(otro: ConjuntoTweet) : ConjuntoTweet = filtrar(tweet => otro.contiene(tweet)) // filtra con comunes

  /**
    * Metodo con el numero de tuits del conjunto
    *
    * @return
    */
  def numeroMensajes: Integer = 1 + izquierda.numeroMensajes + derecha.numeroMensajes // recursividad a los lados y suma

  /**
    * Determina si el conjunto contiene un mensaje
    *
    * @param mensaje
    * @return
    */
  def contiene(mensaje: Tweet): Boolean =
    // Si el mensaje de texto es anterior en orden lexicografico,
    // entonces habra que buscar en la izquierda; en caso contrario,
    // en la derecha. Si no es ni menor ni mayor, sera igual y se devuelve
    // true
    if (mensaje.texto < raiz.texto) izquierda.contiene(mensaje)
    else if (raiz.texto < mensaje.texto) derecha.contiene(mensaje)
    else true

  /**
    * Metodo para incluir un nuevo mensaje
    *
    * @param mensaje
    * @return
    */
  def incluir(mensaje: Tweet): ConjuntoTweet = {
    // Igual idea que en el metodo anterior: si el mensaje tiene menor
    // orden lexicografico que la raiz, se inserta como raiz. En caso
    // contrario se inserta a la derecha. Si tiene igual orden se devuelve
    // el propio conjunto
    if (mensaje.texto < raiz.texto) new ConjuntoTweetNoVacio(raiz, izquierda.incluir(mensaje), derecha)
    else if (raiz.texto < mensaje.texto) new ConjuntoTweetNoVacio(raiz, izquierda, derecha.incluir(mensaje))
    else this
  }

  /**
    * Indica si el conjunto esta vacio; por definicion no lo estara
    *
    * @return
    */
  def estaVacio = false

  /**
    * Devuelve el primer mensaje (con menor orden)
    *
    * @return
    */
  def head = if (izquierda.estaVacio) raiz else izquierda.head

  /**
    * Devuelve los demas
    *
    * @return
    */
  def tail = if (izquierda.estaVacio) derecha else new ConjuntoTweetNoVacio(raiz, izquierda.tail, derecha)

  /**
    * Elimina mensaje del conjunto
    *
    * @param mensaje
    * @return
    */
  def eliminar(mensaje: Tweet): ConjuntoTweet =
    if (mensaje.texto < raiz.texto) new ConjuntoTweetNoVacio(raiz, izquierda.eliminar(mensaje), derecha)
    else if (raiz.texto < mensaje.texto) new ConjuntoTweetNoVacio(raiz, izquierda, derecha.eliminar(mensaje))
    else izquierda.union(derecha)
}