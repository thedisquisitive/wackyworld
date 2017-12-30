/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wackyworld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 *
 * @author user
 */
public class WackyDeathListener implements Listener {
    private WackyWorld plugin;
    
    public WackyDeathListener(WackyWorld plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onPlayerDeath(EntityDeathEvent event) {
        Random r = new Random();
        if (!(event instanceof PlayerDeathEvent)) {
            return;
        }
        PlayerDeathEvent e = (PlayerDeathEvent) event;
        final EntityDamageEvent cause = e.getEntity().getLastDamageCause();
        if (cause instanceof EntityDamageByEntityEvent) {
            //e.setDeathMessage(getMurderedDeathMessage(e.getEntity()));
            return;
        }
        else {
            String msg, msg2;
            switch (cause.getCause()) {
                case LAVA:
                    msg = plugin.lavaDeaths.get(r.nextInt(plugin.lavaDeaths.size()));
                    msg2 = msg.replace("$P", e.getEntity().getName());
                    e.setDeathMessage(msg2);
                    break;
                case DROWNING:
                    msg = plugin.drowningDeaths.get(r.nextInt(plugin.drowningDeaths.size()));
                    msg2 = msg.replace("$P", e.getEntity().getName());
                    e.setDeathMessage(msg2);
                    break;
                case FALL:
                    msg = plugin.fallDeaths.get(r.nextInt(plugin.fallDeaths.size()));
                    msg2 = msg.replace("$P", e.getEntity().getName());
                    e.setDeathMessage(msg2);
                    break;
                case FALLING_BLOCK:
                case SUFFOCATION:
                    msg = plugin.crushDeaths.get(r.nextInt(plugin.crushDeaths.size()));
                    msg2 = msg.replace("$P", e.getEntity().getName());
                    e.setDeathMessage(msg2);
                    break;
                case FIRE_TICK:
                case FIRE:
                    msg = plugin.fireDeaths.get(r.nextInt(plugin.fireDeaths.size()));
                    msg2 = msg.replace("$P", e.getEntity().getName());
                    e.setDeathMessage(msg2);
                    break;
                case BLOCK_EXPLOSION:
                    msg = plugin.explosionDeaths.get(r.nextInt(plugin.explosionDeaths.size()));
                    msg2 = msg.replace("$P", e.getEntity().getName());
                    e.setDeathMessage(msg2);
                    break;
                case STARVATION:
                    msg = plugin.starvationDeaths.get(r.nextInt(plugin.starvationDeaths.size()));
                    msg2 = msg.replace("$P", e.getEntity().getName());
                    e.setDeathMessage(msg2);
                    break;
                case PROJECTILE:
                    msg = plugin.projectileDeaths.get(r.nextInt(plugin.projectileDeaths.size()));
                    msg2 = msg.replace("$P", e.getEntity().getName());
                    e.setDeathMessage(msg2);
                    break;
                case ENTITY_EXPLOSION:
                    msg = plugin.creeperDeaths.get(r.nextInt(plugin.creeperDeaths.size()));
                    msg2 = msg.replace("$P", e.getEntity().getName());
                    e.setDeathMessage(msg2);
                    break;
                default:
                    break;
            }
        }
    }
}
