package fr.Shayana.r0xCommands;

import org.bukkit.plugin.java.JavaPlugin;

public class r0xCommands extends JavaPlugin {

	
	protected configManager config;
	protected mySQLManager mysql;
	protected Vault vault;
	@Override
	public void onEnable(){
		this.config = new configManager(this);
		this.mysql = new mySQLManager(this);
		this.vault = new Vault(this);
		vault.setupVault();
	}
	
	
	@Override
	public void onDisable(){
		
	}
}
