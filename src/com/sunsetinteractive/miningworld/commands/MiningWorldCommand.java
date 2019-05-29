package com.sunsetinteractive.miningworld.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sunsetinteractive.miningworld.utils.Configuration;
import com.sunsetinteractive.miningworld.MiningWorld;
import com.sunsetinteractive.miningworld.menus.Transport;

public class MiningWorldCommand implements CommandExecutor {

	private final MiningWorld plugin;

	public MiningWorldCommand(MiningWorld plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if(player.hasPermission(Configuration.menu_permission)) {
			new Transport(plugin, player);
		}
		return false;
	}

}
