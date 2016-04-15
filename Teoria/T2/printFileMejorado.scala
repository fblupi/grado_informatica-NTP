import scala.io.Source
if (args.length > 0) {
	var lineas = Source.fromFile(args(0)).getLines().toList

	def calcularAnchoTamLinea(s : String) = s.length.toString.length

/* Método funcional
	var maximoAnchoTam = 0
	for(linea <- lineas)
		maximoAnchoTam = maximoAnchoTam.max(calcularAnchoTamLinea(linea)) // máximo entre lo que vale en ese momento y lo que se le pasa como argumento
*/

	val lineaMasLarga = lineas.reduceLeft((a,b) => if (a.length > b.length) a else b)
	var maximoAnchoTam = calcularAnchoTamLinea(lineaMasLarga)

	for (linea <- lineas) {
		// Se calcula el número de espacios en blanco para igualar con el máximo tama ancho
		val numeroEspacios = maximoAnchoTam - calcularAnchoTamLinea(linea)

		// Se construye la cadena de blancos de relleno
		var relleno = " " * numeroEspacios

		// Se muestra la linea
		println(relleno + linea.length + "|" + linea)
	}

} else 
	Console.err.println("Introduzca nombre de archivo")