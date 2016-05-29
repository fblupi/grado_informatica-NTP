case class NodoHoja(val caracter: Char, val peso: Int) extends Nodo {
  override def toString: String = "Hoja --> CHAR: " + caracter + ", PESO: " + peso + "\n"
}