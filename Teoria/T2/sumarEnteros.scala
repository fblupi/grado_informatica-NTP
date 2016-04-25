def sumarEnteros(desde: Int, hasta: Int): Int =
  if (desde > hasta) 0
  else desde + sumarEnteros(desde + 1, hasta)

def elevarCuadrado(x: Int) = x * x

def sumarCuadrados(desde: Int, hasta: Int): Int =
  if (desde > hasta) 0
  else elevarCuadrado(desde) + sumarCuadrados(desde + 1, hasta)

println(sumarCuadrados(1, 3))
