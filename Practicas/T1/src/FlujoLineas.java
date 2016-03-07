import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by FranciscoJavier on 07/03/2016.
 */
public class FlujoLineas {
    public static void main(String[] args) throws IOException {
        Pattern pattern = Pattern.compile("\\s+");

        Files.lines(Paths.get("alicia.txt"), StandardCharsets.ISO_8859_1).forEach(System.out::println);

        // obtener lineas
        Stream<String> lineas = Files.lines(Paths.get("alicia.txt"), StandardCharsets.ISO_8859_1);

        // quita signos de puntuación
        Stream<String> flujoLineas = lineas.map(linea -> linea.replaceAll("(?!')\\p{Punct}", ""));

        // pasar de cadenas que representan líneas a cadenas que representan palabras
        Stream<String> flujoPalabras = flujoLineas.flatMap(linea -> pattern.splitAsStream(linea));

        // quita palabras vacías
        Stream<String> flujoPalabrasOk = flujoPalabras.filter(palabra -> !palabra.isEmpty());

        // agrupar
        TreeMap<String, Long> contadores = flujoPalabrasOk.collect(
                Collectors.groupingBy(String::toLowerCase,
                        TreeMap::new, Collectors.counting())
        );


    }
}
