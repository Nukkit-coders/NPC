package idk.plugin.npc.listeners;

import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityVehicleEnterEvent;
import cn.nukkit.nbt.tag.CompoundTag;

public class EntityVehicleEnterListener implements Listener {
    @EventHandler
    public void onVehicleEnter(EntityVehicleEnterEvent event) {
        Entity entity = event.getEntity();
        CompoundTag namedTag = entity.namedTag;

        if (namedTag.getBoolean("npc")) {
            event.setCancelled();
        }
    }
}