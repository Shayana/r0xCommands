package fr.Shayana.r0xCommands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Home {
	
	r0xCommands plugin;
	Player player;
	
	public Home(r0xCommands plugin, Player player) {
		
		this.plugin = plugin;
		this.player = player;
	}
	
	public void Command_home() {
		
		Location location;
		
		double x = 0;
		double y = 0;
		double z = 0;
		
		//A mettre : utilisation de la BDD
		
		if(plugin.mysql.homeDefini) {
			
			location = new Location(player.getWorld(), x, y, z);
			
			player.teleport(location);
		}
		
		else {
			
			player.sendMessage(ChatColor.RED + "Veuillez d'abord définir un home");
		}
	}
}
