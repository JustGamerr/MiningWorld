package dev.alterum.miningworld.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryUtils {
	
	public ItemStack createItem(Material material, String name) {
		ItemStack i = new ItemStack(material, 1);
		ItemMeta iMeta = i.getItemMeta();
		iMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		iMeta.setLore(null);
		i.setItemMeta(iMeta);
		
		return i;
	}
	
	public ItemStack createItem(Material material, String name, Integer amount) {
		ItemStack i = new ItemStack(material, amount);
		ItemMeta iMeta = i.getItemMeta();
		iMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		i.setItemMeta(iMeta);
		
		return i;
	}
	
	public ItemStack createItem(Material material, String name, ArrayList<String> lore) {
		ItemStack i = new ItemStack(material, 1);
		ItemMeta iMeta = i.getItemMeta();
		iMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		iMeta.setLore(lore);
		i.setItemMeta(iMeta);
		
		return i;
	}
	
	public ItemStack createItem(Material material, String name, List<String> lore,  Integer amount) {
		ItemStack i = new ItemStack(material, 1);
		ItemMeta iMeta = i.getItemMeta();
		iMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		iMeta.setLore(lore);
		i.setItemMeta(iMeta);
		
		return i;
	}
	
	
	public void open(Player player, Inventory inv) {
		player.openInventory(inv);
		return;
	}
}
