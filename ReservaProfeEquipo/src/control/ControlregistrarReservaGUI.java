/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package control;

/**
 *
 * @author Hernan Medina
 */

package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Profesor;
import modelo.Equipo;
import modelo.Reserva;
import vista.RegistroReservaGUI;

public class ControlRegistrarReservaGUI implements ActionListener {
    private RegistroReservaGUI vistaReservaGUI;
    private List<Reserva> reservas;
    private List<Profesor> docentes;
    private List<Equipo> equipos;

    public ControlRegistrarReservaGUI() {
        this.vistaReservaGUI = new RegistroReservaGUI();
        this.reservas = new ArrayList<>();
        this.docentes = new ArrayList<>();
        this.equipos = new ArrayList<>();

        this.vistaReservaGUI.setVisible(true);
        this.vistaReservaGUI.jbtn_reserva.addActionListener(this);
    }

    public void agregarProfesor(Profesor profesor) {
        docentes.add(profesor);
    }

    public void agregarEquipo(Equipo equipo) {
        equipos.add(equipo);
    }

    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public Profesor buscarDocente(int numCedula) {
        for (Profesor profesor : docentes) {
            if (profesor.getCedula() == numCedula) {
                return profesor;
            }
        }
        return null;
    }

    public Equipo buscarEquipo(int numeroInventario) {
        for (Equipo equipo : equipos) {
            if (equipo.getNumeroInventario() == numeroInventario) {
                return equipo;
            }
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistaReservaGUI.jbtn_reserva) {
            try {
                int cedula = Integer.parseInt(vistaReservaGUI.jtf_cedulaReserva.getText());
                int numInventario = Integer.parseInt(vistaReservaGUI.jtf_numInventarioReserva.getText());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                Date fechaRecogida = sdf.parse(vistaReservaGUI.jtf_fechaRecogida.getText());
                Date fechaEntrega = sdf.parse(vistaReservaGUI.jtf_fechaEntrega.getText());
                
                Profesor profesor = buscarDocente(cedula);
                Equipo equipo = buscarEquipo(numInventario);
                
                if (profesor == null) {
                    JOptionPane.showMessageDialog(vistaReservaGUI, "Error: Profesor no encontrado.");
                    return;
                }
                if (equipo == null) {
                    JOptionPane.showMessageDialog(vistaReservaGUI, "Error: Equipo no encontrado.");
                    return;
                }
                
                for (Reserva r : reservas) {
                    if (r.getEquipo().getNumeroInventario() == numInventario &&
                        fechaRecogida.before(r.getHoraEntrega()) && fechaEntrega.after(r.getHoraRecogida())) {
                        JOptionPane.showMessageDialog(vistaReservaGUI, "Error: El equipo ya está reservado en ese horario.");
                        return;
                    }
                }
                
                Reserva nuevaReserva = new Reserva(profesor, equipo, new Date(), fechaRecogida, fechaEntrega);
                agregarReserva(nuevaReserva);
                JOptionPane.showMessageDialog(vistaReservaGUI, "¡Reserva registrada con éxito!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vistaReservaGUI, "Error: Verifique los datos numéricos ingresados.");
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(vistaReservaGUI, "Error: Formato de fecha incorrecto. Use dd/MM/yyyy HH:mm");
            }
        }
    }
}

