import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Alumno {
	private int id_;
	private String nombre_;
	private Date fechaNacimiento_;
	private float notaMedia_;
	private String curso_;
	private PreparedStatement st_;
	private Scanner s_;
	
	public static void main( String[] args ) throws ParseException {
		String url = "jdbc:mysql://127.0.0.1:3306/Instituto";
		String login = "alvaro";
		String password = "macarrones";
		Alumno alumno = new Alumno();
		
		try {
			Connection conexion = DriverManager.getConnection( url, login, password );
			int opcion = 0;
			alumno.s_ = new Scanner( System.in );
			do {
				System.out.println( "OPERACIONES CON LA BASE DE DATOS." );
				System.out.println( "1.Insertar datos" );
				System.out.println( "2.Leer datos" );
				System.out.println( "3.Actualizar datos" );
				System.out.println( "4.Eliminar datos" );
				System.out.println( "5.Salir" );
				opcion = alumno.s_.nextInt();
				alumno.s_.nextLine();
				switch( opcion ){
					case 1: // Insertar datos
						alumno.entradaDatos();
						if( alumno.create( conexion ) )
							System.out.println( "Registro añadido. " );
						else
							System.out.println( "No se pudo añadir el registro. " );
					break;
				case 2: // Leer datos
					ResultSet rs = alumno.read( conexion );
					while( rs.next() )
						System.out.println( new Alumno( rs.getInt( "id" ),
														rs.getString( "nombre" ),
														rs.getDate( "fechaNacimiento" ),
														rs.getFloat( "notaMedia" ),
														rs.getString( "curso" ) ) );
					break;
				case 3: // Actualizar datos
					alumno.entradaDatos();
					if( alumno.update( conexion ) )
						System.out.println( "Alumno correctamente actualizado" );
					else
						System.out.println( "Alumno no se ha actualizado correctamente " );					
					break;
				case 4:// Eliminar datos
					System.out.println( "Introduce id: " );
					int id = alumno.s_.nextInt();
					alumno.s_.nextLine();

					if( alumno.delete( conexion, id ) )
						System.out.println( "Registro eliminado con éxito. " );
					else
						System.out.println( "No se pudo eliminar el registro. " );
					break;
				case 5:// Salir
					alumno.s_.close();
					System.out.println( "Hasta luego." );
					break;
				default:
					System.out.println( "Elige una opcion entre 1 y 5." );
					break;
				}
			} while (opcion != 5);

		} catch (SQLException e) {
			System.out.println( "Excepcion SQL: " + e.getMessage() );
			System.out.println( "Estado SQL: " + e.getSQLState() );
			System.out.println( "Código del Error: " + e.getErrorCode() );
		}
	}

	public Alumno(){}
	public Alumno( int id, String nombre, Date fechaNacimiento, float notaMedia, String curso ){
		this.setId( id );
		this.setNombre( nombre );
		this.setFechaNacimiento( fechaNacimiento );
		this.setNotaMedia( notaMedia );
		this.setCurso( curso );
	}
	
	private void entradaDatos() {
		System.out.println( "Introduce id: " );
		this.setId( this.s_.nextInt() );
		this.s_.nextLine();
		System.out.println( "Introduce nombre: " );
		this.setNombre( this.s_.nextLine() );	
		System.out.println( "Introduce fecha nacimiento(dd/MM/yyyy)" );
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			this.setFechaNacimiento( formato.parse( this.s_.nextLine() ) );
		} catch (ParseException e) { e.printStackTrace(); }
		
		System.out.println( "Introduce curso" );
		this.setCurso( this.s_.nextLine() );
		System.out.println( "Introduce nota media: ");
		this.setNotaMedia( this.s_.nextFloat() );
	}
	
	// getters
	public int getId(){
		return this.id_;
	}
	
	public String getNombre(){
		return this.nombre_;
	}
	
	public Date getFechaNacimiento(){
		return this.fechaNacimiento_;
	}	
	
	public float getNotaMedia() {
		return this.notaMedia_;
	}
	
	public String getCurso(){
		return this.curso_;
	}
	
	
	// setters
	public void setId( int id ){
		this.id_ = id;
	}

	public void setNombre( String nombre ){
		if( nombre.length() < 31 )
			this.nombre_ = nombre;		
		else
			this.nombre_ = nombre.substring(0, 31);
	}

	public void setFechaNacimiento( Date fecha ){
		this.fechaNacimiento_ = fecha;
	}

	public void setNotaMedia( float notaMedia ){
		if( notaMedia > -1 && notaMedia > 11 )
			this.notaMedia_ = notaMedia;
		else
			this.notaMedia_ = 5;
	}

	public void setCurso( String curso ){
		if( curso.matches( "^[1234][ABC]$" ) ) {
			this.curso_ = curso;}
		else
			this.curso_ = null;
	}
	
	/*
	 * @Override toString
	 */
	public String toString() {
		return "id:" + this.getId()
				+ "\nNombre: " + this.getNombre()
				+ "\nFecha nacimiento: " + this.getFechaNacimiento()
				+ "\nNota media: " + this.getNotaMedia()
				+ "\nCurso: " + this.getCurso() + "\n";
	}

	public boolean create(Connection conexion) throws SQLException {
		this.st_ = conexion.prepareStatement( "INSERT INTO ALUMNOS VALUES (?,?,?,?,?)" );
		this.st_.setInt( 1, this.getId() );
		this.st_.setString( 2, this.getNombre() );
		this.st_.setDate( 3, new java.sql.Date( this.getFechaNacimiento().getTime() ) );
		this.st_.setString( 4, this.getCurso() );
		this.st_.setFloat( 5, this.getNotaMedia() );
		return ( this.st_.executeUpdate() == 1 );
	}

	public ResultSet read( Connection conexion ) throws SQLException {
		this.st_ = conexion.prepareStatement( "SELECT * FROM ALUMNOS" );
		return this.st_.executeQuery();
	}

	public boolean update( Connection conexion ) throws SQLException {
		this.st_ = conexion.prepareStatement(
					"UPDATE ALUMNOS SET nombre=?, fechaNacimiento=?, notaMedia=?, curso=? WHERE id=?;" );
		this.st_.setString( 1, this.getNombre() );
		this.st_.setDate( 2, new java.sql.Date( this.getFechaNacimiento().getTime() ) );
		this.st_.setFloat( 3, this.getNotaMedia() );
		this.st_.setString( 4, this.getCurso() );
		this.st_.setInt( 5, this.getId() );
		return ( st_.executeUpdate() == 1 );
	}

	public boolean delete( Connection conexion, int id ) throws SQLException {
		this.st_ = conexion.prepareStatement( "DELETE FROM ALUMNOS WHERE id=?" );
		this.st_.setInt( 1, id );
		return ( this.st_.executeUpdate() == 1);
	}
}