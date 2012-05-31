package fr.Shayana.r0xCommands;

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
				
				player.sendMessage(ChatColor.BLUE +"Il y a actuellement "+ ChatColor.GREEN + i +" joueurs connectés.");
			}
			else if (i == 1) {
				
				player.sendMessage(ChatColor.BLUE +"Il y a actuellement "+ ChatColor.BLUE + i +" joueur connecté");
			}
			else if(i == 0) {
				
				player.sendMessage(ChatColor.RED +"Il n'y a aucun joueur connecté");
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
				
				player.sendMessage(ChatColor.BLUE +"Il y a actuellement "+ ChatColor.GREEN + j + plugin.vault.chat.getGroupPrefix(player.getWorld(), args[0]) + args[0] +" connectés.");
			}
			else if (j == 1) {
				
				player.sendMessage(ChatColor.BLUE +"Il y a actuellement "+ ChatColor.GREEN + j + plugin.vault.chat.getGroupPrefix(player.getWorld(), args[0]) + args[0] +" connecté.");
			}
			else if(j == 0) {
				
				player.sendMessage(ChatColor.BLUE +"Il n'y a actuellement aucun "+ ChatColor.GREEN + args[0] + " connecté");
			}
			
			return;
		}
		else {
			
			player.sendMessage(ChatColor.RED +"Vous ne pouvez spécifier qu'un seul groupe à la fois !");
		}
	}
	
	public void Commands_suicide() {
		
		player.setHealth(0);
		player.getServer().broadcastMessage(ChatColor.RED + player.getName() + " ne tient plus à la vie");
	}
	
	public void Commands_kill() {
		
		if(args.length == 0) {
			
			player.setHealth(0);
			player.getServer().broadcastMessage(ChatColor.RED + player.getName() + " ne tient plus à la vie");
			
			return;
		}
		if(args.length == 1) {
			
			Player p1 = plugin.getServer().getPlayer(args[0]);
			
			if(p1 == null) {
				
				player.sendMessage(ChatColor.RED + args[0] + " n'est pas en ligne actuellement.");
			}
			else {
				
				p1.setHealth(0);
				plugin.getServer().broadcastMessage(ChatColor.RED + p1.getName() + " n'est pass très amis avec " + player.getName());
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
				
				player.sendMessage(ChatColor.RED + args[1] + " n'est pas un matériel valide.");
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
			
			Material mat = Material.matchMaterial(args[0]);
			int nbMat = Integer.parseInt(args[1]);
			
			if(mat == null) {
				
				player.sendMessage(ChatColor.RED + args[0] + " n'est pas un matériel valide.");
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
		else if(args.length == 1 && args[0].equalsIgnoreCase("list")) {
					
			for(Material mat : Material.values()) {
				
				player.sendMessage(mat.name());
			}
		}
		else {
			
			player.sendMessage(ChatColor.RED + "Veuillez respecter la syntaxe de la commande.");
		}
	}
}
