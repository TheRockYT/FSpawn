package TheRockYT.FSpawn;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class FSpawn {

	public static ArrayList<UUID> teleportedUsers;
	public static YamlConfiguration config;
	public static YamlConfiguration spawns;
	public static YamlConfiguration userdata;
	public static File pdf = new File("plugins/FSpawn/playerdata.yml");
	public static File cf = new File("plugins/FSpawn/config.yml");
	public static File sf = new File("plugins/FSpawn/spawns.yml");
	
	public static void check(Player p) {
		if(config.getInt("TeleportMode") == 0) {
			if(containsLocation(spawns, "Spawn.default") && !teleportedUsers.contains(p.getUniqueId())) {
				p.teleport(getLocation(spawns, "Spawn.default"));
				teleportedUsers.add(p.getUniqueId());
			}
		}
		if(config.getInt("TeleportMode") == 1) {
			if(containsLocation(spawns, "Spawn.default")) {
				p.teleport(getLocation(spawns, "Spawn.default"));
				if(!teleportedUsers.contains(p.getUniqueId())) {
					teleportedUsers.add(p.getUniqueId());
				}
			}
		}
	}
	public static String getMessage(String path) {
		String m = config.getString(path);
		m = ChatColor.translateAlternateColorCodes('&', m);
		m = m.replace("%nl%", "\n");
		return m;
	}
	public static boolean containsLocation(YamlConfiguration cfg,String path) {
		boolean contains = true;
		if(!cfg.contains(path+".x")) {
			contains = false;
		}
		if(!cfg.contains(path+".y")) {
			contains = false;
		}
		if(!cfg.contains(path+".z")) {
			contains = false;
		}
		if(!cfg.contains(path+".yaw")) {
			contains = false;
		}
		if(!cfg.contains(path+".pitch")) {
			contains = false;
		}
		if(!cfg.contains(path+".world")) {
			contains = false;
		}
		return contains;
	}
	public static Location getLocation(YamlConfiguration cfg,String path) {
		double x = cfg.getDouble(path+".x");
		double y = cfg.getDouble(path+".y");
		double z = cfg.getDouble(path+".z");
		float yaw = (float) cfg.getDouble(path+".yaw");
		float pitch = (float) cfg.getDouble(path+".pitch");
		String world = cfg.getString(path+".world");
		return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
	}
	
	public static void setLocation(YamlConfiguration cfg,String path,Location loc) {
		cfg.set(path+".x", loc.getX());
		cfg.set(path+".y", loc.getY());
		cfg.set(path+".z", loc.getZ());
		cfg.set(path+".yaw", loc.getYaw());
		cfg.set(path+".pitch", loc.getPitch());
		cfg.set(path+".world", loc.getWorld().getName());
		try {
			cfg.save(FSpawn.sf);
		} catch (IOException e) {
		}
	}
	public static void removeLocation(YamlConfiguration cfg,String path) {
		cfg.set(path+".x", null);
		cfg.set(path+".y", null);
		cfg.set(path+".z", null);
		cfg.set(path+".yaw", null);
		cfg.set(path+".pitch", null);
		cfg.set(path+".world", null);
		try {
			cfg.save(FSpawn.sf);
		} catch (IOException e) {
		}
	}
}
