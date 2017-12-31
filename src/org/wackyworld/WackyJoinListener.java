/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wackyworld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;

/**
 *
 * @author user
 */
public class WackyJoinListener implements Listener {
    private WackyWorld plugin;
    
    public WackyJoinListener(WackyWorld plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        //Bukkit.broadcastMessage("Wacky join.");
        generateGreeting(event);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
                WackyWorld.fireworkControl.setRandomFirework(event.getPlayer().getLocation());
            }
        }, 20);
    }
    
    public void generateGreeting(PlayerJoinEvent event) {
        Random r = new Random();
        int roll = r.nextInt(plugin.joinMessages.size());
        
        String playerName = event.getPlayer().getName();
        String greeting = plugin.joinMessages.get(roll).replace("$P", playerName);
        
        event.setJoinMessage(greeting);
        
    }
}
