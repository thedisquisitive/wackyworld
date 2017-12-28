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
    public static List<String> replaceWith;
    public static int wackyChance;
    public static Boolean logEnabled;
    static Random randomGen = new Random();
    public static PluginDescriptionFile pdfFile;
    
    @Override
    public void onEnable() {
        
        this.saveDefaultConfig();
        
        new WackySpawnListener(this);
        new WackyJoinListener(this);
        
        WackyWorld.dataFolder = getDataFolder();
        
        this.loadConfig();
        
        WackyWorld.pdfFile = this.getDescription();
        
        getCommand("wacky").setExecutor(new WackyCommands(this));
        
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
        WackyWorld.replaceWith = getConfig().getStringList("replaceWith");
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
}
