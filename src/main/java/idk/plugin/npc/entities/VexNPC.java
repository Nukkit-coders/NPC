package idk.plugin.npc.entities;

import cn.nukkit.entity.mob.EntityVex;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class VexNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityVex.NETWORK_ID;

    public VexNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
