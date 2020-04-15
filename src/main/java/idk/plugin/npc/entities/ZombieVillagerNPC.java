package idk.plugin.npc.entities;

import cn.nukkit.entity.mob.EntityZombieVillager;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class ZombieVillagerNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityZombieVillager.NETWORK_ID;

    public ZombieVillagerNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}