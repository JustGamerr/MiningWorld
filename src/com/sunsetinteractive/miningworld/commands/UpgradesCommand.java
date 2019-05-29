package com.sunsetinteractive.miningworld.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sunsetinteractive.miningworld.MiningWorld;
import com.sunsetinteractive.miningworld.menus.Upgrades;
import com.sunsetinteractive.miningworld.utils.Configuration;

public class UpgradesCommand implements CommandExecutor {
	private final MiningWorld plugin;

	public UpgradesCommand(MiningWorld plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if(player.hasPermission(Configuration.upgrades_permission)) {
			new Upgrades(plugin, player);
		}
		return false;
	}
}
