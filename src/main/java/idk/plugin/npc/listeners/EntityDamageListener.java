package idk.plugin.npc.listeners;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.StringTag;
import idk.plugin.npc.NPC;

import java.util.List;

public class EntityDamageListener implements Listener {
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        CompoundTag namedTag = entity.namedTag;

        if (namedTag.getBoolean("npc")) {
            event.setCancelled();

            if (event instanceof EntityDamageByEntityEvent) {
                Entity damager = ((EntityDamageByEntityEvent) event).getDamager();

                if (damager instanceof Player) {
                    Player player = (Player) damager;
                    String playerName = player.getName();

                    if (NPC.id.contains(playerName)) {
                        player.sendMessage("§aThe ID from that entity is " + entity.getId());
                        NPC.id.remove(playerName);
                        return;
                    }

                    if (NPC.kill.contains(playerName)) {
                        entity.close();
                        player.sendMessage("§aEntity removed");
                        NPC.kill.remove(playerName);
                        return;
                    }

                    List<StringTag> consoleCommands = namedTag.getList("Commands", StringTag.class).getAll();
                    consoleCommands.forEach(commandTag -> Server.getInstance().dispatchCommand(Server.getInstance().getConsoleSender(), commandTag.data.replaceAll("%p", "\"" + playerName + "\"")));

                    List<StringTag> playerCommands = namedTag.getList("PlayerCommands", StringTag.class).getAll();
                    playerCommands.forEach(commandTag -> Server.getInstance().dispatchCommand(player, commandTag.data));
                }
            }
        }
    }
}