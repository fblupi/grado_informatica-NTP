package listado;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
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
        // Paso 1: Leer asignatura e incluirla a los alumnos sin asignarles grupo
        String asignatura = Files.lines(Paths.get(archivo)) // Obtiene todas las lineas
                .findFirst() // Escoge la primera
                .get(); // Obtiene el String con la asignatura
        lista.forEach((s, alumno) -> alumno.asignarAsignatura(Asignatura.valueOf(asignatura), -1)); // Incluye la asignatura sin asignar grupo a todos los alumnos
        // Paso 2: Asignar grupos a los alumnos
        Files.lines(Paths.get(archivo)) // Obtiene todas las lineas (el otro se agoto con el findFirst)
                .skip(2) // Se salta las dos primeras (nombre de asignatura y linea en blanco)
                .forEach(linea -> asignarGrupoAsignatura(linea, Asignatura.valueOf(asignatura))); // Asigna grupo
    }

    private void asignarGrupoAsignatura(String linea, Asignatura asignatura) {
        Pattern pattern = Pattern.compile("\\s");
        List<String> infos = pattern.splitAsStream(linea) // Se separan las lineas en cadenas
                .collect(Collectors.toList()); // Se convierte el flujo en una lista de cadenas
        lista.get(infos.get(0)) // Obtiene al alumno
                .asignarAsignatura(asignatura, Integer.parseInt(infos.get(1))); // Le asigna el grupo
    }

    public Map<Asignatura, Map<Integer, Long>> obtenerContadoresGrupos() {
        Map<Asignatura, Map<Integer, Long>> resultado = new HashMap<>(); // Crea el HashMap resultado
        Stream.of(Asignatura.values()) // Crea un flujo con el array de asignaturas
                .forEach(a -> resultado.put(a, obtenerContadoresGruposDeAsignatura(a))); // Para cada una obtiene el contador de grupos
        return resultado;
    }

    public Map<Asignatura, Map<Integer, Long>> obtenerContadoresGruposNoFuncional() {
        Asignatura[] asignaturas = Asignatura.values(); // Obtiene todas las asignaturas
        Map<Asignatura, Map<Integer, Long>> resultado = new HashMap<>(); // Crea el HashMap resultado
        for (Asignatura a : asignaturas) { // Recorre todas las asignaturas
            resultado.put(a, obtenerContadoresGruposDeAsignaturaNoFuncional(a)); // Para cada una obtiene el contador de grupos
        }
        return resultado;
    }

    public Map<Integer, Long> obtenerContadoresGruposDeAsignatura(Asignatura asignatura) {
        Map<Integer, Long> resultado = new HashMap<>();
        lista.values() // Obtiene los valores del map
                .stream() // Crea un flujo sobre ellos
                .forEach(alumno -> resultado.merge(alumno.getGrupo(asignatura), 1L, Long::sum)); // Para cada alumno añade el grupo, si existe ya incrementa, si no da valor 1
        return resultado;
    }

    public Map<Integer, Long> obtenerContadoresGruposDeAsignaturaNoFuncional(Asignatura asignatura) {
        Map<Integer, Long> resultado = new HashMap<>();
        for (Alumno a : lista.values()) { // Recorre todos los alumnos
            resultado.merge(a.getGrupo(asignatura), 1L, Long::sum); // Añade el grupo, si no existe da valor 1, si no, incrementa
        }
        return resultado;
    }

    public List<Alumno> buscarAlumnosNoAsignados(String asignatura) {
        return lista.values() // Obtiene los valores del map
                .stream() // Crea un flujo sobre ellos
                .filter(alumno -> !alumno.cursarAsignatura(Asignatura.valueOf(asignatura))) // Filtra por alumnos que no están cursando una asignatura
                .collect(Collectors.toList()); // Lo pasa a la lista del resultado
    }

    public List<Alumno> buscarAlumnosNoAsignadosNoFuncional(String asignatura) {
        List<Alumno> resultado = new ArrayList<>();
        for (Alumno a : lista.values()) { // Recorre todos los alumnos
            if (!a.cursarAsignatura(Asignatura.valueOf(asignatura))) { // Si no está cursando una asignatura
                resultado.add(a); // Lo añade al resultado
            }
        }
        return  resultado;
    }

    public long obtenerLongitud() {
        return lista.entrySet() // Obtiene las entradas
                .stream() // Crea un flujo sobre ellas
                .count(); // Cuenta cuántas hay
    }

    public long obtenerLongitudNoFuncional() {
        return lista.size(); // Devuelve el tamaño del map
    }

    @Override
    public String toString() {
        return lista.values().toString();
    }

    public static void main(String[] args) throws IOException {
        Listado l = new Listado("./data/datos.txt");
        l.cargarArchivoAsignacion("./data/asignacionES.txt");
        l.cargarArchivoAsignacion("./data/asignacionLMD.txt");
        l.cargarArchivoAsignacion("./data/asignacionMP.txt");
        l.cargarArchivoAsignacion("./data/asignacionTOC.txt");
        System.out.println(l.toString());
    }
}