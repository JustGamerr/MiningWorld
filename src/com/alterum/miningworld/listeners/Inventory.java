package com.alterum.miningworld.listeners;

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

import com.alterum.miningworld.MiningWorld;

import org.bukkit.event.inventory.InventoryClickEvent;


public class Inventory implements Listener {

	private final MiningWorld plugin;
	private final FileConfiguration config;
	private Integer rows;

	public Inventory(MiningWorld plugin) {
		this.plugin = plugin;
		this.config = plugin.getConfig();
		this.rows = config.getInt("Menus.Upgrades.Rows");
	}

	@EventHandler
	public void onInventoryInteract(InventoryClickEvent event) {
		org.bukkit.inventory.Inventory inventory = event.getInventory();
		Player player = (Player) event.getWhoClicked();
		
		if(ChatColor.stripColor(inventory.getName()).equals(config.getString("Menus.Main.Name"))) {
			event.setCancelled(true);
			for(String key : config.getConfigurationSection("Menus.Main.Items").getKeys(false)) {
				int pos = 0;
				try {
					pos = Integer.parseInt(key);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if (pos > rows * 9 || pos < 0) {
					Bukkit.getConsoleSender().sendMessage("Could not load item action at position " + pos + " for main GUI as an invalid position was provided.");
					continue;
				}
				
//		        Bukkit.getScheduler().runTask(plugin, () -> {
//		            for (String command : config.getStringList("Menus.Main.Items" + pos + ".Action")) {
//		                String[] commandSplit = command.split(" ");
//
//		                if (commandSplit[0].equalsIgnoreCase("[msg]")) {
//		                    event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', command.replaceFirst("msg! ", "")));
//		                } else if (commandSplit[0].equalsIgnoreCase("[broadcast]")) {
//		                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', command.replaceFirst("broadcast! ", "").replace("{PLAYER}", event.getPlayer().getName()).replace("{DISPLAYNAME}", event.getPlayer().getDisplayName())));
//		                } else if (commandSplit[0].equalsIgnoreCase("msgDiscord!")) {
//		                    if (commandSplit.length < 2) return;
//		                    String s = command.substring(command.indexOf(" ", 2) + 1).replace("{PLAYER}", player.getName()).replace("{DISPLAYNAME}", event.getPlayer().getDisplayName());
//		                }
//		                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command.replace("{PLAYER}", player.getName()).replace("{DISPLAYNAME}", event.getPlayer().getDisplayName()));
//		            }
//		        });
			}
		}
		
		if(ChatColor.stripColor(inventory.getName()).equals(config.getString("Menus.Upgrades.Name"))) {
			event.setCancelled(true);	
			for(String key : config.getConfigurationSection("Menus.Upgrades.Items").getKeys(false)) {
				int pos = 0;
				try {
					pos = Integer.parseInt(key);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if (pos > rows * 9 || pos < 0) {
					Bukkit.getConsoleSender().sendMessage("Could not load item action at position " + pos + " for upgrades GUI as an invalid position was provided.");
					continue;
				}
				
//		        Bukkit.getScheduler().runTask(plugin, () -> {
//		            for (String command : config.getStringList("Menus.Upgrades.Items" + pos + ".Action")) {
//		                String[] commandSplit = command.split(" ");
//
//		                if (commandSplit[0].equalsIgnoreCase("[msg]")) {
//		                    event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', command.replaceFirst("msg! ", "")));
//		                } else if (commandSplit[0].equalsIgnoreCase("[broadcast]")) {
//		                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', command.replaceFirst("broadcast! ", "").replace("{PLAYER}", event.getPlayer().getName()).replace("{DISPLAYNAME}", event.getPlayer().getDisplayName())));
//		                } else if (commandSplit[0].equalsIgnoreCase("msgDiscord!")) {
//		                    if (commandSplit.length < 2) return;
//		                    String s = command.substring(command.indexOf(" ", 2) + 1).replace("{PLAYER}", player.getName()).replace("{DISPLAYNAME}", event.getPlayer().getDisplayName());
//		                }
//		                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command.replace("{PLAYER}", player.getName()).replace("{DISPLAYNAME}", event.getPlayer().getDisplayName()));
//		            }
//		        });
			}

		}
		
		if(ChatColor.stripColor(inventory.getName()).equals(config.getString("Menus.Stats.Name"))) {
			event.setCancelled(true);
			for(String key : config.getConfigurationSection("Menus.Stats.Items").getKeys(false)) {
				int pos = 0;
				try {
					pos = Integer.parseInt(key);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if (pos > rows * 9 || pos < 0) {
					Bukkit.getConsoleSender().sendMessage("Could not load item action at position " + pos + " for stats GUI as an invalid position was provided.");
					continue;
				}
				
//		        Bukkit.getScheduler().runTask(plugin, () -> {
//		            for (String command : config.getStringList("Menus.Stats.Items" + pos + ".Action")) {
//		                String[] commandSplit = command.split(" ");
//	
//		                if (commandSplit[0].equalsIgnoreCase("[msg]")) {
//		                    event.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', command.replaceFirst("msg! ", "")));
//		                } else if (commandSplit[0].equalsIgnoreCase("[broadcast]")) {
//		                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', command.replaceFirst("broadcast! ", "").replace("{PLAYER}", event.getPlayer().getName()).replace("{DISPLAYNAME}", event.getPlayer().getDisplayName())));
//		                } else if (commandSplit[0].equalsIgnoreCase("msgDiscord!")) {
//		                    if (commandSplit.length < 2) return;
//		                    String s = command.substring(command.indexOf(" ", 2) + 1).replace("{PLAYER}", player.getName()).replace("{DISPLAYNAME}", event.getPlayer().getDisplayName());
//		                }
//		                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command.replace("{PLAYER}", player.getName()).replace("{DISPLAYNAME}", event.getPlayer().getDisplayName()));
//		            }
//		        });
			}

		}
	}
}
