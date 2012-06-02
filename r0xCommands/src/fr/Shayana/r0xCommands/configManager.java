package fr.Shayana.r0xCommands;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<String> rules() {
		
		ArrayList<String> rules = (ArrayList<String>) plugin.getConfig().getStringList("rules");
		return rules;
	}
	
	public List<String> kit(String group){
		ArrayList<String> kit = (ArrayList<String>) plugin.getConfig().getStringList("Kits."+ group);
		return kit;
	}
	
	public List<String>kits(){
		ArrayList<String> kits = (ArrayList<String>)plugin.getConfig().getStringList("Kits");
		return kits;
	}
	
	
	
//Informations Teleportation
	public String superTP(){
		return plugin.getConfig().getString("SuperTP");
	}
	
	
}
