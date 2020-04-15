package idk.plugin.npc.entities;

import cn.nukkit.entity.passive.EntityRabbit;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class RabbitNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityRabbit.NETWORK_ID;
    
    public RabbitNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
