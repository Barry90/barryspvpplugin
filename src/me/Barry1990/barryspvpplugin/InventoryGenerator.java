package me.Barry1990.barryspvpplugin;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

/** Generator for Chest Inventory
 *
 */
public class InventoryGenerator {

	private Inventory inventory = null;
	private final int chestsize = 24;
	
	public InventoryGenerator() {
		 Bukkit.createInventory(null, chestsize, "");
		 this.generateItems();
	}
	
	public void generateItems() {
		ItemParser ip = new ItemParser();
		for (int i = 0; i < this.chestsize; i++) {
			//TODO: Randomize the items
			this.inventory.setItem(i, ip.getItemStack());
			
		}
				
	}
}
