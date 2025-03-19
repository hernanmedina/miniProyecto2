package control;

import vista.Menu_ReservaGUI;
import modelo.Profesor;
import modelo.Equipo;
import modelo.Reserva;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

public class ControlMenuReservasGUI implements ActionListener {
    private Menu_ReservaGUI vistaMenu;
    private List<Profesor> docentes;
    private List<Equipo> equipos;
    private List<Reserva> reservas;

    public ControlMenuReservasGUI(List<Profesor> docentes, List<Equipo> equipos, List<Reserva> reservas) {
        this.docentes = docentes;
        this.equipos = equipos;
        this.reservas = reservas;
        this.vistaMenu = new Menu_ReservaGUI();
        
        this.vistaMenu.setVisible(true);
        this.vistaMenu.jBtn_Registrar_Profesor.addActionListener(this);
        this.vistaMenu.jBtn_Registrar_Equipo.addActionListener(this);
        this.vistaMenu.jBtn_Registra_reserva.addActionListener(this);
        this.vistaMenu.jBtn_Listar_Reserva.addActionListener(this);
        this.vistaMenu.jBtn_buscar_profesor.addActionListener(this);
        this.vistaMenu.jBtn_buscar_Equipo.addActionListener(this);
        this.vistaMenu.jBtn_buscar_reserva.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistaMenu.jBtn_Registrar_Profesor) {
            new ControlRegistroProfesorGUI(docentes);
        } else if (e.getSource() == this.vistaMenu.jBtn_Registrar_Equipo) {
            new ControlRegistrarEquipoGUI(equipos);
        } else if (e.getSource() == this.vistaMenu.jBtn_Registra_reserva) {
            new ControlRegistrarReservaGUI(reservas, docentes, equipos);
        } else if (e.getSource() == this.vistaMenu.jBtn_Listar_Reserva) {
            new ControlMostrarReservaGUI(reservas);
        } else if (e.getSource() == this.vistaMenu.jBtn_buscar_profesor) {
            buscarProfesor();
        } else if (e.getSource() == this.vistaMenu.jBtn_buscar_Equipo) {
            buscarEquipo();
        } else if (e.getSource() == this.vistaMenu.jBtn_buscar_reserva) {
            buscarReserva();
        }
    }
    
    private void buscarProfesor() {
        try {
            int cedula = Integer.parseInt(vistaMenu.jTf_Profersor.getText());
            for (Profesor profesor : docentes) {
                if (profesor.getCedula() == cedula) {
                    JOptionPane.showMessageDialog(vistaMenu, "Profesor encontrado: " + profesor.getNombre() + " " + profesor.getApellido());
                    return;
                }
            }
            JOptionPane.showMessageDialog(vistaMenu, "Profesor no encontrado.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaMenu, "Error: Ingrese una cédula válida.");
        }
    }

    private void buscarEquipo() {
        try {
            int numInventario = Integer.parseInt(vistaMenu.jTf_Equipo.getText());
            for (Equipo equipo : equipos) {
                if (equipo.getNumeroInventario() == numInventario) {
                    JOptionPane.showMessageDialog(vistaMenu, "Equipo encontrado: " + equipo.getMarca());
                    return;
                }
            }
            JOptionPane.showMessageDialog(vistaMenu, "Equipo no encontrado.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaMenu, "Error: Ingrese un número de inventario válido.");
        }
    }

    private void buscarReserva() {
        try {
            int numInventario = Integer.parseInt(vistaMenu.jTf_Reserva.getText());
            for (Reserva reserva : reservas) {
                if (reserva.getEquipo().getNumeroInventario() == numInventario) {
                    JOptionPane.showMessageDialog(vistaMenu, "Reserva encontrada para el equipo " + numInventario);
                    return;
                }
            }
            JOptionPane.showMessageDialog(vistaMenu, "No se encontraron reservas para este equipo.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaMenu, "Error: Ingrese un número de inventario válido.");
        }
    }
}
