package BD;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DAOInterfaz< T, K > {
	boolean insert( T a ) throws SQLException;
	boolean update( T a ) throws SQLException;
	boolean delete( int id ) throws SQLException;
	ResultSet query( int id ) throws SQLException;
}
