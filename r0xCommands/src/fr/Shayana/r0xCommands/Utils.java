package fr.Shayana.r0xCommands;

import java.util.Iterator;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
				
				player.sendMessage(ChatColor.BLUE +"Il n'y a actuellement aucun "+ ChatColor.GREEN + args[0] + " connect�");
			}
			
			return;
		}
		else {
			
			player.sendMessage(ChatColor.RED +"Vous ne pouvez sp�cifier qu'un seul groupe � la fois !");
		}
	}
	
	public void Commands_suicide() {
		
		player.setHealth(0);
		player.getServer().broadcastMessage(ChatColor.RED + player.getName() + " ne tient plus � la vie");
	}
	
	public void Commands_kill() {
		
		if(args.length == 0) {
			
			player.setHealth(0);
			player.getServer().broadcastMessage(ChatColor.RED + player.getName() + " ne tient plus � la vie");
			
			return;
		}
		if(args.length == 1) {
			
			Player p1 = plugin.getServer().getPlayer(args[0]);
			
			if(p1 == null) {
				
				player.sendMessage(ChatColor.RED + args[0] + " n'est pas en ligne actuellement.");
			}
			else {
				
				p1.setHealth(0);
				plugin.getServer().broadcastMessage(ChatColor.RED + p1.getName() + " n'est pass tr�s amis avec " + player.getName());
			}
		}
	}
	
	public void Commands_give() {
		
		if(args.length == 3) {
			
			Player p = plugin.getServer().getPlayer(args[0]);
			Material mat = Material.matchMaterial(args[1]);
			int nbMat = Integer.parseInt(args[2]);
			
			if(p == null) {
				
				player.sendMessage(ChatColor.RED + args[0] + " n'est pas en ligne actuellement.");
				return;
			}
			if(mat == null) {
				
				player.sendMessage(ChatColor.RED + args[1] + " n'est pas un mat�riel valide.");
				return;
			}
			if(nbMat > 64) {
				
				int nbStack = nbMat/64;
				int reste = nbMat%64;
				
				for(int j = 0; j < nbStack; j++) {
					
					p.getInventory().addItem(new ItemStack[] { new ItemStack(mat, 64) });
				}
				
				p.getInventory().addItem(new ItemStack[] { new ItemStack(mat, reste) });
			}
			else {
				
				p.getInventory().addItem(new ItemStack[] { new ItemStack(mat, nbMat) });
			}
		}
		else if(args.length == 2) {
			
			if(!args[0].equalsIgnoreCase("list")) {
				
				Material mat = Material.matchMaterial(args[0]);
				int nbMat = Integer.parseInt(args[1]);
				
				if(mat == null) {
					
					player.sendMessage(ChatColor.RED + args[0] + " n'est pas un mat�riel valide.");
					return;
				}
				if(nbMat > 64) {
					
					int nbStack = nbMat/64;
					int reste = nbMat%64;
					
					for(int j = 0; j < nbStack; j++) {
						
						player.getInventory().addItem(new ItemStack[] { new ItemStack(mat, 64) });
					}
					
					player.getInventory().addItem(new ItemStack[] { new ItemStack(mat, reste) });
				}
				else {
					
					player.getInventory().addItem(new ItemStack[] { new ItemStack(mat, nbMat) });
				}
			}
			
			else {
				
				int nbPage = Integer.parseInt(args[1]);
				int nbPageTot = 26;
				int nbLigne = 9;
				int ligne = 0;
				int page = 0;
				
							
				player.sendMessage(ChatColor.GOLD + "----- Page : " + nbPage + " sur : " + nbPageTot + " -----");
				
				for(Material mat : Material.values()) {
					
					if(ligne < nbLigne) {
						
						if(page == nbPage) {
							player.sendMessage(mat.name());
						}
						ligne++;
					}
					else {
						
						ligne = 0;
						page++;
					}
				}
			}
			
			
		}
		else {
			
			player.sendMessage(ChatColor.RED + "Veuillez respecter la syntaxe de la commande.");
		}
	}
	
	public void Commands_time() {
		
		if(args.length == 1) {
			
			if(args[0].equalsIgnoreCase("day")) {
				
				player.getWorld().setTime(0);
			}
			else if(args[0].equalsIgnoreCase("night")) {
				
				player.getWorld().setTime(12000);
			}
			else {
				
				player.sendMessage(ChatColor.RED + "Argument invialide");
			}
		}
		else if(args.length == 2 && args[0].equalsIgnoreCase("set")) {
			
			int time = Integer.parseInt(args[1]);
			player.getWorld().setTime(time);
		}
		else {
			
			player.sendMessage(ChatColor.RED + "Veuillez respecter la syntaxe de la commande.");
		}
	}
	
	public void Commands_clear() {
		
		if(args.length == 0) {
			
			player.getInventory().clear();
		}
		else if(args.length == 1) {
			
			if(player.hasPermission("rc.clear.admin")) {
				
				Player p = plugin.getServer().getPlayer(args[0]);
				
				if(p == null) {
					
					player.sendMessage(ChatColor.RED + args[0] + " n'est pas en ligne actuellement.");
				}
				else {
					
					p.getInventory().clear();
				}
			}
			else {
				
				player.sendMessage(ChatColor.RED +"Vous n'avez pas la permission de faire ceci !");
			}
		}
		else {
			
			player.sendMessage(ChatColor.RED + "Veuillez respecter la syntaxe de la commande.");
		}
	}
	
	public void Commands_me() {
		
		StringBuilder builder = new StringBuilder();
		
		for(int i = 0; i < args.length ; i++) {
			
			builder.append(args[i]);
			builder.append(" ");
		}
		
		plugin.getServer().broadcastMessage(player.getDisplayName() + " " + builder.toString());
	}
	
	public void Commands_rules() {
		
		Iterator<String> rules = plugin.config.rules().iterator();
		int i = 1;
		
		while(rules.hasNext()) {
			
			StringBuilder build = new StringBuilder();
			build.append("[" + i + "] : " + rules.next());
			player.sendMessage(build.toString());
			i++;
		}
	}
	
	public void Commands_whois() {
		
		if(args.length != 1) {
			
			player.sendMessage(ChatColor.RED + "Veuillez respecter la syntaxe de la commande.");
		}
		else {
			
			Player p = plugin.getServer().getPlayer(args[0]);
			
			if(p == null) {
				
				player.sendMessage(ChatColor.RED + args[0] + " n'est pas en ligne actuellement.");
				return;
			}
			
			player.sendMessage("Voici les différentes informations sur " + p.getDisplayName() + " : ");
			player.sendMessage("- Le rang : " + plugin.vault.perms.getPrimaryGroup(p));
			player.sendMessage("- Le monde : " + p.getWorld().getName());
			player.sendMessage("- Le mode de jeu : " + p.getGameMode().getValue());
			player.sendMessage("- Le niveau : " + p.getLevel());
		}
	}
	
	public void Commands_broadcast() {
		
		StringBuilder build = new StringBuilder();
		
		for(int i = 0; i < args.length ; i++) {
			
			String s = args[i];
			
			if(s.contentEquals(plugin.config.color("red"))) {
				
				build.append(ChatColor.RED + s.replace(plugin.config.color("red"), ""));
			}
			else if(s.contentEquals(plugin.config.color("blue"))) {
				
				build.append(ChatColor.BLUE + s.replace(plugin.config.color("blue"), ""));
			}
		}
	}
}
