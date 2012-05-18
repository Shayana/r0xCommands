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
		Player p = (Player) sender;
		
		
		if (sender instanceof Player && !(plugin.vault.perms.has(sender, "rc."+label))){
			p.sendMessage(ChatColor.RED+"Vous n'avez pas la permission de faire ceci!");
			return true;
		}
		
		
		
		
		if (label.equalsIgnoreCase("who")){
			int i =	plugin.getServer().getOnlinePlayers().length;
			if (args.length < 1){
			
			
				
				if (i > 1){
					p.sendMessage(ChatColor.BLUE +"Il y a actuellement "+ ChatColor.GREEN + i +" joueurs connectés.");
				}
				if (i == 1){
					p.sendMessage(ChatColor.BLUE +"Il y a actuellement "+ ChatColor.BLUE + i +" joueur connecté");
				}
				if (i == 0){
					p.sendMessage(ChatColor.RED +"Il n'y a aucun joueur connecté");
				}
				return true;
			}
			
		
			if (args.length == 1){
				int j = 0;
				for (int k = 0; k <= i; k++){
					Player[] players = plugin.getServer().getOnlinePlayers();
						if (plugin.vault.perms.playerInGroup(players[k], args[0])){
							j++;
						}	
				}
				if (j > 1){
					p.sendMessage(ChatColor.BLUE +"Il y a actuellement "+ ChatColor.GREEN + j + plugin.vault.chat.getGroupPrefix(p.getWorld(), args[0]) + args[0] +" connectés.");
				}
				if (j == 1){
					p.sendMessage(ChatColor.BLUE +"Il y a actuellement "+ ChatColor.GREEN + j + plugin.vault.chat.getGroupPrefix(p.getWorld(), args[0]) + args[0] +" connecté.");

				}
				if (j == 0){
					p.sendMessage(ChatColor.BLUE +" Il n'y a actuellement aucun "+ ChatColor.GREEN + args[0] + " connecté");
				}
				return true;
			}
			else {
				p.sendMessage(ChatColor.RED +"Vous ne pouvez spécifier qu'un seul groupe à la fois !");
			}
	}
		return true;
}
}
