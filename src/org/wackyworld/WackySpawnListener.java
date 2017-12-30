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
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

/**
 *
 * @author keptil
 */
public class WackySpawnListener implements Listener {
    private WackyWorld plugin;
    
    
    
    public WackySpawnListener(WackyWorld plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
        //generateNames();
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSpawn(ItemSpawnEvent event) {
        World eworld = event.getLocation().getWorld();
        Location loc = event.getLocation();
        
        Item test = (Item) event.getEntity();
        ItemStack is = test.getItemStack();
        
        if (is.getType() == Material.EGG) {
            wackyEgg(event);
        }
        
        else if (is.getType() == Material.SEEDS) {
            wackySeeds(event);
        }
        
        else if (is.getType() == Material.APPLE) {
            wackyApple(event);
        }
        
        else {
            wackyItemName(event);
        }
        
    }
    public void wackyEgg(ItemSpawnEvent event) {
        Random r = new Random();
        int roll = r.nextInt(100);
        
        System.out.print("Egg spawned. Rolled a " + roll + ", need " + (100-WackyWorld.wackyChance) + ".\n");
        if (roll > (100-WackyWorld.wackyChance)) {
            event.getEntity().remove();
            event.setCancelled(true);
            
            WackyWorld.fireworkControl.setRandomFirework(event.getLocation());
            //Bukkit.broadcastMessage("Patriotic Chicken Alert at " + event.getLocation().getX() + "/" + event.getLocation().getZ() + "!!");
        }
    }
    
    public void wackySeeds(ItemSpawnEvent event) {
        Random r = new Random();
        int roll = r.nextInt(100);
        
        if (roll > (100-WackyWorld.wackyChance)) {
            event.getEntity().setCustomName(plugin.getRandomSeedName());
            event.getEntity().setCustomNameVisible(true);
            
            //Bukkit.broadcastMessage(event.getEntity().getCustomName() + " spawned.");
        }
    }
    
    public void wackyItemName(ItemSpawnEvent event) {
        Random r = new Random();
        int roll = r.nextInt(100);
        
        if (roll > (100-WackyWorld.wackyChance)) {
            event.getEntity().setCustomName(plugin.getRandomItemName());
            event.getEntity().setCustomNameVisible(true);
        }
    }
    
    public void wackyApple(ItemSpawnEvent event) {
        Random r = new Random();
        int roll = r.nextInt(100);
        
        if (roll > (100-WackyWorld.wackyChance)) {
            event.getEntity().setCustomName(plugin.getRandomAppleName());
            event.getEntity().setCustomNameVisible(true);
            
            //Bukkit.broadcastMessage(event.getEntity().getCustomName() + " spawned.");
        }
    }
    
    
    
    
}
