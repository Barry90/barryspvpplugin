package me.Barry1990.barryspvpplugin;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;


public class BarrysPVPPlugin extends JavaPlugin {

	private ChestSpawner chestspawner = null; //new ChestSpawner();
	
	@Override
	public void onDisable() {
		
		this.getLogger().info(ChatColor.YELLOW + this.getDescription().getFullName() + " disabled.");
		
	}
	
	@Override
	public void onEnable() {
		this.getLogger().info(ChatColor.AQUA + this.getDescription().getFullName() + " enabled.");
		this.getLogger().info(ChatColor.AQUA +"   version" + this.getDescription().getVersion());
		
		Settings.loadConfig(this);
		
		try {
			chestspawner = new ChestSpawner(this, Settings.worldname);
		} catch (PVPPluginException e) {
			e.printStackTrace();
		}
		
		if (chestspawner == null){
			return;			
		}
		
		chestspawner.start();
		
		
		//this.getDataFolder()
	}
}
