package idk.plugin.npc.entities;

import cn.nukkit.entity.passive.EntityMule;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class MuleNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityMule.NETWORK_ID;

    public MuleNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}