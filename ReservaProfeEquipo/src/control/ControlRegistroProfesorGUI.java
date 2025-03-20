package control;

import modelo.Profesor;
import vista.RegistroProfesorGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

public class ControlRegistroProfesorGUI implements ActionListener {
    private RegistroProfesorGUI vistaRegistroProfesor;
    private List<Profesor> docentes;

    public ControlRegistroProfesorGUI(List<Profesor> docentes) {
        this.docentes = docentes;
        this.vistaRegistroProfesor = new RegistroProfesorGUI();

        this.vistaRegistroProfesor.setVisible(true);
        this.vistaRegistroProfesor.jbtn_registroDocente.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistaRegistroProfesor.jbtn_registroDocente) {
            try {
                int cedula = Integer.parseInt(vistaRegistroProfesor.jtf_cedula.getText());
                String nombre = vistaRegistroProfesor.jtf_nombre.getText();
                String apellido = vistaRegistroProfesor.jtf_apellido.getText();
                String curso = vistaRegistroProfesor.jtf_curso.getText();

                for (Profesor p : docentes) {
                    if (p.getCedula() == cedula) {
                        JOptionPane.showMessageDialog(vistaRegistroProfesor, "Error: Profesor ya registrado.");
                        return;
                    }
                }

                Profesor nuevoProfesor = new Profesor(cedula, nombre, apellido, curso);
                docentes.add(nuevoProfesor);
                JOptionPane.showMessageDialog(vistaRegistroProfesor, "¡Profesor registrado con exito!");
                
                 // Mostrar la lista de profesores en consola
                System.out.println("Lista de Equipos Registrados:");
                for (Profesor p : docentes) {
                    System.out.println("Cedula: " + p.getCedula() + ", Nombre: " + p.getNombre() + ", Apellido: " + p.getApellido() + ", Curso: " + p.getCurso());
                }
                vistaRegistroProfesor.jtf_cedula.setText("");
                vistaRegistroProfesor.jtf_nombre.setText("");
                vistaRegistroProfesor.jtf_apellido.setText("");
                vistaRegistroProfesor.jtf_curso.setText("");
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vistaRegistroProfesor, "Error: La cedula debe ser un numero.");
   
            }
        }
    }
}

