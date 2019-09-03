package dev.alterum.miningworld.settings;

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

import dev.alterum.miningworld.MiningWorld;

import java.util.*;
import java.util.regex.Pattern;


public class SettingsManager implements Listener {

//    private final MiningWorld instance;
//    private final FileConfiguration config;
//
//    public SettingsManager(MiningWorld plugin) {
//        this.instance = plugin;
//        this.config = plugin.getConfig();
//        Bukkit.getPluginManager().registerEvents(this, plugin);
//    }
//
//    public void updateSettings() {
//    }
//
//    public List<String> getStringList(String setting) {
//        return config.getStringList(setting);
//    }
//
//    public boolean getBoolean(String setting) {
//        return config.getBoolean(setting);
//    }
//
//    public int getInt(String setting) {
//        return config.getInt(setting);
//    }
//
//    public String getString(String setting) {
//        return config.getString(setting);
//    }
}