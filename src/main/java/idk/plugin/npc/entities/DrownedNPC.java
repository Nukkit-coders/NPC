package idk.plugin.npc.entities;

import cn.nukkit.entity.mob.EntityDrowned;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class DrownedNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityDrowned.NETWORK_ID;

    public DrownedNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
