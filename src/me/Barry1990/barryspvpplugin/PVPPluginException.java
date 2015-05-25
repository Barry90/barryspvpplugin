package me.Barry1990.barryspvpplugin;

import org.bukkit.ChatColor;


public class PVPPluginException extends Exception {

	private static final long serialVersionUID = -6783842665460452526L;
	
	public PVPPluginException(BarrysPVPPlugin plugin, String message) {
		this(message);
		plugin.getLogger().info(ChatColor.RED + "Es wurde eine PVPPluginException geworfen: \n    " + message);
    }
	
	
	private PVPPluginException(String message) {
        super(message);
    }
	
}
