package BD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import hospital.Ingreso;

public class DAOIngreso implements DAOInterfaz < Ingreso, Integer > {
	
	private static DAOConexion instancia_ = null;
	private PreparedStatement st_ = null;

	// constructor
	public DAOIngreso() throws SQLException { instancia_ = DAOConexion.getInstancia(); }

	public boolean insert( Ingreso a ) throws SQLException {
		instancia_.cn_ = instancia_.setTransaccion( false );
		this.st_ = instancia_.cn_.prepareStatement( "INSERT INTO INGRESO VALUES (?,?,?,?,?,?,?)" );
		this.st_.setInt( 1, a.getId() );
		this.st_.setInt( 2, a.getIdPaciente() );
		this.st_.setInt( 3, a.getIdMedico() );
		this.st_.setDate( 4, a.getFechaIngreso() );
		this.st_.setInt( 5, a.getHabitacion() );
		this.st_.setInt( 6, a.getCama() );
		this.st_.setDate( 7, null );
		int salida = this.st_.executeUpdate();
		instancia_.cn_ = instancia_.setTransaccion( true );
		return ( salida == 1 );
	}

	public ResultSet query( int id ) throws SQLException {
		String query;
		if( id == 0 )	query = ";";
		else			query = " WHERE id=" + id + ";";
		this.st_ = instancia_.cn_.prepareStatement( "SELECT * FROM INGRESO" + query );
		return this.st_.executeQuery();
	}
	
	public boolean update(  Ingreso a ) throws SQLException {
		instancia_.cn_ = instancia_.setTransaccion( false );
		this.st_ = instancia_.cn_.prepareStatement( "UPDATE INGRESO SET idPaciente=?, idMedico=?, fechaIngreso=?, habitacion=?, cama=?, fechaAlta=? WHERE id=?;" );
		this.st_.setInt( 1, a.getIdPaciente() );		
		this.st_.setInt( 2, a.getIdMedico() );		
		this.st_.setDate( 3, a.getFechaIngreso() );
		this.st_.setInt( 4, a.getHabitacion() );		
		this.st_.setInt( 5, a.getCama() );		
		this.st_.setDate( 6, a.getFechaAlta() );
		this.st_.setInt( 7, a.getId() );
		int salida = this.st_.executeUpdate();
		instancia_.cn_ = instancia_.setTransaccion( true );
		return ( salida == 1 );
	}

	public boolean delete( int id ) throws SQLException {
		instancia_.cn_ = instancia_.setTransaccion( false );
		this.st_ = instancia_.cn_.prepareStatement( "DELETE FROM INGRESO WHERE id=?" );
		this.st_.setInt( 1, id );
		int salida = this.st_.executeUpdate();
		instancia_.cn_ = instancia_.setTransaccion( true );
		return ( salida == 1 );
    }
}