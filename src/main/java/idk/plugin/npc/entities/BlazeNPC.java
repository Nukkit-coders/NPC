package idk.plugin.npc.entities;

import cn.nukkit.entity.mob.EntityBlaze;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class BlazeNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityBlaze.NETWORK_ID;

    public BlazeNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
