package TheRockYT.FSpawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteSpawnCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String egal, String[] args) {
		if (sender instanceof Player) {
			if (args.length == 0) {
				String[] s = { "default" };
				onCommand(sender, cmd, egal, s);
			} else if (args.length == 1) {
				if(sender.hasPermission("FSpawn.DELETE")) {

					String name = args[0].toLowerCase();
					if(FSpawn.containsLocation(FSpawn.spawns, "Spawn."+name)) {
						FSpawn.removeLocation(FSpawn.spawns, "Spawn."+name);
						FSpawn.spawns.set("Spawn."+name+".Permission", null);
						sender.sendMessage(FSpawn.getMessage("Command.Delete_Completed"));
					}else {
						sender.sendMessage(FSpawn.getMessage("Command.SPAWN_NOT_EXISTS"));
						
					}
				}else {

					sender.sendMessage(FSpawn.getMessage("Command.No_Permission_DELETE"));
				}
			} else {
				sender.sendMessage(FSpawn.getMessage("Command.USAGE_Delete"));
			}
		} else {
			sender.sendMessage(FSpawn.getMessage("Command.No_Player"));
		}
		return false;
	}

}
