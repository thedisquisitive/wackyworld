/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wackyworld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

/**
 *
 * @author user
 */
public class WackyFireworks {
    //private WackyWorld plugin;
    private List<Color> colorList = new ArrayList<Color>();
    
    private void populateColors() {
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
    
    public WackyFireworks() {
        //this.plugin = plugin;
        populateColors();
    }
    
    public void setRandomFirework(Location loc) {
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
        
        int r1i = r.nextInt(colorList.size());
        int r2i = r.nextInt(colorList.size());
        Color c1 = colorList.get(r1i);
        Color c2 = colorList.get(r2i);
        
        FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();
        fwm.addEffect(effect);
        int rp = r.nextInt(2) + 1;
        fwm.setPower(rp);
        
        fw.setFireworkMeta(fwm);
    }
}
