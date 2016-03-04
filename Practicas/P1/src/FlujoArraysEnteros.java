import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by FranciscoJavier on 04/03/2016.
 */
public class FlujoArraysEnteros {
    public static void main(String[] args) {
        Integer[] valores = {2, 9, 5, 0, 3, 7, 1, 4, 8, 6};

        System.out.printf("Valores originales: %s%n", Arrays.asList(valores));

        System.out.printf("Valores ordenados: %s%n",
                Arrays.stream(valores)
                        .sorted()
                        .collect(Collectors.toList()));

        List<Integer> mayores4 =
                Arrays.stream(valores)
                        .filter(valor -> valor > 4)
                        .collect(Collectors.toList());

        System.out.printf("Mayores a 4: %s%n", Arrays.asList(mayores4));

        System.out.printf("Mayores a 4 ordenados: %s%n",
                Arrays.stream(valores)
                        .filter(valor -> valor > 4)
                        .sorted()
                        .collect(Collectors.toList()));
    }
}
