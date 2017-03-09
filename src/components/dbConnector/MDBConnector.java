package components.dbConnector;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MDBConnector {
	
	private String serverPort, serverName, login, password,dbName;
	private File file;
	
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getServerPort() {
		return serverPort;
	}
	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
	Connection con;
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		if (this.con != null) return con;
		String connectionUrl = "jdbc:sqlserver://"+serverName+":"+serverPort+";" + "databaseName="+dbName+";"
				+ "user="+login+";"+"password="+password+";"; 
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
		con = DriverManager.getConnection(connectionUrl); 
		return con;
	}	

}
