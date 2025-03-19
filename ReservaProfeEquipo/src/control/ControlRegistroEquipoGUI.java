/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Equipo;
import vista.RegistroEquipoGUI;

/**
 *
 * @author Hernan Medina
 */
public class ControlRegistroEquipoGUI implements ActionListener{
    private RegistroEquipoGUI vistaRegistroEquipo;
    private Equipo equipo;
    
    public ControlRegistroEquipoGUI(){
        
        this.vistaRegistroEquipo= new RegistroEquipoGUI();
        this.vistaRegistroEquipo.setVisible(true);
        
        this.vistaRegistroEquipo.jbtn_registrarEquipo.addActionListener(this);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.vistaRegistroEquipo.jbtn_registrarEquipo){
            javax.swing.JOptionPane.showMessageDialog(vistaRegistroEquipo, "Registro de Equipo Exitoso.");
        }
    }
}
