package idk.plugin.npc.entities;

import cn.nukkit.entity.mob.EntityEvoker;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class EvokerNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityEvoker.NETWORK_ID;

    public EvokerNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}