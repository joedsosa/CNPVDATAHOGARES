package ine.gob.hn.vaciadolistener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
public class Mysql {
    // Usado por MainCNPV.java: credenciales explícitas por conexión (ircas / cnpv_data)
    public Connection conectarMySQL(final String ip, final String db, final String user, final String pass) throws ClassNotFoundException {
        Connection con = null;
        String sURL = "jdbc:mysql://" + ip + "/" + db 
            + "?allowPublicKeyRetrieval=true"
            + "&useSSL=false"
            + "&autoReconnect=true"
            + "&maxReconnects=5"
            + "&initialTimeout=2"
            + "&serverTimezone=UTC"
            + "&socketTimeout=120000"
            + "&connectTimeout=120000"
            + "&maxQuerySizeToLog=256";
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(sURL, user, pass);
            return con;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    // Usado por DatabaseUtils.java y ManagerSis.java (modulo can_db): mantiene credenciales previas
    public Connection conectarMySQL(final String ip, final String db) throws ClassNotFoundException {
        return conectarMySQL(ip, db, "cnpv_m", "INE2025_gti!");
    }
}