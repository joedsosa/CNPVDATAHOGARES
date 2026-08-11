
package ine.gob.hn.vaciadolistener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

public class ManagerSis {
    private final String db="can_db";
    public String saveInDb() throws ClassNotFoundException, SQLException{
        Mysql mysql=new Mysql();
        Connection con = mysql.conectarMySQL("",this.db);
        //String uuid=UUID.randomUUID().toString();
        String query="UPDATE actualizacion_datos SET fecha_actualizacion=NOW() WHERE id=1";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.executeUpdate();
        } catch (SQLException sqle) { 
          System.out.println("Error en la ejecucion:" 
        + sqle.getErrorCode() + " " + sqle.getMessage());    
        }finally{
            if(con!=null){
                con.close();
            }
        }
        return "OK";
    }
    
    public Object[] existeProcesoFinalizado() throws ClassNotFoundException, SQLException{
        Mysql mysql=new Mysql();
        Connection con = mysql.conectarMySQL("",this.db);
        String query="SELECT id,iniciado,finalizado FROM can_db.proceso_vaciado ORDER BY id DESC LIMIT 1;";
        Object[] r = new Object[3];
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                r[0] = rs.getInt("id");
        
                Timestamp iniciadoTimestamp = rs.getTimestamp("iniciado");
                if (iniciadoTimestamp != null) {
                    r[1] = iniciadoTimestamp.toLocalDateTime(); // Convertir a LocalDateTime
                }

                Timestamp finalizadoTimestamp = rs.getTimestamp("finalizado");
                if (finalizadoTimestamp != null) {
                    r[2] = finalizadoTimestamp.toLocalDateTime(); // Convertir a LocalDateTime
                }
            }
        } catch (SQLException sqle) { 
          System.out.println("Error en la ejecucion:" 
        + sqle.getErrorCode() + " " + sqle.getMessage());    
        }finally{
            if(con!=null){
                con.close();
            }
        }
        return r;
    }
}
