package me.Barry1990.barryspvpplugin;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;


public class Settings {
	private static final String configfilename = "config.yml";
	
	public static String worldname;
	public static final String path_worldname = "options.worldname";
	
	
	/*
	public static int spawnpointX;
	public static int spawnpointY;
	public static int spawnpointZ;
	public static int spawnareaRadius;
	*/
	
	
	public static void setDefaultConfig(BarrysPVPPlugin plugin) {
		
		
		FileConfiguration config = plugin.getConfig();
		File file = new File(plugin.getDataFolder(),configfilename);
		
		Settings.worldname = "world";	
		
		config.set(Settings.path_worldname,Settings.worldname);
		
		try {
			config.save(file);
		} catch (IOException e) {
			plugin.getLogger().info(ChatColor.RED + "Fehler beim Speichern von " + file.getAbsolutePath());
			e.printStackTrace();
		}
		
	}
	
	public static void loadConfig(BarrysPVPPlugin plugin) {

		File file = new File(plugin.getDataFolder(),configfilename);
		if (!file.exists()) {
			plugin.getLogger().info(ChatColor.YELLOW + "WARNUNG:  \"" + file.getAbsolutePath()+ "\" konnte nicht gefunden werden. ");
			setDefaultConfig(plugin);
			return;
		}
		FileConfiguration config = plugin.getConfig();
		if ((Settings.worldname = loadStringValue(config,Settings.path_worldname)) == "" ) {
			plugin.getLogger().info(ChatColor.YELLOW + "WARNUNG:  \"" + Settings.path_worldname + "\" ist nicht in der config. ");
			setDefaultConfig(plugin);			
		}
			
	}
	
	private static String loadStringValue(FileConfiguration config, String path) {
		if (config.contains(path)) 
			return config.getString(path);		
		else 
			return "";
		
	}
}
