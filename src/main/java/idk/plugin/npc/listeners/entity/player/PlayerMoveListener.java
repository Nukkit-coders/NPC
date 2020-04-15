package idk.plugin.npc.listeners.entity.player;

import cn.nukkit.Player;
import cn.nukkit.entity.EntityHuman;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerMoveEvent;
import cn.nukkit.math.Vector2;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.network.protocol.MoveEntityAbsolutePacket;
import cn.nukkit.network.protocol.MovePlayerPacket;

import java.util.Arrays;

public class PlayerMoveListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        Arrays.asList(player.getLevel().getNearbyEntities(player.getBoundingBox().clone().expand(16, 16, 16), player)).forEach(entity -> {
            CompoundTag namedTag = entity.namedTag;

            if (namedTag.getBoolean("isRotation")) {
                double xdiff = player.x - entity.x;
                double zdiff = player.z - entity.z;
                double angle = Math.atan2(zdiff, xdiff);
                double yaw = ((angle * 180) / Math.PI) - 90;

                double ydiff = player.y - entity.y;
                Vector2 v = new Vector2(entity.x, entity.z);
                double dist = v.distance(player.x, player.z);
                angle = Math.atan2(dist, ydiff);
                double pitch = ((angle * 180) / Math.PI) - 90;

                if (entity instanceof EntityHuman) {
                    MovePlayerPacket movePlayerPacket = new MovePlayerPacket();
                    movePlayerPacket.eid = entity.getId();
                    movePlayerPacket.x = (float) entity.x;
                    movePlayerPacket.y = (float) entity.y + entity.getEyeHeight();
                    movePlayerPacket.z = (float) entity.z;
                    movePlayerPacket.yaw = (float) yaw;
                    movePlayerPacket.pitch = (float) pitch;
                    movePlayerPacket.headYaw = (float) yaw;
                    movePlayerPacket.onGround = entity.onGround;

                    player.directDataPacket(movePlayerPacket);
                } else {
                    MoveEntityAbsolutePacket moveEntityAbsolutePacket = new MoveEntityAbsolutePacket();
                    moveEntityAbsolutePacket.eid = entity.getId();
                    moveEntityAbsolutePacket.x = entity.x;
                    moveEntityAbsolutePacket.y = entity.y;
                    moveEntityAbsolutePacket.z = entity.z;
                    moveEntityAbsolutePacket.yaw = yaw;
                    moveEntityAbsolutePacket.pitch = pitch;
                    moveEntityAbsolutePacket.headYaw = yaw;
                    moveEntityAbsolutePacket.onGround = entity.onGround;

                    player.directDataPacket(moveEntityAbsolutePacket);
                }
            }
        });
    }
}