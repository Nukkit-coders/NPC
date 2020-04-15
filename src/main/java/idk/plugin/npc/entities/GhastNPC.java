package idk.plugin.npc.entities;

import cn.nukkit.entity.mob.EntityGhast;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class GhastNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityGhast.NETWORK_ID;
    
    public GhastNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
