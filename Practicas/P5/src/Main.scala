object Main extends App {
  import Huffman._

  println(generarListHojasOrdenadas(obtenerTuplasOcurrencias(List('a', 'b', 'a', 'a', 'c', 'b', 'x'))))

  val l = List(1,46,5,6)
  val n1 = l.head
  val n2 = l.tail.head
  println(n1 + n2 :: l.tail.tail)
}