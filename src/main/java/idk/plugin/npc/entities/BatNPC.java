package idk.plugin.npc.entities;

import cn.nukkit.entity.passive.EntityBat;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class BatNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityBat.NETWORK_ID;

    public BatNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}