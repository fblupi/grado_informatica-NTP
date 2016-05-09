// Un punto fijo es un punto x que cumple que f(x) = x
// f(x), f(f(x)), f(f(f(x)))... x = f(x)

import math.abs

val tolerancia = 0.000001

def estaBienAproximado(valor: Double, siguiente: Double): Boolean =
  abs((valor - siguiente) / valor) < tolerancia

def puntoFijo(funcion: Double => Double)(inicio: Double): Double = {
  def iterar(valor: Double): Double = {
    val siguiente = funcion(valor)
    if (estaBienAproximado(valor, siguiente)) siguiente
    else iterar(siguiente)
  }
  iterar(inicio)
}

def raizCuadrada(x: Double) = puntoFijo(y => x / y)(1.0)

println(raizCuadrada(4))