package me.Barry1990.barryspvpplugin;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;


public class barryspvpplugin extends JavaPlugin {

	private ChestSpawner chestspawner = new ChestSpawner();
	
	@Override
	public void onEnable() {
		this.getLogger().info(ChatColor.AQUA + this.getDescription().getFullName() + " enabled.");
		this.getLogger().info("   version" + this.getDescription().getVersion());
	}
	
	@Override
	public void onDisable() {
		
		this.getLogger().info(ChatColor.YELLOW + this.getDescription().getFullName() + " disabled.");

	}
}
