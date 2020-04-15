package idk.plugin.npc.entities;

import cn.nukkit.entity.passive.EntitySkeletonHorse;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class SkeletonHorseNPC extends EntityNPC {

    private static final int NETWORK_ID = EntitySkeletonHorse.NETWORK_ID;
    
    public SkeletonHorseNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
