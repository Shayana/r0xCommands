package fr.Shayana.r0xCommands;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Spawn {
	
	Player player;
	r0xCommands plugin;
	Location location;
	mySQLManager sql;
	
	double x = 0;
	double y = 0;
	double z = 0;
	
	public Spawn(Player player, r0xCommands plugin) {
		
		this.player = player;
		this.plugin = plugin;
		sql = plugin.mysql;
	}
	
	public void Commands_spawn() {
		
		try {
			
			sql.Connection();
			sql.con = sql.stmt.getConnection();
			sql.stmt.executeQuery(""); //A compléter
			sql.rs = sql.stmt.getResultSet();
			
			x = sql.rs.getDouble("x");
			y = sql.rs.getDouble("y");
			z = sql.rs.getDouble("z");
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
		
		location = new Location(player.getWorld(), x, y, z);
		
		player.teleport(location);
	}
}
