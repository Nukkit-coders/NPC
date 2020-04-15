package idk.plugin.npc.entities;

import cn.nukkit.entity.passive.EntityChicken;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class ChickenNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityChicken.NETWORK_ID;
    
    public ChickenNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
