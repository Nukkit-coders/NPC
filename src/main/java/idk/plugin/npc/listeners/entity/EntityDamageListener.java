package idk.plugin.npc.listeners.entity;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.EntityHuman;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.StringTag;
import ru.nukkitx.forms.elements.CustomForm;
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
                        CompoundTag compoundTag = entity.namedTag;
                        SimpleForm simpleForm = new SimpleForm("§l§8NPC Editing", "§l§7NPC ID - " + entity.getId() + "\nNPC Name - \"" + entity.getName() + "\"\n\n")
                                .addButton("Commands")
                                .addButton("Size")
                                .addButton("§cKill");
                        if (compoundTag.getBoolean("isHuman")) {
                            simpleForm.addButton("Replace inventory");
                        }

                        simpleForm.send(player, (target, form, data) -> {
                            switch (data) {
                                case 0: //Commands
                                    break;
                                case 1: //Size
                                    this.sendChangeSize(target, entity);
                                    break;
                                case 2: //Kill
                                    entity.close();
                                    break;
                                case 3: //Replace inventory
                                    EntityHuman human = (EntityHuman) entity;
                                    PlayerInventory playerInventory = player.getInventory();
                                    PlayerInventory inventory = human.getInventory();

                                    inventory.setItemInHand(playerInventory.getItemInHand());
                                    namedTag.putString("Item", playerInventory.getItemInHand().getName());

                                    inventory.setHelmet(playerInventory.getHelmet());
                                    namedTag.putString("Helmet", playerInventory.getHelmet().getName());

                                    inventory.setChestplate(playerInventory.getChestplate());
                                    namedTag.putString("Chestplate", playerInventory.getChestplate().getName());

                                    inventory.setLeggings(playerInventory.getLeggings());
                                    namedTag.putString("Leggings", playerInventory.getLeggings().getName());

                                    inventory.setBoots(playerInventory.getBoots());
                                    namedTag.putString("Boots", playerInventory.getBoots().getName());

                                    entity.respawnToAll();
                                    break;
                            }
                        });

                        npcEditorsList.remove(playerUniqueId);
                        player.sendMessage("§aChanges applied!");
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

    private void sendChangeSize(Player player, Entity entity) {
        new CustomForm("§l§8Change Size")
                .addInput("")
                .send(player, (target, form, data) -> {
                    if (data == null) return;

                    try {
                        float scale = Float.parseFloat((String) data.get(0));
                        entity.namedTag.putFloat("scale", scale);
                        entity.setScale(scale);
                        entity.respawnToAll();
                    } catch (Exception exception) {
                        player.sendMessage("§cEnter float value!");
                        sendChangeSize(target, entity);
                    }
                });
    }
}