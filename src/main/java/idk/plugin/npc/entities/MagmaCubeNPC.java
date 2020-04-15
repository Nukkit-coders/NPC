package idk.plugin.npc.entities;

import cn.nukkit.entity.mob.EntityMagmaCube;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class MagmaCubeNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityMagmaCube.NETWORK_ID;

    public MagmaCubeNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }
    
    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
