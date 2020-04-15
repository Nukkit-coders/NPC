package idk.plugin.npc.listeners.entity;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.StringTag;
import ru.nukkitx.forms.elements.SimpleForm;

import java.util.List;
import java.util.UUID;

import static idk.plugin.npc.NPC.*;

public class EntityDamageListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        CompoundTag namedTag = entity.namedTag;

        if (namedTag.getBoolean("npc")) {
            event.setCancelled();

            if (event instanceof EntityDamageByEntityEvent) {
                Entity damager = ((EntityDamageByEntityEvent) event).getDamager();

                if (damager instanceof Player) {
                    Player player = (Player) damager;
                    UUID playerUniqueId = player.getUniqueId();

                    if (idRecipientList.contains(playerUniqueId)) {
                        player.sendMessage("§aThe ID from that entity is " + entity.getId());
                        idRecipientList.remove(playerUniqueId);
                        return;
                    }

                    if (npcEditorsList.contains(playerUniqueId)) {
                        SimpleForm simpleForm = new SimpleForm("§l§8NPC Editing")
                                .addButton("");
                        simpleForm.send(player, (target, form, data) -> {
                            switch (data) {
                                case 0:
                                    break;
                            }
                        });

                        npcEditorsList.remove(playerUniqueId);
                        return;
                    }

                    List<StringTag> consoleCommands = namedTag.getList("Commands", StringTag.class).getAll();
                    consoleCommands.forEach(commandTag -> {
                        String command = commandTag.data.replaceAll("%p", "\"" + player.getName() + "\"");

                        if (!command.replaceAll(" ", "").equals("")) {
                            Server.getInstance().dispatchCommand(Server.getInstance().getConsoleSender(), command);
                        }
                    });

                    List<StringTag> playerCommands = namedTag.getList("PlayerCommands", StringTag.class).getAll();
                    playerCommands.forEach(commandTag -> {
                        String command = commandTag.data;

                        if (!command.replaceAll(" ", "").equals("")) {
                            Server.getInstance().dispatchCommand(player, command);
                        }
                    });
                }
            }
        }
    }
}