package fr.Shayana.r0xCommands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils {
	
	r0xCommands plugin;
	Player player;
	String args[];
	
	public Utils(r0xCommands plugin, Player player, String args[]) {
		
		this.plugin = plugin;
		this.player = player;
		this.args = args;
	}
	
	public void Commands_who() {
		
		int i =  plugin.getServer().getOnlinePlayers().length;
		
		if (args.length < 1) {
			
			if (i > 1){
				
				player.sendMessage(ChatColor.BLUE +"Il y a actuellement "+ ChatColor.GREEN + i +" joueurs connect�s.");
			}
			else if (i == 1) {
				
				player.sendMessage(ChatColor.BLUE +"Il y a actuellement "+ ChatColor.BLUE + i +" joueur connect�");
			}
			else if(i == 0) {
				
				player.sendMessage(ChatColor.RED +"Il n'y a aucun joueur connect�");
			}
		}
		else if(args.length == 1) {
			
			int j = 0;
			for (int k = 0; k <= i; k++) {
				
				Player[] players = plugin.getServer().getOnlinePlayers();
				
				if (plugin.vault.perms.playerInGroup(players[k], args[0])) {
					
					j++;
				}
			}
			
			if (j > 1) {
				
				player.sendMessage(ChatColor.BLUE +"Il y a actuellement "+ ChatColor.GREEN + j + plugin.vault.chat.getGroupPrefix(player.getWorld(), args[0]) + args[0] +" connect�s.");
			}
			else if (j == 1) {
				
				player.sendMessage(ChatColor.BLUE +"Il y a actuellement "+ ChatColor.GREEN + j + plugin.vault.chat.getGroupPrefix(player.getWorld(), args[0]) + args[0] +" connect�.");
			}
			else if(j == 0) {
				
				player.sendMessage(ChatColor.BLUE +" Il n'y a actuellement aucun "+ ChatColor.GREEN + args[0] + " connect�");
			}
			
			return;
		}
		else {
			
			player.sendMessage(ChatColor.RED +"Vous ne pouvez sp�cifier qu'un seul groupe � la fois !");
		}
		
		return;
	}
}
