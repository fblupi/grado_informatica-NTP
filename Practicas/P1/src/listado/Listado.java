package listado;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Listado {
    /**
     * Dato miembro para almacenar a los alumnos como mapa con pares
     * <dni - alumno>
     */
    private Map<String, Alumno> lista;

    public Listado(String archivo) throws IOException {
        lista = new HashMap<>(); // Inicializa el HashMap donde se almacenaran los alumnos
        Files.lines(Paths.get(archivo)) // Se obtienen todas las lineas del archivo
                .forEach(linea -> crearAlumno(linea)); // Para cada linea se crea el alumno que describe
    }

    private void crearAlumno(String linea) {
        Pattern pattern = Pattern.compile("(,)"); // Se define un patron para las comas que hacen de separador
        List<String> infos = pattern.splitAsStream(linea) // Se separan las lineas en cadenas
                .collect(Collectors.toList()); // Se convierte el flujo en una lista de cadenas
        Alumno alumno = new Alumno(infos.get(0), infos.get(1), infos.get(2), infos.get(3)); // Se crea el alumno
        lista.put(infos.get(0), alumno); // Se incluye en el map
    }

    public void cargarArchivoAsignacion(String archivo) throws IOException {

    }

    public Map<Asignatura, Map<Integer, Long>> obtenerContadoresGrupos() {
        return null;
    }

    public Map<Integer, Long> obtenerContadoresGruposDeAsignatura(Asignatura asignatura) {
        return null;
    }

    public List<Alumno> buscarAlumnosNoAsignados(String asignatura) {
        return null;
    }

    public long obtenerLongitud() {
        return lista.entrySet().stream().count();
    }

    public long obtenerLongitudNoFuncional() {
        return lista.size();
    }

    @Override
    public String toString() {
        return lista.toString();
    }

    public static void main(String[] args) throws IOException {
        Listado l = new Listado("./data/datos.txt");
        System.out.println(l.toString());
        System.out.println(l.obtenerLongitud());
        System.out.println(l.obtenerLongitudNoFuncional());
    }
}