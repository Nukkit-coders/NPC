package idk.plugin.npc.entities;

import cn.nukkit.entity.mob.EntityPhantom;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class PhantomNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityPhantom.NETWORK_ID;

    public PhantomNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}