package idk.plugin.npc.entities;

import cn.nukkit.entity.passive.EntitySheep;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class SheepNPC extends EntityNPC {

    private static final int NETWORK_ID = EntitySheep.NETWORK_ID;
    
    public SheepNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
