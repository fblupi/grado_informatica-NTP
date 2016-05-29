object Main extends App {
  import Huffman._

  val cad = "aaaaaaaabbbcdefgh"
  val chars = stringAListaCaracteres(cad)
  println(generarArbolCodificacion(chars))
}