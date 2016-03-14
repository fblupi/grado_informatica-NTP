import scala.io.Source

if (args.length > 0)
	for (linea <- Source.fromFile(args(0)).getLines())
		println(linea.length + " " + linea)
else 
	Console.err.println("Introduzca nombre de archivo")