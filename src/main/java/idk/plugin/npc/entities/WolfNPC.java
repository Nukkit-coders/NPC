package idk.plugin.npc.entities;

import cn.nukkit.entity.passive.EntityWolf;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class WolfNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityWolf.NETWORK_ID;
    
    public WolfNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
