package TheRockYT.FSpawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class FSpawnCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String egal, String[] args) {
		if(args.length == 1 && args[0].equalsIgnoreCase("reload")) {
			if(sender.hasPermission("FSpawn.RELOAD")) {
				sender.sendMessage(FSpawn.getMessage("Command.FSPAWN.RELOADING"));
				Loader.instance.reload();
				sender.sendMessage(FSpawn.getMessage("Command.FSPAWN.RELOADED"));
			}else {
				//Command.FSPAWN.No_Permission_RELOAD
				sender.sendMessage(FSpawn.getMessage("Command.FSPAWN.No_Permission_RELOAD"));
			}
		} else {
			sender.sendMessage(FSpawn.getMessage("Command.FSPAWN.HELP"));
		}
		return false;
	}

}
