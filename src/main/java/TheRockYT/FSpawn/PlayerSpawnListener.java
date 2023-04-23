package TheRockYT.FSpawn;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

import static TheRockYT.FSpawn.FSpawn.getLocation;
import static TheRockYT.FSpawn.FSpawn.spawns;
import static TheRockYT.FSpawn.FSpawn.config;

public class PlayerSpawnListener implements Listener {
    @EventHandler
    public void onPlayerSpawn(PlayerRespawnEvent event) {
        if(config.getBoolean("TeleportOnRespawn")){
            Location location = getLocation(spawns, "Spawn.default");
            event.setRespawnLocation(location);
        }
    }
}
