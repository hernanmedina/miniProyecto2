/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

/**
 *
 * @author Hernan Medina
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import modelo.Profesor;
import modelo.Equipo;
import modelo.Reserva;
import vista.RegistroReservaGUI;


public class ControlregistrarReservaGUI implements ActionListener{
    
    
    private RegistroReservaGUI vistaReservaGUI;
    private List<Reserva> reservas;
    private List<Profesor> docentes;
    private List<Equipo> equipos;


    // Constructor
    public ControlregistrarReservaGUI() {
        
        // Instanciamos la vista
        this.vistaReservaGUI = new RegistroReservaGUI();
        
        this.reservas = new ArrayList<>();
        this.docentes = new ArrayList<>();
        this.equipos = new ArrayList<>();
        
        this.vistaReservaGUI.setVisible(true);
        this.vistaReservaGUI.jbtn_reserva.addActionListener(this);
    }

    // Agregar un profesor
    public void agregarProfesor(Profesor profesor) {
        docentes.add(profesor);
    }

    // Agregar un equipo
    public void agregarEquipo(Equipo equipo) {
        equipos.add(equipo);
    }

    // Agregar una reserva
    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    // Listar todas las reservas
    public void listarReservas() {
        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
    }

    // Buscar un equipo por numero de inventario
    public Equipo buscarEquipo(int numeroInventario) {
        for (Equipo equipo : equipos) {
            if (equipo.getNumeroInventario() == numeroInventario) {
                return equipo;
            }
        }
        return null;
    }

    // Buscar un docente por cedula
    public Profesor buscarDocente(int numCedula) {
        for (Profesor profesor : docentes) {
            if (profesor.getCedula() == numCedula) {
                return profesor;
            }
        }
        return null;
    }
    
    // Metodo que atiende los eventos de los botones
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.vistaReservaGUI.jbtn_reserva){
            javax.swing.JOptionPane.showMessageDialog(vistaReservaGUI, "¡Solicitud Enviada!\n Gracias.");
        }
    }
}
