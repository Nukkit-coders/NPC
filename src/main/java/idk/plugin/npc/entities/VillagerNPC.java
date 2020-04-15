package idk.plugin.npc.entities;

import cn.nukkit.entity.passive.EntityVillager;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class VillagerNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityVillager.NETWORK_ID;
    
    public VillagerNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
