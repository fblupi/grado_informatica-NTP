args.foreach(arg => println(arg)) // abreviado sería args.foreach(println)

println("ahora con for")
for(i <- 0 to args.length - 1)
	println(args(i))