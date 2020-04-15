package idk.plugin.npc.entities;

import cn.nukkit.entity.mob.EntityEndermite;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class EndermiteNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityEndermite.NETWORK_ID;

    public EndermiteNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
