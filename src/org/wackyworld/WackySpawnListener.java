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
    private List<Color> colorList = new ArrayList<Color>();
    
    public WackySpawnListener(WackyWorld plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
        populateColors();        
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSpawn(ItemSpawnEvent event) {
        World eworld = event.getLocation().getWorld();
        Location loc = event.getLocation();
        
        Item test = (Item) event.getEntity();
        ItemStack is = test.getItemStack();
        
        if (is.getType() != Material.EGG) return;
        
        //System.out.print("Egg spawned.\n");
        
        Random r = new Random();
        int roll = r.nextInt(100);
        
        System.out.print("Egg spawned. Rolled a " + roll + ", need " + WackyWorld.wackyChance + ".\n");
        if (r.nextInt(100) > (100-WackyWorld.wackyChance)) {
            event.getEntity().remove();
            event.setCancelled(true);
            
            setFirework(loc);
            Bukkit.broadcastMessage("Patriotic Chicken Alert.");
        }
        
    }
    
    public void populateColors() {
        colorList.add(Color.RED);
        colorList.add(Color.AQUA);
        colorList.add(Color.BLACK);
        colorList.add(Color.BLUE);
        colorList.add(Color.FUCHSIA);
        colorList.add(Color.GRAY);
        colorList.add(Color.GREEN);
        colorList.add(Color.LIME);
        colorList.add(Color.MAROON);
        colorList.add(Color.NAVY);
        colorList.add(Color.OLIVE);
        colorList.add(Color.ORANGE);
        colorList.add(Color.PURPLE);
        colorList.add(Color.SILVER);
        colorList.add(Color.TEAL);
        colorList.add(Color.WHITE);
        colorList.add(Color.YELLOW);
    }
    
    public void setFirework(Location loc) {
        Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();
        
        Random r = new Random();
        int rt = r.nextInt(4) + 1;
        Type type = Type.BALL;
        if (rt == 1) type = Type.BALL;
        if (rt == 2) type = Type.BALL_LARGE;
        if (rt == 3) type = Type.BURST;
        if (rt == 4) type = Type.CREEPER;
        if (rt == 5) type = Type.STAR;
        
        int r1i = r.nextInt(colorList.size()) + 1;
        int r2i = r.nextInt(colorList.size()) + 1;
        Color c1 = colorList.get(r1i);
        Color c2 = colorList.get(r2i);
        
        FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();
        fwm.addEffect(effect);
        int rp = r.nextInt(2) + 1;
        fwm.setPower(rp);
        
        fw.setFireworkMeta(fwm);
    }
    
    
}
