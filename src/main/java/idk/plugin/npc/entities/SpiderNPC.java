package idk.plugin.npc.entities;

import cn.nukkit.entity.mob.EntitySpider;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class SpiderNPC extends EntityNPC {

    private static final int NETWORK_ID = EntitySpider.NETWORK_ID;

    public SpiderNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
