package listado;

import java.util.HashMap;

public class Alumno {
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
    }

    boolean cursarAsignatura(Asignatura asignatura) {
        return false;
    }
}