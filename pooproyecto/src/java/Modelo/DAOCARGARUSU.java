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

public class DAOCARGARUSU extends Conexion {

    public List<usuario> listarUsuarios() throws Exception {
        List<usuario> usuarios;
        usuario usuari;
        ResultSet rs = null;
        String sql = "SELECT U.IDUSUARIO, U.NOMBREUSUARIO FROM USUARIO U "
                + "ORDER BY U.IDUSUARIO";

        try {
            this.conectar(false);
            rs = this.ejecutarOrdenDatos(sql);
            usuarios = new ArrayList<>();
            while (rs.next() == true) {
                usuari = new usuario();
                usuari. setId_usuario(rs.getInt("IDUSUARIO"));
                usuari.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
                usuarios.add(usuari);
            }
            this.cerrar(true);
        } catch (Exception e) {
            throw e;
        } finally {
        }
        return usuarios;
    }
}