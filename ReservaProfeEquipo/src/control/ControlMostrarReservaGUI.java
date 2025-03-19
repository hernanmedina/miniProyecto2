/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

/**
 *
 * @author Hernan Medina
 */


import vista.ListarReservasGUI;
import modelo.Reserva;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

public class ControlMostrarReservaGUI implements ActionListener {
    private ListarReservasGUI vistaListarReservas;
    private List<Reserva> reservas;

    public ControlMostrarReservaGUI(List<Reserva> reservas) {
        this.reservas = reservas;
        this.vistaListarReservas = new ListarReservasGUI();
        
        this.vistaListarReservas.setVisible(true);
        this.vistaListarReservas.jButton1.addActionListener(this);
        mostrarReservas();
    }

    private void mostrarReservas() {
        StringBuilder lista = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        for (Reserva r : reservas) {
            lista.append("Profesor: ").append(r.getProfesor().getNombre()).append(" ")
                 .append(r.getProfesor().getApellido()).append("\n")
                 .append("Equipo: ").append(r.getEquipo().getMarca()).append(" (Inv. ")
                 .append(r.getEquipo().getNumeroInventario()).append(")\n")
                 .append("Recogida: ").append(sdf.format(r.getHoraRecogida())).append("\n")
                 .append("Entrega: ").append(sdf.format(r.getHoraEntrega())).append("\n\n");
        }
        
        if (reservas.isEmpty()) {
            lista.append("No hay reservas registradas.");
        }
        
        this.vistaListarReservas.jTextArea1.setText(lista.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistaListarReservas.jButton1) {
            this.vistaListarReservas.dispose();
        }
    }
}
