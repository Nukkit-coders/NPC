package idk.plugin.npc.entities;

import cn.nukkit.entity.passive.EntityParrot;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class ParrotNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityParrot.NETWORK_ID;

    public ParrotNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}