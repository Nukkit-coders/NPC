package idk.plugin.npc.entities;

import cn.nukkit.Player;
import cn.nukkit.entity.EntityHuman;
import cn.nukkit.entity.data.Skin;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.network.protocol.AddPlayerPacket;

import java.util.UUID;

public class NPC_Human extends EntityHuman {

    public NPC_Human(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
        if (namedTag.contains("Scale")) {
            setScale(namedTag.getFloat("Scale"));
        }
        if (namedTag.contains("ScoreTag")) {
            setScoreTag(namedTag.getString("ScoreTag"));
        }
    }

    @Override
    public void spawnTo(Player player) {
        if (!this.hasSpawned.containsKey(player.getLoaderId())) {
            this.hasSpawned.put(player.getLoaderId(), player);

            this.server.updatePlayerListData(this.getUniqueId(), this.getId(), this.getName(), this.checkSkin(this.skin), new Player[]{player});

            AddPlayerPacket pk = new AddPlayerPacket();
            pk.uuid = this.getUniqueId();
            pk.username = this.getName();
            pk.entityUniqueId = this.getId();
            pk.entityRuntimeId = this.getId();
            pk.x = (float) this.x;
            pk.y = (float) this.y;
            pk.z = (float) this.z;
            pk.speedX = (float) this.motionX;
            pk.speedY = (float) this.motionY;
            pk.speedZ = (float) this.motionZ;
            pk.yaw = (float) this.yaw;
            pk.pitch = (float) this.pitch;
            pk.item = this.inventory.getItemInHand();
            pk.metadata = this.dataProperties;
            player.dataPacket(pk);

            this.inventory.sendHeldItem(player);
            this.inventory.sendArmorContents(player);
            this.offhandInventory.sendContents(player);

            this.server.removePlayerListData(this.getUniqueId(), new Player[]{player});
        }
    }

    private Skin checkSkin(Skin skin) {
        skin.setTrusted(true);
        if (!skin.isPersona()) {
            skin.setFullSkinId(UUID.randomUUID().toString());
        }
        return skin;
    }
}
