package me.Barry1990.barryspvpplugin;

import org.bukkit.plugin.java.JavaPlugin;


public class barryspvpplugin extends JavaPlugin {

	@Override
	public void onEnable() {
		
		this.getLogger().info("v" + this.getDescription().getVersion() + " enabled.");
	}
	
	@Override
	public void onDisable() {
		
		this.getLogger().info("v" + this.getDescription().getVersion() + " disabled.");

	}
}
