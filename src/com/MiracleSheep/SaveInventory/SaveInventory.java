/**
 * Description: This is the main class of the plugin. It is responsible for calling all of the other classes on enable and disable of the plugin.
 *
 * @author: John Khalife
 * @version: Created August 8th 2021
 */


//The package that this script is in
package com.MiracleSheep.SaveInventory;

//importing from other packages and libraries
import com.MiracleSheep.SaveInventory.Commands.PluginCommands;
import com.MiracleSheep.SaveInventory.Events.PluginEvents;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

//Main plugin class
public class SaveInventory extends JavaPlugin {

    //code that gets run when the plugin is enabled
    @Override
    public void onEnable() {

        //This creates a config file (optionnal)
        saveDefaultConfig();

        //This checks the config as an example to see if the plugin is enabled
        if (getConfig().getBoolean("Enabled") == true) {
            //This calls the command class and enables the commands for the plugin
            PluginCommands command = new PluginCommands(this);
            //This calles the itemanager class and enables items for the plugin

            //This registers events for the plugin by called the event class
            getServer().getPluginManager().registerEvents(new PluginEvents(this), this);
            //Use this line as a template for when adding a new command. It adds the command to the plugin Simply copy paste the line and replace test with what the user needs to type in
            getCommand("invload").setExecutor(command);
            getCommand("invsave").setExecutor(command);
            //Sending a message to show the plugin is enabled
            getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[SaveInventory] plugin is enabled.");

        }
    }

    //This function is called when the plugin is disabled (usually when the server turns off, but plugins can be disabled while in-game)
    @Override
    public void onDisable() {

    }


    public void saveInventory(Player player) {
        File f = new File(this.getDataFolder().getAbsolutePath(), player.getName() + ".yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        c.set("inventory.armor", player.getInventory().getArmorContents());
        c.set("inventory.content", player.getInventory().getContents());
        try {
            c.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void restoreInventory(Player player) {
        File f = new File(this.getDataFolder().getAbsolutePath(), player.getName() + ".yml");
        FileConfiguration c = YamlConfiguration.loadConfiguration(f);
        ItemStack[] content = ((List<ItemStack>) c.get("inventory.armor")).toArray(new ItemStack[0]);
        player.getInventory().setArmorContents(content);
        content = ((List<ItemStack>) c.get("inventory.content")).toArray(new ItemStack[0]);
        player.getInventory().setContents(content);
    }

}


