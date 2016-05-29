case class NodoIntermedio(val hijoIzda: Nodo, val hijoDcha: Nodo, val caracteres: List[Char], val peso: Int) extends Nodo {
  override def toString: String = "Intermedio --> CHARS: " + caracteres + ", PESO: " + peso + "\n{\n1." + hijoIzda.toString + "2." + hijoDcha.toString + "}\n"
}
