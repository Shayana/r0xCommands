package fr.Shayana.r0xCommands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class commandManager {

	protected r0xCommands plugin;
	
	Map<Player, Player> tpMap;

	public commandManager(r0xCommands plugin) {
		
		tpMap = new HashMap<Player, Player>();
		this.plugin = plugin;
	}

	@EventHandler
	public boolean onCommand(CommandSender sender, Command command, String label, String args[]){
		
		Player player = (Player) sender;	
		Teleport teleport = new Teleport(plugin, player, args);
		Utils utils = new Utils(plugin, player, args);
		Spawn spawn = new Spawn(player, plugin);
		Home home = new Home(plugin, player);
		

		if (sender instanceof Player && !(plugin.vault.perms.has(sender, "rc."+label))) {
			
			player.sendMessage(ChatColor.RED+"Vous n'avez pas la permission de faire ceci!");
			
			return true;
		}
		
		if (label.equalsIgnoreCase("who")) {
			
			utils.Commands_who();
		}
		
		else if(label.equalsIgnoreCase("home")) {
			
			home.Command_home();
		}
		
		else if(label.equalsIgnoreCase("tp")) {
			
			tpMap = teleport.Commands_tp(tpMap);
		}
		
		else if(label.equalsIgnoreCase("tph") || label.equalsIgnoreCase("bring")) {
			
			teleport.Commands_tph(tpMap);
		}
		
		else if(label.equalsIgnoreCase("tpall")) {
			
			teleport.Commands_tpall();
		}
		
		else if(label.equalsIgnoreCase("put")) {
			
			teleport.Commands_put();
		}
		
		else if(label.equalsIgnoreCase("accept")) {
			
			teleport.Command_accept(tpMap);
		}
		
		else if(label.equalsIgnoreCase("refuse")) {
			
			teleport.Command_refuse(tpMap);
		}
		
		else if(label.equalsIgnoreCase("spawn")) {
			
			spawn.Commands_spawn();
		}
		
		else if(label.equalsIgnoreCase("suicide")) {
			
			utils.Commands_suicide();
		}
		
		else if(label.equalsIgnoreCase("kill")) {
			
			utils.Commands_kill();
		}
		
		return true;
	}
}
