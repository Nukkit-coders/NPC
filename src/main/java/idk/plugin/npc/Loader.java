package idk.plugin.npc;

import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;
import idk.plugin.npc.commands.NpcCommand;
import idk.plugin.npc.listeners.EntityDamageListener;
import idk.plugin.npc.listeners.EntityVehicleEnterListener;

import java.util.Arrays;

public class Loader extends PluginBase {

    @Override
    public void onEnable() {
        this.registerListeners();
        this.registerCommands();
    }

    private void registerListeners() {
        Arrays.asList(
                new EntityDamageListener(),
                new EntityVehicleEnterListener()
        ).forEach(listener -> Server.getInstance().getPluginManager().registerEvents(listener, this));
    }

    private void registerCommands() {
        Server.getInstance().getCommandMap().register("", new NpcCommand());
    }
}