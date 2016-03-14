def areaCirculo(r: Double) : Double = math.Pi * math.pow(r, 2)
//def areaCirculo(r: Double) = math.Pi * math.pow(r, 2)

if (args.length > 0)
	println(areaCirculo(args(0).toDouble))
else 
	Console.err.println("Introduzca radio del círculo")