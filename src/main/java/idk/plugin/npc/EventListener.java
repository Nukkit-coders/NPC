package idk.plugin.npc;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.entity.EntityVehicleEnterEvent;
import cn.nukkit.nbt.tag.StringTag;
import idk.plugin.npc.entities.NPC_Entity;
import idk.plugin.npc.entities.NPC_Human;

import java.util.List;

public class EventListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        Entity entity = e.getEntity();

        if (entity instanceof NPC_Entity || entity instanceof NPC_Human || entity.namedTag.getBoolean("npc")) {
            e.setCancelled();

            if (e instanceof EntityDamageByEntityEvent) {
                EntityDamageByEntityEvent ev = (EntityDamageByEntityEvent) e;
                if (ev.getDamager() instanceof Player) {
                    Player player = (Player) ev.getDamager();
                    String name = player.getName();

                    if (NPC.id.contains(name)) {
                        player.sendMessage("§aThe ID from that entity is " + entity.getId());
                        NPC.id.remove(name);

                    } else if (NPC.kill.contains(name)) {
                        entity.close();
                        player.sendMessage("§aEntity removed");
                        NPC.kill.remove(name);

                    } else {
                        List<StringTag> cmddd = entity.namedTag.getList("Commands", StringTag.class).getAll();
                        for (StringTag cmdd : cmddd) {
                            String cmd = cmdd.data;
                            cmd = cmd.replaceAll("%p", "\"" + player.getName() + "\"");
                            Server.getInstance().dispatchCommand(Server.getInstance().getConsoleSender(), cmd);
                        }

                        List<StringTag> cmdddd = entity.namedTag.getList("PlayerCommands", StringTag.class).getAll();
                        for (StringTag cmdd : cmdddd) {
                            String cmd = cmdd.data;
                            Server.getInstance().dispatchCommand(player, cmd);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEnterVehicle(EntityVehicleEnterEvent e) {
        if (e.getEntity() instanceof NPC_Entity || e.getEntity() instanceof NPC_Human) {
            e.setCancelled(true);
        }
    }
}
