package idk.plugin.npc.entities;

import cn.nukkit.entity.passive.EntityTurtle;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class TurtleNPC extends EntityNPC {

    private static final int NETWORK_ID = EntityTurtle.NETWORK_ID;
    
    public TurtleNPC(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public int getNetworkId() {
        return NETWORK_ID;
    }
}
