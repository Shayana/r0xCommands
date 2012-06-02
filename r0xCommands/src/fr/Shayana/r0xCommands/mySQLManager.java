package fr.Shayana.r0xCommands;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;


public class mySQLManager {

	r0xCommands plugin;
	
	Statement stmt;
	Connection con;
	ResultSet rs;
	
	String user;
	String mdp;
	String url;
	
	
	public mySQLManager(r0xCommands plugin) {
		
		this.plugin = plugin;
		
		user = plugin.config.user();
		mdp = plugin.config.pass();
		url = "jdbc:mysql://" + plugin.config.url() + ":" + plugin.config.port() + "/" + plugin.config.database();
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
	
	public Location homeDefini(Player p, String nom){
		Connection();
		try{
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Homes WHERE Player LIKE '"+ p.getName()+"' AND Nom Like '"+nom+"'");
			if (rs.first()){
				int x = rs.getInt("x");
				int y = rs.getInt("y");
				int z = rs.getInt("z");
				float pitch = rs.getFloat("pitch");
				float yaw = rs.getFloat("yaw");
				String world = rs.getString("world");
				return new Location(Bukkit.getWorld(world), x, y, z, pitch, yaw);
			}
			else {
				return null;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
			
		}
	
	}
}
