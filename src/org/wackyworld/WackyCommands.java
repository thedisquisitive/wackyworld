/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wackyworld;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author keptil
 */
public class WackyCommands implements CommandExecutor {
    private WackyWorld plugin;
    
    public WackyCommands(WackyWorld plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player) && !(sender instanceof ConsoleCommandSender)) {
            sender.sendMessage("Only players can affect the wackiness of this world!");
        }
        else {
            if (cmd.getName().equalsIgnoreCase("wacky")) {
                wacky(sender, cmd, label, args);
                return true;
            }
        }
        return false;
    }
    
    public void wacky(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload")) {
                plugin.reloadConfig();
                plugin.loadConfig();
                sender.sendMessage("Wacky Settings Reloaded!");
                
                if (WackyWorld.logEnabled) {
                    WackyWorld.logToFile(sender.getName() + " reloaded the wacky settings!");
                }
            }
            else if (args[0].equalsIgnoreCase("save")) {
                plugin.saveConfig();
                plugin.loadConfig();
                sender.sendMessage("Wacky settings saved!");
                
                if (WackyWorld.logEnabled) {
                    WackyWorld.logToFile(sender.getName() + " saved the wacky settings!");
                }
            }
            else if (args[0].equalsIgnoreCase("chance")) {
                WackyWorld.wackyChance = Integer.parseInt(args[1]);
                if (WackyWorld.wackyChance < 0) {
                    WackyWorld.wackyChance = 0;
                }
                if (WackyWorld.wackyChance > 100) {
                    WackyWorld.wackyChance = 100;
                }
            }
            else if (args[0].equalsIgnoreCase("status")) {
                sender.sendMessage("WackyWorld Wacky Chance: " + WackyWorld.wackyChance);
            }
            else if (args[0].equalsIgnoreCase("firework")) {
                WackyWorld.fireworkControl.setRandomFirework(Bukkit.getPlayer(sender.getName()).getLocation());
            }
            else {
                sender.sendMessage("Wacky usage: /wacky <reload|save|chance|status>");
            }
        }
        else {
            sender.sendMessage("Wacky usage: /wacky <reload|save|chance|status>");
        }
    }
}
