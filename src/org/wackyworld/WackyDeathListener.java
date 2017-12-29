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
    
    private List<String> lavaDeaths = new ArrayList<String>();
    private List<String> fallDeaths = new ArrayList<String>();
    private List<String> crushDeaths = new ArrayList<String>();
    private List<String> explosionDeaths = new ArrayList<String>();
    private List<String> starvationDeaths = new ArrayList<String>();
    private List<String> drowningDeaths = new ArrayList<String>();
    private List<String> fireDeaths = new ArrayList<String>();
    private List<String> suicideDeaths = new ArrayList<String>();
    
    public WackyDeathListener(WackyWorld plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
        generateMessages();
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
                    msg = lavaDeaths.get(r.nextInt(lavaDeaths.size()));
                    msg2 = msg.replace("$P", e.getEntity().getName());
                    e.setDeathMessage(msg2);
                    break;
                case DROWNING:
                    msg = drowningDeaths.get(r.nextInt(drowningDeaths.size()));
                    msg2 = msg.replace("$P", e.getEntity().getName());
                    e.setDeathMessage(msg2);
                    break;
                case FALL:
                    msg = fallDeaths.get(r.nextInt(fallDeaths.size()));
                    msg2 = msg.replace("$P", e.getEntity().getName());
                    e.setDeathMessage(msg2);
                    break;
                case FALLING_BLOCK:
                case SUFFOCATION:
                    msg = crushDeaths.get(r.nextInt(crushDeaths.size()));
                    msg2 = msg.replace("$P", e.getEntity().getName());
                    e.setDeathMessage(msg2);
                    break;
                default:
                    break;
            }
        }
    }
    
    private void generateMessages() {
        lavaDeaths.add("Feeling a bit cold, $P warmed themselves in lava.");
        lavaDeaths.add("$P exploded as their body hit the lava.");
        lavaDeaths.add("$P thought the lava was friendly. It was not.");
        lavaDeaths.add("Much to $P's disappointment, the lava did not appreciate the hug.");
        lavaDeaths.add("Everything on $P, including their own body, has been consumed by lava.");
        lavaDeaths.add("$P really loves lava.");
        lavaDeaths.add("$P learned something new about lava.");
        
        fallDeaths.add("$P discovered gravity.");
        fallDeaths.add("It wasn't the fall that killed $P, it was the sudden stop.");
        fallDeaths.add("$P is now known as 'Dances with Pavement'.");
        fallDeaths.add("$P collided with the ground, making a satisfying 'squishy-crunchy' noise.");
        fallDeaths.add("Taking a trip, $P? See ya after the fall!");
        fallDeaths.add("$P learned that ravines are deep.");
        fallDeaths.add("It turns out that $P was unable to fly after all.");
        
        drowningDeaths.add("$P unceromoniously drowned.");
        drowningDeaths.add("Davy Jones has claimed $P for himself!");
        drowningDeaths.add("$P didn't evolve gills in time.");
        drowningDeaths.add("$P thought they were a fish.");
        
        crushDeaths.add("$P thought sand would make a good roof.");
        crushDeaths.add("$P learned that respiration requires oxygen, not dirt.");
        crushDeaths.add("$P shouldn't dig straight up.");
        crushDeaths.add("$P has become one with the earth.");
    }
}
