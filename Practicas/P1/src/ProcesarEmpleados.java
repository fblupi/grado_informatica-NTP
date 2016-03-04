import java.util.Arrays;
import java.util.List;

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

        List<Empleado> lista = Arrays.asList(empleados);
        
    }
}
