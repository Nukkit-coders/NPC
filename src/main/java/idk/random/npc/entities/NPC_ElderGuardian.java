package idk.random.npc.entities;

import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class NPC_ElderGuardian extends NPC_Entity {

    public static final int NID = 50;

    public NPC_ElderGuardian(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }
    
    @Override
    public int getNetworkId() {
        return NID;
    }
}
