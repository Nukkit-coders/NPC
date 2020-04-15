package idk.plugin.npc.entities;

import cn.nukkit.entity.passive.EntityOcelot;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class OcelotNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityOcelot.NETWORK_ID;

    public OcelotNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}