package listado;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Listado {
    /**
     * Dato miembro para almacenar a los alumnos como mapa con pares
     * <dni - alumno>
     */
    private Map<String, Alumno> lista;

    public Listado(String archivo) throws IOException {
        lista = new HashMap<>();
        Stream<String> lineas = Files.lines(Paths.get(archivo));
        lineas.forEach(linea -> crearAlumno(linea));
    }

    private void crearAlumno(String linea) {
        Pattern pattern = Pattern.compile("(,)");
        List<String> infos = pattern.splitAsStream(linea).collect(Collectors.toList());
        Alumno alumno = new Alumno(infos.get(0), infos.get(1), infos.get(2), infos.get(3));
        lista.put(infos.get(0), alumno);
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
        return 0;
    }

    @Override
    public String toString() {
        return lista.toString();
    }

    public static void main(String[] args) throws IOException {
        Listado l = new Listado("/home/fblupi/Documentos/GitHub/grado_informatica-NTP/Practicas/P1/data/datos.txt");
        System.out.println(l.toString());
    }
}