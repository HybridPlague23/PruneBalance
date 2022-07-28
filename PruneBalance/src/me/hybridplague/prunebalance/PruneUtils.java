package me.hybridplague.prunebalance;

public class PruneUtils {

	private static final String noPerms = PruneBalance.getInstance().getConfig().getString("No-Perms");
	private static final String noArgument = PruneBalance.getInstance().getConfig().getString("Usage");
	private static final String invalidPlayer = PruneBalance.getInstance().getConfig().getString("Player-Not-Found");
	private static final String playerImmune = PruneBalance.getInstance().getConfig().getString("Player-Is-Immune");
	private static final String govAccount = PruneBalance.getInstance().getConfig().getString("Success.gov-account");
	
	private static String msgToSender = PruneBalance.getInstance().getConfig().getString("Success.message-to-sender");
	private static String logCommand = PruneBalance.getInstance().getConfig().getString("Success.log-command");
	
	public static String getNoPerms() {
		return noPerms;
	}
	
	public static String getNoArgument() {
		return noArgument;
	}
	
	public static String getInvalidPlayer() {
		return invalidPlayer;
	}
	
	public static String getPlayerImmune() {
		return playerImmune;
	}
	
	public static String getMsgToSender() {
		return msgToSender;
	}
	
	public static String getLogCommand() {
		return logCommand;
	}
	
	public static String getGovAccount() {
		return govAccount;
	}


}
