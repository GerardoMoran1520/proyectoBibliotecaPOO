package Modelo;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class Conexion {

    private Connection conexion;
    private boolean transaccionIniciada;

    protected Connection getConexion() {
        return conexion;
    }

    protected void conectar(boolean wTransaccion) throws Exception {
        MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
        ds.setServerName("localhost");
        ds.setPort(3306);
        ds.setDatabaseName("pooproyecto1");
        conexion = ds.getConnection("root", "root");

        if (wTransaccion == true) {
            this.conexion.setAutoCommit(false);
            this.transaccionIniciada = true;
        } else {
            this.conexion.setAutoCommit(true);
            this.transaccionIniciada = false;
        }
    }

    protected void cerrar(boolean wEstado) throws Exception {
        if (this.conexion != null) {
            if (this.transaccionIniciada == true) {
                try {
                    if (wEstado == true) {
                        this.conexion.commit();
                    } else {
                        this.conexion.rollback();
                    }
                } catch (Exception e) {
                    throw e;
                }
            }
            try {
                this.conexion.close();
            } catch (Exception e) {
            }
        }
        this.conexion = null;
    }

    protected void ejecutarOrden(String wSQL) throws Exception {
        Statement st;

        if (this.conexion != null) {
            st = this.conexion.createStatement();
            st.executeUpdate(wSQL);
        }
    }

    protected ResultSet ejecutarOrdenDatos(String wSQL) throws Exception {
        Statement st;
        ResultSet rs = null;

        if (this.conexion != null) {
            st = this.conexion.createStatement();
            rs = st.executeQuery(wSQL);
        }

        return rs;
    }

   

}
