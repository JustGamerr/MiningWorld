package dev.alterum.miningworld.menus;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import dev.alterum.miningworld.MiningWorld;
import dev.alterum.miningworld.utils.InventoryUtils;

public class Transport extends InventoryUtils {
	
	private final MiningWorld plugin;
	private Inventory inv = null;
	private final FileConfiguration config;
	private Integer rows;
	private ArrayList<Integer> itemsList = new ArrayList<Integer>();
	
	public Transport(MiningWorld plugin, Player player) {
		this.plugin = plugin;
		this.config = plugin.getConfig();
		this.rows = config.getInt("Menus.Main.Rows");
		if(rows <= 0) {
			plugin.getServer().getLogger().info("Unable to load main menu as rows number is invalid");;
			return;
		}
		
		if(rows > 6) rows = 6;
		inv = Bukkit.createInventory(null, (rows * 9), ChatColor.translateAlternateColorCodes('&', config.getString("Menus.Main.Name")));
		createGUI();
		open(player, inv);
	}
	
	public void createGUI() {
		for(String key : config.getConfigurationSection("Menus.Main.Items").getKeys(false)) {
			int pos = 0;
			try {
				pos = Integer.parseInt(key);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (pos > rows * 9 || pos < 0) {
				Bukkit.getConsoleSender().sendMessage("Could not load item at position " + pos + " for main GUI as an invalid position was provided.");
				continue;
			}
			
			Material mat;
			try {                       
				mat = Material.getMaterial((String) config.get("Menus.Main.Items." + key + ".Material"));
			} catch (Exception e) {
				plugin.getServer().getLogger().info("Unable to convert material for position " + pos);
				continue;
			}
			
			String displayName = (String) config.getString("Menus.Main.Items." + key + ".Name");
			List<String> lore = config.getStringList("Menus.Main.Items." + key + ".Lore");
			Integer amount = config.getInt("Menus.Main.Items." + key + ".Amount");

			inv.setItem(pos, createItem(mat, displayName, lore, amount));
		}
	}
}