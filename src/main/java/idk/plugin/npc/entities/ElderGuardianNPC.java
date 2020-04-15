package idk.plugin.npc.entities;

import cn.nukkit.entity.mob.EntityElderGuardian;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class ElderGuardianNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityElderGuardian.NETWORK_ID;

    public ElderGuardianNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
        this.setDataFlag(DATA_FLAGS, DATA_FLAG_ELDER, true);
    }
    
    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
