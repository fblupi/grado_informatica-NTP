import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
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
                    .findFirst()
                    .get());
    }
}
