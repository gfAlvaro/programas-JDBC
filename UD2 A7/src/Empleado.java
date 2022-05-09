
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Empleado {
	private String nombre_;
	private int edad_ = 0;
	private int telefono_ = 0;
	Scanner s_;
	PreparedStatement st_;
	
	public static void main(String[] args) {
		String url = "jdbc:mysql://127.0.0.1:3306/HLC";
		String login = "alvaro";
		String password = "macarrones";

		try {
			Connection conexion = DriverManager.getConnection(url, login, password);					
			Empleado empleado = new Empleado();
			empleado.s_ = new Scanner( System.in );
			int opcion = 0;

			do {
				System.out.println( "OPERACIONES BASE DE DATOS" );
				System.out.println( "1. Insertar datos" );
				System.out.println( "2. Leer datos" );
				System.out.println( "3. Actualizar datos" );
				System.out.println( "4. Eliminar datos" );
				System.out.println( "5. Salir" );
				opcion = empleado.s_.nextInt();
				empleado.s_.nextLine();
				
				switch( opcion ) {
					case 1: // Insertar datos			
						empleado.entradaDatos();
						if( empleado.create( conexion ) )
							System.out.println( "Registro añadido con éxito. " );
						else
							System.out.println( "No se pudo añadir el registro. " );
						break;
					case 2: // Leer datos
						ResultSet rs = empleado.read( conexion );
						while( rs.next() )
							System.out.println( "Nombre: " + rs.getString( "nombre" )
											  + ", Edad: " + rs.getInt( "edad" )
											  + ", Telefono: " + rs.getInt( "telefono" ) );
						break;
					case 3: // Actualizar datos
						empleado.entradaDatos();
						if( empleado.update( conexion ) )
							System.out.println( "Actualización realizada con éxito" );
						else
							System.out.println( "No se pudo realizar la actualización." );
						break;
					case 4: // Eliminar datos
						System.out.println( "Introduce nombre" );
						String nombre = empleado.s_.nextLine();

						if( empleado.delete( conexion, nombre ) )
							System.out.println( "Registro eliminado con éxito" );
						else
							System.out.println( "El registro no se pudo eliminar" );
						break;
					case 5: // Salir
						empleado.s_.close();
						System.out.println( "Hasta luego" );
						break;
					default:
						System.out.println( "Elige una opcion entre 1 y 5" );
						break;
				}
			}while( opcion != 5 );

		} catch ( SQLException e ) {
			System.out.println( "Excepcion SQL: " + e.getMessage() );
			System.out.println( "Estado SQL: " + e.getSQLState() );
			System.out.println( "Código de error: " + e.getErrorCode() );
		}
	}

	// método para entrada de datos
	private void entradaDatos() {
		System.out.println( "Introduce nombre: ");
		this.nombre_ = this.s_.nextLine();
		System.out.println( "Introduce edad: " );
		this.edad_ = this.s_.nextInt();
		this.s_.nextLine();
		System.out.println( "Introduce telefono: " );
		this.telefono_ = this.s_.nextInt();
		this.s_.nextLine();
	}
	
	// Función auxiliar para validar datos de entrada
	private boolean entradaEsValida() {
		return ( ( this.nombre_.length() > 0 ) && ( this.nombre_.length() <= 30 )
				&& ( this.edad_ >= 0 ) && (this.edad_ < 1000 )
				&& ( Integer.toString( this.telefono_ ).length() == 9 ) );
	}
	
	public boolean create( Connection connection ) throws SQLException {

		if( !this.entradaEsValida() )
			return false;

		// Ejecutamos la sentencia insert
		this.st_ = connection.prepareStatement( "INSERT INTO Empleados (nombre, edad, telefono) VALUES (?,?,?)" );
		this.st_.setString( 1, this.nombre_ );
		this.st_.setInt( 2, this.edad_ );
		this.st_.setInt( 3, this.telefono_ );

		return ( this.st_.executeUpdate() == 1 );
	}

	// devuelve una consulta completa de la tabla Empleado
	public ResultSet read( Connection conexion ) throws SQLException {
		this.st_ = conexion.prepareStatement( "SELECT * FROM Empleados" );
		return this.st_.executeQuery();
	}

	public boolean update( Connection conexion ) throws SQLException {

		if( !this.entradaEsValida() )
			return false;

		this.st_ = conexion.prepareStatement( "UPDATE Empleados SET edad=? , telefono=? WHERE nombre=?" );
		this.st_.setInt( 1, this.edad_ );
		this.st_.setInt( 2, this.telefono_ );
		this.st_.setString( 3, this.nombre_ );

		return ( this.st_.executeUpdate() == 1 );
	}
	
	public boolean delete( Connection conexion, String nombre ) throws SQLException {

		this.st_ = conexion.prepareStatement( "DELETE FROM Empleados WHERE nombre=?" );
		this.st_.setString( 1, nombre );

		return ( this.st_.executeUpdate() == 1 );
	}
}