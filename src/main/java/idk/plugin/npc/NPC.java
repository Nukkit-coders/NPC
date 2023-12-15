package idk.plugin.npc;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.entity.Entity;
import cn.nukkit.plugin.PluginBase;
import idk.plugin.npc.entities.*;

import java.util.ArrayList;
import java.util.List;

public class NPC extends PluginBase {

    static List<Long> cmd_id = new ArrayList<>();
    static List<Long> cmd_kill = new ArrayList<>();

    @Override
    public void onLoad() {
        registerNPCs();
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventListener(), this);
    }

    private static void registerNPCs() {
        Entity.registerEntity(NPC_Bat.class.getSimpleName(), NPC_Bat.class);
        Entity.registerEntity(NPC_Chicken.class.getSimpleName(), NPC_Chicken.class);
        Entity.registerEntity(NPC_Cow.class.getSimpleName(), NPC_Cow.class);
        Entity.registerEntity(NPC_Donkey.class.getSimpleName(), NPC_Donkey.class);
        Entity.registerEntity(NPC_Horse.class.getSimpleName(), NPC_Horse.class);
        Entity.registerEntity(NPC_Mooshroom.class.getSimpleName(), NPC_Mooshroom.class);
        Entity.registerEntity(NPC_Mule.class.getSimpleName(), NPC_Mule.class);
        Entity.registerEntity(NPC_Ocelot.class.getSimpleName(), NPC_Ocelot.class);
        Entity.registerEntity(NPC_Pig.class.getSimpleName(), NPC_Pig.class);
        Entity.registerEntity(NPC_PolarBear.class.getSimpleName(), NPC_PolarBear.class);
        Entity.registerEntity(NPC_Rabbit.class.getSimpleName(), NPC_Rabbit.class);
        Entity.registerEntity(NPC_Sheep.class.getSimpleName(), NPC_Sheep.class);
        Entity.registerEntity(NPC_SkeletonHorse.class.getSimpleName(), NPC_SkeletonHorse.class);
        Entity.registerEntity(NPC_Villager.class.getSimpleName(), NPC_Villager.class);
        Entity.registerEntity(NPC_Wolf.class.getSimpleName(), NPC_Wolf.class);
        Entity.registerEntity(NPC_ZombieHorse.class.getSimpleName(), NPC_ZombieHorse.class);
        Entity.registerEntity(NPC_ElderGuardian.class.getSimpleName(), NPC_ElderGuardian.class);
        Entity.registerEntity(NPC_Guardian.class.getSimpleName(), NPC_Guardian.class);
        Entity.registerEntity(NPC_Snowman.class.getSimpleName(), NPC_Snowman.class);
        Entity.registerEntity(NPC_Llama.class.getSimpleName(), NPC_Llama.class);
        Entity.registerEntity(NPC_Squid.class.getSimpleName(), NPC_Squid.class);
        Entity.registerEntity(NPC_Vindicator.class.getSimpleName(), NPC_Vindicator.class);
        Entity.registerEntity(NPC_Vex.class.getSimpleName(), NPC_Vex.class);
        Entity.registerEntity(NPC_IronGolem.class.getSimpleName(), NPC_IronGolem.class);
        Entity.registerEntity(NPC_Blaze.class.getSimpleName(), NPC_Blaze.class);
        Entity.registerEntity(NPC_Wither.class.getSimpleName(), NPC_Wither.class);
        Entity.registerEntity(NPC_Ghast.class.getSimpleName(), NPC_Ghast.class);
        Entity.registerEntity(NPC_CaveSpider.class.getSimpleName(), NPC_CaveSpider.class);
        Entity.registerEntity(NPC_Creeper.class.getSimpleName(), NPC_Creeper.class);
        Entity.registerEntity(NPC_Enderman.class.getSimpleName(), NPC_Enderman.class);
        Entity.registerEntity(NPC_Endermite.class.getSimpleName(), NPC_Endermite.class);
        Entity.registerEntity(NPC_ZombiePigman.class.getSimpleName(), NPC_ZombiePigman.class);
        Entity.registerEntity(NPC_Silverfish.class.getSimpleName(), NPC_Silverfish.class);
        Entity.registerEntity(NPC_Skeleton.class.getSimpleName(), NPC_Skeleton.class);
        Entity.registerEntity(NPC_Spider.class.getSimpleName(), NPC_Spider.class);
        Entity.registerEntity(NPC_Stray.class.getSimpleName(), NPC_Stray.class);
        Entity.registerEntity(NPC_Witch.class.getSimpleName(), NPC_Witch.class);
        Entity.registerEntity(NPC_Husk.class.getSimpleName(), NPC_Husk.class);
        Entity.registerEntity(NPC_Zombie.class.getSimpleName(), NPC_Zombie.class);
        Entity.registerEntity(NPC_ZombieVillager.class.getSimpleName(), NPC_ZombieVillager.class);
        Entity.registerEntity(NPC_Evoker.class.getSimpleName(), NPC_Evoker.class);
        Entity.registerEntity(NPC_Shulker.class.getSimpleName(), NPC_Shulker.class);
        Entity.registerEntity(NPC_Slime.class.getSimpleName(), NPC_Slime.class);
        Entity.registerEntity(NPC_WitherSkeleton.class.getSimpleName(), NPC_WitherSkeleton.class);
        Entity.registerEntity(NPC_MagmaCube.class.getSimpleName(), NPC_MagmaCube.class);
        Entity.registerEntity(NPC_Human.class.getSimpleName(), NPC_Human.class);
        Entity.registerEntity(NPC_Parrot.class.getSimpleName(), NPC_Parrot.class);
        Entity.registerEntity(NPC_Dolphin.class.getSimpleName(), NPC_Dolphin.class);
        Entity.registerEntity(NPC_Turtle.class.getSimpleName(), NPC_Turtle.class);
        Entity.registerEntity(NPC_Phantom.class.getSimpleName(), NPC_Phantom.class);
        Entity.registerEntity(NPC_Drowned.class.getSimpleName(), NPC_Drowned.class);
        Entity.registerEntity(NPC_Cat.class.getSimpleName(), NPC_Cat.class);
        Entity.registerEntity(NPC_Panda.class.getSimpleName(), NPC_Panda.class);
        Entity.registerEntity(NPC_Pillager.class.getSimpleName(), NPC_Pillager.class);
        Entity.registerEntity(NPC_WanderingTrader.class.getSimpleName(), NPC_WanderingTrader.class);
        Entity.registerEntity(NPC_Fox.class.getSimpleName(), NPC_Fox.class);
        Entity.registerEntity(NPC_Bee.class.getSimpleName(), NPC_Bee.class);
        Entity.registerEntity(NPC_Strider.class.getSimpleName(), NPC_Strider.class);
        Entity.registerEntity(NPC_Zoglin.class.getSimpleName(), NPC_Zoglin.class);
        Entity.registerEntity(NPC_Piglin.class.getSimpleName(), NPC_Piglin.class);
        Entity.registerEntity(NPC_Hoglin.class.getSimpleName(), NPC_Hoglin.class);
        Entity.registerEntity(NPC_Ravager.class.getSimpleName(), NPC_Ravager.class);
        Entity.registerEntity(NPC_Block.class.getSimpleName(), NPC_Block.class);
        Entity.registerEntity(NPC_PiglinBrute.class.getSimpleName(), NPC_PiglinBrute.class);
        Entity.registerEntity(NPC_Goat.class.getSimpleName(), NPC_Goat.class);
        Entity.registerEntity(NPC_Allay.class.getSimpleName(), NPC_Allay.class);
        Entity.registerEntity(NPC_Axolotl.class.getSimpleName(), NPC_Axolotl.class);
        Entity.registerEntity(NPC_Frog.class.getSimpleName(), NPC_Frog.class);
        Entity.registerEntity(NPC_GlowSquid.class.getSimpleName(), NPC_GlowSquid.class);
        Entity.registerEntity(NPC_Tadpole.class.getSimpleName(), NPC_Tadpole.class);
        Entity.registerEntity(NPC_Warden.class.getSimpleName(), NPC_Warden.class);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return CommandHandler.handle(sender, command, args);
    }
}
