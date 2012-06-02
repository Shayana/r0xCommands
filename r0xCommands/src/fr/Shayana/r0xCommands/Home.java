package fr.Shayana.r0xCommands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Home {
	
	r0xCommands plugin;
	Player player;
	String home;
	
	public Home(r0xCommands plugin, Player player, String home) {
		
		this.plugin = plugin;
		this.player = player;
		this.home = home;
	}
	
	public void Command_home() {
		
		if (home == null){
			if (plugin.mysql.homeDefini(player, "default") != null){
				player.teleport(plugin.mysql.homeDefini(player, "default"));
			}
			else {
				player.sendMessage(ChatColor.RED + " Veuillez d'abord définir un home !");
			}
		}
		if(plugin.mysql.homeDefini(player, home) != null) {
			player.teleport(plugin.mysql.homeDefini(player, home));
			player.sendMessage(ChatColor.BLUE + "Téléportation à "+ home);
		}
		
		else {
			player.sendMessage(ChatColor.RED + "Pas de home défini en tant que "+ home);
		}
	}
}
