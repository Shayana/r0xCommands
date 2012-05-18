package fr.Shayana.r0xCommands;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class commandManager {

	protected r0xCommands plugin;
	private HashMap<Player, Player> tpMap;
	public commandManager(r0xCommands plugin){
		this.plugin = plugin;
		this.tpMap = new HashMap<Player, Player>();
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
		
		if (label.equalsIgnoreCase("tp")){
			if (args.length < 1){
				p.sendMessage(ChatColor.RED +"Vous devez spécifier un endroit ou vous téléporter !");
				return true;
			}
			if (args.length > 1){
				if (!(plugin.vault.perms.playerInGroup(p, plugin.config.superTP()))){
					p.sendMessage(ChatColor.RED +"Vous n'avez pas la permission de faire ceci !");
				}
				else {
					Player p1 = plugin.getServer().getPlayer(args[0]);
					Player p2 = plugin.getServer().getPlayer(args[1]);
					if (p1 == null ||  p2 == null){
						p.sendMessage(ChatColor.RED+ "Erreur sur l'un des noms de joueurs.");
						return true;
					}
					p1.sendMessage(ChatColor.BLUE +p.getDisplayName()+ " vous téléporte à "+ p2.getDisplayName());
					p2.sendMessage(ChatColor.BLUE +p.getDisplayName()+ " téléporte "+ p1.getDisplayName() +" à votre position");
					p1.teleport(p2);
				}
				return true;
			}
			
			if (args.length == 1){
				
				
				Player p1 = plugin.getServer().getPlayer(args[0]);
				if (p1 == null){
					p.sendMessage(ChatColor.RED + args[0] + " n'est pas en ligne actuellement.");
					return true;
				}
				if (plugin.vault.perms.playerInGroup(p, plugin.config.superTP())){
					p.teleport(p1);
				}
				else{
				
					p.sendMessage(ChatColor.BLUE+ "Demande de téléportation envoyée à "+ p1.getDisplayName());
					p1.sendMessage(ChatColor.BLUE + p.getDisplayName()+" souhaiterais se téléporter à vous, tapez "+ ChatColor.GREEN +  "/accept "+ ChatColor.BLUE +"pour accepter, ou "+ ChatColor.BLUE +"/deny "+ ChatColor.GREEN +"pour refuser.");
					tpMap.put(p1, p);
			}
			}
		}
		
		
		if (label.equalsIgnoreCase("tph") || label.equalsIgnoreCase("bring")){
			
			Player p1 = plugin.getServer().getPlayer(args[0]);
			if (p1 == null){
				p.sendMessage(ChatColor.RED + args[0] + " n'est pas en ligne actuellement.");
				return true;
			}
			if (plugin.vault.perms.playerInGroup(p, plugin.config.superTP())){
				p.teleport(p1);
			}
			else {
				tpMap.put(p, p1);
			}
		}
		return true;
}
}
