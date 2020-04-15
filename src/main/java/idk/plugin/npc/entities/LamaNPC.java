package idk.plugin.npc.entities;

import cn.nukkit.entity.passive.EntityLlama;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class LamaNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityLlama.NETWORK_ID;

    public LamaNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}