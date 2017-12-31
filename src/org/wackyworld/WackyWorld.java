/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.wackyworld;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author keptil
 */
public class WackyWorld  extends JavaPlugin {
    //Data tracking
    public static File dataFolder;
    public final Logger logger = Logger.getLogger("Minecraft.WackyWorld");
    
    public static int wackyChance;
    public static Boolean logEnabled;
    static Random randomGen = new Random();
    public static PluginDescriptionFile pdfFile;
    public static WackyFireworks fireworkControl = new WackyFireworks();
    
    private List<String> seedNames;// = new List<String>();
    private List<String> appleNames;// = new List<String>();
    private List<String> adjectiveList;// = new List<String>();
    private List<String> properNounList;// = new ArrayList<String>();
    private List<String> nounList;// = new ArrayList<String>();
    
    public List<String> joinMessages;
    public List<String> lavaDeaths;
    public List<String> fallDeaths = new ArrayList<String>();
    public List<String> crushDeaths = new ArrayList<String>();
    public List<String> explosionDeaths = new ArrayList<String>();
    public List<String> starvationDeaths = new ArrayList<String>();
    public List<String> drowningDeaths = new ArrayList<String>();
    public List<String> fireDeaths = new ArrayList<String>();
    public List<String> suicideDeaths = new ArrayList<String>();
    public List<String> contactDeaths = new ArrayList<String>();
    public List<String> projectileDeaths = new ArrayList<String>();
    public List<String> creeperDeaths = new ArrayList<String>();
    
    @Override
    public void onEnable() {
        
        this.saveDefaultConfig();
        
        WackyWorld.dataFolder = getDataFolder();
        
        this.loadConfig();
        
        WackyWorld.pdfFile = this.getDescription();
        generateNames();
        
        new WackySpawnListener(this);
        new WackyJoinListener(this);
        new WackyDeathListener(this);
        
        getCommand("wacky").setExecutor(new WackyCommands(this));
        getCommand("gps").setExecutor(new WackyCommands(this));
        
        if (WackyWorld.logEnabled) {
            WackyWorld.logToFile(WackyWorld.pdfFile.getName() + " ver. " + WackyWorld.pdfFile.getVersion() + " [enabled]");
        }
    }
    
    @Override
    public void onDisable() {
        
    }
    
    static boolean random(int percentChance) {
        return randomGen.nextInt(100) > percentChance;
    }
    
    public void loadConfig() {
        WackyWorld.wackyChance = getConfig().getInt("wackyChance");
        WackyWorld.logEnabled = getConfig().getBoolean("logEnabled");
    }
    
    public static void logToFile(String message) {
        try {
            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }
            
            File saveTo = new File(dataFolder, "wackyworld.log");
            if (!saveTo.exists()) {
                saveTo.createNewFile();
            }
            
            FileWriter fw = new FileWriter(saveTo, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(getDate() + " " + message);
            pw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getDate() {
        String s;
        Format formatter;
        Date date = new Date();
        formatter = new SimpleDateFormat("[MM/dd/yyyy HH:mm:ss]");
        s = formatter.format(date);
        return s;
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
    
    public void generateNames() {
        seedNames = this.getConfig().getStringList("seedNames");
        appleNames = this.getConfig().getStringList("appleNames");
        adjectiveList = this.getConfig().getStringList("adjectiveList");
        properNounList = this.getConfig().getStringList("properNounList");
        nounList = this.getConfig().getStringList("nounList");
        
        lavaDeaths = this.getConfig().getStringList("deaths.lava");
        crushDeaths = this.getConfig().getStringList("deaths.crushing");
        fallDeaths = this.getConfig().getStringList("deaths.falling");
        explosionDeaths = this.getConfig().getStringList("deaths.explosion");
        starvationDeaths = this.getConfig().getStringList("deaths.starvation");
        drowningDeaths = this.getConfig().getStringList("deaths.drowning");
        fireDeaths = this.getConfig().getStringList("deaths.fire");
        suicideDeaths = this.getConfig().getStringList("deaths.suicide");
        contactDeaths = this.getConfig().getStringList("deaths.contact");
        projectileDeaths = this.getConfig().getStringList("deaths.projectile");
        creeperDeaths = this.getConfig().getStringList("deaths.creeper");
        
        joinMessages = this.getConfig().getStringList("join");
        
        System.out.println("Names loaded..");
        System.out.println("seedNames.size: " + seedNames.size());
        System.out.println("appleNames.size: " + appleNames.size());
        System.out.println("adjectiveList.size: " + adjectiveList.size());
        System.out.println("properNouns.size: " + properNounList.size());
        System.out.println("nounList.size: " + nounList.size());
        System.out.println("lavaDeaths.size: " + lavaDeaths.size());
    }
}
