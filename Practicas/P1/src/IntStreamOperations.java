import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by fblupi on 26/02/2016.
 */

public class IntStreamOperations {

    // Dato miembro para almacenar los valores
    private int[] valores;

    public IntStreamOperations(int num) {
        // Se reserva memoria para un vector con el tamaño indicado
        valores = new int[num];

        // Se rellena con valores aleatorios entre 0 y 100
        Random generador = new Random();
        for (int i = 0; i < num; i++){
            valores[i] = generador.nextInt(101);
        }
    }

    public void mostrarValoresFuncional() {
        IntStream.of(valores)
                .forEach(value -> System.out.printf("%d ", value));
        System.out.println();
        // Una vez se ha utilizado el flujo este queda inutil

        // Alternativa: IntStream.of(valores).forEach(System.out::println);
        // Bloque de sentencias: IntStream.of(valores).forEach(value -> { ... });
    }

    public void mostrarValores() {
        for (int i = 0; i < valores.length; i++) {
            System.out.printf("%d ", valores[i]);
        }
        System.out.println();
    }

    public long contarValores() {
        return valores.length;
    }

    public long contarValoresFuncional() {
        return IntStream.of(valores).count();
    }

    public int obtenerMinimo() {
        int minimo = valores[0];
        for (int i = 1; i < valores.length; i++) {
            if (valores[i] < minimo) {
                minimo = valores[i];
            }
        }
        return minimo;
    }

    public int obtenerMinimoFuncional() {
        return IntStream.of(valores).min().getAsInt();  // Se hace getAsInt porque devuelve un OptionalInt.
                                                        // Devuelve un OptionalInt para protegerse si no hay valores
    }

    public int obtenerMaximo() {
        int maximo = valores[0];
        for (int i = 1; i < valores.length; i++) {
            if (valores[i] > maximo) {
                maximo = valores[i];
            }
        }
        return maximo;
    }

    public int obtenerMaximoFuncional() {
        return IntStream.of(valores).max().getAsInt();
    }

    public long obtenerSuma() {
        long suma = 0;
        for (int i = 0; i < valores.length; i++) {
            suma += valores[i];
        }
        return suma;
    }

    public long obtenerSumaFuncional() {
        return IntStream.of(valores).sum();
    }

    public long obtenerSumaReduceExpresionesLambda() {
        return IntStream.of(valores).reduce(0, (x, y) -> x + y);
    }

    public static void main(String[] args) {
        IntStreamOperations objeto = new IntStreamOperations(10000);
        objeto.mostrarValores();
        objeto.mostrarValoresFuncional();
        System.out.println(objeto.contarValores());
        System.out.println(objeto.contarValoresFuncional());
        System.out.println(objeto.obtenerMinimo());
        System.out.println(objeto.obtenerMinimoFuncional());
        System.out.println(objeto.obtenerMaximo());
        System.out.println(objeto.obtenerMaximoFuncional());
        System.out.println(objeto.obtenerSuma());
        System.out.println(objeto.obtenerSumaFuncional());
        System.out.println(objeto.obtenerSumaReduceExpresionesLambda());
    }
}

