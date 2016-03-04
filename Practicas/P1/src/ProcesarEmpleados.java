import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by FranciscoJavier on 04/03/2016.
 */
public class ProcesarEmpleados {
    public static void main(String[] args) {
        Empleado[] empleados = {
                new Empleado("Juan", "Lopez", 5000, "IT"),
                new Empleado("Antonio", "Garcia", 7600, "IT"),
                new Empleado("Mateo", "Insausti", 3587.5, "Ventas"),
                new Empleado("Joaquín", "Fernandez", 4700.77, "Marketing"),
                new Empleado("Lucas", "Martinez", 6200, "IT"),
                new Empleado("Pedro", "Garcia", 3200, "Ventas"),
                new Empleado("Fernado", "Gonzalez", 4236.4, "Marketing")};

        List<Empleado> lista = Arrays.asList(empleados); // paso a lista
        Stream<Empleado> flujo = lista.stream(); // paso a flujo

        System.out.printf("%nLista completa de empleados:%n");
        flujo.forEach(System.out::println); // necesario toString en la clase empleado

        Predicate<Empleado> condicion = // evito tener que definir un método
                empleado -> (empleado.obtenerSueldo() >= 4000 &&
                             empleado.obtenerSueldo() <= 6000);

        System.out.printf("%nEmpleados seleccionados y ordenados:%n");
        lista.stream()
                .filter(condicion)
                .sorted(Comparator.comparing(Empleado::obtenerSueldo))
                .forEach(System.out::println);

        System.out.printf("%nPrimer empleado de los seleccionados:%n%s%n",
                lista.stream()
                    .filter(condicion)
                    .findFirst() // se detiene justo cuando lo encuentra ya que filter es una operación perezosa que no se ejecuta hasta que después hay un count, findFirst...
                    .get());

        // Functions para luego hacer más sencilla la declaración de los comparadores sucesivos
        Function<Empleado, String> refNombre = Empleado::obtenerNombre;
        Function<Empleado, String> refPrimerApellido = Empleado::obtenerPrimerApellido;

        Comparator<Empleado> comparador =
                Comparator.comparing(refPrimerApellido)
                        .thenComparing(refNombre);

        System.out.printf("%nEmpleados en orden Apellido Nombre:%n");
        lista.stream()
                .sorted(comparador)
                .forEach(System.out::println);

        System.out.printf("%nEmpleados en orden inverso Apellido Nombre:%n");
        lista.stream()
                .sorted(comparador.reversed())
                .forEach(System.out::println);

        System.out.printf("%nApellidos sin repeticiones y ordenados:%n");
        lista.stream()
                .map(Empleado::obtenerPrimerApellido) // aquí psaría el flujo a ser un flujo de String
                .distinct()
                .sorted()
                .forEach(System.out::println);

        System.out.printf("%nMuestra solo nombres:%n");
        lista.stream()
                .sorted(comparador)
                .map(Empleado::obtenerNombre)
                .forEach(System.out::println);

        System.out.printf("%nEmpleados por departamentos:%n");
        Map<String, List<Empleado>> agrupadoPorDepartamentos =
                lista.stream()
                .collect(Collectors.groupingBy(Empleado::obtenerDepartamento));

        agrupadoPorDepartamentos.forEach(
                (departamento, empleadosEnDepartamento) -> {
                    System.out.println(departamento);
                    empleadosEnDepartamento.forEach(System.out::println);
                }
        );

        System.out.printf("%nCuenta empleados por departamentos:%n");
        Map<String, Long> cuentaEmpleadosPorDepartamento =
                lista.stream()
                .collect(Collectors.groupingBy(Empleado::obtenerDepartamento,
                        TreeMap::new, Collectors.counting()));

        cuentaEmpleadosPorDepartamento.forEach(
                (departamento, cuenta) -> System.out.printf(
                        "%s tiene %d empleados%n", departamento, cuenta
                )
        );

    }
}
