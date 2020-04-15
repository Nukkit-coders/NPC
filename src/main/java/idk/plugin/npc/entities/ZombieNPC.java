package idk.plugin.npc.entities;

import cn.nukkit.entity.mob.EntityZombie;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class ZombieNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityZombie.NETWORK_ID;
    
    public ZombieNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
