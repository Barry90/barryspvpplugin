package me.Barry1990.barryspvpplugin;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;


public class ChestSpawner extends BukkitRunnable {
	
	private Chest pvpchest = null;
	private Random random = new Random();
	private World w;
	private BarrysPVPPlugin p;
	
	private final int minheight = 1;

	public ChestSpawner(World world){
		this.w = world;
	}
	
	public ChestSpawner(BarrysPVPPlugin plugin, String worldname) throws PVPPluginException{
		if (Bukkit.getWorld(worldname) == null) {
			throw new PVPPluginException(plugin, "Es kann keine Welt mit dem Namen \"" + worldname +"\" gefunden werden.");
		}
		
		this.w = Bukkit.getWorld(worldname);
		this.p = plugin;
	}
	
	@Override
	public void run() {
	
		while (true) {
			//Random Y,Z Coordinates
			int x = randInt(-50, 50);
			int z = randInt(-50, 50);
			p.getLogger().info(" dam dam");
			//find the first solid block
			Location chestloc = null;
			for (int y = 255; y > this.minheight; y--) {
				
				if (this.w.getBlockAt(x, y, z).getType() == Material.AIR){
					
					if (this.IsSolid(this.w.getBlockAt(x, y-1, z).getType()) ) {					
						chestloc = new Location(this.w, x, y, z);
						p.getLogger().info("Kiste bei x: " + x +" y: "+y+" z: "+z);
						break;
					}
					
				}
				
			}
			
			//TODO: test for chest  x � 1 ; y � 1
			
			if (chestloc != null) {
				this.w.getBlockAt(chestloc).setType(Material.CHEST);				
				p.getLogger().info("Kiste erschaffen");
				pvpchest = (Chest) chestloc.getBlock().getState();				
				Inventory inv = pvpchest.getInventory();
				InventoryGenerator iventorygenerator = new InventoryGenerator();
				p.getLogger().info("Inventar erstellt");
				
				inv.setContents(iventorygenerator.getInventory().getContents());
				p.getLogger().info("Inventar gesetzt");
				break;
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
