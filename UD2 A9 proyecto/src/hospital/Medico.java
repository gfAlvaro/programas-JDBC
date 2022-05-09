package hospital;

import java.sql.ResultSet;
import java.sql.SQLException;

import BD.DAOMedico;

public class Medico {

	private int id_; // 4 cifras
	private String nombre_; // 20 caracteres
	private String apellidos_; // 30 caracteres
	private int telefono_; // 9 cifras
	private String especialidad_; // 20 caracteres
	private static int cuentaMedicos_ = 0;
	private static DAOMedico conexion_ = null;
	
	// constructor
	public Medico() throws SQLException { conexion_ = new DAOMedico(); }
	
	// getters
	public int getId() { return this.id_; }
	public String getNombre() { return this.nombre_; }
	public String getApellidos() { return this.apellidos_; }
	public int getTelefono() { return this.telefono_; }
	public String getEspecialidad() { return this.especialidad_; }
	
	// setters
	private void setId( int id ){
		if( ( id < 9999 ) && ( id > 0 ) )	this.id_ = id;
		else								this.id_ = Math.abs( id % 10000 );
	}
	
	private void setNombre( String nombre ) {
		if( nombre.length() > 20 )	this.nombre_ = nombre.substring( 0, 21 );
		else						this.nombre_ = nombre;
	}
	
	private void setApellidos( String apellidos ) {
		if( apellidos.length() > 30 )	this.apellidos_ = apellidos.substring( 0, 31 );
		else							this.apellidos_ = apellidos;
	}

	private void setTelefono( int telefono ) {
		if( ( telefono < 1000000000 ) && ( telefono > -1 ) )	this.telefono_ = telefono;
		else													this.telefono_ = Math.abs( telefono % 1000000000 );
	}

	private void setEspecialidad( String especialidad ) {
		if( especialidad.length() > 20 )	this.especialidad_ = especialidad.substring( 0, 21 );
		else								this.especialidad_ = especialidad;
	}

	public boolean inserta( String nombre, String apellidos, int telefono, String especialidad) throws SQLException {
		cuentaMedicos_++;
		this.setId( cuentaMedicos_ );		
		this.setNombre( nombre );
		this.setApellidos( apellidos );
		this.setTelefono( telefono );
		this.setEspecialidad( especialidad );		
		return conexion_.insert( this );
    }

	public ResultSet lee( int id ) throws SQLException { return conexion_.query( id ); }

	public void actualiza( int id, String nombre, String apellidos,
						   int telefono, String especialidad ) throws SQLException {
		this.setId( id );
		this.setNombre( nombre );
		this.setApellidos( apellidos );
		this.setTelefono( telefono );
		this.setEspecialidad( especialidad );
		conexion_.update( this );
	}

	public void borra( int id ) throws SQLException { conexion_.delete( id ); }	
}
