package idk.plugin.npc.entities;

import cn.nukkit.entity.mob.EntityShulker;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class ShulkerNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityShulker.NETWORK_ID;

    public ShulkerNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
