import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;

public class AtaqueSQLInjection {

	public static void main(String[] args) {
		String url = "jdbc:mysql://127.0.0.1:3306/HLC";
		String login = "alvaro";
		String password = "macarrones";
		try {
			Connection conexion = DriverManager.getConnection(url, login, password);
			
			// primero consultamos la tabla alumnos para ver su estado inicial
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT nombre FROM ALUMNOS");
            System.out.println("tabla ALUMNOS");
            while(rs.next()) {
                String nombre = rs.getString("nombre");
                System.out.println( "Nombre: " + nombre );    
            }
            
            // Hacemos un ataque SQLInjection
			st = conexion.createStatement();
			int nombresEliminados = st.executeUpdate("DELETE FROM ALUMNOS WHERE nombre='fernando' OR 1=1;");
			System.out.println( "Nombres eliminados: " + nombresEliminados );
			
			// Consultamos de nuevo la tabla alumnos
            st = conexion.createStatement();
            rs = st.executeQuery( "SELECT nombre FROM ALUMNOS" );
            System.out.println("tabla ALUMNOS");
            while(rs.next()) {
                String nombre = rs.getString("nombre");
                System.out.println( "Nombre: " + nombre );    
            }
            
			
	    } catch ( SQLTimeoutException e )    { System.out.print("Error de tiempo de conexion."); }
	      catch ( SQLException e )           { e.printStackTrace(); }
	      catch ( Exception e )              { e.printStackTrace (); }
	}
}
