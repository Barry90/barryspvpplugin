package me.Barry1990.barryspvpplugin;

import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemParser {

	private ItemStack itemstack;
	
	public ItemParser() {
		this(Material.STONE,1);
	}
	
	public ItemParser(Material material, int amount) {	
		this.createItem(material, amount);
	}
	
	public void createItem(Material material, int amount) {		
		this.itemstack = new ItemStack(material, amount);		
	}
	
	public void setDisplayName(String itemname) {
		ItemMeta im = this.itemstack.getItemMeta();
		im.setDisplayName(itemname);
		this.itemstack.setItemMeta(im);
	}
	
	public void setItemLore(List<String> itemlore) {
		ItemMeta im = this.itemstack.getItemMeta();
		im.setLore(itemlore);
		this.itemstack.setItemMeta(im);
	}
	
	public static Material getRandomMaterial() {
		Random random = new Random();
        int x = random.nextInt(Material.class.getEnumConstants().length);
        return Material.class.getEnumConstants()[x];
    }
	
	public ItemStack getItemStack() {
		return this.itemstack;
	}
}
