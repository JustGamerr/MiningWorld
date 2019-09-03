package dev.alterum.miningworld.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.alterum.miningworld.MiningWorld;
import dev.alterum.miningworld.menus.Upgrades;
import dev.alterum.miningworld.utils.Configuration;

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
