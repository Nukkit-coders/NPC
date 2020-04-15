package idk.plugin.npc.entities;

import cn.nukkit.entity.passive.EntityWanderingTrader;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class WanderingTraderNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityWanderingTrader.NETWORK_ID;

    public WanderingTraderNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
