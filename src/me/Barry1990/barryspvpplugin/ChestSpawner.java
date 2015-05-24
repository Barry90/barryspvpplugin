package me.Barry1990.barryspvpplugin;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;


public class ChestSpawner extends Thread {
	
	private Chest pvpchest = null;
	private Random random = new Random();
	private World w;
	
	private final int minheight = 30;

	public ChestSpawner(World world){
		this.w = world;
	}
	
	@Override
	public void run() {
	
		while (true) {
			//Random Y,Z Coordinates
			int x = randInt(-50, 50);
			int z = randInt(-50, 50);
			
			//find the first solid block
			Location chestloc = null;
			for (int y = 255; y > this.minheight; y--) {
				
				if (this.w.getBlockAt(x, y, z).getType() == Material.AIR){
					
					if (this.IsSolid(this.w.getBlockAt(x, y-1, z).getType()) ) {					
						chestloc = new Location(this.w, x, y, z);
						break;
					}
					
				}
				
			}
			
			//TODO: test for chest  x ± 1 ; y ± 1
			
			if (chestloc != null) {
				this.w.getBlockAt(chestloc).setType(Material.CHEST);				
				
				pvpchest = (Chest) chestloc.getBlock().getState();				
				Inventory inv = pvpchest.getInventory();
				
				InventoryGenerator iventorygenerator = new InventoryGenerator();
				
				inv.setContents(iventorygenerator.getInventory().getContents());
			}
			
		}
		
		
	}
	
	private int randInt(int min, int max) {
	    int randomNum = random.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	
	private boolean IsSolid(Material material) {	
		//TODO: add tranperant blocks, e.g torches
		if (material == Material.AIR)
			return false;
		if (material == Material.WATER)
			return false;
		if (material == Material.LAVA)
			return false;
		
		return true;
	}
	
}
