package com.sunsetinteractive.miningworld;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sunsetinteractive.miningworld.commands.ConfigCommand;
import com.sunsetinteractive.miningworld.commands.MiningWorldCommand;
import com.sunsetinteractive.miningworld.commands.ReloadCommand;
import com.sunsetinteractive.miningworld.commands.StatsCommand;
import com.sunsetinteractive.miningworld.commands.UpgradesCommand;
import com.sunsetinteractive.miningworld.commands.VersionCommand;
import com.sunsetinteractive.miningworld.economy.EconomyManager;
import com.sunsetinteractive.miningworld.utils.Configuration;
import com.sunsetinteractive.miningworld.utils.world.VoidGenerator;

public class MiningWorld extends JavaPlugin implements Listener {
	
	private File configfile;
	private FileConfiguration configdata;
	private static final MiningWorld instance = MiningWorld.getPlugin();
	
	private EconomyManager economyManager;
	
	public void onEnable() {
		getServer().getLogger().info("Enabling MiningWorld " + this.getDescription().getVersion() + " developed by Sunset Interactive.");
		createFiles();
		loadMessages();
		
		economyManager = new EconomyManager();
		
		getServer().getPluginManager().registerEvents(this, this);
		getCommand("miningworldconfig").setExecutor(new ConfigCommand(this));
		getCommand("miningworld").setExecutor(new MiningWorldCommand(this));
		getCommand("miningworldreload").setExecutor(new ReloadCommand(this));
		getCommand("miningworldstats").setExecutor(new StatsCommand(this));
		getCommand("miningworldupgrades").setExecutor(new UpgradesCommand(this));
		getCommand("miningworldversion").setExecutor(new VersionCommand(this));

	}
	
	private void createFiles() {
		try {
			if (!getDataFolder().exists()) {
				getDataFolder().mkdirs();
			}
			this.configfile = new File(getDataFolder(), "config.yml");
			this.configdata = new YamlConfiguration();
			if (!this.configfile.exists()) {
				getLogger().info("Configuration file not found, generating a new one now.");
				saveDefaultConfig();
				this.configdata.load(this.configfile);
			} else {
				getLogger().info("Valid configuration file located, loading information now.");
				this.configdata.load(this.configfile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadMessages() {
		Configuration.admin_permission = getConfigFile().getString("Permissions.all-permissions");
		Configuration.menu_permission = getConfigFile().getString("Permissions.main-command-permission");
		Configuration.use_mine_permsision = getConfigFile().getString("Permissions.use-mine-permission");
		Configuration.view_stats_permission = getConfigFile().getString("Permissions.view-stats-permission");
		Configuration.view_others_stats_permission = getConfigFile().getString("Permissions.view-others-stats-permission");
		Configuration.upgrades_permission = getConfigFile().getString("Permissions.all-permissions");
		Configuration.others_upgrades_permission = getConfigFile().getString("Permissions.others-upgrades-permission");
		Configuration.manage_upgrades_permission = getConfigFile().getString("Permissions.manage-upgrades-permission");
		Configuration.config_permission = getConfigFile().getString("Permissions.config-permission");
		Configuration.reload_permission = getConfigFile().getString("Permissions.reload-permission");
	}
	
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("Disabling MiningWorld " + this.getDescription().getVersion() + " developed by Sunset Interactive.");
	}
	
	public String formatText(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}
	
	public FileConfiguration getConfigFile() {
		return this.configdata;
	}
		
	public  MiningWorld getInstance() {
		return instance;
	}
	
	public EconomyManager getEconomyManager() {
		return economyManager;
	}
	
	public ChunkGenerator getWorldGenerator(String worldName, String worldID) {
		return new VoidGenerator();
	}

	public static MiningWorld getPlugin() {
		return instance;
	}
}
