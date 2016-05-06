/**
  * Clase de utilidad para representar conjuntos de tweets con temas de
  * google y apple, junto con un objeto de la clase Tendencia con todos
  * ellos
  */
object TerminosGoogleApple {
  // Lista de terminos de interes para google
  val google = List("android", "Android", "galaxy", "Galaxy", "nexus", "Nexus")

  // Lista de terminos de interes para apple
  val apple = List("ios", "iOS", "iphone", "iPhone", "ipad", "iPad")

  // Conjuntos de tweets para ambas listas de terminos (filter)
  val mensajesGoogle: ConjuntoTweet = LectorTweets.obtenerConjuntoConTerminos(google)
  val mensajesApple: ConjuntoTweet = LectorTweets.obtenerConjuntoConTerminos(apple)

  // Se genera la lista completa de mensajes de ambos temas (union)
  val tendencia: Tendencia = mensajesApple.union(mensajesGoogle).ordenacionAscendentePorRetweet
}

/**
  * Clase para probar la funcionalidad
  */
object Main extends App {
  print("1. Numero de mensajes en:\n")
  print("\tGoogle: ")
  print(TerminosGoogleApple.mensajesGoogle.numeroMensajes)
  print("\n\tApple: ")
  print(TerminosGoogleApple.mensajesApple.numeroMensajes)
  print("\n\n")

  print("2. Numero de mensajes en la tendencia: ")
  print(TerminosGoogleApple.tendencia.length)
  print("\n\n")

  val mensajesComunes = TerminosGoogleApple.mensajesGoogle.interseccion(TerminosGoogleApple.mensajesApple)

  print("3. Numero de mensajes comunes: ")
  print(mensajesComunes.numeroMensajes)
  print("\n\n")

  print("4. Orden de influencia de los mensajes comunes:\n")
  mensajesComunes.ordenacionAscendentePorRetweet.foreach(t => println(t))
  print("\n\n")

  print("5. Maximo y minimo numero de retweets en los mensajes comunes:\n")
  print("\tMaximo: ")
  //print(mensajesComunes.ordenacionAscendentePorRetweet)
  print("\n\tMinimo: ")
  //print(mensajesComunes.ordenacionAscendentePorRetweet)
  print("\n\n")

  print("6. Maximo y minimo de retweets en toda la coleccion de tendencia:\n")
  print("\tMaximo: ")
  //print(tendencia)
  print("\n\tMinimo: ")
  //print(tendencia)
  print("\n\n")
}