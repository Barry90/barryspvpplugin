package me.Barry1990.barryspvpplugin;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

/** Generator for Chest Inventory
 *
 */
public class InventoryGenerator {

	private Inventory inventory = null;
	private final int chestsize = 27;
	
	public InventoryGenerator() {
		this.inventory = Bukkit.createInventory(null, chestsize, "");
		this.generateItems();
	}
	
	private void generateItems() {
		ItemElement ip = new ItemElement();
		for (int i = 0; i < this.chestsize; i++) {
			//TODO: Randomize the items
			ip.createItem(ItemElement.getRandomMaterial(),1);
			this.inventory.setItem(i, ip.getItemStack());
			
		}
				
	}
	
	public Inventory getInventory() {
		return this.inventory;
	}
}
