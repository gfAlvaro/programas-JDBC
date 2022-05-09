import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;

public class ConsultaJDBC {
	    
    public static void main(String[] args){
    	
    	String db = "HLC";
    	String login = "alvaro";
        String password = "macarrones";
        String url = "jdbc:mysql://127.0.0.1:3306/" + db;	
        String query = "SELECT nombre FROM PROFESORES;";
    	
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");			
            Connection conexion = DriverManager.getConnection(url, login, password);

            if( conexion != null ) {
                Statement st = conexion.createStatement();
                System.out.println( "Conexion a base de datos " + db + " correcta." );
                ResultSet rs = st.executeQuery(query);
                System.out.println("tabla PROFESORES");
                System.out.println("-nombre-");

                while(rs.next()) {
                    String nombre = rs.getString("nombre");
                    System.out.println(nombre + " ");
                }
            } else
                System.out.println("Conexion fallida.");

	    } catch ( SQLTimeoutException e )    { System.out.print("Error de tiempo de conexion."); }
	      catch ( SQLException e )           { e.printStackTrace(); }
	      catch ( ClassNotFoundException e ) { e.printStackTrace(); }
	      catch ( Exception e )              { e.printStackTrace (); }
    }
}

