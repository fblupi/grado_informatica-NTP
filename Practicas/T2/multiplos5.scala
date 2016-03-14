def escribirMultiplos5(ini: Int, fin: Int) : Unit = {
	if (ini <= fin) {
		println(ini)
		escribirMultiplos5(ini + 5, fin)
	}
}

if (args.length >= 2)
	escribirMultiplos5(args(0).toInt, args(1).toInt)
else 
	Console.err.println("Insertar número inicial y final")