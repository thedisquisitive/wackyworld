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
        Random r = new Random();
        int numfw = r.nextInt(3) + 1;
        for (int i = 0; i < numfw; i++) {
            setFirework(event.getPlayer().getLocation());
        }
    }
    
    public void generateGreeting(PlayerJoinEvent event) {
        Random r = new Random();
        int roll = r.nextInt(7);
        
        String playerName = event.getPlayer().getName();
        
        switch (roll) {
            case 0:
                event.setJoinMessage("Welcome back to the USSR, " + playerName + "!");
                break;
            case 1:
                event.setJoinMessage("Warning: " + playerName + " just joined the sexiest server on the flat earth.");
                break;
            case 2:
                event.setJoinMessage(playerName + " joined right as the mob of creepers blew up their base.");
                break;
            case 3:
                event.setJoinMessage(playerName + " has decided to tip-toe through the tulips with us.");
                break;
            case 4:
                event.setJoinMessage("I hope you brought bacon, " + playerName + ".");
                break;
            case 5:
                event.setJoinMessage("Just follow the white rabbit, " + playerName + "...");
                break;
            case 6:
                event.setJoinMessage(playerName + " HAS HACKED THE PLANET!");
                break;
            default:
                break;
        }
    }
    
    public void setFirework(Location loc) {
        Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();
        
        Random r = new Random();
        int rt = r.nextInt(4) + 1;
        FireworkEffect.Type type = FireworkEffect.Type.BALL;
        if (rt == 1) type = FireworkEffect.Type.BALL;
        if (rt == 2) type = FireworkEffect.Type.BALL_LARGE;
        if (rt == 3) type = FireworkEffect.Type.BURST;
        if (rt == 4) type = FireworkEffect.Type.CREEPER;
        if (rt == 5) type = FireworkEffect.Type.STAR;
        
        Color c1 = Color.RED;
        Color c2 = Color.BLUE;
        
        FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();
        fwm.addEffect(effect);
        int rp = r.nextInt(2) + 1;
        fwm.setPower(rp);
        
        fw.setFireworkMeta(fwm);
    }
}
