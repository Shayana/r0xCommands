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
			
			Utils who = new Utils(plugin, player, args);
			who.Commands_who();
		}
		
		else if(label.equalsIgnoreCase("home")) {
			
			Home home = new Home(plugin, player);
			home.Command_home();
		}
		
		else if(label.equalsIgnoreCase("tp")) {
			
			Teleport tp = new Teleport(plugin, player, args);
			tp.Commands_tp();
		}
		
		else if(label.equalsIgnoreCase("tph") || label.equalsIgnoreCase("bring")) {
			
			Teleport tph = new Teleport(plugin, player, args);
			tph.Commands_tph();
		}
		
		else if(label.equalsIgnoreCase("put")) {
			
			Teleport put = new Teleport(plugin, player, args);
			put.Commands_put();
		}
		
		else if(label.equalsIgnoreCase("spawn")) {
			
			Spawn spawn = new Spawn(player);
			spawn.Commands_spawn();
		}
		
		return true;
	}
}