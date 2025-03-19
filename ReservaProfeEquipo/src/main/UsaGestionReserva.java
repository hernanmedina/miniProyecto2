package main;

import control.ControlMenuReservasGUI;
import control.ControlMostrarReservaGUI;
import control.ControlRegistrarEquipoGUI;
import control.ControlRegistrarReservaGUI;
import control.ControlRegistroProfesorGUI;

import modelo.Profesor;
import modelo.Equipo;
import modelo.Reserva;
import java.util.ArrayList;
import java.util.List;

public class UsaGestionReserva {
    public static void main(String[] args) {
        // Crear listas compartidas
        List<Profesor> docentes = new ArrayList<>();
        List<Equipo> equipos = new ArrayList<>();
        List<Reserva> reservas = new ArrayList<>();

        // Inicializar los controladores con las listas
        //new ControlRegistrarReservaGUI(reservas, docentes, equipos);
        //new ControlRegistroProfesorGUI(docentes);
        //new ControlRegistrarEquipoGUI(equipos);
        new ControlMenuReservasGUI(docentes, equipos, reservas);
    }
}
