package idk.plugin.npc.entities;

import cn.nukkit.entity.mob.EntityCaveSpider;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class CaveSpiderNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityCaveSpider.NETWORK_ID;

    public CaveSpiderNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
