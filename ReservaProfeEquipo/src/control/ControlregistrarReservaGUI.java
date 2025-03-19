package control;

import vista.RegistroReservaGUI;
import modelo.Profesor;
import modelo.Equipo;
import modelo.Reserva;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class ControlRegistrarReservaGUI implements ActionListener {
    private RegistroReservaGUI vistaReserva;
    private List<Reserva> reservas;
    private List<Profesor> docentes;
    private List<Equipo> equipos;

    public ControlRegistrarReservaGUI(List<Reserva> reservas, List<Profesor> docentes, List<Equipo> equipos) {
        this.reservas = reservas;
        this.docentes = docentes;
        this.equipos = equipos;
        this.vistaReserva = new RegistroReservaGUI();
        
        this.vistaReserva.setVisible(true);
        this.vistaReserva.jbtn_reserva.addActionListener(this);
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
        if (e.getSource() == this.vistaReserva.jbtn_reserva) {
            try {
                int cedula = Integer.parseInt(vistaReserva.jtf_cedulaReserva.getText());
                int numInventario = Integer.parseInt(vistaReserva.jtf_numInventarioReserva.getText());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaRecogida = sdf.parse(vistaReserva.jtf_fechaRecogida.getText());
                Date fechaEntrega = sdf.parse(vistaReserva.jtf_fechaEntrega.getText());
                
                Profesor profesor = buscarDocente(cedula);
                Equipo equipo = buscarEquipo(numInventario);
                
                if (profesor == null) {
                    JOptionPane.showMessageDialog(vistaReserva, "Error: Profesor no encontrado.");
                    return;
                }
                if (equipo == null) {
                    JOptionPane.showMessageDialog(vistaReserva, "Error: Equipo no encontrado.");
                    return;
                }
                
                for (Reserva r : reservas) {
                    if (r.getEquipo().getNumeroInventario() == numInventario &&
                        fechaRecogida.before(r.getHoraEntrega()) && fechaEntrega.after(r.getHoraRecogida())) {
                        JOptionPane.showMessageDialog(vistaReserva, "Error: El equipo ya está reservado en ese horario.");
                        return;
                    }
                }
                
                Reserva nuevaReserva = new Reserva(profesor, equipo, new Date(), fechaRecogida, fechaEntrega);
                reservas.add(nuevaReserva);
                JOptionPane.showMessageDialog(vistaReserva, "¡Reserva registrada con éxito!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vistaReserva, "Error: Verifique los datos numéricos ingresados.");
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(vistaReserva, "Error: Formato de fecha incorrecto. Use dd/MM/yyyy");
            }
        }
    }
}
