package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOConexion {

	private static DAOConexion instancia_ = null;
	public Connection cn_ = null;
	private String url_ = "jdbc:mysql://127.0.0.1:3306/HOSPITAL";
	private String login_ = "alvaro";
	private String password_ = "macarrones";

	/**
	 * constructor privado para el patrón singleton
	 * @throws SQLException
	 */
	private DAOConexion() throws SQLException { this.cn_ = DriverManager.getConnection( url_, login_, password_ ); }

	/**
	 * implementación de patrón singleton
	 * @return DAO
	 * @throws SQLException
	 */
	public static DAOConexion getInstancia() throws SQLException {
		if( instancia_ == null )	instancia_ = new DAOConexion();
		return instancia_;
	}

	/**
	 * implementación de transacciones SQL
	 * @param commit
	 * @return
	 * @throws SQLException
	 */
	public Connection setTransaccion( boolean commit ) throws SQLException {
		if( commit )	this.cn_.commit();
		else			this.cn_.setAutoCommit( false );
		return this.cn_;
	}
}