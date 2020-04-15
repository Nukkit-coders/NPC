package idk.plugin.npc.entities;

import cn.nukkit.entity.mob.EntityGuardian;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class GuardianNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityGuardian.NETWORK_ID;

    public GuardianNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}