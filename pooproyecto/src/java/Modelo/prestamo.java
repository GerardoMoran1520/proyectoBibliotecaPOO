/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;

/**
 *
 * @author drago
 */
public class prestamo {
    private int idPrestamo;
    private int idUsuario;
    private int idDocumento;
    private String fechaPrestamo;
    private String fechaDevolucion;      
    private double mora;
    private documento titulo;
    private usuario nombreUsuario;
    private boolean estado;
    // Getters y setters

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
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

    public double getMora() {
        return mora;
    }

    public void setMora(double mora) {
        this.mora = mora;
    }

    public documento getTitulo() {
        return titulo;
    }

    public void setTitulo(documento titulo) {
        this.titulo = titulo;
    }

    public usuario getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(usuario nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    

    
}