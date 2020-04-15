package idk.plugin.npc.entities;

import cn.nukkit.entity.passive.EntityPanda;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class PandaNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityPanda.NETWORK_ID;

    public PandaNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}