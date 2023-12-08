/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author drago
 */
public class DAOPRESTAMO extends Conexion {
    

    

 
 
      public List<prestamo> listarPrestamos() throws Exception {
        List<prestamo> prestamos;
        prestamo presta;
        ResultSet rs = null;
        String sql = "SELECT P.IDPRESTAMO, U.NOMBREUSUARIO, D.IDDOCUMENTO, D.TITULO, P.FECHAPRESTAMO, P.FECHADEVOLUCION FROM prestamos P \n" +
             "INNER JOIN usuario U ON U.IDUSUARIO = P.IDUSUARIO \n" +
             "INNER JOIN documentos D ON D.IDDOCUMENTO = P.IDDOCUMENTO \n" +
             "ORDER BY P.IDPRESTAMO";

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            prestamos = new ArrayList<>();
            while (rs.next() == true) {
                presta = new prestamo();
                presta.setIdPrestamo(rs.getInt("IDPRESTAMO"));
                presta.setFechaPrestamo(rs.getString("FECHAPRESTAMO"));
                presta.setFechaDevolucion(rs.getString("FECHADEVOLUCION"));
                presta.setNombreUsuario(new usuario());
                presta.getNombreUsuario().setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                presta.setTitulo(new documento());
                presta.getTitulo().setTitulo(rs.getString("TITULO"));
                
                prestamos.add(presta);
            }
            this.cerrar(true);
        } catch (Exception e) {
            throw e;
        } finally {
        }
        return prestamos;
    }
      
   
   public void registrarPrestamo(prestamo presta) throws Exception {
    String sql;
    sql = "INSERT INTO prestamos (IDUSUARIO, IDDOCUMENTO, FECHAPRESTAMO, FECHADEVOLUCION) "
            + "VALUES ('"
            + presta.getNombreUsuario().getId_usuario() + "', "
            + presta.getTitulo().getIdDocumento() + ", '"
            + presta.getFechaPrestamo() + "', '"  
            + presta.getFechaDevolucion() + "')";

    try {
        this.conectar(false);
        this.ejecutarOrden(sql);
        this.cerrar(true);
    } catch (Exception e) {
        this.cerrar(false);
        throw e;
    }
}

    public void cambiarVigencia(prestamo prest) throws Exception {
        String sql = "UPDATE prestamos SET ESTADO = "
                + (prest.isEstado() == true ? "1" : "0")
                + " WHERE IDPRESTAMO = " + prest.getIdPrestamo();
        try {
            this.conectar(false);
            this.ejecutarOrden(sql);
            this.cerrar(true);
        } catch (Exception e) {
            this.cerrar(false);
            throw e;
        }
    }
  
}
