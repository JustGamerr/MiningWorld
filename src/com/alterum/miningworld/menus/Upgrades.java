package com.alterum.miningworld.menus;

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

import com.alterum.miningworld.MiningWorld;
import com.alterum.miningworld.utils.InventoryUtils;

public class Upgrades extends InventoryUtils {
	
	private final MiningWorld plugin;
	private Inventory inv = null;
	private final FileConfiguration config;
	private Integer rows;
	private ArrayList<Integer> itemsList = new ArrayList<Integer>();
	
	public Upgrades(MiningWorld plugin, Player player) {
		this.plugin = plugin;
		this.config = plugin.getConfig();
		this.rows = config.getInt("Menus.Upgrades.Rows");
		if(rows <= 0) {
			plugin.getServer().getLogger().info("Unable to load upgrades menu as rows number is invalid");;
			return;
		}
		
		if(rows > 6) rows = 6;
		inv = Bukkit.createInventory(null, (rows * 9), ChatColor.translateAlternateColorCodes('&', config.getString("Menus.Main.Name")));
		createGUI();
		open(player, inv);
	}
	
	public void createGUI() {
		for(String key : config.getConfigurationSection("Menus.Upgrades.Items").getKeys(false)) {
			int pos = 0;
			try {
				pos = Integer.parseInt(key);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (pos > rows * 9 || pos < 0) {
				Bukkit.getConsoleSender().sendMessage("Could not load item at position " + pos + " for upgrades GUI as an invalid position was provided.");
				continue;
			}
			
			Material mat;
			try {                       
				mat = Material.getMaterial((String) config.get("Menus.Upgrades.Items." + key + ".Material"));
			} catch (Exception e) {
				plugin.getServer().getLogger().info("Unable to convert material for position " + pos);
				continue;
			}
			
			String displayName = (String) config.getString("Menus.Upgrades.Items." + key + ".Name");
			List<String> lore = config.getStringList("Menus.Upgrades.Items." + key + ".Lore");
			Integer amount = config.getInt("Menus.Upgrades.Items." + key + ".Amount");

			inv.setItem(pos, createItem(mat, displayName, lore, amount));
		}
	}
}