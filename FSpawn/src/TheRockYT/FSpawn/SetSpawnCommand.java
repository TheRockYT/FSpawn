package TheRockYT.FSpawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String egal, String[] args) {
		
		if(sender instanceof Player) {
			if(args.length == 0) {
				String[] s = {"default"};
				onCommand(sender, cmd, egal, s);
			}else
			if (args.length == 2) {
				if(sender.hasPermission("FSpawn.SET")) {
					String name = args[0].toLowerCase();
					FSpawn.setLocation(FSpawn.spawns, "Spawn."+name, ((Player) sender).getLocation());
					if(args[1].equalsIgnoreCase("none")) {
						FSpawn.spawns.set("Spawn."+name+".Permission", null);
					}else {
						FSpawn.spawns.set("Spawn."+name+".Permission", args[1].toLowerCase());
						
					}
					sender.sendMessage(FSpawn.getMessage("Command.SPAWN_WAS_SET").replace("%name%", name));
				}else {
					sender.sendMessage(FSpawn.getMessage("Command.No_Permission_SET"));
				}
			}else if (args.length == 1) {
				String[] s = {args[0],"none"};
				onCommand(sender, cmd, egal, s);
			}
			else {
			
				sender.sendMessage(FSpawn.getMessage("Command.USAGE_SET"));
			}
		}else {
			sender.sendMessage(FSpawn.getMessage("Command.No_Player"));
		}
		return false;
	}

}
