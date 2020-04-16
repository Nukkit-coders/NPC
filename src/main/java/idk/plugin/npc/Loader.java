package idk.plugin.npc;

import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import idk.plugin.npc.commands.NpcCommand;
import idk.plugin.npc.listeners.entity.EntityDamageListener;
import idk.plugin.npc.listeners.entity.EntityVehicleEnterListener;
import idk.plugin.npc.listeners.entity.player.PlayerMoveListener;
import idk.plugin.npc.listeners.entity.player.PlayerQuitListener;

import java.util.Arrays;

public class Loader extends PluginBase {

    @Override
    public void onEnable() {
        NPC.registerEntity();
        PluginManager pluginManager = Server.getInstance().getPluginManager();
        if(pluginManager.getPlugin("FormAPI") == null) {
            getLogger().alert("§cRequired component not found (FormAPI Plugin)");
            pluginManager.disablePlugin(this);
            return;
        }
        this.registerListeners();
        this.registerCommands();
    }

    private void registerListeners() {
        Arrays.asList(
                new EntityDamageListener(),
                new EntityVehicleEnterListener(),
                new PlayerQuitListener(),
                new PlayerMoveListener()
        ).forEach(listener -> Server.getInstance().getPluginManager().registerEvents(listener, this));
    }

    private void registerCommands() {
        Server.getInstance().getCommandMap().register("", new NpcCommand());
    }
}