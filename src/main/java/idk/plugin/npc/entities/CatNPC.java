package idk.plugin.npc.entities;

import cn.nukkit.entity.passive.EntityCat;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class CatNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityCat.NETWORK_ID;

    public CatNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }
    
    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
