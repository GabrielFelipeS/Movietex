package ifsp.movietex.base.db;

import java.sql.Connection;

public interface IDBConnector {
	Connection getConnection();
	Connection getConnection(String url, String user, String password);
	
	void disableAutoCommit();
	void closeConnection();
	void commit();
	void rollback();
}
