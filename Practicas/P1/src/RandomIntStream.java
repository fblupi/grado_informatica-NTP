import java.security.SecureRandom;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by FranciscoJavier on 07/03/2016.
 */
public class RandomIntStream {
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();

        long muestras = 100_000_000;

        random.ints(muestras, 1, 7)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()))
                .forEach((resultado, contador) ->
                        System.out.printf("%-10d-10d%d&%n", resultado, contador,
                                ((double) contador / muestras)));

        // Paso a paso
        //IntStream ints = random.ints(muestras, 1, 7);
        //Stream<Integer> boxed = random.ints(muestras, 1, 7).boxed();

        Stream<Integer> flujoEnterosAleatorios = random.ints(muestras, 1, 7).boxed();

        Map<Integer, Long> mapaValorContador = flujoEnterosAleatorios.collect(
                Collectors.groupingBy(Function.identity(), Collectors.counting())
        );

        mapaValorContador.forEach((resultado, contador) ->
                System.out.printf("%-10d-10d%d&%n", resultado, contador,
                        ((double) contador / muestras)));
    }
}
