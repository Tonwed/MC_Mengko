package com.tonwed.mengko;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Event implements Listener {
    @EventHandler
    public void dontMove(PlayerMoveEvent e) {
        // 方法名随意
        double distance = e.getFrom().distance(e.getTo());
        if (distance != 0) {
            e.setCancelled(true);
        }
    }
}
