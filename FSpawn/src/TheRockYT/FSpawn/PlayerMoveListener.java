package TheRockYT.FSpawn;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

@SuppressWarnings("deprecation")
public class PlayerMoveListener implements Listener {

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (SpawnCommand.teleporters.contains(e.getPlayer().getUniqueId())) {
			SpawnCommand.teleporters.remove(e.getPlayer().getUniqueId());
			e.getPlayer().sendMessage(FSpawn.getMessage("Command.MOVED"));
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPreprocess(PlayerCommandPreprocessEvent e) {
		if (SpawnCommand.teleporters.contains(e.getPlayer().getUniqueId())) {
			e.getPlayer().sendMessage(FSpawn.getMessage("Command.COMMAND"));
			e.setCancelled(true);
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPreprocess(InventoryClickEvent e) {
		if (SpawnCommand.teleporters.contains(e.getWhoClicked().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPreprocess(PlayerInteractEvent e) {
		if (SpawnCommand.teleporters.contains(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPreprocess(PlayerDropItemEvent e) {
		if (SpawnCommand.teleporters.contains(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPreprocess(PlayerPickupItemEvent e) {
		if (SpawnCommand.teleporters.contains(e.getPlayer().getUniqueId())) {
			e.setCancelled(true);
		}
	}
//	@EventHandler(priority = EventPriority.HIGHEST)
//	public void onPreprocess(PlayerSwapHandItemsEvent e) {
//		if (SpawnCommand.teleporters.contains(e.getPlayer().getUniqueId())) {
//			e.setCancelled(true);
//		}
//	}
}
