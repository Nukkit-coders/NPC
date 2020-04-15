package idk.plugin.npc.entities;

import cn.nukkit.entity.passive.EntityMooshroom;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class MooshroomNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityMooshroom.NETWORK_ID;

    public MooshroomNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}