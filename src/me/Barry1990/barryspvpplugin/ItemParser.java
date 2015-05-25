package me.Barry1990.barryspvpplugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;


public class ItemParser {
	
	private final static String STR_ITEMNAME = "MATERIAL_NAME";
	private final static String STR_AMOUNT = "AMOUNT";
	private final static String STR_CUSTOMNAME = "CUSTOMNAME";
	private final static String STR_LORE = "LORE";
	private final static String STR_ENCHANTMENTS = "ENCHANTMENTS";
	private final static String STR_DAMAGE = "DAMAGE";
	
	
	private Material material;
	private int amount;
	private String customName;
	private List<String> lore;
	private Map<Enchantment, Integer> enchantments;
		
	public void parseStringToItemStack(String string) {
		
		this.material = null;
		this.amount = 0;
		this.customName = null;
		this.lore = null;
		this.enchantments = null;
		
		if (string != null && string.length() > 0) {
			
			for (String str : string.split(";")) {
				String[] KeyValue = str.split(":");
				
				/* Material */
				if (KeyValue[0].equals(ItemParser.STR_ITEMNAME))
					if (KeyValue.length > 1 && KeyValue[1].length() > 0) 
						this.material = getMaterialFromString(KeyValue[1]);
				
				/* Anzahl */
				if (KeyValue[0].equals(ItemParser.STR_AMOUNT))
					if (KeyValue.length > 1 && KeyValue[1].length() > 0) 
						this.amount = stringToInt(KeyValue[1], 1);
						
				/* CustomName */
				if (KeyValue[0].equals(ItemParser.STR_CUSTOMNAME))
					if (KeyValue.length > 1 && KeyValue[1].length() > 0) 
						this.customName = KeyValue[1];
				
				/* Lore */
				if (KeyValue[0].equals(ItemParser.STR_LORE))
					if (KeyValue.length > 1) 
						this.lore = parseLoreString(KeyValue[1]);
				
				/* Enchantments */
				if (KeyValue[0].equals(ItemParser.STR_ENCHANTMENTS))
					if (KeyValue.length > 1) 
						this.enchantments = parseEnchantmentString(KeyValue[1]);
				
				//TODO: add damage value
				/*
				 * 
				 */
				
				
				//TODO: parse additional attributes	
				/*
				 * 
				 */
			}			
		} 
		
		//TODO: Create the ItemStack		
		
		/*
		 * 
		 */
		
	}
	
	public String itemStackToString(ItemStack item) {
		
		/* Lore to String*/
		String lorestring = "";
		List<String> lorelist = item.getItemMeta().getLore();
		if (lorelist != null) {
			for (int i =0; i < lorelist.size(); i++) {
				lorestring+=lorelist.get(i);
				if (i < lorelist.size()-1) 
					lorestring+= "\\";
			}
		}
		
		/* Enchantments to String */
		String enchstring = "";
		int i = 0;
		Map<Enchantment, Integer> itemench = item.getEnchantments();
		for (Enchantment ench: itemench.keySet()) {
			enchstring+=ench.getName()+"."+itemench.get(ench);
			i++;
			if (i < itemench.size())
				enchstring+=",";
		}
		
		/* DisplayName*/
		String displayName = item.getItemMeta().getDisplayName() == null ? "" : item.getItemMeta().getDisplayName();
		
		
		String parsedItem = String.format(
				"%s:%s;%s:%d;%s:%s;%s:%s;%s:%s",
				STR_ITEMNAME,item.getType().toString(),
				STR_AMOUNT,item.getAmount(),
				STR_CUSTOMNAME, displayName,
				STR_LORE,lorestring,
				STR_ENCHANTMENTS, enchstring);
		
		return parsedItem;
	}
	
	/** Convert a String to a Material */
 	private static Material getMaterialFromString(String string) {
	    if( string != null ) {
	        try {
	            return Material.valueOf(string.trim().toUpperCase());
	        } catch(IllegalArgumentException ex) {
	        }
	    }
	    return Material.AIR;
	}
	
 	/** Convert a String to an Enchantment */
	private static Enchantment getEnchantmentFromString(String string) {
	    if( string != null ) {
	    	return Enchantment.getByName(string.trim().toUpperCase());
	    }
	    return null;
	}
	
	/** Convert a formated String to an Enchantment Map */
	private static Map<Enchantment, Integer> parseEnchantmentString(String string) {
		Map<Enchantment, Integer> ret = new HashMap<Enchantment, Integer>();;
		if (string != null && string.length() > 0) {
			for (String str : string.split(",")) {
				String[] ench = str.split(".");
				ret.put(getEnchantmentFromString(ench[0]), stringToInt(ench[1],0));
			}			
		}
		return ret;
	}
	
	/** Convert a formated String to Lore-List*/
 	private static List<String> parseLoreString(String string) {
		if (string != null && string.length() > 0) {
			List<String> list = new ArrayList<String>();
			for (String str : string.split("\\")) {
				list.add(str);
			}
			return list;
		}
		return new ArrayList<String>();
	}
	
 	/** Convert String to int or to the default value on error*/
	private static int stringToInt(String string, int def) {
		try {
			int ret = Integer.parseInt(string);
			return ret;
		} catch (NumberFormatException e) {
			return def;
		}
	}
}
