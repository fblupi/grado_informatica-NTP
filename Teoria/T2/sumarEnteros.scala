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

def sumarEnterosConSumAnonima(desde: Int, hasta: Int) = sum(desde, hasta, x => x)
def sumarCuadradosConSumAnonima(desde: Int, hasta: Int) = sum(desde, hasta, x => x * x)

// -----------------------------------------------------------------------------

/**
  * Función equivalente a sum pero ahorrando los parámetros desde y hasta
  */
def sumar(f: Int => Int): (Int, Int) => Int = {
  def sumar0(desde: Int, hasta: Int): Int =
    if (desde > hasta) 0
    else f(desde) + sumar0(desde + 1, hasta)
  sumar0 // devolución de sumar
}

def sumarCurrying (f: Int => Int)(desde: Int, hasta: Int): Int = {
  if (desde > hasta) 0
  else f(desde) + sumarCurrying(f)(desde + 1, hasta)
}

def sumarEnteros2 = sumar(x => x)
def sumarCuadrados2 = sumar(x => x * x)
def sumarPotenciasDos2 = sumar(obtenerPotenciaDos)

println(sumarEnteros2(2, 5))
println(sumar(x => x)(2, 5)) // Currying
println(sumarCurrying(x => x)(2, 5)) // Currying más puro
println(sumarCuadrados2(2, 5))
println(sumar(x => x * x)(2, 5)) // Currying
println(sumarCurrying(x => x * x)(2, 5)) // Currying más puro
println(sumarPotenciasDos2(2, 5))
println(sumar(obtenerPotenciaDos)(2, 5)) // Currying
println(sumarCurrying(obtenerPotenciaDos)(2, 5)) // Currying más puro
