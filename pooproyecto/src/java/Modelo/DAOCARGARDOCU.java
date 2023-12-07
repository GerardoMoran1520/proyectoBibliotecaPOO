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
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class DAOCARGARDOCU extends Conexion {

    public List<documento> listarDocumentos() throws Exception {
        List<documento> documentos;
        documento documen;
        ResultSet rs = null;
        String sql = "SELECT D.IDDOCUMENTO , D.TITULO, D.ESTADO FROM documentos D " 
                + "ORDER BY D.IDDOCUMENTO";

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            documentos = new ArrayList<>();
            while (rs.next() == true) {
                documen = new documento();
                documen. setIdDocumento(rs.getInt("IDDOCUMENTO"));
                documen.setTitulo(rs.getString("TITULO"));
                documen.setEstado(rs.getBoolean("ESTADO"));
                documentos.add(documen);
            }
            this.cerrar(true);
        } catch (Exception e) {
            throw e;
        } finally {
        }
        return documentos;
    }
}
