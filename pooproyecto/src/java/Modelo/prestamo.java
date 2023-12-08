/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;



/**
 *
 * @author drago
 */
public class prestamo {
    private int idPrestamo;
    private usuario nombreUsuario;
    private documento titulo;
    private String fechaPrestamo;
    private String fechaDevolucion; 
    private boolean estado;
    private double mora;
   
    // Getters y setters

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public usuario getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(usuario nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public documento getTitulo() {
        return titulo;
    }

    public void setTitulo(documento titulo) {
        this.titulo = titulo;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public double getMora() {
        return mora;
    }

    public void setMora(double mora) {
        this.mora = mora;
    }

   

    

    
}