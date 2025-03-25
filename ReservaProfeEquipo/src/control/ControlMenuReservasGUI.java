package control;

import vista.Menu_ReservaGUI;
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
import vista.RegistroEquipoGUI;
import vista.RegistroProfesorGUI;
import vista.RegistroReservaGUI;

public class ControlMenuReservasGUI implements ActionListener {
    private Menu_ReservaGUI vistaMenu;
    private RegistroReservaGUI vistaReserva;
    private List<Profesor> docentes;
    private List<Equipo> equipos;
    private List<Reserva> reservas;

    public ControlMenuReservasGUI(List<Profesor> docentes, List<Equipo> equipos, List<Reserva> reservas) {
        this.docentes = docentes;
        this.equipos = equipos;
        this.reservas = reservas;
        
        if (this.vistaMenu == null) {
        this.vistaMenu = new Menu_ReservaGUI();
        }
        
        this.vistaMenu.setVisible(true);
        this.vistaMenu.jBtn_Registrar_Profesor.addActionListener(this);
        this.vistaMenu.jBtn_Registrar_Equipo.addActionListener(this);
        this.vistaMenu.jBtn_Registra_reserva.addActionListener(this);
        this.vistaMenu.jBtn_Listar_Reserva.addActionListener(this);
        this.vistaMenu.jBtn_buscar_profesor.addActionListener(this);
        this.vistaMenu.jBtn_buscar_Equipo.addActionListener(this);
        this.vistaMenu.jBtn_buscar_reserva.addActionListener(this);
        this.vistaMenu.jbtn_modificar_reserva.addActionListener(this);
        this.vistaMenu.jbtn_eliminar_reserva.addActionListener(this);
        this.vistaMenu.jbtn_modificar_Profesor.addActionListener(this);
        this.vistaMenu.jbtn_eliminar_profesor.addActionListener(this);
        this.vistaMenu.jbtn_modificar_equipo.addActionListener(this);
        this.vistaMenu.jbtn_eliminar_equipo.addActionListener(this);




        

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
        }else if (e.getSource() == this.vistaMenu.jbtn_modificar_reserva) {
            abrirVistaModificarReserva();
        }else if (e.getSource()== this.vistaMenu.jbtn_eliminar_reserva){
            eliminarReserva();
        }else if (e.getSource() == this.vistaMenu.jbtn_modificar_Profesor) {
            abrirVistaModificarProfesor();
        }else if (e.getSource() == this.vistaMenu.jbtn_eliminar_profesor) {
            eliminarProfesor();
        }else if (e.getSource() == this.vistaMenu.jbtn_modificar_equipo) {
            abrirVistaModificarEquipo();
        }else if (e.getSource() == this.vistaMenu.jbtn_eliminar_equipo) {
            eliminarEquipo();
        }



    }
    
    private void buscarProfesor() {
        try {
            int cedula = Integer.parseInt(vistaMenu.jTf_Profersor.getText());
            for (Profesor profesor : docentes) {
                if (profesor.getCedula() == cedula) {
                    JOptionPane.showMessageDialog(vistaMenu, "Profesor encontrado: " + profesor.getNombre() + " " + profesor.getApellido() + "\ncurso dictado: " + profesor.getCurso());
                    return;
                }
            }
            JOptionPane.showMessageDialog(vistaMenu, "Profesor no encontrado.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaMenu, "Error: Ingrese una cedula valida.");
        }
    }

    private void buscarEquipo() {
        try {
            int numInventario = Integer.parseInt(vistaMenu.jTf_Equipo.getText());
            for (Equipo equipo : equipos) {
                if (equipo.getNumeroInventario() == numInventario) {
                    JOptionPane.showMessageDialog(vistaMenu, "Equipo encontrado: " + equipo.getMarca() + "\n año de compra: " + equipo.getAnoCompra());
                    return;
                }
            }
            JOptionPane.showMessageDialog(vistaMenu, "Equipo no encontrado.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaMenu, "Error: Ingrese un numero de inventario valido.");
        }
    }
    
    private void modificarReserva() {
        try {
            int numInventario = Integer.parseInt(vistaMenu.jTf_Reserva.getText());

            for (Reserva reserva : reservas) {
                if (reserva.getEquipo().getNumeroInventario() == numInventario) {
                    // Mostrar valores actuales en la GUI
                    vistaMenu.jTf_Profersor.setText(String.valueOf(reserva.getProfesor().getCedula()));

                    // Preguntar si se quiere modificar
                    int confirm = JOptionPane.showConfirmDialog(vistaMenu, "¿Desea modificar esta reserva?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        int nuevaCedula = Integer.parseInt(vistaMenu.jTf_Profersor.getText());

                        // Buscar al nuevo profesor
                        Profesor nuevoProfesor = null;
                        for (Profesor prof : docentes) {
                            if (prof.getCedula() == nuevaCedula) {
                                nuevoProfesor = prof;
                                break;
                            }
                        }

                        if (nuevoProfesor == null) {
                            JOptionPane.showMessageDialog(vistaMenu, "Profesor no encontrado.");
                            return;
                        }

                        // Actualizar la reserva
                        reserva.setProfesor(nuevoProfesor);
                        JOptionPane.showMessageDialog(vistaMenu, "Reserva modificada con exito.");
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(vistaMenu, "No se encontraron reservas para este equipo.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaMenu, "Error: Ingrese datos validos.");
        }
    }


    private void eliminarReserva() {
        try {
            int numInventario = Integer.parseInt(vistaMenu.jTf_Reserva.getText());

            // Buscar la reserva en la lista
            Reserva reservaAEliminar = null;
            for (Reserva reserva : reservas) {
                if (reserva.getEquipo().getNumeroInventario() == numInventario) {
                    reservaAEliminar = reserva;
                    break;
                }
            }

            // Si se encontro la reserva, confirmamos antes de eliminar
            if (reservaAEliminar != null) {
                int confirm = JOptionPane.showConfirmDialog(vistaMenu, 
                    "¿Esta seguro de que desea eliminar esta reserva?", 
                    "Confirmar Eliminacion", 
                    JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    reservas.remove(reservaAEliminar);
                    JOptionPane.showMessageDialog(vistaMenu, "Reserva eliminada con exito.");
                }
            } else {
                JOptionPane.showMessageDialog(vistaMenu, "No se encontro ninguna reserva con ese numero de inventario.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaMenu, "Error: Ingrese un numero de inventario valido.");
        }
    }


        private void abrirVistaModificarReserva() {
        try {
            int numInventario = Integer.parseInt(vistaMenu.jTf_Reserva.getText());

            for (Reserva reserva : reservas) {
                if (reserva.getEquipo().getNumeroInventario() == numInventario) {
                    // Abrir la ventana de modificacion
                    RegistroReservaGUI ventanaModificar = new RegistroReservaGUI();

                    // Cargar los datos actuales de la reserva
                    ventanaModificar.jtf_cedulaReserva.setText(String.valueOf(reserva.getProfesor().getCedula()));
                    ventanaModificar.jtf_numInventarioReserva.setText(String.valueOf(reserva.getEquipo().getNumeroInventario()));
                    ventanaModificar.jtf_fechaRecogida.setText(reserva.getHoraRecogida().toString());
                    ventanaModificar.jtf_fechaEntrega.setText(reserva.getHoraEntrega().toString());

                    // Accion para guardar los cambios
                    ventanaModificar.jbtn_reserva.setText("Actualizar Reserva");
                    ventanaModificar.jbtn_reserva.addActionListener(ev -> {
                    try {
                        guardarCambiosReserva(reserva, ventanaModificar);
                        } catch (ParseException ex) {
                            JOptionPane.showMessageDialog(ventanaModificar, "Error: Ingrese una fecha valida en formato dd/MM/yyyy.");
                        }
                    });


                    ventanaModificar.setVisible(true);
                    return;
                }
            }
            JOptionPane.showMessageDialog(vistaMenu, "No se encontraron reservas para este equipo.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaMenu, "Error: Ingrese un numero de inventario valido.");
        }
    }

        private void guardarCambiosReserva(Reserva reserva, RegistroReservaGUI ventanaModificar) throws ParseException {
        try {
            int nuevaCedula = Integer.parseInt(ventanaModificar.jtf_cedulaReserva.getText());
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date nuevaFechaRecogida = formato.parse(ventanaModificar.jtf_fechaRecogida.getText());
            Date nuevaFechaEntrega = formato.parse(ventanaModificar.jtf_fechaEntrega.getText());

            // Buscar el nuevo profesor
            Profesor nuevoProfesor = null;
            for (Profesor prof : docentes) {
                if (prof.getCedula() == nuevaCedula) {
                    nuevoProfesor = prof;
                    break;
                }
            }

            if (nuevoProfesor == null) {
                JOptionPane.showMessageDialog(ventanaModificar, "Profesor no encontrado.");
                return;
            }

            // Actualizar los datos de la reserva
            reserva.setProfesor(nuevoProfesor);
            reserva.setHoraRecogida(nuevaFechaRecogida);
            reserva.setHoraEntrega(nuevaFechaEntrega);

            JOptionPane.showMessageDialog(ventanaModificar, "Reserva modificada con exito.");
            ventanaModificar.dispose();  // Cerrar la ventana después de actualizar
        } catch (NumberFormatException | ParseException ex) {
            JOptionPane.showMessageDialog(ventanaModificar, "Error: Ingrese datos validos.");
        }
    }

        private void abrirVistaModificarProfesor() {
        try {
            int cedula = Integer.parseInt(vistaMenu.jTf_Profersor.getText());

            for (Profesor profesor : docentes) {
                if (profesor.getCedula() == cedula) {
                    RegistroProfesorGUI ventanaModificar = new RegistroProfesorGUI();

                    // Cargar datos actuales
                    ventanaModificar.jtf_nombre.setText(profesor.getNombre());
                    ventanaModificar.jtf_apellido.setText(profesor.getApellido());
                    ventanaModificar.jtf_cedula.setText(String.valueOf(profesor.getCedula()));
                    ventanaModificar.jtf_curso.setText(profesor.getCurso());

                    // Acción para guardar cambios
                    ventanaModificar.jbtn_registroDocente.setText("Actualizar Profesor");
                    ventanaModificar.jbtn_registroDocente.addActionListener(ev -> guardarCambiosProfesor(profesor, ventanaModificar));

                    ventanaModificar.setVisible(true);
                    return;
                }
            }
            JOptionPane.showMessageDialog(vistaMenu, "No se encontró un profesor con esa cédula.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaMenu, "Error: Ingrese una cédula válida.");
        }
    }

        private void guardarCambiosProfesor(Profesor profesor, RegistroProfesorGUI ventanaModificar) {
        try {
            profesor.setNombre(ventanaModificar.jtf_nombre.getText());
            profesor.setApellido(ventanaModificar.jtf_apellido.getText());
            profesor.setCurso(ventanaModificar.jtf_curso.getText());

            JOptionPane.showMessageDialog(ventanaModificar, "Profesor modificado con éxito.");
            ventanaModificar.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(ventanaModificar, "Error: No se pudieron guardar los cambios.");
        }
    }

        private void eliminarProfesor() {
        try {
            int cedula = Integer.parseInt(vistaMenu.jTf_Profersor.getText());

            Profesor profesorAEliminar = null;
            for (Profesor profesor : docentes) {
                if (profesor.getCedula() == cedula) {
                    profesorAEliminar = profesor;
                    break;
                }
            }

            if (profesorAEliminar != null) {
                int confirm = JOptionPane.showConfirmDialog(vistaMenu, "¿Está seguro de eliminar este profesor?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    docentes.remove(profesorAEliminar);
                    JOptionPane.showMessageDialog(vistaMenu, "Profesor eliminado con éxito.");
                }
            } else {
                JOptionPane.showMessageDialog(vistaMenu, "No se encontró un profesor con esa cédula.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaMenu, "Error: Ingrese una cédula válida.");
        }
    }

        private void abrirVistaModificarEquipo() {
        try {
            int numInventario = Integer.parseInt(vistaMenu.jTf_Equipo.getText());

            for (Equipo equipo : equipos) {
                if (equipo.getNumeroInventario() == numInventario) {
                    RegistroEquipoGUI ventanaModificar = new RegistroEquipoGUI();

                    // Cargar datos actuales
                    ventanaModificar.jtf_numInventario.setText(String.valueOf(equipo.getNumeroInventario()));
                    ventanaModificar.jtf_marca.setText(equipo.getMarca());
                    ventanaModificar.jtf_anioCompra.setText(String.valueOf(equipo.getAnoCompra()));

                    // Acción para guardar cambios
                    ventanaModificar.jbtn_registrarEquipo.setText("Actualizar Equipo");
                    ventanaModificar.jbtn_registrarEquipo.addActionListener(ev -> guardarCambiosEquipo(equipo, ventanaModificar));

                    ventanaModificar.setVisible(true);
                    return;
                }
            }
            JOptionPane.showMessageDialog(vistaMenu, "No se encontró un equipo con ese número de inventario.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaMenu, "Error: Ingrese un número de inventario válido.");
        }
    }

        private void guardarCambiosEquipo(Equipo equipo, RegistroEquipoGUI ventanaModificar) {
        try {
            equipo.setMarca(ventanaModificar.jtf_marca.getText());
            equipo.setAnoCompra(Integer.parseInt(ventanaModificar.jtf_anioCompra.getText()));

            JOptionPane.showMessageDialog(ventanaModificar, "Equipo modificado con éxito.");
            ventanaModificar.dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(ventanaModificar, "Error: Ingrese un año válido.");
        }
    }

        private void eliminarEquipo() {
        try {
            int numInventario = Integer.parseInt(vistaMenu.jTf_Equipo.getText());

            Equipo equipoAEliminar = null;
            for (Equipo equipo : equipos) {
                if (equipo.getNumeroInventario() == numInventario) {
                    equipoAEliminar = equipo;
                    break;
                }
            }

            if (equipoAEliminar != null) {
                int confirm = JOptionPane.showConfirmDialog(vistaMenu, "¿Está seguro de eliminar este equipo?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    equipos.remove(equipoAEliminar);
                    JOptionPane.showMessageDialog(vistaMenu, "Equipo eliminado con éxito.");
                }
            } else {
                JOptionPane.showMessageDialog(vistaMenu, "No se encontró un equipo con ese número de inventario.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaMenu, "Error: Ingrese un número de inventario válido.");
        }
    }


    private void buscarReserva() {
        try {
            int numInventario = Integer.parseInt(vistaMenu.jTf_Reserva.getText());
            for (Reserva reserva : reservas) {
                if (reserva.getEquipo().getNumeroInventario() == numInventario) {
                    JOptionPane.showMessageDialog(vistaMenu, "Reserva encontrada para el equipo " + numInventario + " Equipo asignado al profesor: " + reserva.getProfesor().getNombre());
                    return;
                }
            }
            JOptionPane.showMessageDialog(vistaMenu, "No se encontraron reservas para este equipo.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaMenu, "Error: Ingrese un numero de inventario valido.");
        }
    }
}
