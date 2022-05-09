package BD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import hospital.Medico;

public class DAOMedico implements DAOInterfaz <Medico, Integer> {

	private static DAOConexion instancia_ = null;
	private PreparedStatement st_ = null;
	
	// constructor
	public DAOMedico() throws SQLException { instancia_ = DAOConexion.getInstancia();	 }

	public ResultSet query( int id ) throws SQLException {
		String query;
		if( id == 0 )	query = ";";
		else			query = " WHERE id=" + id + ";";
		this.st_ = instancia_.cn_.prepareStatement( "SELECT * FROM MEDICO" + query );
		return this.st_.executeQuery();
	}

	public boolean insert( Medico a ) throws SQLException {
		instancia_.cn_ = instancia_.setTransaccion( false );
		this.st_ = instancia_.cn_.prepareStatement("INSERT INTO MEDICO VALUES (?,?,?,?,?)");
		this.st_.setInt( 1, a.getId() );
		this.st_.setString( 2, a.getNombre() );
		this.st_.setString( 3, a.getApellidos() );
		this.st_.setInt( 4, a.getTelefono() );
		this.st_.setString( 5, a.getEspecialidad() );
		int salida = this.st_.executeUpdate();
		instancia_.cn_ = instancia_.setTransaccion( true );
		return ( salida == 1 );
	}

	public boolean update( Medico a ) throws SQLException {
		instancia_.cn_ = instancia_.setTransaccion( false );
		this.st_ = instancia_.cn_.prepareStatement( "UPDATE MEDICO SET nombre=?, apellidos=?, telefono=?, especialidad=? WHERE id=?;" );
		this.st_.setString( 1, a.getNombre() );		
		this.st_.setString( 2, a.getApellidos() );		
		this.st_.setInt( 3, a.getTelefono() );
		this.st_.setString( 4, a.getEspecialidad() );		
		this.st_.setInt( 5, a.getId() );
		int salida = this.st_.executeUpdate();
		instancia_.cn_ = instancia_.setTransaccion( true );
		return ( salida == 1 );
	}

	public boolean delete( int id ) throws SQLException {
		instancia_.cn_ = instancia_.setTransaccion( false );
		this.st_ = instancia_.cn_.prepareStatement( "DELETE FROM MEDICO WHERE id=?" );
		this.st_.setInt( 1, id );
		int salida = this.st_.executeUpdate();
		instancia_.cn_ = instancia_.setTransaccion( true );
		return ( salida == 1);
    }
}