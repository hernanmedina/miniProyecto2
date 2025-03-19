/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Hernan Medina
 */
public class Profesor {
    private int cedula;
    private String nombre;
    private String apellido;
    private String curso;

    // Constructor vacío
    public Profesor() {}

    // Constructor con parámetros
    public Profesor(int cedula, String nombre, String apellido, String curso) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.curso = curso;
    }

    // Getters
    public int getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCurso() {
        return curso;
    }
}
