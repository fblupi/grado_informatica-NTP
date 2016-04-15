//def areaCirculo(r: Double) : Double = math.Pi * math.pow(r, 2)
//def areaCirculo(r: Double) = math.Pi * math.pow(r, 2)
def areaCirculo(r: String) : Double = {
	r.isEmpty match {
		case true => 0
		case false => math.Pi * math.pow(r.toDouble, 2)
	}
}

if (args.length > 0)
	println(areaCirculo(args(0)))
else 
	Console.err.println("Introduzca radio del círculo")