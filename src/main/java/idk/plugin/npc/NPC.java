package idk.plugin.npc;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.data.Skin;
import idk.plugin.npc.entities.*;
import cn.nukkit.nbt.tag.*;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class NPC {

    public static final List<UUID> idRecipientList = new ArrayList<>();
    public static final List<UUID> npcEditorsList = new ArrayList<>();

    public static final List<String> entityList = Arrays.asList(
            "Bat", "Blaze", "Cat", "CaveSpider", "Chicken", "Cow", "Creeper",
            "Dolphin", "Donkey", "ElderGuardian", "Enderman", "Endermite", "Evoker", "Ghast", "Guardian",
            "Horse", "Human", "Husk", "IronGolem", "Lama", "Mooshroom", "MagmaCube", "Mule", "Ocelot", "Panda", "Parrot", "Phantom",
            "Pig", "Pillager", "PolarBear", "Rabbit", "SkeletonHorse", "Sheep", "Shulker", "Silverfish", "Skeleton", "Slime",
            "Snowman", "Spider", "Squid", "Stray", "Turtle", "Vex", "Villager", "Vindicator", "WanderingTrader", "Witch", "Wither",
            "WitherSkeleton", "Wolf", "ZombieHorse", "Zombie", "ZombiePigman", "ZombieVillager"
    );

    public static CompoundTag nbt(Object... params) {
        Player player = (Player) params[0];
        String entityType = (String) params[1];
        List<String> commands = Arrays.asList((String[]) params[2]);
        Boolean hasPlayer = (Boolean) params[3];
        Boolean rotation = (Boolean) params[4];

        ListTag<StringTag> consoleCommands = new ListTag<>("Commands");
        ListTag<StringTag> playerCommands = new ListTag<>("PlayerCommands");

        commands.forEach(command -> {
            if (hasPlayer) {
                playerCommands.add(new StringTag("", command));
            } else {
                consoleCommands.add(new StringTag("", command));
            }
        });

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
                .putBoolean("isRotation", rotation)
                .putList(consoleCommands)
                .putList(playerCommands)
                .putBoolean("npc", true)
                .putFloat("scale", 1);

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

    public static void registerEntity() {
        Entity.registerEntity(BatNPC.class.getSimpleName(), BatNPC.class);
        Entity.registerEntity(ChickenNPC.class.getSimpleName(), ChickenNPC.class);
        Entity.registerEntity(CowNPC.class.getSimpleName(), CowNPC.class);
        Entity.registerEntity(DonkeyNPC.class.getSimpleName(), DonkeyNPC.class);
        Entity.registerEntity(HorseNPC.class.getSimpleName(), HorseNPC.class);
        Entity.registerEntity(MooshroomNPC.class.getSimpleName(), MooshroomNPC.class);
        Entity.registerEntity(MuleNPC.class.getSimpleName(), MuleNPC.class);
        Entity.registerEntity(OcelotNPC.class.getSimpleName(), OcelotNPC.class);
        Entity.registerEntity(PigNPC.class.getSimpleName(), PigNPC.class);
        Entity.registerEntity(PolarBearNPC.class.getSimpleName(), PolarBearNPC.class);
        Entity.registerEntity(RabbitNPC.class.getSimpleName(), RabbitNPC.class);
        Entity.registerEntity(SheepNPC.class.getSimpleName(), SheepNPC.class);
        Entity.registerEntity(SkeletonHorseNPC.class.getSimpleName(), SkeletonHorseNPC.class);
        Entity.registerEntity(VillagerNPC.class.getSimpleName(), VillagerNPC.class);
        Entity.registerEntity(WolfNPC.class.getSimpleName(), WolfNPC.class);
        Entity.registerEntity(ZombieHorseNPC.class.getSimpleName(), ZombieHorseNPC.class);
        Entity.registerEntity(ElderGuardianNPC.class.getSimpleName(), ElderGuardianNPC.class);
        Entity.registerEntity(GuardianNPC.class.getSimpleName(), GuardianNPC.class);
        Entity.registerEntity(SnowmanNPC.class.getSimpleName(), SnowmanNPC.class);
        Entity.registerEntity(LamaNPC.class.getSimpleName(), LamaNPC.class);
        Entity.registerEntity(SquidNPC.class.getSimpleName(), SquidNPC.class);
        Entity.registerEntity(VindicatorNPC.class.getSimpleName(), VindicatorNPC.class);
        Entity.registerEntity(VexNPC.class.getSimpleName(), VexNPC.class);
        Entity.registerEntity(IronGolemNPC.class.getSimpleName(), IronGolemNPC.class);
        Entity.registerEntity(BlazeNPC.class.getSimpleName(), BlazeNPC.class);
        Entity.registerEntity(WitherNPC.class.getSimpleName(), WitherNPC.class);
        Entity.registerEntity(GhastNPC.class.getSimpleName(), GhastNPC.class);
        Entity.registerEntity(CaveSpiderNPC.class.getSimpleName(), CaveSpiderNPC.class);
        Entity.registerEntity(CreeperNPC.class.getSimpleName(), CreeperNPC.class);
        Entity.registerEntity(EndermanNPC.class.getSimpleName(), EndermanNPC.class);
        Entity.registerEntity(EndermiteNPC.class.getSimpleName(), EndermiteNPC.class);
        Entity.registerEntity(ZombiePigmanNPC.class.getSimpleName(), ZombiePigmanNPC.class);
        Entity.registerEntity(SilverfishNPC.class.getSimpleName(), SilverfishNPC.class);
        Entity.registerEntity(SkeletonNPC.class.getSimpleName(), SkeletonNPC.class);
        Entity.registerEntity(SpiderNPC.class.getSimpleName(), SpiderNPC.class);
        Entity.registerEntity(StrayNPC.class.getSimpleName(), StrayNPC.class);
        Entity.registerEntity(WitchNPC.class.getSimpleName(), WitchNPC.class);
        Entity.registerEntity(HuskNPC.class.getSimpleName(), HuskNPC.class);
        Entity.registerEntity(ZombieNPC.class.getSimpleName(), ZombieNPC.class);
        Entity.registerEntity(ZombieVillagerNPC.class.getSimpleName(), ZombieVillagerNPC.class);
        Entity.registerEntity(EvokerNPC.class.getSimpleName(), EvokerNPC.class);
        Entity.registerEntity(ShulkerNPC.class.getSimpleName(), ShulkerNPC.class);
        Entity.registerEntity(SlimeNPC.class.getSimpleName(), SlimeNPC.class);
        Entity.registerEntity(WitherSkeletonNPC.class.getSimpleName(), WitherSkeletonNPC.class);
        Entity.registerEntity(MagmaCubeNPC.class.getSimpleName(), MagmaCubeNPC.class);
        Entity.registerEntity(HumanNPC.class.getSimpleName(), HumanNPC.class);
        Entity.registerEntity(ParrotNPC.class.getSimpleName(), ParrotNPC.class);
        Entity.registerEntity(DolphinNPC.class.getSimpleName(), DolphinNPC.class);
        Entity.registerEntity(TurtleNPC.class.getSimpleName(), TurtleNPC.class);
        Entity.registerEntity(PhantomNPC.class.getSimpleName(), PhantomNPC.class);
        Entity.registerEntity(DrownedNPC.class.getSimpleName(), DrownedNPC.class);
        Entity.registerEntity(CatNPC.class.getSimpleName(), CatNPC.class);
        Entity.registerEntity(PandaNPC.class.getSimpleName(), PandaNPC.class);
        Entity.registerEntity(PillagerNPC.class.getSimpleName(), PillagerNPC.class);
        Entity.registerEntity(WanderingTraderNPC.class.getSimpleName(), WanderingTraderNPC.class);
    }
}