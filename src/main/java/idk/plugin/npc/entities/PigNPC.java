package idk.plugin.npc.entities;

import cn.nukkit.entity.passive.EntityPig;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class PigNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityPig.NETWORK_ID;

    public PigNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}