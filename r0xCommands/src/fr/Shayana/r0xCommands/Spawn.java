package fr.Shayana.r0xCommands;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Spawn {
	
	Player player;
	Location location;
	
	double x = 0;
	double y = 0;
	double z = 0;
	
	public Spawn(Player player) {
		
		this.player = player;
	}
	
	//Consultation de la BDD
	
	public void Commands_spawn() {
		
		location = new Location(player.getWorld(), x, y, z);
		
		player.teleport(location);
	}
}
