package control;

import vista.RegistroEquipoGUI;
import modelo.Equipo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

public class ControlRegistrarEquipoGUI implements ActionListener {
    private RegistroEquipoGUI vistaRegistroEquipo;
    private List<Equipo> equipos;

    public ControlRegistrarEquipoGUI(List<Equipo> equipos) {
        this.equipos = equipos;
        this.vistaRegistroEquipo = new RegistroEquipoGUI();
        
        this.vistaRegistroEquipo.setVisible(true);
        this.vistaRegistroEquipo.jbtn_registrarEquipo.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistaRegistroEquipo.jbtn_registrarEquipo) {
            try {
                int numInventario = Integer.parseInt(vistaRegistroEquipo.jtf_numInventario.getText());
                String marca = vistaRegistroEquipo.jtf_marca.getText();
                int anoCompra = Integer.parseInt(vistaRegistroEquipo.jtf_anioCompra.getText());
                
                for (Equipo equipo : equipos) {
                    if (equipo.getNumeroInventario() == numInventario) {
                        JOptionPane.showMessageDialog(vistaRegistroEquipo, "Error: El equipo ya está registrado.");
                        return;
                    }
                }
                
                Equipo nuevoEquipo = new Equipo(numInventario, marca, anoCompra);
                equipos.add(nuevoEquipo);
                JOptionPane.showMessageDialog(vistaRegistroEquipo, "¡Equipo registrado con éxito!");
                
                // Mostrar la lista de equipos en consola
                System.out.println("Lista de Equipos Registrados:");
                for (Equipo eq : equipos) {
                    System.out.println("Inventario: " + eq.getNumeroInventario() + ", Marca: " + eq.getMarca() + ", Año: " + eq.getAnoCompra());
                }
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vistaRegistroEquipo, "Error: Verifique los datos numéricos ingresados.");
            }
        }
    }
}
