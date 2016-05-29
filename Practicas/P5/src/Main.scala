object Main extends App {
  import Huffman._

  val chars = stringAListaCaracteres("aaaaaaaabbbcdefgh")
  println(generarListHojasOrdenadas(obtenerTuplasOcurrencias(chars)))
  println(generarArbolCodificacion(chars))
}