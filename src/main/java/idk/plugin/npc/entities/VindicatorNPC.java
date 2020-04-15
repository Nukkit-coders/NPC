package idk.plugin.npc.entities;

import cn.nukkit.entity.mob.EntityVindicator;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class VindicatorNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityVindicator.NETWORK_ID;

    public VindicatorNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }
    
    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
