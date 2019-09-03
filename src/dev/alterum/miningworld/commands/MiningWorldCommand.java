package dev.alterum.miningworld.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.alterum.miningworld.MiningWorld;
import dev.alterum.miningworld.menus.Transport;
import dev.alterum.miningworld.utils.Configuration;

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
