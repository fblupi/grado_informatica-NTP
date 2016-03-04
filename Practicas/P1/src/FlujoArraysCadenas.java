import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by FranciscoJavier on 04/03/2016.
 */
public class FlujoArraysCadenas {
    public static void main(String[] args) {
        String[] cadenas = {"Rojo", "Naranja", "Amarillo", "Verde", "azul", "indigo", "Violeta"};

        System.out.printf("Cadenas originales: %s%n", Arrays.asList(cadenas));

        System.out.printf("Cadenas en mayúscula: %s%n",
                Arrays.stream(cadenas)
                        .map(String::toUpperCase)
                        .collect(Collectors.toList()));

        System.out.printf("Cadenas filtradas (mayores que m...) y ordenadas (orden ascendente): %s%n",
                Arrays.stream(cadenas)
                        .filter(s -> s.compareToIgnoreCase("m") > 0)
                        .sorted(String.CASE_INSENSITIVE_ORDER)
                        .collect(Collectors.toList()));
    }
}
