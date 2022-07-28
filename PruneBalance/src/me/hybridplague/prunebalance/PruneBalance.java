package me.hybridplague.prunebalance;

import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class PruneBalance extends JavaPlugin {

	static PruneBalance instance;
	public Permission perm;
	public Economy eco;
	
	@Override
	public void onEnable() {
		if (!setupPermission()) {
			System.out.println(ChatColor.RED + "You must have Vault installed.");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		if (!setupEconomy()) {
			System.out.println(ChatColor.RED + "You must have Vault" + " and an economy plugin installed.");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		
		instance = this;
		
		this.saveDefaultConfig();
		this.getCommand("prune").setExecutor(new PruceCommandListener());
	}
	
	@Override
	public void onDisable() {
	}
	
	public static PruneBalance getInstance() {
		return instance;
	}
	
	public boolean setupPermission() {
		RegisteredServiceProvider<Permission> permissions = getServer().
				getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
		if (permissions != null)
			perm = permissions.getProvider();
		return (perm != null);
	}
	
	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economy = getServer().
				getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economy != null)
			eco = economy.getProvider();
		return (eco != null);
	}
	
}
