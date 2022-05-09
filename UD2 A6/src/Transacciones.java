import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;

public class Transacciones {
	public static void main(String[] args) {
		String url = "jdbc:mysql://127.0.0.1:3306/HLC";
		String login = "alvaro";
		String password = "macarrones";
		try {
			Connection conexion = DriverManager.getConnection(url, login, password);
			conexion.setAutoCommit(false);

			// primero consultamos la tabla alumnos para ver su estado inicial
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT nombre, notaMedia,curso FROM ALUMNOS");
            System.out.println("tabla ALUMNOS");
            while(rs.next()) {
                System.out.println( "Nombre: " + rs.getString("nombre")
                				 + " Nota media: " + rs.getInt("notaMedia")
                				 + " Curso: " + rs.getInt("curso"));    
            }			
			
            // Hacemos las actualizaciones con una transacción
			st = conexion.createStatement();
			st.executeUpdate("INSERT INTO ALUMNOS(nombre,notaMedia,curso) VALUES('maria',8,1)");
			st.executeUpdate("INSERT INTO ALUMNOS(nombre,notaMedia,curso) VALUES('pepe',7,2)");
			st.executeUpdate("INSERT INTO ALUMNOS(nombre,notaMedia,curso) VALUES('ana',9,3)");
			st.executeUpdate("INSERT INTO ALUMNOS(nombre,notaMedia,curso) VALUES('fernando',6,2)");
			conexion.commit();
			
			// Volvemos a consultar la tabla alumnos para ver su estado final
            st = conexion.createStatement();
            rs = st.executeQuery("SELECT nombre, notaMedia,curso FROM ALUMNOS");
            System.out.println("tabla ALUMNOS");
            while(rs.next()) {
                System.out.println( "Nombre: " + rs.getString("nombre")
                				 + " Nota media: " + rs.getInt("notaMedia")
                				 + " Curso: " + rs.getInt("curso"));    
            }
            
	    } catch ( SQLTimeoutException e )    { System.out.print("Error de tiempo de conexion."); }
	      catch ( SQLException e )           { e.printStackTrace(); }
	      catch ( Exception e )              { e.printStackTrace (); }
	}
}
