package TheRockYT.FSpawn;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
	public static ArrayList<UUID> teleporters;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String egal, String[] args) {
		if (sender instanceof Player) {
			if (args.length == 0) {
				String[] s = { "default" };
				onCommand(sender, cmd, egal, s);
			} else if (args.length == 1) {
				String name = args[0].toLowerCase();
				if (FSpawn.containsLocation(FSpawn.spawns, "Spawn." + name)) {
					if(FSpawn.spawns.contains("Spawn." + name + ".Permission")) {
						if(!sender.hasPermission(FSpawn.spawns.getString("Spawn." + name + ".Permission"))) {
							sender.sendMessage(FSpawn.getMessage("Command.No_Permission_Teleport"));
							return true;
						}
					}
					UUID pid = ((Player) sender).getUniqueId();
					Location tpTo = FSpawn.getLocation(FSpawn.spawns, "Spawn." + name);
					teleporters.add(pid);
					sender.sendMessage(FSpawn.getMessage("Command.Teleport_Started"));
					Bukkit.getScheduler().scheduleSyncDelayedTask(Loader.instance, new Runnable() {
						
						@Override
						public void run() {
							if (Bukkit.getPlayer(pid) != null && teleporters.contains(pid)) {
								Player p = Bukkit.getPlayer(pid);
								p.sendMessage(FSpawn.getMessage("Command.Teleport_Completed"));
								p.teleport(tpTo);
								teleporters.remove(pid);
							}
						}
					}, FSpawn.config.getInt("TeleportDelay"));
				} else {
					sender.sendMessage(FSpawn.getMessage("Command.SPAWN_NOT_EXISTS"));
				}
			} else {
				sender.sendMessage(FSpawn.getMessage("Command.USAGE_Teleport"));
			}
		} else {
			sender.sendMessage(FSpawn.getMessage("Command.No_Player"));
		}
		return false;
	}

}
