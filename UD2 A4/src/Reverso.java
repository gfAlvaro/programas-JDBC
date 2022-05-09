import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;

public class Reverso {
	    
    public static void main(String[] args){
    	
    	String db = "HLC";
    	String login = "alvaro";
        String password = "macarrones";
        String url = "jdbc:mysql://127.0.0.1:3306/" + db;	
    	
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");			
            Connection conexion = DriverManager.getConnection(url, login, password);

            if( conexion != null ) {
                Statement st = conexion.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.FETCH_REVERSE );
                System.out.println( "Conexion a base de datos " + db + " correcta." );
                ResultSet rs = st.executeQuery("SELECT * FROM ALUMNOS;");
    			rs.afterLast();
                System.out.println("tabla ALUMNOS");
                while( rs.previous() ) {
                    System.out.println( "Nombre: " + rs.getString("nombre")
                    				  + " Curso: " + rs.getInt("curso")
                    				  + " Nota media: " + rs.getInt("notaMedia") );
                }
            } else
                System.out.println("Conexion fallida.");

	    } catch ( SQLTimeoutException e )    { System.out.print("Error de tiempo de conexion."); }
	      catch ( SQLException e )           { e.printStackTrace(); }
	      catch ( ClassNotFoundException e ) { e.printStackTrace(); }
	      catch ( Exception e )              { e.printStackTrace (); }
    }
}

