package idk.plugin.npc.entities;

import cn.nukkit.entity.mob.EntityEnderman;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class EndermanNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityEnderman.NETWORK_ID;

    public EndermanNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }
    
    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
