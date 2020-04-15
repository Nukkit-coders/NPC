package idk.plugin.npc.entities;

import cn.nukkit.entity.passive.EntityDolphin;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class DolphinNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityDolphin.NETWORK_ID;
    
    public DolphinNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
