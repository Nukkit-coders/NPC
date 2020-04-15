package idk.plugin.npc.entities;

import cn.nukkit.entity.mob.EntityZombiePigman;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class ZombiePigmanNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityZombiePigman.NETWORK_ID;

    public ZombiePigmanNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
