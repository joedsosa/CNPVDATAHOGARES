package ine.gob.hn.vaciadolistener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class DatabaseUtils {
    //private static final String URL = "jdbc:postgresql://192.168.108.15:5432/SurveySolutions";
    private static final String URL = "jdbc:postgresql://localhost:5432/SurveySolutions";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Survey2023!";

    public static boolean verificarArchivoEnBaseDatos(String nombreArchivo) throws ClassNotFoundException {
        boolean existe = false;
        String query = "SELECT COUNT(*) FROM ws_canprod.mapbrowseitems WHERE id = '" + nombreArchivo + "'"; 
        //System.out.println(query);
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                existe = resultSet.getInt(1) > 0; // Si la cuenta es mayor que 0, el archivo existe
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return existe;
    }
    
    public static boolean insertarArchivoEnBaseDatos(String filename, long size) throws ClassNotFoundException {
        boolean insertado = false;
        
        // Consulta SQL de inserción
        String query = "INSERT INTO ws_canprod.mapbrowseitems (id, size, importdate, filename, wkid, xmaxval, xminval, ymaxval, yminval, maxscale, minscale, uploadedby, ispreviewgeojson, geojson) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Establecer los valores para cada parámetro de la consulta
            preparedStatement.setString(1, filename); // id
            preparedStatement.setLong(2, size); // size
            preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setString(4, filename); // filename
            preparedStatement.setInt(5, 102100); // wkid
            preparedStatement.setDouble(6, -9517702.6706); // xmaxval
            preparedStatement.setDouble(7, -9712687.1837); // xminval
            preparedStatement.setDouble(8, 1631612.0536); // ymaxval
            preparedStatement.setDouble(9, 1507225.5893); // yminval
            preparedStatement.setInt(10, 0); // maxscale
            preparedStatement.setInt(11, 0); // minscale
            preparedStatement.setObject(12, UUID.fromString("0398cc7a-8d45-44b4-b6d3-092633096590"));
            preparedStatement.setBoolean(13, false); // ispreviewgeojson
            preparedStatement.setString(14, "b"); // geojson

            // Ejecutar la consulta
            int filasAfectadas = preparedStatement.executeUpdate();
            insertado = filasAfectadas > 0; // Si se insertaron filas, el archivo fue insertado correctamente

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return insertado;
    }
    
    public static void insertProcessMaps(final String process_uuid,final int num_tpk_procesados,final int num_tpk_nuevos) throws SQLException, ClassNotFoundException{
        Mysql mysql=new Mysql();
        Connection con = null;;
        String db="can_db";
        con = mysql.conectarMySQL("",db);
        con.setAutoCommit(false);
        try {
            
            String queryFinal="INSERT INTO process_maps(process_uuid,num_tpk_procesados,num_tpk_nuevos) VALUES('"+process_uuid+"',"+num_tpk_procesados+","+num_tpk_nuevos+")";
            System.out.println(queryFinal);
            PreparedStatement stmt2 = con.prepareStatement(queryFinal);
            stmt2.executeUpdate();
            
            con.commit();
            con.close();
        } catch (SQLException e) {
            con.rollback();
        }       
    }
    
    public static void insertMap(final String process_uuid,final String nombre_archivo) throws SQLException, ClassNotFoundException{
        Mysql mysql=new Mysql();
        Connection con = null;;
        String db="can_db";
        con = mysql.conectarMySQL("",db);
        con.setAutoCommit(false);
        try {
            
            String queryFinal="INSERT INTO archivos_subidos(nombre_archivo,fk_process) VALUES('"+nombre_archivo+"','"+process_uuid+"')";
            System.out.println(queryFinal);
            PreparedStatement stmt2 = con.prepareStatement(queryFinal);
            stmt2.executeUpdate();
            
            con.commit();
            con.close();
        } catch (SQLException e) {
            con.rollback();
        }       
    }
    
}
