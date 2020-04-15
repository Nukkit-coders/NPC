package idk.plugin.npc.entities;

import cn.nukkit.entity.passive.EntitySquid;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class SquidNPC extends EntityNPC {

    private static final int NETWORK_ID = EntitySquid.NETWORK_ID;
    
    public SquidNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
