package me.Barry1990.barryspvpplugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
		
		
		//TESTBEREICH
		/*

		ItemStack a = new ItemStack(Material.TORCH);
		a.setAmount(12);
		a.getItemMeta().setDisplayName("This is a Torch");
		
		ItemParser ie = new ItemParser();
		this.getLogger().info(ie.itemStackToString(a));
		
		
		ItemStack b = new ItemStack(Material.DIAMOND_HELMET);
		b.setAmount(1);
		b.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		b.addEnchantment(Enchantment.OXYGEN, 1);
		b.addEnchantment(Enchantment.DURABILITY, 2);
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.WHITE+"Reload all Plugins");
		ItemMeta im = b.getItemMeta();
		im.setDisplayName("Götterhelm");
		im.setLore(lore);
		b.setItemMeta(im);
		
		this.getLogger().info(ie.itemStackToString(b));
		*/

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
