package dev.alterum.miningworld.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import dev.alterum.miningworld.MiningWorld;

public class ReloadCommand implements CommandExecutor {
	private final MiningWorld plugin;

	public ReloadCommand(MiningWorld plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return false;
	}
}
