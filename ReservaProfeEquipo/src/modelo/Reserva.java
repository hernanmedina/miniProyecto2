/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.Date;

/**
 *
 * @author Hernan Medina
 */
public class Reserva {
    private Profesor profesor;
    private Equipo equipo;
    private Date fechaReserva;
    private Date horaRecogida;
    private Date horaEntrega;

    // Constructor
    public Reserva(Profesor profesor, Equipo equipo, Date fechaReserva, Date horaRecogida, Date horaEntrega) {
        this.profesor = profesor;
        this.equipo = equipo;
        this.fechaReserva = fechaReserva;
        this.horaRecogida = horaRecogida;
        this.horaEntrega = horaEntrega;
    }

    // Getters
    public Profesor getProfesor() {
        return profesor;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public Date getHoraRecogida() {
        return horaRecogida;
    }

    public Date getHoraEntrega() {
        return horaEntrega;
    }
}

