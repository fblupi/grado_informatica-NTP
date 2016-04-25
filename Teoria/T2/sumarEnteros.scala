def identidad(x: Int) = x

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

def sum(desde: Int, hasta: Int, f: Int => Int): Int =
  if (desde > hasta) 0
  else f(desde) + sum(desde + 1, hasta, f)

def sumarEnterosConSum(desde: Int, hasta: Int) = sum(desde, hasta, identidad)
def sumarCuadradosConSum(desde: Int, hasta: Int) = sum(desde, hasta, elevarCuadrado)
def sumarPotenciasDosConSum(desde: Int, hasta: Int) = sum(desde, hasta, obtenerPotenciaDos)

println(sumarEnterosConSum(2, 5))
println(sumarCuadradosConSum(2, 5))
println(sumarPotenciasDosConSum(2, 5))
