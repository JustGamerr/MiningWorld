package com.sunsetinteractive.miningworld.settings;

import com.sunsetinteractive.miningworld.MiningWorld;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.regex.Pattern;

public class SettingsManager implements Listener {

    private final MiningWorld instance;

    public SettingsManager(MiningWorld plugin) {
        this.instance = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void updateSettings() {
        FileConfiguration config = instance.getConfig();
        
    }

    public List<String> getStringList(String setting) {
        return MiningWorld.getInstance().getConfig().getStringList(setting);
    }

    public boolean getBoolean(String setting) {
        return MiningWorld.getInstance().getConfig().getBoolean(setting);
    }

    public int getInt(String setting) {
        return MiningWorld.getInstance().getConfig().getInt(setting);
    }

    public String getString(String setting) {
        return MiningWorld.getInstance().getConfig().getString(setting);
    }
}