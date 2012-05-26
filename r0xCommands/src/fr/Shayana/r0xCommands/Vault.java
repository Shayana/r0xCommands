package fr.Shayana.r0xCommands;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.plugin.RegisteredServiceProvider;


public class Vault {
	protected r0xCommands plugin;
    public  Economy econ = null;
    public  Permission perms = null;
    public  Chat chat = null;

    	public Vault(r0xCommands plugin){
    		this.plugin = plugin;
    	}
    	

       
public void setupVault(){
	
	this.setupChat();
	this.setupEconomy();
	this.setupPermissions();
}
    
	private boolean setupEconomy() {
		
        if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        
        RegisteredServiceProvider<Economy> rsp = plugin.getServer().getServicesManager().getRegistration(Economy.class);
        
        if (rsp == null) {
            
        	return false;
        }
        
        econ = rsp.getProvider();
        return econ != null;
    }

	private boolean setupChat() {
		
		if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
		
        RegisteredServiceProvider<Chat> rsp = plugin.getServer().getServicesManager().getRegistration(Chat.class);
        
        if(rsp == null) {
        	
        	return false;
        }
        
        chat = rsp.getProvider();       
        return chat != null;
    }

	private boolean setupPermissions() {
		if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
		
        RegisteredServiceProvider<Permission> rsp = plugin.getServer().getServicesManager().getRegistration(Permission.class);
        
        if(rsp == null) {
        	
        	return false;
        }
        
        perms = rsp.getProvider();
        return perms != null;
  }
}
