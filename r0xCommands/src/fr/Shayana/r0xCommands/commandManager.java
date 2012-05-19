package fr.Shayana.r0xCommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class commandManager {

	protected r0xCommands plugin;

	public commandManager(r0xCommands plugin){
		this.plugin = plugin;
	}




	@EventHandler
	public boolean onCommand(CommandSender sender, Command command, String label, String args[]){
		Player player = (Player) sender;


		if (sender instanceof Player && !(plugin.vault.perms.has(sender, "rc."+label))) {
			
			player.sendMessage(ChatColor.RED+"Vous n'avez pas la permission de faire ceci!");
			
			return true;
		}
		
		if (label.equalsIgnoreCase("who")) {
			
			Utils utils = new Utils(plugin, player, args);
			utils.Commands_who();
			
			return true;
		}
		
		else if(label.equalsIgnoreCase("home")) {
			
			Home home = new Home(plugin, player);
			home.Command_home();
			
			return true;
		}
		
		else if(label.equalsIgnoreCase("spawn")) {
			
			Spawn spawn = new Spawn(player);
			spawn.Commands_spawn();
			
			return true;
		}
		return true;
	}
}