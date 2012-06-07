package fr.Shayana.r0xCommands;

import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Teleport {
	
	r0xCommands plugin;
	Player player;
	String args[];
	
	Map<Player, Player> tpMap;
	
	public Teleport(r0xCommands plugin, Player player, String args[]) {
		
		this.plugin = plugin;
		this.player = player;
		this.args = args;
	}
	
	public Map<Player, Player> Commands_tp(Map<Player, Player> Map) {
		
		tpMap = Map;
		
		if (args.length == 1) {
				
			Player p1 = plugin.getServer().getPlayer(args[0]);
			  	
	        if (p1 == null) {
			  	
	          player.sendMessage(ChatColor.RED + args[0] + " n'est pas en ligne actuellement.");
			  	
	          return tpMap;
			  	
	        }
			  	
	        if (plugin.vault.perms.playerInGroup(player, plugin.config.superTP())) {
			  	
			player.teleport(p1);
			  	
	        }
			  	
	        else {
			  	
	          player.sendMessage(ChatColor.RED + "Demande de téléportation envoyée à " + p1.getDisplayName());
		  	
	          p1.sendMessage(ChatColor.RED + player.getDisplayName()+" souhaiterais se téléporter à vous, tapez /accept pour accepter ou /deny pour refuser.");
			  	
	          tpMap.put(p1, player);
	        }
		}
		else if (args.length == 2) {

			if (!(plugin.vault.perms.playerInGroup(player, plugin.config.superTP()))){
			  	
				player.sendMessage(ChatColor.RED +"Vous n'avez pas la permission de faire ceci !");
			  	
			}
		    else {
		    	
		    	Player p1 = plugin.getServer().getPlayer(args[0]);
			  	
		    	if (p1 == null){
		    		
		    		player.sendMessage(ChatColor.RED + args[0] + " n'est pas en ligne actuellement.");
		            return tpMap;
		    	}
		    	
		    	Player p2 = plugin.getServer().getPlayer(args[1]);
		    	
		    	if (p2 == null){
		    		
		    		player.sendMessage(ChatColor.RED + args[1] + " n'est pas en ligne actuellement.");
		            return tpMap;
		    	}
			  	
		    	p1.sendMessage(ChatColor.RED +player.getDisplayName()+ " vous téléporte à "+ p2.getDisplayName());
			  	
		    	p2.sendMessage(ChatColor.RED +player.getDisplayName()+ " téléporte "+ p1.getDisplayName() +" à votre position");
			  	
		    	p1.teleport(p2);
		          
		    }
		       
			return tpMap;    
			
		}
		else if(args.length == 3) {
			
			if(!(plugin.vault.perms.playerInGroup(player, plugin.config.superTP()))) {
				
				double x = Double.parseDouble(args[0]);
				double y = Double.parseDouble(args[1]);
				double z = Double.parseDouble(args[2]);
					
				Location location = new Location(player.getWorld(), x, y, z);
					
				player.teleport(location);
			}
			else {
				
				player.sendMessage(ChatColor.RED +"Vous n'avez pas la permission de faire ceci !");
			}
		}
		
		return tpMap;
	}
	
	public Map<Player, Player> Commands_tph(Map<Player, Player> Map) {
		
		tpMap = Map;			
		Player p1 = plugin.getServer().getPlayer(args[0]);		  	

		if (p1 == null){
			  	
			player.sendMessage(ChatColor.RED + args[0] + " n'est pas en ligne actuellement.");
			  	
			return tpMap;
		}
			  	
		if (plugin.vault.perms.playerInGroup(player, plugin.config.superTP())) {
			  	
			p1.teleport(player);
		}	
		else {
			  	
			p1.sendMessage(ChatColor.RED + player.getDisplayName()+" souhaiterais vous téléporter à lui, tapez /accept pour accepter ou /deny pour refuser.");
			tpMap.put(player, p1);
		}
		return tpMap;
	}
	
	public void Commands_put() {
			
		Player p1 = plugin.getServer().getPlayer(args[0]);
			
		if(p1 == null) {
					
			player.sendMessage(ChatColor.RED + args[0] + " n'est pas en ligne actuellemnt.");
					
			return;
		}
				
		double x = Double.parseDouble(args[1]);
		double y = Double.parseDouble(args[2]);
		double z = Double.parseDouble(args[3]);
				
		Location location = new Location(player.getWorld(), x, y, z);
				
		p1.teleport(location);
		p1.sendMessage(ChatColor.RED + player.getName() + " vous a téléporté à la position " + x + " " + y + " " + z);
	}
	
	public void Commands_tpall() {
		
		if(args.length == 0) {
				
			player.getServer().broadcastMessage(ChatColor.RED + "Attention, vous allez être tous téléporter vers " + player.getName());
				
			for(Player p : Bukkit.getOnlinePlayers()) {
						
				p.teleport(player);
			}
		}
		else if(args.length == 1) {
				
			Player p1 = plugin.getServer().getPlayer(args[0]); 
					
			player.getServer().broadcastMessage(ChatColor.RED + "Attention, vous allez être tous téléporter vers " + p1.getName());
			p1.sendMessage(ChatColor.RED + "Attention, tout les joueurs vont se téléporter sur vous");
					
			for(Player p : Bukkit.getOnlinePlayers()) {
				
				p.teleport(p1);
			}	
		}
	}
	
	public Map<Player, Player> Command_accept(Map<Player, Player> Map) {
		
		tpMap = Map;
		
		for(Entry<Player, Player> entry : tpMap.entrySet()) {

			Player key = entry.getKey();
			Player value = entry.getValue();

			if(key == player) {

				value.teleport(player);
				tpMap.remove(key);
				return tpMap;
			}
			else if(value == player) {

				player.teleport(key);
				tpMap.remove(key);
				return tpMap;
			}
		}
		
		player.sendMessage(ChatColor.RED + "Personne ne vous a demandé une téléportation.");
		
		return tpMap;
	}

	public Map<Player, Player> Command_refuse(Map<Player, Player> Map) {
		
		tpMap = Map;
		
		StringBuilder message = new StringBuilder();
		
		for(int i = 0; i < args.length ; i++) {
			
			message.append(args[i]);
			message.append(" ");
		}
		
		for(Entry<Player, Player> entry : tpMap.entrySet()) {

			Player key = entry.getKey();
			Player value = entry.getValue();

			if(key == player) {

				value.sendMessage(ChatColor.RED + key.getName() + " a refusé votre invitation.");
				
				if(args.length > 0) {
					
					value.sendMessage(ChatColor.RED + "[Justification :] " + ChatColor.WHITE + message.toString());
				}
				else {
					
					value.sendMessage(ChatColor.RED + "[Justification :] Aucune");
				}
				tpMap.remove(key);
				return tpMap;
			}
			else if(value == player) {

				key.sendMessage(ChatColor.RED + value.getName() + " a refusé votre invition.");
				if(args.length > 0) {
				
					key.sendMessage(ChatColor.RED + "[Justification :] " + ChatColor.WHITE + message.toString());
				}
				else {
					
					key.sendMessage(ChatColor.RED + "[Justification :] Aucune");
				}
				tpMap.remove(key);
				return tpMap;
			}
		}
		
		player.sendMessage(ChatColor.RED + "Personne ne vous a demandé une téléportation");
		
		return tpMap;
	}

}

