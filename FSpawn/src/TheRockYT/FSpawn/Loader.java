package TheRockYT.FSpawn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Loader extends JavaPlugin {
	boolean loaded = false;
	boolean loading = false;

	Listener pjl;
	Listener pml;
	public static Loader instance;

	@Override
	public void onEnable() {
		instance = this;
		pjl = new PlayerJoinListener();
		pml = new PlayerMoveListener();
		Bukkit.getPluginManager().registerEvents(pjl, this);
		Bukkit.getPluginManager().registerEvents(pml, this);
		getCommand("setspawn").setExecutor(new SetSpawnCommand());
		getCommand("setwarp").setExecutor(new SetSpawnCommand());
		getCommand("spawn").setExecutor(new SpawnCommand());
		getCommand("warp").setExecutor(new SpawnCommand());
		getCommand("delwarp").setExecutor(new DeleteSpawnCommand());
		getCommand("delspawn").setExecutor(new DeleteSpawnCommand());
		getCommand("deletespawn").setExecutor(new DeleteSpawnCommand());
		getCommand("deletewarp").setExecutor(new DeleteSpawnCommand());
		getCommand("fspawn").setExecutor(new FSpawnCommand());
		load();
	}

	@Override
	public void onDisable() {
		unload();
	}

	public void load() {
		if (!loaded && !loading) {
			loading = true;
			SpawnCommand.teleporters = new ArrayList<UUID>();
			FSpawn.userdata = YamlConfiguration.loadConfiguration(FSpawn.pdf);
			FSpawn.config = YamlConfiguration.loadConfiguration(FSpawn.cf);
			FSpawn.config.options().header("FSpawn by TheRockYT! Teleport modes: 0=FirstJoin;1=Always;2=Never, 20 Ticks = 1 Second, Permissions: SET=FSpawn.SET;DELETE=FSpawn.DELETE;RELOAD=FSpawn.RELOAD");
			FSpawn.config.addDefault("TeleportMode", 0);
			FSpawn.config.addDefault("TeleportDelay", 3*20);
			FSpawn.config.addDefault("Command.No_Player", "&eF&6Spawn &e>> &cYou must be a Player!");
			FSpawn.config.addDefault("Command.No_Permission_SET",
					"&eF&6Spawn &e>> &cYou need the permission FSpawn.SET!");
			FSpawn.config.addDefault("Command.No_Permission_DELETE",
					"&eF&6Spawn &e>> &cYou need the permission FSpawn.DELETE!");
			FSpawn.config.addDefault("Command.FSPAWN.No_Permission_RELOAD",
					"&eF&6Spawn &e>> &cYou need the permission FSpawn.RELOAD!");
			FSpawn.config.addDefault("Command.No_Permission_Teleport",
					"&eF&6Spawn &e>> &cYou need more permissions!");
			FSpawn.config.addDefault("Command.FSPAWN.RELOADING", "&eF&6Spawn &e>> &cReloading...");
			FSpawn.config.addDefault("Command.FSPAWN.RELOADED", "&eF&6Spawn &e>> &aReloaded");
			FSpawn.config.addDefault("Command.USAGE_SET", "&eF&6Spawn &e>> &cUse /SETSPAWN <SPAWN_NAME> [PERMISSION]!");
			FSpawn.config.addDefault("Command.USAGE_Teleport", "&eF&6Spawn &e>> &cUse /SPAWN <SPAWN_NAME>!");
			FSpawn.config.addDefault("Command.USAGE_Delete", "&eF&6Spawn &e>> &cUse /DELETESPAWN <SPAWN_NAME>!");
			FSpawn.config.addDefault("Command.Delete_Completed", "&eF&6Spawn &e>> &6Spawn was deleted!");
			FSpawn.config.addDefault("Command.Teleport_Started", "&eF&6Spawn &e>> &6Teleporting in 3 Seconds...");
			FSpawn.config.addDefault("Command.Teleport_Completed", "&eF&6Spawn &e>> &aYou have been teleported!");
			FSpawn.config.addDefault("Command.SPAWN_WAS_SET", "&eF&6Spawn &e>> &6Spawn %name% was set!");
			FSpawn.config.addDefault("Command.SPAWN_NOT_EXISTS", "&eF&6Spawn &e>> &cThis spawn does not exist!");
			FSpawn.config.addDefault("Command.MOVED", "&eF&6Spawn &e>> &cTeleport failed! You moved!");
			FSpawn.config.addDefault("Command.COMMAND", "&eF&6Spawn &e>> &cYou are in teleportation! Don't execute comands!");
			FSpawn.config.addDefault("Command.FSPAWN.HELP", "&eF&6Spawn &e>> &6FSpawn by TheRockYT!%nl%&eF&6Spawn &e>> &6%nl%&eF&6Spawn &e>> &6Set Spawns: /SETSPAWN <SPAWN_NAME> [PERMISSION]%nl%&eF&6Spawn &e>> &6Teleport to Spawns: /SPAWN <SPAWN_NAME>%nl%&eF&6Spawn &e>> &6Remove Spawns: /DELETESPAWN <SPAWN_NAME>%nl%&eF&6Spawn &e>> &6Reload: /FSPAWN RELOAD");
			try {
				FSpawn.config.options().copyDefaults(true);
				FSpawn.config.save(FSpawn.cf);
			} catch (IOException e) {
			}
			FSpawn.spawns = YamlConfiguration.loadConfiguration(FSpawn.sf);
			FSpawn.teleportedUsers = new ArrayList<>();
			if (FSpawn.userdata.contains("TeleportedUsers")) {
				ArrayList<String> sUUID = (ArrayList<String>) FSpawn.userdata.getStringList("TeleportedUsers");
				for (String s : sUUID) {
					FSpawn.teleportedUsers.add(UUID.fromString(s));
				}
			}
			for (Player all : Bukkit.getOnlinePlayers()) {
				FSpawn.check(all);
			}
			loading = false;
			loaded = true;
		}
	}

	public void unload() {
		if (loaded && !loading) {
			loading = true;
			ArrayList<String> sUUID = new ArrayList<String>();
			for (UUID id : FSpawn.teleportedUsers) {
				sUUID.add(id.toString());
			}
			FSpawn.userdata.set("TeleportedUsers", sUUID);
//			FSpawn.config.set("Spawn", FSpawn.teleportedUsers);
			try {
				FSpawn.userdata.save(FSpawn.pdf);
			} catch (IOException e) {
			}
			loading = false;
			loaded = false;
		}
	}

	public void reload() {
		unload();
		load();
	}
}
