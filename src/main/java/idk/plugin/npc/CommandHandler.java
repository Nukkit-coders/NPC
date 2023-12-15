package idk.plugin.npc;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.data.Skin;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.item.Item;
import cn.nukkit.nbt.tag.*;
import cn.nukkit.utils.PersonaPiece;
import cn.nukkit.utils.PersonaPieceTint;
import cn.nukkit.utils.SkinAnimation;
import idk.plugin.npc.entities.NPC_Block;
import idk.plugin.npc.entities.NPC_Entity;
import idk.plugin.npc.entities.NPC_Human;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandHandler {

    private static final List<String> ENTITIES = Arrays.asList("Allay", "Axolotl", "Bat", "Bee", "Blaze", "Block", "Cat", "CaveSpider", "Chicken", "Cow", "Creeper",
            "Dolphin", "Donkey", "ElderGuardian", "Enderman", "Endermite", "Evoker", "Frog", "Fox", "Ghast", "GlowSquid", "Goat", "Guardian", "Hoglin",
            "Horse", "Human", "Husk", "IronGolem", "Llama", "Mooshroom", "MagmaCube", "Mule", "Ocelot", "Panda", "Parrot", "Phantom",
            "Pig", "Piglin", "PiglinBrute", "Pillager", "PolarBear", "Rabbit", "Ravager", "SkeletonHorse", "Sheep", "Shulker", "Silverfish", "Skeleton", "Slime",
            "Snowman", "Spider", "Squid", "Stray", "Strider", "Tadpole", "Turtle", "Vex", "Villager", "Vindicator", "WanderingTrader", "Warden", "Witch", "Wither",
            "WitherSkeleton", "Wolf", "Zoglin", "ZombieHorse", "Zombie", "ZombiePigman", "ZombieVillager");

    static boolean handle(CommandSender sender, Command command, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cThis command only works in game");
            return true;
        }

        Player player = (Player) sender;

        if (command.getName().equals("npc")) {
            if (args.length < 1) {
                sendHelp(sender);
                return true;
            }
            switch (args[0].toLowerCase()) {
                case "spawn":
                    if (args.length < 2) {
                        sender.sendMessage("§cUsage: /npc spawn <entity> [name]");
                        return true;
                    }

                    if (!ENTITIES.contains(args[1])) {
                        sender.sendMessage("§cEntity '§4" + args[1] + "§c' is not supported. You can see all supported entities with §e/npc entities§c command. Notice that the name is case sensitive.");
                        return true;
                    }
                    String name;
                    if (args.length == 3 && args[2].isEmpty()) {
                        name = "%k";
                    } else {
                        name = String.join(" ", args);
                        name = name.replaceFirst("spawn", "");
                        name = name.replaceFirst(args[1], "");
                        name = name.replaceFirst(" ", "");
                        name = name.replaceFirst(" ", "");
                    }
                    name = name.replace("%n", "\n");
                    CompoundTag nbt = nbt(player, args, name);
                    Entity ent = Entity.createEntity("NPC_" + args[1], player.chunk, nbt);
                    ent.setNameTag(name);
                    if (!"%k".equals(name)) {
                        ent.setNameTagVisible(true);
                        ent.setNameTagAlwaysVisible(true);
                    }
                    if (ent instanceof NPC_Human) {
                        setInventories((NPC_Human) ent, player);
                    }
                    ent.spawnToAll();
                    sender.sendMessage("§a'§e" + args[1] + "§a' NPC spawned (ID: §e" + ent.getId() + "§a)");
                    return true;
                case "getid":
                    NPC.cmd_id.add(player.getId());
                    player.sendMessage("§aID mode - click an entity to get the ID");
                    return true;
                case "addcmd":
                    if (args.length < 3) {
                        sender.sendMessage("§cUsage: /npc addcmd <ID> <cmd>");
                        return true;
                    }
                    if (!isInteger(args[1])) {
                        player.sendMessage("§cUsage: /npc addcmd <ID> <cmd>");
                        return true;
                    }
                    Entity enti = player.getLevel().getEntity(Integer.parseInt(args[1]));
                    if (enti == null) {
                        player.sendMessage("§cNo NPC found with that ID");
                        return true;
                    }
                    if (enti instanceof NPC_Human || enti instanceof NPC_Entity || enti.namedTag.getBoolean("npc")) {
                        String cmd;
                        cmd = String.join(" ", args);
                        cmd = cmd.replaceFirst("addcmd", "");
                        cmd = cmd.replaceFirst(args[1], "");
                        StringTag st = new StringTag(cmd, cmd);
                        if (enti.namedTag.getList("Commands", StringTag.class).getAll().contains(st)) {
                            player.sendMessage("§aCommand already added");
                            return true;
                        }
                        enti.namedTag.getList("Commands", StringTag.class).add(st);
                        player.sendMessage("§aCommand added");
                        return true;
                    } else {
                        player.sendMessage("§cNo NPC found with that ID");
                        return true;
                    }
                case "addplayercmd":
                    if (args.length < 3) {
                        sender.sendMessage("§cUsage: /npc addplayercmd <ID> <cmd>");
                        return true;
                    }
                    if (!isInteger(args[1])) {
                        player.sendMessage("§cUsage: /npc addplayercmd <ID> <cmd>");
                        return true;
                    }
                    Entity enti2 = player.getLevel().getEntity(Integer.parseInt(args[1]));
                    if (enti2 == null) {
                        player.sendMessage("§cNo NPC found with that ID");
                        return true;
                    }
                    if (enti2 instanceof NPC_Human || enti2 instanceof NPC_Entity || enti2.namedTag.getBoolean("npc")) {
                        String cmd;
                        cmd = String.join(" ", args);
                        cmd = cmd.replaceFirst("addplayercmd", "");
                        cmd = cmd.replaceFirst(args[1], "");
                        StringTag st = new StringTag(cmd, cmd);
                        if (enti2.namedTag.getList("PlayerCommands", StringTag.class).getAll().contains(st)) {
                            player.sendMessage("§aCommand already added");
                            return true;
                        }
                        enti2.namedTag.getList("PlayerCommands", StringTag.class).add(st);
                        player.sendMessage("§aCommand added");
                        return true;
                    } else {
                        player.sendMessage("§cNo NPC found with that ID");
                        return true;
                    }
                case "listcmd":
                    if (args.length < 2) {
                        sender.sendMessage("§cUsage: /npc listcmd <ID>");
                        return true;
                    }
                    if (!isInteger(args[1])) {
                        sender.sendMessage("§cUsage: /npc listcmdd <ID>");
                        return true;
                    }
                    Entity entity = player.getLevel().getEntity(Integer.parseInt(args[1]));
                    if (entity == null) {
                        player.sendMessage("§cNo NPC found with that ID");
                        return true;
                    }
                    if (entity instanceof NPC_Entity || entity instanceof NPC_Human || entity.namedTag.getBoolean("npc")) {
                        List<StringTag> cmddd = entity.namedTag.getList("Commands", StringTag.class).getAll();
                        player.sendMessage("§aCommands of §e" + entity.getName() + " (" + entity.getId() + ")§a:");
                        for (StringTag cmdd : cmddd) {
                            player.sendMessage(cmdd.data);
                        }
                        List<StringTag> cmdddd = entity.namedTag.getList("PlayerCommands", StringTag.class).getAll();
                        player.sendMessage("§aPlayer commands of §e" + entity.getName() + " (" + entity.getId() + ")§a:");
                        for (StringTag cmdd : cmdddd) {
                            player.sendMessage(cmdd.data);
                        }
                        return true;
                    } else {
                        player.sendMessage("§cNo NPC found with that ID");
                        return true;
                    }
                case "delcmd":
                    if (args.length < 3) {
                        sender.sendMessage("§cUsage: /npc delcmd <ID> <cmd>");
                        return true;
                    }
                    if (!isInteger(args[1])) {
                        player.sendMessage("§cUsage: /npc delcmd <ID> <cmd>");
                        return true;
                    }
                    Entity en = player.getLevel().getEntity(Integer.parseInt(args[1]));
                    if (en == null) {
                        player.sendMessage("§cNo NPC found with that ID");
                        return true;
                    }
                    if (en instanceof NPC_Human || en instanceof NPC_Entity || en.namedTag.getBoolean("npc")) {
                        String cmd;
                        cmd = String.join(" ", args);
                        cmd = cmd.replaceFirst("delcmd", "");
                        cmd = cmd.replaceFirst(args[1], "");
                        StringTag st = new StringTag(cmd, cmd);
                        if (en.namedTag.getList("Commands", StringTag.class).getAll().contains(st)) {
                            en.namedTag.getList("Commands", StringTag.class).remove(st);
                            player.sendMessage("§aCommand §e" + cmd + "§a removed");
                            return true;
                        } else {
                            player.sendMessage("§cCommand §e" + cmd + "§c not found");
                            return true;
                        }
                    } else {
                        player.sendMessage("§cNo NPC found with that ID");
                        return true;
                    }
                case "delplayercmd":
                    if (args.length < 3) {
                        sender.sendMessage("§cUsage: /npc delplayercmd <ID> <cmd>");
                        return true;
                    }
                    if (!isInteger(args[1])) {
                        player.sendMessage("§cUsage: /npc delplayercmd <ID> <cmd>");
                        return true;
                    }
                    Entity en2 = player.getLevel().getEntity(Integer.parseInt(args[1]));
                    if (en2 == null) {
                        player.sendMessage("§cNo NPC found with that ID");
                        return true;
                    }
                    if (en2 instanceof NPC_Human || en2 instanceof NPC_Entity || en2.namedTag.getBoolean("npc")) {
                        String cmd;
                        cmd = String.join(" ", args);
                        cmd = cmd.replaceFirst("delplayercmd", "");
                        cmd = cmd.replaceFirst(args[1], "");
                        StringTag st = new StringTag(cmd, cmd);
                        if (en2.namedTag.getList("PlayerCommands", StringTag.class).getAll().contains(st)) {
                            en2.namedTag.getList("PlayerCommands", StringTag.class).remove(st);
                            player.sendMessage("§aCommand §e" + cmd + "§a removed");
                            return true;
                        } else {
                            player.sendMessage("§cCommand §e" + cmd + "§c not found");
                            return true;
                        }
                    } else {
                        player.sendMessage("§cNo NPC found with that ID");
                        return true;
                    }
                case "delallcmd":
                    if (args.length < 2) {
                        sender.sendMessage("§cUsage: /npc delallcmd <ID>");
                        return true;
                    }
                    if (!isInteger(args[1])) {
                        player.sendMessage("§cUsage: /npc delallcmd <ID>");
                        return true;
                    }
                    Entity en3 = player.getLevel().getEntity(Integer.parseInt(args[1]));
                    if (en3 == null) {
                        player.sendMessage("§cNo NPC found with that ID");
                        return true;
                    }
                    if (en3 instanceof NPC_Human || en3 instanceof NPC_Entity || en3.namedTag.getBoolean("npc")) {
                        en3.namedTag.putList(new ListTag<StringTag>("Commands")).putList(new ListTag<StringTag>("PlayerCommands"));
                        sender.sendMessage("§aCommands removed");
                    } else {
                        player.sendMessage("§cNo NPC found with that ID");
                    }
                    return true;
                case "edit":
                    if (args.length < 3) {
                        player.sendMessage("§cUsage: /npc edit <ID> <item|offhanditem|armor|scale|name|scoretag|tphere|block|skin> [value]");
                        return true;
                    }
                    if (!isInteger(args[1])) {
                        sender.sendMessage("§cUsage: /npc edit <ID> <item|offhanditem|armor|scale|name|scoretag|tphere|block|skin> [value]");
                        return true;
                    }
                    Entity e = player.getLevel().getEntity(Integer.parseInt(args[1]));
                    if (e == null) {
                        player.sendMessage("§cNo entity found with that ID");
                        return true;
                    }
                    switch (args[2].toLowerCase()) {
                        case "item":
                            if (e instanceof NPC_Human || e.namedTag.getBoolean("ishuman")) {
                                NPC_Human nh = (NPC_Human) e;
                                Item item = player.getInventory().getItemInHand();
                                nh.getInventory().setItemInHand(item);
                                e.saveNBT();
                                e.respawnToAll();
                                player.sendMessage("§aItem changed to §e" + item.getName());
                                return true;
                            } else {
                                player.sendMessage("§cNo human NPC found with that ID");
                                return true;
                            }
                        case "offhanditem":
                            if (e instanceof NPC_Human || e.namedTag.getBoolean("ishuman")) {
                                NPC_Human nh = (NPC_Human) e;
                                Item item = player.getOffhandInventory().getItem(0);
                                nh.getOffhandInventory().setItem(0, item);
                                e.saveNBT();
                                player.sendMessage("§aOffhand item changed to §e" + item.getName());
                                return true;
                            } else {
                                player.sendMessage("§cNo human NPC found with that ID");
                                return true;
                            }
                        case "skin":
                            if (e instanceof NPC_Human || e.namedTag.getBoolean("ishuman")) {
                                e.namedTag.putCompound("Skin", createSkinTag(player));
                                e.respawnToAll();
                                player.sendMessage("§aSkin updated");
                                return true;
                            } else {
                                player.sendMessage("§cNo human NPC found with that ID");
                                return true;
                            }
                        case "armor":
                            if (e instanceof NPC_Human || e.namedTag.getBoolean("ishuman")) {
                                NPC_Human nh = (NPC_Human) e;
                                PlayerInventory pl = player.getInventory();
                                nh.getInventory().setHelmet(pl.getHelmet());
                                player.sendMessage("§aHelmet changed to §e" + pl.getHelmet().getName());
                                nh.getInventory().setChestplate(pl.getChestplate());
                                player.sendMessage("§aChestplate changed to §e" + pl.getChestplate().getName());
                                nh.getInventory().setLeggings(pl.getLeggings());
                                player.sendMessage("§aLeggings changed to §e" + pl.getLeggings().getName());
                                nh.getInventory().setBoots(pl.getBoots());
                                player.sendMessage("§aBoots changed to §e" + pl.getBoots().getName());
                                e.saveNBT();
                                return true;
                            } else {
                                player.sendMessage("§cNo human NPC found with that ID");
                                return true;
                            }
                        case "scale":
                            if (args.length < 4) {
                                player.sendMessage("§cUsage: /npc edit <ID> scale <int> §eDefault is 1.");
                                return true;
                            }
                            if (!isFloat(args[3])) {
                                player.sendMessage("§cUsage: /npc edit <ID> scale <int> §eDefault is 1.");
                                return true;
                            }
                            float scale = Float.parseFloat(args[3]);
                            if (scale > 25) {
                                player.sendMessage("§cMax scale is 25");
                                return true;
                            }
                            if (e instanceof NPC_Human || e instanceof NPC_Entity || e.namedTag.getBoolean("npc")) {
                                e.setScale(scale);
                                e.namedTag.putFloat("Scale", scale);
                                e.saveNBT();
                                player.sendMessage("§aScale changed to §e" + args[3]);
                                return true;
                            } else {
                                player.sendMessage("§cNo NPC found with that ID");
                                return true;
                            }
                        case "name":
                            if (e instanceof NPC_Human || e instanceof NPC_Entity || e.namedTag.getBoolean("npc")) {
                                if (args.length != 3) {
                                    name = String.join(" ", args);
                                    name = name.replaceFirst("edit", "");
                                    name = name.replaceFirst("name", "");
                                    name = name.replaceFirst(args[1], "");
                                    name = name.replaceFirst(" ", "");
                                    name = name.replaceFirst(" ", "");
                                    name = name.replaceFirst(" ", "");

                                    name = name.replace("%n", "\n");
                                    e.setNameTag(name);
                                    e.setNameTagVisible(true);
                                    e.setNameTagAlwaysVisible(true);
                                    player.sendMessage("§aName changed to §e" + name);
                                } else {
                                    name = "%k";
                                    e.setNameTagVisible(false);
                                    e.setNameTagAlwaysVisible(false);
                                    player.sendMessage("§aName removed");
                                }

                                e.namedTag.putString("NameTag", name);
                                e.saveNBT();
                                return true;
                            } else {
                                player.sendMessage("§cNo NPC found with that ID");
                                return true;
                            }
                        case "tphere":
                            if (e instanceof NPC_Human || e instanceof NPC_Entity || e.namedTag.getBoolean("npc")) {
                                e.teleport(player);
                                e.saveNBT();
                                e.respawnToAll();
                                player.sendMessage("§aEntity teleported");
                                return true;
                            }
                        case "scoretag":
                            if (e instanceof NPC_Human || e.namedTag.getBoolean("ishuman")) {
                                if (args.length != 3) {
                                    name = String.join(" ", args);
                                    name = name.replaceFirst("edit", "");
                                    name = name.replaceFirst("scoretag", "");
                                    name = name.replaceFirst(args[1], "");
                                    name = name.replaceFirst(" ", "");
                                    name = name.replaceFirst(" ", "");
                                    name = name.replaceFirst(" ", "");

                                    e.setScoreTag(name);
                                    e.namedTag.putString("ScoreTag", name);
                                    player.sendMessage("§aScore tag changed to §e" + name);
                                } else {
                                    name = "";
                                    e.setScoreTag(name);
                                    e.namedTag.putString("ScoreTag", name);
                                    player.sendMessage("§aScore tag cleared");
                                }
                                e.saveNBT();
                            } else {
                                player.sendMessage("§cNo human NPC found with that ID");
                            }
                            return true;
                        case "block":
                            if (e instanceof NPC_Block) {
                                if (args.length < 4) {
                                    player.sendMessage("§cUsage: /npc edit <ID> block <block id:meta>");
                                    return true;
                                }
                                int id;
                                int damage;
                                try {
                                    String[] block = args[3].split(":");
                                    id = Integer.parseInt(block[0]);
                                    damage = Integer.parseInt(block[1]);
                                } catch (Exception ignore) {
                                    player.sendMessage("§cUsage: /npc edit <ID> block <block id:meta>");
                                    return true;
                                }
                                e.namedTag.putInt("Tile", id);
                                e.namedTag.putByte("Data", damage);
                                e.saveNBT();
                                e.respawnToAll();
                                player.sendMessage("§aBlock set");
                            } else {
                                player.sendMessage("§cNo block NPC found with that ID");
                            }
                            return true;
                    }
                    sender.sendMessage("§cUnknown action");
                    return true;
                case "remove":
                    if (NPC.cmd_kill.contains(player.getId())) {
                        NPC.cmd_kill.remove(player.getId());
                        player.sendMessage("§eKill mode deactivated");
                    } else {
                        NPC.cmd_kill.add(player.getId());
                        player.sendMessage("§eKill mode activated - click an NPC to remove it");
                    }
                    return true;
                case "entities":
                    sender.sendMessage("§aAvailable entities: §3" + ENTITIES);
                    return true;
                default:
                    sendHelp(sender);
                    return true;
            }
        }

        return true;
    }

    private static void setInventories(NPC_Human ent, Player pl) {
        PlayerInventory inventoryPl = pl.getInventory();
        PlayerInventory inventoryEnt = ent.getInventory();
        inventoryEnt.setHelmet(inventoryPl.getHelmet());
        inventoryEnt.setChestplate(inventoryPl.getChestplate());
        inventoryEnt.setLeggings(inventoryPl.getLeggings());
        inventoryEnt.setBoots(inventoryPl.getBoots());
        inventoryEnt.setItemInHand(inventoryPl.getItemInHand());
        ent.getOffhandInventory().setItem(0, pl.getOffhandInventory().getItem(0));
        ent.saveNBT();
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private static boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private static void sendHelp(CommandSender sender) {
        sender.sendMessage("§l§a--- NPC HELP ---");
        sender.sendMessage("§3Spawn NPC: §e/npc spawn <entity> <name>");
        sender.sendMessage("§3Remove NPC: §e/npc remove");
        sender.sendMessage("§3Get list of all available entities: §e/npc entities");
        sender.sendMessage("§3Get entity ID: §e/npc getid");
        sender.sendMessage("§3Add console command: §e/npc addcmd <ID> <cmd>");
        sender.sendMessage("§3Add player command: §e/npc addplayercmd <ID> <cmd>");
        sender.sendMessage("§3Delete console command: §e/npc delcmd <ID> <cmd>");
        sender.sendMessage("§3Delete player command: §e/npc delplayercmd <ID> <cmd>");
        sender.sendMessage("§3Delete all commands: §e/npc delallcmd <ID>");
        sender.sendMessage("§3See all commands: §e/npc listcmd <ID>");
        sender.sendMessage("§3Edit NPC: §e/npc edit <ID> <item|offhanditem|armor|scale|name|scoretag|tphere|block|skin> [value]");
        sender.sendMessage("§3Show command help: §e/npc help");
    }

    private static CompoundTag nbt(Player p, String[] args, String name) {
        CompoundTag nbt = new CompoundTag()
                .putList(new ListTag<>("Pos")
                        .add(new DoubleTag("", p.x))
                        .add(new DoubleTag("", p.y))
                        .add(new DoubleTag("", p.z)))
                .putList(new ListTag<DoubleTag>("Motion")
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0)))
                .putList(new ListTag<FloatTag>("Rotation")
                        .add(new FloatTag("", (float) p.getYaw()))
                        .add(new FloatTag("", (float) p.getPitch())))
                .putBoolean("Invulnerable", true)
                .putString("NameTag", name)
                .putList(new ListTag<StringTag>("Commands"))
                .putList(new ListTag<StringTag>("PlayerCommands"))
                .putFloat("Scale", 1.0f)
                .putBoolean("npc", true);
        if ("Human".equals(args[1])) {
            nbt.putCompound("Skin", createSkinTag(p));
            nbt.putBoolean("ishuman", true);
        } else if ("Block".equals(args[1])) {
            nbt.putInt("Tile", 2);
            nbt.putByte("Data", 0);
        }
        return nbt;
    }

    private static CompoundTag createSkinTag(Player p) {
        Skin skin = p.getSkin();
        CompoundTag skinTag = new CompoundTag()
                .putString("ModelId", skin.getSkinId())
                .putByteArray("Data", skin.getSkinData().data)
                .putInt("SkinImageWidth", skin.getSkinData().width)
                .putInt("SkinImageHeight", skin.getSkinData().height)
                .putString("CapeId", skin.getCapeId())
                .putByteArray("CapeData", skin.getCapeData().data)
                .putInt("CapeImageWidth", skin.getCapeData().width)
                .putInt("CapeImageHeight", skin.getCapeData().height)
                .putByteArray("SkinResourcePatch", skin.getSkinResourcePatch().getBytes(StandardCharsets.UTF_8))
                .putByteArray("GeometryData", skin.getGeometryData().getBytes(StandardCharsets.UTF_8))
                .putByteArray("SkinAnimationData", skin.getAnimationData().getBytes(StandardCharsets.UTF_8))
                .putBoolean("PremiumSkin", skin.isPremium())
                .putBoolean("PersonaSkin", skin.isPersona())
                .putBoolean("CapeOnClassicSkin", skin.isCapeOnClassic())
                .putString("ArmSize", skin.getArmSize())
                .putString("SkinColor", skin.getSkinColor())
                .putBoolean("IsTrustedSkin", true);
        List<SkinAnimation> animations = skin.getAnimations();
        if (!animations.isEmpty()) {
            ListTag<CompoundTag> animationsTag = new ListTag<>("AnimatedImageData");
            for (SkinAnimation animation : animations) {
                animationsTag.add(new CompoundTag()
                        .putFloat("Frames", animation.frames)
                        .putInt("Type", animation.type)
                        .putInt("ImageWidth", animation.image.width)
                        .putInt("ImageHeight", animation.image.height)
                        .putInt("AnimationExpression", animation.expression)
                        .putByteArray("Image", animation.image.data));
            }
            skinTag.putList(animationsTag);
        }
        List<PersonaPiece> personaPieces = skin.getPersonaPieces();
        if (!personaPieces.isEmpty()) {
            ListTag<CompoundTag> piecesTag = new ListTag<>("PersonaPieces");
            for (PersonaPiece piece : personaPieces) {
                piecesTag.add(new CompoundTag().putString("PieceId", piece.id)
                        .putString("PieceType", piece.type)
                        .putString("PackId", piece.packId)
                        .putBoolean("IsDefault", piece.isDefault)
                        .putString("ProductId", piece.productId));
            }
        }
        List<PersonaPieceTint> tints = skin.getTintColors();
        if (!tints.isEmpty()) {
            ListTag<CompoundTag> tintsTag = new ListTag<>("PieceTintColors");
            for (PersonaPieceTint tint : tints) {
                ListTag<StringTag> colors = new ListTag<>("Colors");
                colors.setAll(tint.colors.stream().map(s -> new StringTag("", s)).collect(Collectors.toList()));
                tintsTag.add(new CompoundTag()
                        .putString("PieceType", tint.pieceType)
                        .putList(colors));
            }
        }
        if (!skin.getPlayFabId().isEmpty()) {
            skinTag.putString("PlayFabId", skin.getPlayFabId());
        }
        return skinTag;
    }
}
