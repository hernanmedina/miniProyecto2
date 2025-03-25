/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Hernan Medina
 */
public class Equipo {
    private int numeroInventario;
    private String marca;
    private int anoCompra;

    // Constructor vacío
    public Equipo() {}

    // Constructor con parámetros
    public Equipo(int numeroInventario, String marca, int anoCompra) {
        this.numeroInventario = numeroInventario;
        this.marca = marca;
        this.anoCompra = anoCompra;
    }

    // Getters
    public int getNumeroInventario() {
        return numeroInventario;
    }

    public String getMarca() {
        return marca;
    }

    public int getAnoCompra() {
        return anoCompra;
    }
    
    //Setters
    public void setNumeroInventario(int numeroInventario) {
        this.numeroInventario = numeroInventario;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setAnoCompra(int anoCompra) {
        this.anoCompra = anoCompra;
    }
}

