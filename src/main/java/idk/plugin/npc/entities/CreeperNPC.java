package idk.plugin.npc.entities;

import cn.nukkit.entity.mob.EntityCreeper;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class CreeperNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityCreeper.NETWORK_ID;

    public CreeperNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }
    
    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
