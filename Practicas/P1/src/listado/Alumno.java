package listado;

import java.util.HashMap;

public class Alumno {
    private final int NO_ASIGNADO = -1;
    private String dni;
    private String nombre;
    private String apellidos;
    private String email;
    /**
     * Dato miembro para almacenar las asignaciones de grupo
     */
    private HashMap<Asignatura, Integer> asignacion;

    public Alumno(String dni, String nombre, String apellidos, String email) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        asignacion = new HashMap<>();
    }

    void asignarAsignatura(Asignatura asignatura, Integer grupo) {
        asignacion.put(asignatura, grupo);
    }

    boolean cursarAsignatura(Asignatura asignatura) {
        if (asignacion.get(asignatura) == NO_ASIGNADO)
            return false;
        else
            return true;
    }

    Integer getGrupo(Asignatura asignatura) {
        return asignacion.get(asignatura);
    }

    @Override
    public String toString() {
        return String.format("\n%-8s %-18s %-22s %-12s\t", dni, nombre, apellidos, email) + asignacion.toString();
    }
}