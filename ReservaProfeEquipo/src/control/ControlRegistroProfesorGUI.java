/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Profesor;
import vista.RegistroProfesorGUI;

/**
 *
 * @author Hernan Medina
 */
public class ControlRegistroProfesorGUI implements ActionListener{
    
    private RegistroProfesorGUI vistaRegistroProfesor;
    private Profesor profesor;
    
    public ControlRegistroProfesorGUI(){
        
        this.vistaRegistroProfesor= new RegistroProfesorGUI();
        this.vistaRegistroProfesor.setVisible(true);
        
        this.vistaRegistroProfesor.jbtn_registroDocente.addActionListener(this);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.vistaRegistroProfesor.jbtn_registroDocente){
            javax.swing.JOptionPane.showMessageDialog(vistaRegistroProfesor, "Registro de Docente Exitoso.");
        }
    }
}
