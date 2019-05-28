package com.sunsetinteractive.miningworld.menus;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.sunsetinteractive.miningworld.utils.InventoryUtils;

public class Transport extends InventoryUtils {
	
	private final Inventory inv;
	
	public Transport() {
		inv = Bukkit.createInventory(null, 9, "Mining World Transporation");
	}
	
	public void createGUI() {
		inv.setItem(5, createItem(Material.DIAMOND, ""));
	}
	
	
	@EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        String invName = e.getInventory().getName();
        if (!invName.equals(inv.getName())) {
            return;
        }
        if (e.getClick().equals(ClickType.NUMBER_KEY)){
             e.setCancelled(true); 
        }
        
        e.setCancelled(true);

        Player p = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();

        // verify current item is not null
        if (clickedItem == null || clickedItem.getType().equals(Material.AIR)) return;

        // Using slots click is a best option for your inventory click's
        if (e.getRawSlot() == 10) p.sendMessage("You clicked at slot " + 10);
    }
	
}
