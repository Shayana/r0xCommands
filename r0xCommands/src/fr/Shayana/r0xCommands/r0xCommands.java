package fr.Shayana.r0xCommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class r0xCommands extends JavaPlugin {
	
	protected configManager config;
	protected mySQLManager mysql;
	protected Vault vault;
	protected commandManager cmd;
	
	@Override
	public void onEnable(){
		this.config = new configManager(this);
		this.mysql = new mySQLManager(this, config);
		this.cmd = new commandManager(this);
		this.vault = new Vault(this);
		vault.setupVault();
	}


	@Override
	public void onDisable(){

	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String args[]) {
		
		return cmd.onCommand(sender, command, label, args);
	}
}