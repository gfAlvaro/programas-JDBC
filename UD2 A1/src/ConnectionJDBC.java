import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;

public class ConnectionJDBC {

    private static String db_ = "HLC";
    private static String login_ = "alvaro";
    private static String password_ = "macarrones";
    private static String url_ = "jdbc:mysql://127.0.0.1:3306/" + db_;	
    private static Connection connection_ = null;
    private static Statement st_ = null;
    
	public static void main (String[] args){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");			
            connection_ = DriverManager.getConnection(url_, login_, password_);

            if( connection_ != null ) {
                st_ = connection_.createStatement();
                System.out.println("Conexion a base de datos " + db_ + " correcta.");
            } else
                System.out.println("Conexion fallida.");
        } catch ( SQLTimeoutException e ) { System.out.print("Error de tiempo de conexion."); }
          catch ( SQLException e ) { e.printStackTrace(); }
          catch ( ClassNotFoundException e ) { e.printStackTrace(); }
          catch ( Exception e ) { e.printStackTrace (); }
    }
}

