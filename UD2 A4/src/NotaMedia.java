import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;

public class NotaMedia {
	    
    public static void main(String[] args){
    	
    	String db = "HLC";
    	String login = "alvaro";
        String password_ = "macarrones";
        String url = "jdbc:mysql://127.0.0.1:3306/" + db;	
        String query = "SELECT * FROM ALUMNOS ORDER BY notaMedia;";
    	
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");			
            Connection conexion = DriverManager.getConnection(url, login, password_);

            if( conexion != null ) {
                Statement st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.FETCH_UNKNOWN);
                System.out.println( "Conexion a base de datos " + db + " correcta." );
                ResultSet rs = st.executeQuery(query);

                System.out.println("tabla ALUMNOS");
                while( rs.next() ) {
                    String nombre = rs.getString("nombre");
                    int notaMedia = rs.getInt("notaMedia");
                    int curso = rs.getInt("curso");
                    System.out.println("Nombre: " + nombre + ", curso: " + curso + ", nota media: " + notaMedia);
                }
                
                rs = st.executeQuery(query);    			
    			rs.absolute(1);
    			System.out.println( "Alumno con peor nota: " + rs.getString("nombre") + ", nota media "
    					+ rs.getFloat("notaMedia") + " en curso " + rs.getInt("curso") );
    			rs.last();
    			System.out.println( "Alumno mejor nota: " + rs.getString("nombre") + ", nota media "
    					+ rs.getFloat("notaMedia") + " en curso " + rs.getInt("curso") );

            } else
                System.out.println("Conexion fallida.");

	    } catch ( SQLTimeoutException e )    { System.out.print("Error de tiempo de conexion."); }
	      catch ( SQLException e )           { e.printStackTrace(); }
	      catch ( ClassNotFoundException e ) { e.printStackTrace(); }
	      catch ( Exception e )              { e.printStackTrace (); }
    }
}

