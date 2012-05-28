package fr.Shayana.r0xCommands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Spawn {
	
	Player player;
	r0xCommands plugin;
	Location location;
	mySQLManager sql;	
	String args[];
	
	double x = 0;
	double y = 0;
	double z = 0;
	
	public Spawn(Player player, r0xCommands plugin, String args[]) {
		
		this.player = player;
		this.plugin = plugin;
		this.args = args;
		sql = plugin.mysql;
	}
	
	public void Commands_spawn() {
		
		try {
			
			sql.Connection();
			sql.con = sql.stmt.getConnection();
			sql.stmt.executeQuery(""); //A compl�ter
			sql.rs = sql.stmt.getResultSet();
			
			x = sql.rs.getDouble("x");
			y = sql.rs.getDouble("y");
			z = sql.rs.getDouble("z");
			
			location = new Location(player.getWorld(), x, y, z);
			
		}
		catch (Exception e) {
			
			e.printStackTrace();
			
			return;
		}
		
		player.teleport(location);
	}
	
	public void Commands_putspawn() {
		
		if(args.length == 1) {
			
			try {
				
				sql.Connection();
				sql.con = sql.stmt.getConnection();
				sql.stmt.executeQuery(""); //A compl�ter
				sql.rs = sql.stmt.getResultSet();
				
				x = sql.rs.getDouble("x");
				y = sql.rs.getDouble("y");
				z = sql.rs.getDouble("z");
				
				location = new Location(player.getWorld(), x, y, z);
				
			}
			catch (Exception e) {
				
				e.printStackTrace();
				
				return;
			}
			
			Player p1 = plugin.getServer().getPlayer(args[0]);
			
			if(p1 != null) {
				
				p1.teleport(location);
			}
			else {
				
				player.sendMessage(ChatColor.RED + args[0] + " n'est pas en ligne actuellemnt.");
			}
		}
		else {
			
			player.sendMessage(ChatColor.RED + "Veuillez respecter la syntaxe");
		}
	}
}
