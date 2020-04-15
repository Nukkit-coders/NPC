package idk.plugin.npc.entities;

import cn.nukkit.entity.mob.EntityStray;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class StrayNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityStray.NETWORK_ID;

    public StrayNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
