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
public class DAODOCUMENTO extends Conexion {
    

      public List<documento> listarDocumentos() throws Exception {
        List<documento> documentos;
        documento docume;
        ResultSet rs = null;
        String sql = "SELECT IDDOCUMENTO , TIPODOCUMENTO, TITULO, AUTOR, UBICACIONFISICA, CANTIDADTOTAL,\n" +
                     "CANTIDADDISPONIBLE, ANIOPUBLICACION, ESTADO FROM documentos \n" +
                     "ORDER BY IDDOCUMENTO";

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            documentos = new ArrayList<>();
            while (rs.next() == true) {
                docume = new documento();
                docume.setIdDocumento(rs.getInt("IDDOCUMENTO"));
                docume.setTipoDocumento(rs.getString("TIPODOCUMENTO"));
                docume.setTitulo(rs.getString("TITULO"));
                docume.setAutor(rs.getString("AUTOR"));
                docume.setUbicacionFisica(rs.getString("UBICACIONFISICA"));
                docume.setCantidadTotal(rs.getInt("CANTIDADTOTAL"));
                docume.setCantidadDisponible(rs.getInt("CANTIDADDISPONIBLE"));
                docume.setAnioPublicacion(rs.getInt("ANIOPUBLICACION"));
                documentos.add(docume);
            }
            this.cerrar(true);
        } catch (Exception e) {
            throw e;
        } finally {
        }
        return documentos;
    }
      
    public void registrarDocumento(documento doc) throws Exception {
    String sql;
    sql = "INSERT INTO Documentos (TIPODOCUMENTO, TITULO, AUTOR, UBICACIONFISICA, CANTIDADTOTAL, CANTIDADDISPONIBLE, ANIOPUBLICACION) "
            + "VALUES ('" + doc.getTipoDocumento() + "', '"
            + doc.getTitulo() + "', '"
            + doc.getAutor() + "', '"
            + doc.getUbicacionFisica() + "', "
            + doc.getCantidadTotal() + ", "
            + doc.getCantidadDisponible() + ", "
            + doc.getAnioPublicacion() + ")";
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
