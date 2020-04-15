package idk.plugin.npc.entities;

import cn.nukkit.entity.passive.EntityHorse;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class HorseNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityHorse.NETWORK_ID;

    public HorseNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}