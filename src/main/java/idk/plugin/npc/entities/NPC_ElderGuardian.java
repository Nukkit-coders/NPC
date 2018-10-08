package idk.plugin.npc.entities;

import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class NPC_ElderGuardian extends NPC_Entity {

    public static final int NID = 50;

    public NPC_ElderGuardian(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
        this.setDataFlag(DATA_FLAGS, DATA_FLAG_ELDER, true);
    }
    
    @Override
    public int getNetworkId() {
        return NID;
    }
}
