package idk.plugin.npc.listeners.entity;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
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
import ru.nukkitx.forms.elements.*;

import java.util.*;

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
                        this.sendNPCEditingForm(player, entity);
                        npcEditorsList.remove(playerUniqueId);
                        player.sendMessage("§aChanges applied!");
                        return;
                    }

                    this.dispatchCommands(Server.getInstance().getConsoleSender(), namedTag.getList("Commands", StringTag.class).getAll());
                    this.dispatchCommands(player, namedTag.getList("PlayerCommands", StringTag.class).getAll());
                }
            }
        }
    }

    public void sendNPCEditingForm(Player player, Entity entity) {
        CompoundTag namedTag = entity.namedTag;
        SimpleForm simpleForm = new SimpleForm("§l§8NPC Editing", "\n§l§7NPC ID - " + entity.getId() + "\nNPC Name - \"" + entity.getName() + "\"\n\n")
                .addButton("Commands")
                .addButton("Size")
                .addButton("§cKill");
        if (namedTag.getBoolean("isHuman")) {
            simpleForm.addButton("Replace inventory");
        }

        simpleForm.send(player, (target, form, data) -> {
            switch (data) {
                case 0: //Commands
                    this.sendCommands(target, entity);
                    break;
                case 1: //Size
                    this.sendChangeSize(target, entity);
                    break;
                case 2: //Kill
                    new ModalForm("§l§8Delete a command")
                            .setButton1("§l§cYes")
                            .setButton2("§l§aNo")
                            .send(target, (target1, form1, data1) -> {
                                if (data1 == 0) {
                                    entity.close();
                                    player.sendMessage("§aEntity removed");
                                    return;
                                }

                                this.sendNPCEditingForm(target1, entity);
                            });
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
    }

    private void sendChangeSize(Player player, Entity entity) {
        new CustomForm("§l§8Change Size")
                .addInput("")
                .send(player, (target, form, data) -> {
                    if (data == null) {
                        this.sendNPCEditingForm(target, entity);
                        return;
                    }

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

    private void sendCommands(Player player, Entity entity) {
        new SimpleForm("§l§8Commands")
                .addButton("Player Commands")
                .addButton("Console Commands")
                .addButton("Add command")
                .send(player, (target, form, data) -> {
                    switch (data) {
                        case -1:
                            this.sendNPCEditingForm(target, entity);
                            break;
                        case 0: //Player Commands
                            this.sendCommandList(target, entity, "PlayerCommands");
                            break;
                        case 1: //Console Commands
                            this.sendCommandList(target, entity, "Commands");
                            break;
                        case 2: //Add command
                            new CustomForm("§l§8Add Command")
                                    .addInput("§l§7Command", "Enter the command you want to run")
                                    .addToggle("§l§fExecute by playеr", true)
                                    .send(target, (target1, form1, data1) -> {
                                        if (data1 == null) {
                                            this.sendNPCEditingForm(target, entity);
                                            return;
                                        }
                                        String command = (String) data1.get(0);
                                        boolean isPlayer = (Boolean) data1.get(1);
                                        StringTag tag = new StringTag("", command);
                                        if (entity.namedTag.getList(isPlayer ? "PlayerCommands" : "Commands", StringTag.class).getAll().contains(tag)) {
                                            player.sendMessage("§aCommand already added");
                                            return;
                                        }

                                        entity.namedTag.getList(isPlayer ? "PlayerCommands" : "Commands", StringTag.class).add(tag);
                                        player.sendMessage("§aCommand added");

                                        entity.respawnToAll();

                                        sendCommands(target1, entity);
                                    });
                            break;
                    }
                });
    }

    private void sendCommandList(Player player, Entity entity, String listName) {
        SimpleForm simpleForm = new SimpleForm();
        List<StringTag> tagList = entity.namedTag.getList(listName, StringTag.class).getAll();
        Iterator<StringTag> iterator = tagList.iterator();

        while (iterator.hasNext()) {
            StringTag stringTag = iterator.next();
            String command = stringTag.data;

            if (!command.replaceAll(" ", "").equals("")) {
                simpleForm.addButton(command);
            } else {
                iterator.remove();
            }
        }

        if (tagList.isEmpty()) {
            player.sendMessage("§cThere are no commands");
            return;
        }

        simpleForm.send(player, (target, form, data) -> {
            if (data == -1) {
                this.sendNPCEditingForm(target, entity);
                return;
            }

            new ModalForm("§l§8Delete a command")
                    .setButton1("§l§cYes")
                    .setButton2("§l§aNo")
                    .send(target, (target1, form1, data1) -> {
                        if (data1 == 0) {
                            StringTag command = tagList.get(data);
                            entity.namedTag.getList(listName, StringTag.class).remove(command);
                            player.sendMessage("§aCommand §e" + command.data + "§a removed");
                            return;
                        }

                        this.sendNPCEditingForm(target1, entity);
                    });
        });
    }

    private void dispatchCommands(CommandSender sender, List<StringTag> tagList) {
        tagList.forEach(commandTag -> {
            String command = commandTag.data.replaceAll("%p", "\"" + sender.getName() + "\"");

            if (!command.replaceAll(" ", "").equals("")) {
                Server.getInstance().dispatchCommand(sender, command);
            }
        });
    }
}