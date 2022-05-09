package hospital;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import BD.DAOPaciente;

public class Paciente {
	private int id_;
	private String nombre_;
	private String apellidos_;
	private String direccion_;
	private String poblacion_;
	private String provincia_;
	private int codigoPostal_;
	private Date fechaNacimiento_;
	private int telefono_;
	private static int cuentaPacientes_ = 0;
	private DAOPaciente conexion_;
	
	// constructor
	public Paciente () throws SQLException { conexion_ = new DAOPaciente(); }
	
	// getters
	public int getId() { return this.id_; }
	public String getNombre() { return this.nombre_; }
	public String getApellidos() { return this.apellidos_; }
	public String getDireccion() { return this.direccion_; }
	public String getPoblacion() { return this.poblacion_; }
	public String getProvincia() { return this.provincia_; }
	public int getCodigoPostal() { return this.codigoPostal_; }
	public Date getFechaNacimiento() { return this.fechaNacimiento_; }
	public int getTelefono() { return this.telefono_; }
	
	// setters
	private void setId( int id ){ this.id_ = id; }

	private void setNombre( String nombre ) {
		if( nombre.length() > 20 )	this.nombre_ = nombre.substring( 0, 21 );
		else						this.nombre_ = nombre;
	}

	private void setApellidos( String apellidos ) {
		if( apellidos.length() > 30 )	this.apellidos_ = apellidos.substring( 0, 31 );
		else							this.apellidos_ = apellidos;
	}

	private void setDireccion( String direccion ) {
		if( direccion.length() > 50 )	this.direccion_ = direccion.substring( 0, 51 );
		else							this.direccion_ = direccion;
	}

	private void setPoblacion( String poblacion ) {
		if( poblacion.length() > 30 )	this.poblacion_ = poblacion.substring( 0, 31 );
		else							this.poblacion_ = poblacion;
	}

	private void setProvincia( String provincia ) {
		if( provincia.length() > 30 )	this.provincia_ = provincia.substring( 0, 31 );
		else							this.provincia_ = provincia;
	}

	private void setCodigoPostal( int codigoPostal ) {
		if( ( codigoPostal > 0 ) && ( codigoPostal < 100000 ) ) this.codigoPostal_ = codigoPostal;
		else 													this.codigoPostal_ = Math.abs( codigoPostal % 100000 );
	}

	private void setFechaNacimiento( Date fechaNacimiento ) { this.fechaNacimiento_ = fechaNacimiento; }

	private void setTelefono( int telefono ) {
		if( ( telefono > 0 ) && ( telefono < 1000000000 ) ) this.telefono_ = telefono;
		else 												this.telefono_ = Math.abs(telefono % 1000000000);
	}

	public boolean inserta( String nombre, String apellidos, String direccion,
			String poblacion, String provincia, int codigoPostal, int telefono, Date fechaNacimiento ) throws Exception {
		cuentaPacientes_++;
		this.setId( cuentaPacientes_ );		
		this.setNombre( nombre );
		this.setApellidos( apellidos );
		this.setDireccion( direccion );
		this.setPoblacion( poblacion );
		this.setProvincia( provincia );
		this.setCodigoPostal( codigoPostal );
		this.setTelefono( telefono );
		this.setFechaNacimiento( fechaNacimiento );
		return this.conexion_.insert( this );
    }

	public ResultSet lee( int id ) throws SQLException { return this.conexion_.query( id ); }

	public void actualiza( int id, String nombre, String apellidos, String direccion,
			String poblacion, String provincia, int codigoPostal, int telefono, Date fechaNacimiento ) throws Exception {
		this.setId(id);
		this.setNombre( nombre );
		this.setApellidos( apellidos );
		this.setDireccion( direccion );
		this.setPoblacion( poblacion );
		this.setProvincia( provincia );
		this.setCodigoPostal( codigoPostal );
		this.setTelefono( telefono );
		this.setFechaNacimiento( fechaNacimiento );
		this.conexion_.update( this );
	}

	public void borra( int id ) throws SQLException{ this.conexion_.delete( id ); }
}
