package fr.Shayana.r0xCommands;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Teleport {
	
	r0xCommands plugin;
	Player player;
	String args[];
	
	private HashMap<Player, Player> tpMap;
	
	public Teleport(r0xCommands plugin, Player player, String args[]) {
		
		this.plugin = plugin;
		this.player = player;
		this.args = args;
	}
	
	public void Commands_tp() {
		
			if (args.length < 1){
		  	
				player.sendMessage(ChatColor.RED +"Vous devez spécifier un endroit ou vous téléporter !");
			  	
				return;	
		     }  	
			else if (args.length > 1){
			  	
		       if (!(plugin.vault.perms.playerInGroup(player, plugin.config.superTP()))){
			  	
		          player.sendMessage(ChatColor.RED +"Vous n'avez pas la permission de faire ceci !");
			  	
		       }
		       else {
			  	
		          Player p1 = plugin.getServer().getPlayer(args[0]);
			  	
		          Player p2 = plugin.getServer().getPlayer(args[1]);
			  	
		          if (p1 == null ||  p2 == null){
			  	
		        	  player.sendMessage(ChatColor.RED+ "Erreur sur l'un des noms de joueurs.");
			  	
		            return;
		          }
			  	
		          p1.sendMessage(ChatColor.BLUE +player.getDisplayName()+ " vous téléporte à "+ p2.getDisplayName());
			  	
		          p2.sendMessage(ChatColor.BLUE +player.getDisplayName()+ " téléporte "+ p1.getDisplayName() +" à votre position");
			  	
		          p1.teleport(p2);
		          
		       }
		       
		       return;
		       
			}
			else if (args.length == 1){
				
				Player p1 = plugin.getServer().getPlayer(args[0]);
			  	
		        if (p1 == null) {
			  	
		          player.sendMessage(ChatColor.RED + args[0] + " n'est pas en ligne actuellement.");
			  	
		          return;
			  	
		        }
			  	
		        if (plugin.vault.perms.playerInGroup(player, plugin.config.superTP())){
			  	
				player.teleport(p1);
			  	
		        }
			  	
		        else {
			  	
		          player.sendMessage(ChatColor.BLUE+ "Demande de téléportation envoyée à "+ p1.getDisplayName());
		  	
		          p1.sendMessage(ChatColor.BLUE + player.getDisplayName()+" souhaiterais se téléporter à vous, tapez "+ ChatColor.GREEN +  "/accept "+ ChatColor.BLUE +"pour accepter, ou "+ ChatColor.BLUE +"/deny "+ ChatColor.GREEN +"pour refuser.");
			  	
		          tpMap.put(p1, player);
		        }
			}
		
		return;
	}
	
	public void Commands_tph() {
		
		Player p1 = plugin.getServer().getPlayer(args[0]);
	  	
	      if (p1 == null){
		  	
	        player.sendMessage(ChatColor.RED + args[0] + " n'est pas en ligne actuellement.");
		  	
	        return;
	      }
		  	
	      if (plugin.vault.perms.playerInGroup(player, plugin.config.superTP())){
		  	
	        player.teleport(p1);
	      }	
	      else {
		  	
	        tpMap.put(player, p1);
	      }
		
		return;
	}
}
