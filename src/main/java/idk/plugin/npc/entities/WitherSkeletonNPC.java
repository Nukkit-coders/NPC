package idk.plugin.npc.entities;

import cn.nukkit.entity.mob.EntityWitherSkeleton;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class WitherSkeletonNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityWitherSkeleton.NETWORK_ID;

    public WitherSkeletonNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
