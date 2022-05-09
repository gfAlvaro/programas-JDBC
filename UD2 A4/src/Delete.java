import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;

public class Delete {
	    
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
                Statement st = conexion.createStatement();
                System.out.println( "Conexion a base de datos " + db + " correcta." );
                ResultSet rs = st.executeQuery(query);

                System.out.println( "tabla ALUMNOS" );
                while( rs.next() ) {
                    System.out.println( "Nombre: " + rs.getString("nombre")
                    				  + ", curso: " + rs.getInt("curso")
                    				  + ", nota media: " + rs.getInt("notaMedia") );
                }
                
                System.out.println( "Borramos los alumnos de 3º curso." );

                st = conexion.createStatement();
    			
    			int nEliminados = st.executeUpdate("DELETE FROM ALUMNOS WHERE curso=3;");
                System.out.println( "Alumnos eliminados: " + nEliminados );
                st = conexion.createStatement();

                System.out.println("tabla ALUMNOS");
                rs = st.executeQuery(query);
                while( rs.next() ) {
                    System.out.println( "Nombre: " + rs.getString("nombre")
                    				  + ", curso: " + rs.getInt("curso")
                    				  + ", nota media: " + rs.getInt("notaMedia") );
                }                

            } else
                System.out.println("Conexion fallida.");

	    } catch ( SQLTimeoutException e )    { System.out.print("Error de tiempo de conexion."); }
	      catch ( SQLException e )           { e.printStackTrace(); }
	      catch ( ClassNotFoundException e ) { e.printStackTrace(); }
	      catch ( Exception e )              { e.printStackTrace (); }
    }
}

