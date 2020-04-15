package idk.plugin.npc.entities;

import cn.nukkit.entity.passive.EntityCow;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class CowNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityCow.NETWORK_ID;

    public CowNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }
    
    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
