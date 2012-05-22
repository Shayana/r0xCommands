package fr.Shayana.r0xCommands;

public class configManager {
	
	protected r0xCommands plugin;
	
	public configManager(r0xCommands plugin){
		this.plugin = plugin;
	}
	

//Informations MySQL	
	
	public String url(){
		return plugin.getConfig().getString("MySQL.url");
	}
	
	public String pass(){
		return plugin.getConfig().getString("MySQL.password");
	}
	
	public String user(){
		return plugin.getConfig().getString("MySQL.username");
	}
	
	public String port(){
		return plugin.getConfig().getString("MySQL.port");
	}
	
	public String database(){
		return plugin.getConfig().getString("MySQL.database");
	}
	
	
	
//Informations Teleportation
	public String superTP(){
		return plugin.getConfig().getString("SuperTP");
	}
	
	
}
