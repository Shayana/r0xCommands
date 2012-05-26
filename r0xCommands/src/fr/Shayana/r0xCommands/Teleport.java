package fr.Shayana.r0xCommands;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
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
		  	
				player.sendMessage(ChatColor.RED +"Vous devez sp�cifier un endroit ou vous t�l�porter !");
			  	
				return;	
		    }
			else if (args.length == 1) {
				
				Player p1 = plugin.getServer().getPlayer(args[0]);
			  	
		        if (p1 == null) {
			  	
		          player.sendMessage(ChatColor.RED + args[0] + " n'est pas en ligne actuellement.");
			  	
		          return;
			  	
		        }
			  	
		        if (plugin.vault.perms.playerInGroup(player, plugin.config.superTP())) {
			  	
				player.teleport(p1);
			  	
		        }
			  	
		        else {
			  	
		          player.sendMessage(ChatColor.BLUE + "Demande de t�l�portation envoy�e � " + p1.getDisplayName());
		  	
		          p1.sendMessage(ChatColor.BLUE + player.getDisplayName()+" souhaiterais se t�l�porter � vous, tapez "+ ChatColor.GREEN +  "/accept "+ ChatColor.BLUE +"pour accepter, ou "+ ChatColor.BLUE +"/deny "+ ChatColor.GREEN +"pour refuser.");
			  	
		          tpMap.put(p1, player);
		        }
			}
			else if (args.length == 2) {
			  	
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
			  	
		          p1.sendMessage(ChatColor.BLUE +player.getDisplayName()+ " vous t�l�porte � "+ p2.getDisplayName());
			  	
		          p2.sendMessage(ChatColor.BLUE +player.getDisplayName()+ " t�l�porte "+ p1.getDisplayName() +" � votre position");
			  	
		          p1.teleport(p2);
		          
		       }
		       
		       return;    
			}
			else if(args.length == 3) {
				
				if((plugin.vault.perms.playerInGroup(player, plugin.config.superTP()))) {
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
		
		return;
	}
	
	public void Commands_tph() {
		
		if(args.length > 0) {
			
			Player p1 = plugin.getServer().getPlayer(args[0]);
		  	
		      if (p1 == null){
			  	
		        player.sendMessage(ChatColor.RED + args[0] + " n'est pas en ligne actuellement.");
			  	
		        return;
		      }
			  	
		      if (plugin.vault.perms.playerInGroup(player, plugin.config.superTP())) {
			  	
		        p1.teleport(player);
		      }	
		      else {
			  	
		    	  p1.sendMessage(ChatColor.BLUE + player.getDisplayName()+" souhaiterais vous t�l�porter � lui, tapez "+ ChatColor.GREEN +  "/accept "+ ChatColor.BLUE +"pour accepter, ou "+ ChatColor.BLUE +"/deny "+ ChatColor.GREEN +"pour refuser.");
		    	  tpMap.put(player, p1);
		      }
			
			return;
		}
		else {
			
			player.sendMessage(ChatColor.RED + "Veuillez indiquer un joueur");
		}
	}
	
	public void Commands_put() {
		
		if(plugin.vault.perms.playerInGroup(player, plugin.config.superTP())) {
			
			if(args.length == 4) {
				
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
				p1.sendMessage(ChatColor.RED + player.getName() + " vous a t�l�port� � la position " + x + " " + y + " " + z);
			}
			else {
				
				player.sendMessage(ChatColor.RED + "Veuillez respecter la syntaxe");
			}
		}
		else {
			
			player.sendMessage(ChatColor.RED + "Vous n'avez pas la permission de faire ceci !");
		}
	}
	
	public void Commands_tpall() {
		
		if(plugin.vault.perms.playerInGroup(player, plugin.config.superTP())) {
			
			if(args.length == 0) {
				
				player.getServer().broadcastMessage(ChatColor.RED + "Attention, vous allez �tre tous t�l�porter vers " + player.getName());
				
				for(World w : player.getServer().getWorlds()) {
					
					for(Player p : w.getPlayers()) {
						
						p.teleport(player);
					}
				}
			}
			else if(args.length == 1) {
				
				Player p1 = plugin.getServer().getPlayer(args[0]); 
				
				player.getServer().broadcastMessage(ChatColor.RED + "Attention, vous allez �tre tous t�l�porter vers " + p1.getName());
				p1.sendMessage(ChatColor.RED + "Attention, tout les joueurs vont se t�l�porter sur vous");
					
				for(World w : player.getServer().getWorlds()) {
						
					for(Player p : w.getPlayers()) {
							
						p.teleport(p1);
					}
				}
			}
		}
		else {
			
			player.sendMessage(ChatColor.RED + "Vous n'avez pas la permission de faire ceci !");
		}
	}
	
	public void Command_accept() {
		
		for(Entry<Player, Player> entry : tpMap.entrySet()) {
			
			Player key = entry.getKey();
			Player value = entry.getValue();
			
			if(key == player) {
				
				value.teleport(player);
				tpMap.remove(key);
				return;
			}
			else if(value == player) {
				
				player.teleport(key);
				tpMap.remove(key);
				return;
			}
		}
		
		player.sendMessage(ChatColor.RED + "Personne ne vous a demand� une t�l�portation.");
	}

	public void Command_refuse() {
		
		for(Entry<Player, Player> entry : tpMap.entrySet()) {
			
			Player key = entry.getKey();
			Player value = entry.getValue();
			
			if(key == player) {
				
				value.sendMessage(ChatColor.RED + key.getName() + " a refus� votre invitation");
				tpMap.remove(key);
				return;
			}
			else if(value == player) {
				
				key.sendMessage(ChatColor.RED + value.getName() + " a refus� votre invition");
				tpMap.remove(key);
				return;
			}
		}
		
		player.sendMessage(ChatColor.RED + "Personne ne vous a envoy� d'invitation");
	}
	
	
}
