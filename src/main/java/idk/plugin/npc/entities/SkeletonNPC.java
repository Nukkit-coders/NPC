package idk.plugin.npc.entities;

import cn.nukkit.entity.mob.EntitySkeleton;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class SkeletonNPC extends EntityNPC {

    private static final int NETWORK_ID = EntitySkeleton.NETWORK_ID;

    public SkeletonNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
