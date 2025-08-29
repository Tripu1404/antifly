package com.tuplugin.antifly;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerMoveEvent;
import cn.nukkit.math.Vector3;
import cn.nukkit.plugin.PluginBase;

public class AntiFly extends PluginBase implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (player.isCreative() || player.isSpectator()) return;

        double deltaY = event.getTo().y - event.getFrom().y;
        double deltaXZ = Math.sqrt(Math.pow(event.getTo().x - event.getFrom().x, 2) + Math.pow(event.getTo().z - event.getFrom().z, 2));

        boolean movimientoCancelado = false;

        if (deltaY > 0.8 && player.getMotion().y > 0.5) {
            movimientoCancelado = true;
        }

        if (deltaXZ > 1.2 && player.getMotion().y == 0) {
            movimientoCancelado = true;
        }

        if (movimientoCancelado) {
            event.setCancelled(true);

            Vector3 retroceso = player.getDirectionVector().multiply(-0.5);
            player.setMotion(retroceso);
        }
    }
}
