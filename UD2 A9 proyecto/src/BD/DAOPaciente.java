package BD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import hospital.Paciente;
public class DAOPaciente implements DAOInterfaz<Paciente, Integer> {
	
	private static DAOConexion instancia_ = null;
	private PreparedStatement st_;

	// constructor
	public DAOPaciente() throws SQLException { instancia_ = DAOConexion.getInstancia(); }

	public ResultSet query( int id ) throws SQLException{
		String query;
		if( id == 0 )	query = ";";
		else			query = " WHERE id=" + id + ";";
		this.st_ = instancia_.cn_.prepareStatement( "SELECT * FROM PACIENTE" + query );
		return this.st_.executeQuery();
	}

	public boolean insert( Paciente a ) throws SQLException {
		instancia_.cn_ = instancia_.setTransaccion( false );
		this.st_ = instancia_.cn_.prepareStatement( "INSERT INTO PACIENTE VALUES (?,?,?,?,?,?,?,?,?)" );
		this.st_.setInt( 1, a.getId() );
		this.st_.setString( 2, a.getNombre() );
		this.st_.setString( 3, a.getApellidos() );
		this.st_.setString( 4, a.getDireccion() );
		this.st_.setString( 5, a.getPoblacion() );
		this.st_.setString( 6, a.getProvincia() );
		this.st_.setInt( 7, a.getCodigoPostal() );
		this.st_.setInt( 8, a.getTelefono() );
		this.st_.setDate( 9, a.getFechaNacimiento() );
		int salida = this.st_.executeUpdate();
		instancia_.cn_ = instancia_.setTransaccion( true );
		return ( salida == 1 );
	}

	public boolean update( Paciente a ) throws SQLException {
		instancia_.cn_ = instancia_.setTransaccion(false);
		this.st_ = instancia_.cn_.prepareStatement( "UPDATE PACIENTE SET nombre=?, apellidos=?, direccion=?, poblacion=?, provincia=?, codigoPostal=?, telefono=?, fechaNacimiento=? WHERE id=?;" );
		this.st_.setString( 1, a.getNombre() );
		this.st_.setString( 2, a.getApellidos() );		
		this.st_.setString( 3, a.getDireccion() );
		this.st_.setString( 4, a.getPoblacion() );		
		this.st_.setString( 5, a.getProvincia() );
		this.st_.setInt( 6, a.getCodigoPostal() );
		this.st_.setInt( 7, a.getTelefono() );
		this.st_.setDate( 8, a.getFechaNacimiento() );
		this.st_.setInt( 9, a.getId() );
		int salida = this.st_.executeUpdate();
		instancia_.cn_ = instancia_.setTransaccion( true );		
		return ( salida == 1 );
	}

	public boolean delete( int id ) throws SQLException {
		instancia_.cn_ = instancia_.setTransaccion( false );		
		this.st_ = instancia_.cn_.prepareStatement( "DELETE FROM PACIENTE WHERE id=?" );
		this.st_.setInt( 1, id );
		int salida = this.st_.executeUpdate();
		instancia_.cn_ = instancia_.setTransaccion( true );
		return ( salida == 1);
    }
}