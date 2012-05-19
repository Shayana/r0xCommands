package fr.Shayana.r0xCommands;

public class mySQLManager {

	
	protected r0xCommands plugin;
	protected configManager config;
	
	boolean homeDefini;
	
	public mySQLManager(r0xCommands plugin){
		this.plugin = plugin;
	}
	
	public mySQLManager(configManager config){
		this.config = config;
	}
}
