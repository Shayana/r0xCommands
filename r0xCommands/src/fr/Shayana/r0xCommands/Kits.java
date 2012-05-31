package fr.Shayana.r0xCommands;

import java.util.Iterator;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Kits {

	protected r0xCommands plugin;
	
	private Player p;
	private String kit;

public Kits(r0xCommands plugin){
	this.plugin = plugin;
}

	public Kits(Player player, String kit) {
		this.p = player;
		this.kit = kit;
	}

	public boolean canGet(Player p, String kit){
		if (plugin.config.kit(kit)== null){
			p.sendMessage(ChatColor.YELLOW +"Ce kit n'existe pas, tapez /kits pour avoir une liste des kits disponibles.");
			return false;
		}
		else if (!(plugin.vault.perms.has(p, "kits."+kit.toLowerCase())) || (!plugin.vault.perms.playerInGroup(p, kit))){
			p.sendMessage(ChatColor.YELLOW +"Vous n'avez pas accès Ã  ce kit.");
			return false;
		}
		else {
			return true;
		}
	}
	


	public void giveKit(){
		if (canGet(p, kit)){
			Iterator<String> it = plugin.config.kit(kit).iterator();
			while (it.hasNext()){
			Material mat =  Material.matchMaterial(it.next());
			ItemStack item = new ItemStack(mat, 1);
			p.getInventory().addItem(item);
			}
		}
		else {
			return;
		}
}
	
	public void sendList(){
		p.sendMessage(ChatColor.GRAY + "Kits existants :");
		Iterator<String> it = plugin.config.kits().iterator();
			while(it.hasNext()){
				String kit = it.next();
				if (canGet(p,kit)){
					p.sendMessage(ChatColor.GREEN + kit);
				}
				else{
					p.sendMessage(ChatColor.RED + kit);
				}
			}
	}
}