package idk.plugin.npc.entities;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.entity.data.IntEntityData;
import cn.nukkit.level.GlobalBlockPalette;
import cn.nukkit.level.Level;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.network.protocol.AddEntityPacket;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class NPC_Block extends NPC_Entity {

    public static final int NID = 66;

    private static Field f_protocol;
    private static Method f_getOrCreateRuntimeId;

    static {
        if ("Nukkit PetteriM1 Edition".equals(Server.getInstance().getName())) {
            try {
                f_protocol = Player.class.getDeclaredField("protocol");
                f_getOrCreateRuntimeId = GlobalBlockPalette.class.getDeclaredMethod("getOrCreateRuntimeId", int.class, int.class, int.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public NPC_Block(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
        this.setDataFlag(DATA_FLAGS, DATA_FLAG_IMMOBILE, true);
        if (f_getOrCreateRuntimeId == null) {
            this.setDataProperty(new IntEntityData(DATA_TYPE_INT, GlobalBlockPalette.getOrCreateRuntimeId(this.namedTag.getInt("Tile"), this.namedTag.getByte("Data"))));
        }
    }

    @Override
    public int getNetworkId() {
        return NID;
    }

    @Override
    public void spawnTo(Player player) {
        if (f_getOrCreateRuntimeId == null) {
            super.spawnTo(player);
            return;
        }

        if (!this.hasSpawned.containsKey(player.getLoaderId())) {
            Boolean hasChunk = player.usedChunks.get(Level.chunkHash(this.chunk.getX(), this.chunk.getZ()));
            if (hasChunk != null && hasChunk) {
                AddEntityPacket addEntity = new AddEntityPacket();
                addEntity.type = this.getNetworkId();
                addEntity.entityUniqueId = this.id;
                addEntity.entityRuntimeId = this.id;
                addEntity.yaw = (float) this.yaw;
                addEntity.headYaw = (float) this.yaw;
                addEntity.pitch = (float) this.pitch;
                addEntity.x = (float) this.x;
                addEntity.y = (float) this.y;
                addEntity.z = (float) this.z;
                addEntity.speedX = (float) this.motionX;
                addEntity.speedY = (float) this.motionY;
                addEntity.speedZ = (float) this.motionZ;
                try {
                    int protocol = (int) f_protocol.get(player);
                    addEntity.metadata = this.dataProperties.clone().put(new IntEntityData(DATA_VARIANT, protocol > 201 ? (int) f_getOrCreateRuntimeId.invoke(null, protocol, this.namedTag.getInt("Tile"), this.namedTag.getByte("Data")) : this.namedTag.getInt("Tile")));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                player.dataPacket(addEntity);
                this.hasSpawned.put(player.getLoaderId(), player);
            }
        }
    }

    @Override
    public void respawnToAll() {
        if (f_getOrCreateRuntimeId == null) {
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
