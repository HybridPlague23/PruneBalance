package me.hybridplague.prunebalance;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class PruceCommandListener implements CommandExecutor {
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player))
			return false;
		Player p = (Player) sender;
		
		if (!p.hasPermission("prunebalance.use")) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', PruneUtils.getNoPerms()));
			return true;
		}
		switch(args.length) {
		case 0:
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', PruneUtils.getNoArgument()));
			break;
		case 1:
			OfflinePlayer arg1 = Bukkit.getOfflinePlayer(args[0]);
			if (!arg1.hasPlayedBefore()) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', PruneUtils.getInvalidPlayer()));
				return true;
			}
			if (PruneBalance.getInstance().perm.playerHas(null, arg1, "prunebalance.immune")) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', PruneUtils.getPlayerImmune()));
				return true;
			}
			double balance = PruneBalance.getInstance().eco.getBalance(arg1);
			balance = (balance * 0.50); // take
			PruneBalance.getInstance().eco.withdrawPlayer(arg1, balance);
			
			balance = (balance - (balance * 0.25)); // burn
			
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', PruneUtils.getMsgToSender().replace("%arg-1%", arg1.getName())
					.replace("%pruned%", String.valueOf(balance))));
			
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ChatColor.translateAlternateColorCodes('&', "eco give " + PruneUtils.getGovAccount() + " " + balance));
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ChatColor.translateAlternateColorCodes('&', PruneUtils.getLogCommand().replace("%player%", p.getName())
					.replace("%arg-1%", arg1.getName())
					.replace("%pruned%", String.valueOf(balance))));
			break;
		default:
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', PruneUtils.getNoArgument()));
			break;
		}

		return true;
	}
	
}
