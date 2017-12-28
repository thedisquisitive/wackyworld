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
    private List<String> seedNames = new ArrayList<String>();
    private List<String> appleNames = new ArrayList<String>();
    private List<String> adjectiveList = new ArrayList<String>();
    private List<String> properNounList = new ArrayList<String>();
    private List<String> nounList = new ArrayList<String>();
    
    public WackySpawnListener(WackyWorld plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
        populateColors();
        generateNames();
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
    
    public void generateNames() {
        seedNames.add("Chicken Nuts");
        seedNames.add("Gopher Balls");
        seedNames.add("Bird Bait");
        seedNames.add("Rabbit Pellets");
        seedNames.add("Seeds");
        seedNames.add("17 Year Old Candy");
        seedNames.add("Strange Crunchy Things");
        seedNames.add("Flat-Earther Cerebellum");
        seedNames.add("Mardis Gras Beads");
        
        appleNames.add("Large Rat Testicle");
        appleNames.add("Approximately 1 Apple");
        appleNames.add("Tree Kidney");
        appleNames.add("Gravity Detector");
        appleNames.add("An Ordinary Banana");
        appleNames.add("Your Trusty Sidearm");
        appleNames.add("Rusted Macaroni");
        
        adjectiveList.add("hairy");
        adjectiveList.add("tiny");
        adjectiveList.add("giant");
        adjectiveList.add("giant, garrish");
        adjectiveList.add("yellow");
        adjectiveList.add("foul-smelling");
        adjectiveList.add("sweet");
        adjectiveList.add("slimy");
        adjectiveList.add("fictional");
        adjectiveList.add("glowing");
        adjectiveList.add("sexy");
        adjectiveList.add("moist");
        adjectiveList.add("androgynous");
        adjectiveList.add("mad");
        adjectiveList.add("angry");
        adjectiveList.add("docile");
        adjectiveList.add("blueberry");
        adjectiveList.add("new");
        adjectiveList.add("rusty");
        adjectiveList.add("hypodermically pleasing");
        adjectiveList.add("nail-biting");
        adjectiveList.add("rather phallic");
        adjectiveList.add("simmering");
        adjectiveList.add("toasted");
        adjectiveList.add("masturbating");
        
        properNounList.add("Pete's");
        properNounList.add("gerbil");
        properNounList.add("inflated sheep");
        properNounList.add("pop tart");
        properNounList.add("spider");
        properNounList.add("crying hamster");
        properNounList.add("sock puppet");
        properNounList.add("Molly's");
        properNounList.add("Bob's");
        properNounList.add("herpes infected");
        properNounList.add("now extinct");
        properNounList.add("NSA agent's");
        properNounList.add("kitten's");
        properNounList.add("Russian");
        properNounList.add("Tricky Dick Autographed");
        properNounList.add("mescaline flavored");
        
        nounList.add("wallet");
        nounList.add("purse");
        nounList.add("condom");
        nounList.add("hot mixtape");
        nounList.add("hand lotion");
        nounList.add("drugs");
        nounList.add("chapstick");
        nounList.add("hair clippings");
        nounList.add("coupons");
        nounList.add("paper clips");
        nounList.add("fishing lure");
        nounList.add("Simpson's bobblehead");
        nounList.add("bag of corn chips");
        nounList.add("hooker");
        nounList.add("lottery ticket");
        nounList.add("stick of ultimate destiny");
        nounList.add("wookie costume");
        nounList.add("prized earwax collection");
        nounList.add("excrement");
        nounList.add("Communist propaganda");
        nounList.add("monkey paw");
        nounList.add("slightly used band-aid");
        nounList.add("hat");
        nounList.add("empty bean can");
        nounList.add("can of tamales");
        nounList.add("leaked sextape");
        nounList.add("fabrige egg");
        nounList.add("plot twist");
        nounList.add("hopes and dreams");
        nounList.add("liberal tears");
        nounList.add("sick beat");
    }
    
    public String getRandomSeedName() {
        Random r = new Random();
        return seedNames.get(r.nextInt(seedNames.size()));
    }
    
    public String getRandomAppleName() {
        Random r = new Random();
        return appleNames.get(r.nextInt(appleNames.size()));
    }
    
    public String getRandomItemName() {
        Random r = new Random();
        return (adjectiveList.get(r.nextInt(adjectiveList.size())) + " " + properNounList.get(r.nextInt(properNounList.size())) + " " + nounList.get(r.nextInt(nounList.size())));
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
    
    public void wackyEgg(ItemSpawnEvent event) {
        Random r = new Random();
        int roll = r.nextInt(100);
        
        System.out.print("Egg spawned. Rolled a " + roll + ", need " + (100-WackyWorld.wackyChance) + ".\n");
        if (roll > (100-WackyWorld.wackyChance)) {
            event.getEntity().remove();
            event.setCancelled(true);
            
            setFirework(event.getLocation());
            //Bukkit.broadcastMessage("Patriotic Chicken Alert at " + event.getLocation().getX() + "/" + event.getLocation().getZ() + "!!");
        }
    }
    
    public void wackySeeds(ItemSpawnEvent event) {
        Random r = new Random();
        int roll = r.nextInt(100);
        
        if (roll > (100-WackyWorld.wackyChance)) {
            event.getEntity().setCustomName(getRandomSeedName());
            event.getEntity().setCustomNameVisible(true);
            
            //Bukkit.broadcastMessage(event.getEntity().getCustomName() + " spawned.");
        }
    }
    
    public void wackyItemName(ItemSpawnEvent event) {
        Random r = new Random();
        int roll = r.nextInt(100);
        
        if (roll > (100-WackyWorld.wackyChance)) {
            event.getEntity().setCustomName(getRandomItemName());
            event.getEntity().setCustomNameVisible(true);
        }
    }
    
    public void wackyApple(ItemSpawnEvent event) {
        Random r = new Random();
        int roll = r.nextInt(100);
        
        if (roll > (100-WackyWorld.wackyChance)) {
            event.getEntity().setCustomName(getRandomAppleName());
            event.getEntity().setCustomNameVisible(true);
            
            Bukkit.broadcastMessage(event.getEntity().getCustomName() + " spawned.");
        }
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
