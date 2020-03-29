package idk.plugin.npc;

import cn.nukkit.Player;
import cn.nukkit.entity.data.Skin;
import cn.nukkit.nbt.tag.*;
import cn.nukkit.plugin.PluginBase;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NPC {

    public static final List<String> entityList = Arrays.asList(
            "Bat", "Blaze", "Cat", "CaveSpider", "Chicken", "Cow", "Creeper",
            "Dolphin", "Donkey", "ElderGuardian", "Enderman", "Endermite", "Evoker", "Ghast", "Guardian",
            "Horse", "Human", "Husk", "IronGolem", "Lama", "Mooshroom", "MagmaCube", "Mule", "Ocelot", "Panda", "Parrot", "Phantom",
            "Pig", "Pillager", "PolarBear", "Rabbit", "SkeletonHorse", "Sheep", "Shulker", "Silverfish", "Skeleton", "Slime",
            "Snowman", "Spider", "Squid", "Stray", "Turtle", "Vex", "Villager", "Vindicator", "WanderingTrader", "Witch", "Wither",
            "WitherSkeleton", "Wolf", "ZombieHorse", "Zombie", "ZombiePigman", "ZombieVillager"
    );

    public static List<String> id = new ArrayList<>();
    public static List<String> kill = new ArrayList<>();

    public static CompoundTag nbt(Object... params) {
        Player player = (Player) params[0];
        String entityType = (String) params[1];
        List<String> commands = Arrays.asList((String[]) params[2]);
        Boolean isPlayer = (Boolean) params[3];

        CompoundTag nbt = new CompoundTag()
                .putList(new ListTag<>("Pos")
                        .add(new DoubleTag("", player.x))
                        .add(new DoubleTag("", player.y))
                        .add(new DoubleTag("", player.z)))
                .putList(new ListTag<DoubleTag>("Motion")
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0))
                        .add(new DoubleTag("", 0)))
                .putList(new ListTag<FloatTag>("Rotation")
                        .add(new FloatTag("", (float) player.getYaw()))
                        .add(new FloatTag("", (float) player.getPitch())))
                .putBoolean("Invulnerable", true)
                .putBoolean("npc", true)
                .putFloat("scale", 1);

        ListTag<StringTag> consoleCommands = new ListTag<>("Commands");
        ListTag<StringTag> playerCommands = new ListTag<>("PlayerCommands");

        commands.forEach(command -> {
            if (isPlayer) {
                playerCommands.add(new StringTag("", command));
            } else {
                consoleCommands.add(new StringTag("", command));
            }
        });

        nbt.putList(consoleCommands);
        nbt.putList(playerCommands);

        if (entityType.equals("Human")) {
            nbt.putCompound("Skin", getSkinTag(player));
            nbt.putBoolean("isHuman", true);
        }

        return nbt;
    }

    public static CompoundTag getSkinTag(Player player) {
        Skin skin = player.getSkin();

        return new CompoundTag()
                .putByteArray("Data", skin.getSkinData().data)
                .putInt("SkinImageWidth", skin.getSkinData().width)
                .putInt("SkinImageHeight", skin.getSkinData().height)
                .putString("ModelId", skin.getSkinId())
                .putString("CapeId", skin.getCapeId())
                .putByteArray("CapeData", skin.getCapeData().data)
                .putInt("CapeImageWidth", skin.getCapeData().width)
                .putInt("CapeImageHeight", skin.getCapeData().height)
                .putByteArray("SkinResourcePatch", skin.getSkinResourcePatch().getBytes(StandardCharsets.UTF_8))
                .putByteArray("GeometryData", skin.getGeometryData().getBytes(StandardCharsets.UTF_8))
                .putByteArray("AnimationData", skin.getAnimationData().getBytes(StandardCharsets.UTF_8))
                .putBoolean("PremiumSkin", skin.isPremium())
                .putBoolean("PersonaSkin", skin.isPersona())
                .putBoolean("CapeOnClassicSkin", skin.isCapeOnClassic());
    }
}