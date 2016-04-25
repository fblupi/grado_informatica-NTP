def sumarEnteros(desde: Int, hasta: Int): Int =
  if (desde > hasta) 0
  else desde + sumarEnteros(desde + 1, hasta)

def elevarCuadrado(x: Int) = x * x

def sumarCuadrados(desde: Int, hasta: Int): Int =
  if (desde > hasta) 0
  else elevarCuadrado(desde) + sumarCuadrados(desde + 1, hasta)

def obtenerPotenciaDos(x: Int): Int =
  if (x == 0) 1
  else 2 * obtenerPotenciaDos(x - 1)

def sumarPotenciasDos(desde: Int, hasta: Int): Int =
  if (desde > hasta) 0
  else obtenerPotenciaDos(desde) + sumarPotenciasDos(desde + 1, hasta)

println(sumarPotenciasDos(1, 3))
