package com.sunsetinteractive.miningworld.economy;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class EconomyManager {
	
	private Economy economy;

	public EconomyManager() {
		startup();
	}

	public void startup() {
		if (Bukkit.getServer().getPluginManager().getPlugin("Vault") != null) {
			RegisteredServiceProvider<Economy> registeredServiceProvider = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);

			if (registeredServiceProvider != null) {
				economy = registeredServiceProvider.getProvider();
			}
		}
	}

	public double getBalance(Player player) {
		if (economy == null) {
			return 0.0D;
		} else {
			return economy.getBalance(player);
		}
	}

	public boolean hasFunds(Player player, double money) {
		return getBalance(player) >= money;
	}

	public void withdrawFunds(Player player, double money) {
		if (economy != null) {
			economy.withdrawPlayer(player, money);
		}
	}

	public void depositFunds(Player player, double money) {
		if (economy != null) {
			economy.depositPlayer(player, money);
		}
	}

	public boolean checkEconomy() {
		return Bukkit.getServer().getPluginManager().getPlugin("Vault") != null;
	}
}
