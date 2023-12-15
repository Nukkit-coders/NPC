package idk.plugin.npc.entities;

import cn.nukkit.entity.data.IntEntityData;
import cn.nukkit.level.GlobalBlockPalette;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class NPC_Block extends NPC_Entity {

    public static final int NID = 66;

    public NPC_Block(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
        this.setDataFlag(DATA_FLAGS, DATA_FLAG_IMMOBILE, true);
        if (!this.getServer().getName().equals("Nukkit PetteriM1 Edition")) {
            this.setDataProperty(new IntEntityData(DATA_TYPE_INT, GlobalBlockPalette.getOrCreateRuntimeId(this.namedTag.getInt("Tile"), this.namedTag.getByte("Data"))));
        }
    }

    @Override
    public int getNetworkId() {
        return NID;
    }

    @Override
    public void respawnToAll() {
        if (!this.getServer().getName().equals("Nukkit PetteriM1 Edition")) {
            this.setDataProperty(new IntEntityData(DATA_TYPE_INT, GlobalBlockPalette.getOrCreateRuntimeId(this.namedTag.getInt("Tile"), this.namedTag.getByte("Data"))));
        }
        super.respawnToAll();
    }

    @Override
    public float getHeight() {
        return 0.98f;
    }

    @Override
    public float getWidth() {
        return 0.98f;
    }
}
