package idk.plugin.npc.entities;

import cn.nukkit.entity.passive.EntityZombieHorse;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class ZombieHorseNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityZombieHorse.NETWORK_ID;
    
    public ZombieHorseNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
