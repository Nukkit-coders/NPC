package idk.plugin.npc.entities;

import cn.nukkit.entity.EntityHuman;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class HumanNPC extends EntityHuman {

    private static final int NETWORK_ID = EntityHuman.NETWORK_ID;

    public HumanNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}