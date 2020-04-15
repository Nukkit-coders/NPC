package idk.plugin.npc.entities;

import cn.nukkit.entity.mob.EntityWither;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class WitherNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityWither.NETWORK_ID;

    public WitherNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
