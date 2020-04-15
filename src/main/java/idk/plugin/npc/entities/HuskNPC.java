package idk.plugin.npc.entities;

import cn.nukkit.entity.mob.EntityHusk;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class HuskNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityHusk.NETWORK_ID;

    public HuskNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }
    
    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
