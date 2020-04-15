package idk.plugin.npc.listeners.entity.player;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

import static idk.plugin.npc.NPC.*;

public class PlayerQuitListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UUID playerUniqueId = player.getUniqueId();

        idRecipientList.remove(playerUniqueId);
        npcEditorsList.remove(playerUniqueId);
    }
}