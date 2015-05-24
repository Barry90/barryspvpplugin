package me.Barry1990.barryspvpplugin;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;


public class ItemParser {

	private ItemStack itemstack;
	
	public ItemParser() {
		this(Material.STONE,1);
	}
	
	public ItemParser(Material material, int amount) {	
		this.createItem(material, amount);
	}
	
	public void createItem(Material material, int amount) {		
		this.itemstack = new ItemStack(Material.GOLD_INGOT, amount);		
	}
	
	public void addMataData() {
		//this.itemstack.setData(data);
	}
	
}
