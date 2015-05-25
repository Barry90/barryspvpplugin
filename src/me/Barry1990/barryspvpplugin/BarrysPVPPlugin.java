package me.Barry1990.barryspvpplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
		
		//chestspawner.start();
		
		
		//this.getDataFolder()
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}

		Player player = (Player) sender;
		
		if (command.getName().equalsIgnoreCase("test")) {
			if (this.chestspawner != null){
				this.getLogger().info("okay");
				
				//TODO: START/STOP the Task
				/* not working */
				try {
					chestspawner.runTaskTimer(this, 0L, (long) 20*30);
				} catch (IllegalStateException e) {
					chestspawner.cancel();
				}
				
				player.sendMessage("running test");
			}
			return true;
		}
		
		return false;
	}
}
