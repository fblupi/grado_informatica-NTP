def sumarEnteros(desde: Int, hasta: Int): Int =
  if (desde > hasta) 0
  else desde + sumarEnteros(desde + 1, hasta)
  
println(sumarEnteros(4, 8))
