package idk.plugin.npc.commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.EntityHuman;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.nbt.tag.CompoundTag;
import idk.plugin.npc.NPC;
import ru.nukkitx.forms.elements.CustomForm;

public class NpcCommand extends Command {

    public NpcCommand() {
        super("npc", "", "/npc");
        this.getCommandParameters().put("default",
                new CommandParameter[]{
                        new CommandParameter("create", CommandParamType.TEXT, false)
                });
        this.setPermission("npc.use");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (!this.testPermission(sender)) {
            return false;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage("§cUse command in game!");
            return false;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage(
                    "Available commands: \n" +
                            " /npc spawn - Create npc entity \n" +
                            " /npc getID - Get ID entity \n" +
                            " /npc list - Get npc entity list \n" +
                            " /npc teleport - Teleport entity to you \n" +
                            " /npc edit - Open entity setup menu"
            );

            return true;
        }

        switch (args[0].toLowerCase()) {
            case "spawn":
            case "create":
                CustomForm customForm = new CustomForm("§l§8Create NPC")
                        .addDropDown("§l§7Entity Type", NPC.entityList, 16)
                        .addInput("§l§7Entity Name")
                        .addInput("§l§7Commands (Across ,)", "cmd1, cmd2, cmd3")
                        .addToggle("§l§fExecute by playеr", true)
                        .addLabel("\n§l§7If the npc is a Human:")
                        .addToggle("§l§fNametag visibilitу", true)
                        .addToggle("§l§fUsе itеms on you", false);

                customForm.send(player, (target, form, data) -> {
                    if (data == null) return;

                    String entityType = (String) data.get(0);
                    String entityName = (String) data.get(1);
                    String[] commands = ((String) data.get(2)).split(", ");
                    boolean isPlayer = (Boolean) data.get(3);
                    boolean visibleTag = (Boolean) data.get(5);
                    boolean hasUseItem = entityType.equals("Human") ? (Boolean) data.get(6) : false;
                    CompoundTag compoundTag = NPC.nbt(target, entityType, commands, isPlayer);

                    Entity entity = Entity.createEntity(entityType, target.chunk, compoundTag);
                    entity.setNameTag(entityName);

                    if (entityType.equals("Human")) {
                        EntityHuman human = (EntityHuman) entity;

                        if (hasUseItem) {
                            PlayerInventory inventory = target.getInventory();
                            PlayerInventory humanInventory = human.getInventory();

                            humanInventory.setContents(inventory.getContents());
                        }

                        if (!visibleTag) {
                            human.setNameTagVisible(false);
                            human.setNameTagAlwaysVisible(false);
                        }
                    }

                    entity.spawnToAll();
                    sender.sendMessage("§fNPC §aspawned§f with ID §e" + entity.getId() + " §fand the name §b\"§f" + entity.getName() + "§b\"");
                });
                break;

            case "getid":
            case "id":
                NPC.id.add(player.getName());
                player.sendMessage("§aID MODE - click an entity to get the ID");
                break;

            case "list":
            case "entities":
                sender.sendMessage("§aAvailable entities: §3" + NPC.entityList.toString());
                break;

            case "edit":
                break;
        }

        return false;
    }
}