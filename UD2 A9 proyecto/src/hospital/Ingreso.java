package hospital;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import BD.DAOIngreso;

public class Ingreso {

	private int id_;
	private int idPaciente_;
	private int idMedico_;
	private Date fechaIngreso_;
	private int habitacion_;
	private int cama_;
	private Date fechaAlta_ = null;
	private static int cuentaIngresos_ = 0;
	private static DAOIngreso conexion_ = null;

	// constructor
	public Ingreso() throws SQLException { conexion_ = new DAOIngreso(); }
	
	// getters
	public int getId() { return this.id_; }
	public int getIdPaciente() { return this.idPaciente_; }
	public int getIdMedico(){ return this.idMedico_; }
	public Date getFechaIngreso() { return this.fechaIngreso_; }
	public int getHabitacion(){ return this.habitacion_; }
	public int getCama() { return this.cama_; }
	public Date getFechaAlta() { return fechaAlta_; }

	// setters
	private void setId( int id ) {
		if( ( id < 10000 ) && ( id > 0 ) )	this.id_ = id;
		else 								this.id_ = id % 10000;
	}
	private void setIdPaciente( int idPaciente ) {
		if( ( idPaciente < 10000 ) && ( idPaciente > 0 ) )	this.idPaciente_ = idPaciente;
		else 												this.idPaciente_ = idPaciente % 10000;
	}
	private void setIdMedico( int idMedico ) {
		if( ( idMedico < 10000 ) && ( idMedico > 0 ) )	this.idMedico_ = idMedico;
		else											this.idMedico_ = idMedico % 10000;
	}
	private void setFechaIngreso() {
		this.fechaIngreso_ = new java.sql.Date( new java.util.Date().getTime() );
	}
	private void setFechaIngreso( Date fechaIngreso ) { this.fechaIngreso_ = fechaIngreso; }
	private void setHabitacion( int habitacion ){
		if( ( habitacion > 1 ) && ( habitacion <= 999 ) )	this.habitacion_ = ( habitacion % 1 ) + 1;
		else												this.habitacion_ = habitacion;
	}
	private void setCama( int cama ) {
		if( ( cama > 0 ) && ( cama < 3 ) )	this.cama_ = cama;
		else								this.cama_ = ( cama % 2 ) + 1;
	}
	private void setFechaAlta( Date fechaAlta ) { this.fechaAlta_ = fechaAlta; }
	
	public boolean inserta( int idPaciente, int idMedico, int habitacion, int cama ) throws Exception {		
		cuentaIngresos_++;
		this.setId( cuentaIngresos_ );		
		this.setIdPaciente( idPaciente );
		this.setIdMedico( idMedico );
		this.setFechaIngreso();
		this.setHabitacion( habitacion );
		this.setCama( cama );		
		return conexion_.insert( this );
    }

	public ResultSet lee( int id ) throws Exception { return conexion_.query( id ); }

	public void actualiza( int id, int idPaciente, int idMedico, Date fechaIngreso,
						   int habitacion, int cama, Date fechaAlta ) throws Exception {		
		this.setId( id );
		this.setIdPaciente( idPaciente );
		this.setIdMedico( idMedico );
		this.setHabitacion( habitacion );
		this.setFechaIngreso( fechaIngreso );
		this.setHabitacion( habitacion );
		this.setCama( cama );
		this.setFechaAlta( fechaAlta );		
		conexion_.update( this );
	}

	public void borra( int id ) throws SQLException { conexion_.delete( id ); }
}
