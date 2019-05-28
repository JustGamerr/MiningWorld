package com.sunsetinteractive.miningworld;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import com.sunsetinteractive.miningworld.commands.MiningWorldCommand;
import com.sunsetinteractive.miningworld.configuration.FileManager;
import com.sunsetinteractive.miningworld.economy.EconomyManager;
import com.sunsetinteractive.miningworld.utils.world.VoidGenerator;

public class MiningWorld extends JavaPlugin implements Listener {
	
	private static MiningWorld instance;
	
	private FileManager fileManager;
	private EconomyManager economyManager;
	
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage("Enabling MiningWorld " + this.getDescription().getVersion() + " developed by Sunset Interactive.");
		createFiles();
		loadMessages();
		
		fileManager = new FileManager(this);
		economyManager = new EconomyManager();
		
		getServer().getPluginManager().registerEvents(this, this);
		getCommand("miningworldconfig").setExecutor(new MiningWorldCommand(this));
		getCommand("miningworld").setExecutor(new MiningWorldCommand(this));
		getCommand("miningworldreload").setExecutor(new MiningWorldCommand(this));
		getCommand("miningworldstats").setExecutor(new MiningWorldCommand(this));
		getCommand("miningworldupgrades").setExecutor(new MiningWorldCommand(this));
		getCommand("miningworldversion").setExecutor(new MiningWorldCommand(this));

	}
	
	public void createFiles() {
		
	}
	
	public void loadMessages() {
		
	}
	
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("Disabling MiningWorld " + this.getDescription().getVersion() + " developed by Sunset Interactive.");
	}
	
	public String formatText(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}
	
	public static MiningWorld getInstance() {
		return instance;
	}
	
	public FileManager getFileManager() {
		return fileManager;
	}
	
	public EconomyManager getEconomyManager() {
		return economyManager;
	}
	
	public ChunkGenerator getWorldGenerator(String worldName, String worldID) {
		return new VoidGenerator();
	}
}
