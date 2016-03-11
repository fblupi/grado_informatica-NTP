package listado;

import java.util.List;
import java.util.Map;

public class Listado {
    /**
     * Dato miembro para almacenar a los alumnos como mapa con pares
     * <dni - alumno></dni>
     */
    private Map<String, Alumno> lista;

    public Listado(String s) {

    }

    public void cargarArchivoAsignacion(String archivo) {

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
        return null;
    }
}