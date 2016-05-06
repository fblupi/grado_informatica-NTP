/**
  * Clase para representar colecciones no vacias
  *
  * @param mensajeInicial
  * @param resto
  */
class TendenciaNoVacia(mensajeInicial: Tweet, resto: Tendencia) extends Tendencia {
  /**
    * Se agrega mensaje al final de la secuencia
    *
    * @param mensaje
    * @return
    */
  def +(mensaje: Tweet): Tendencia = new TendenciaNoVacia(mensajeInicial, resto + mensaje)

  /**
    * Devuelve el mensaje inicial
    *
    * @return
    */
  def head: Tweet = mensajeInicial

  /**
    * Devuelve el resto de mensajes
    *
    * @return
    */
  def tail: Tendencia = resto

  /**
    * Indica si la coleccion esta vacia: no por definicion
    *
    * @return
    */
  def isEmpty: Boolean = false

  /**
    * Longitud de la tendencia
    *
    * @return
    */
  def length: Integer =
    if (resto.isEmpty) 1  // Toda tendencia acaba con una tendencia vacia
    else resto.length + 1 // Si no, aumenta el "contador"

  /**
    * Metodo toString
    *
    * @return
    */
  override def toString = "TendenciaNoVacia(" + mensajeInicial.retweets + ", " + resto + ")"
}
