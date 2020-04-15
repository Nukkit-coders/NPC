package idk.plugin.npc.entities;

import cn.nukkit.entity.mob.EntityWitch;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class WitchNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityWitch.NETWORK_ID;

    public WitchNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
