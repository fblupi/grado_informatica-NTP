case class NodoHoja(val caracter: Char, val peso: Int) extends Nodo {
  override def toString: String = "Hoja: " + caracter + " -> " + peso + "\n"
}