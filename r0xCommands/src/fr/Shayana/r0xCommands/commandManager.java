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
			

		if (!(sender instanceof Player)) {
			
			return true;
		}
		
		Player player = (Player)sender;
		
		if(!(plugin.vault.perms.has(sender, "rc."+label))) {
			
			player.sendMessage(ChatColor.RED + "VOus n'avez pas la permission de faire ceci.");
			return true;
		}
		
		// Les commandes de teleportations
		
		if(label.equalsIgnoreCase("tp")) {
			
			Teleport teleport = new Teleport(plugin, player, args);			
			tpMap = teleport.Commands_tp(tpMap);
		}
		
		else if(label.equalsIgnoreCase("tph")) {
			
			Teleport teleport = new Teleport(plugin, player, args);	
			teleport.Commands_tph(tpMap);
		}
		
		else if(label.equalsIgnoreCase("tpall")) {
			
			Teleport teleport = new Teleport(plugin, player, args);	
			teleport.Commands_tpall();
		}
		
		else if(label.equalsIgnoreCase("put")) {
			
			Teleport teleport = new Teleport(plugin, player, args);	
			teleport.Commands_put();
		}
		
		else if(label.equalsIgnoreCase("accept")) {
			
			Teleport teleport = new Teleport(plugin, player, args);	
			teleport.Command_accept(tpMap);
		}
		
		else if(label.equalsIgnoreCase("refuse")) {
			
			Teleport teleport = new Teleport(plugin, player, args);	
			teleport.Command_refuse(tpMap);
		}
		
		// Les divers commandes
		
		else if (label.equalsIgnoreCase("who")) {
			
			Utils utils = new Utils(plugin, player, args);
			utils.Commands_who();
		}
		
		else if(label.equalsIgnoreCase("suicide")) {
			
			Utils utils = new Utils(plugin, player, args);
			utils.Commands_suicide();
		}
		
		else if(label.equalsIgnoreCase("kill")) {
			
			Utils utils = new Utils(plugin, player, args);
			utils.Commands_kill();
		}
		
		else if (label.equalsIgnoreCase("kit")){
			
			Kits kit = new Kits(player, args[0]);
			kit.giveKit();	
		}
		
		else if (label.equalsIgnoreCase("kits")){
			
			Kits kit = new Kits(player, args[0]);
			kit.sendList();
		}
		
		else if (label.equalsIgnoreCase("give")) {
			
			Utils utils = new Utils(plugin, player, args);
			utils.Commands_give();
		}
		
		else if(label.equalsIgnoreCase("time")) {
			
			Utils utils = new Utils(plugin, player, args);
			utils.Commands_time();
		}
		
		else if(label.equalsIgnoreCase("clear")) {
			
			Utils utils = new Utils(plugin, player, args);
			utils.Commands_clear();
		}
		
		else if(label.equalsIgnoreCase("me")) {
			
			Utils utils = new Utils(plugin, player, args);
			utils.Commands_me();
		}
		
		else if(label.equalsIgnoreCase("rules")) {
			
			Utils utils = new Utils(plugin, player, args);
			utils.Commands_rules();
		}
		
		// Les commandes du spawn
		
		else if(label.equalsIgnoreCase("spawn")) {
			
			Spawn spawn = new Spawn(player, plugin, args);
			spawn.Commands_spawn();
		}
		
		else if(label.equalsIgnoreCase("putspawn")) {
			
			Spawn spawn = new Spawn(player, plugin, args);
			spawn.Commands_putspawn();
		}
		
		// Les commandes du Home
		
		else if(label.equalsIgnoreCase("home")) {
			
			Home home = new Home(plugin, player, "test");
			home.Command_home();
		}
		
		return true;
	}
}
