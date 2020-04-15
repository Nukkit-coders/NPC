package idk.plugin.npc.entities;

import cn.nukkit.entity.mob.EntityPillager;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class PillagerNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityPillager.NETWORK_ID;

    public PillagerNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
