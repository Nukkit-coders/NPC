package idk.plugin.npc.entities;

import cn.nukkit.entity.mob.EntitySlime;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class SlimeNPC extends EntityNPC {

    private static final int NETWORK_ID = EntitySlime.NETWORK_ID;

    public SlimeNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
