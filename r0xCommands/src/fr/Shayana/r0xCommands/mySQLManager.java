package fr.Shayana.r0xCommands;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

public class mySQLManager {

	
	protected r0xCommands plugin;
	protected configManager config;
	
	Statement stmt;
	Connection con;
	ResultSet rs;
	
	boolean homeDefini;
	
	String user = plugin.config.user();
	String mdp = plugin.config.pass();
	String url = plugin.config.url();
	
	public mySQLManager(r0xCommands plugin, configManager config){
		this.plugin = plugin;
		this.config = config;
	}
	
	public void Connection() {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			
			System.err.print("ClassNotFoundExeption : ");
			System.err.println(e);
		}
		
		try {
			
			con = DriverManager.getConnection(url, user, mdp);
			stmt = con.createStatement();
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void CloseConnection() {
		
		try {
			
			if(con != null) {
				
				con.close();
			}
			if (stmt != null) {
				
				stmt.close();
			}
			if(rs != null) {
				
				rs.close();
			}
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
