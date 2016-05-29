object Main extends App {
  import Huffman._

  print(generarListHojasOrdenadas(obtenerTuplasOcurrencias(List('a', 'b', 'a', 'a', 'c', 'b', 'x'))))

}