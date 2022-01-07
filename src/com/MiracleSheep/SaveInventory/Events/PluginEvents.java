/**
 * Description: This is the class for the events of the game. This class listens for events and calls code when they are triggered
 *
 * @author: John Khalife
 * @version: Created August 8th 2021
 */



//Name of the package
package com.MiracleSheep.SaveInventory.Events;

//importing libraries and packages
import com.MiracleSheep.SaveInventory.SaveInventory;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

//this is the class that listens for events
public class PluginEvents implements Listener {

    //getting an instance of the main class
    public static SaveInventory main;

    //This is the constructor for the events class. it passes the inventory (optionnal) and main classes
    public PluginEvents(SaveInventory main) {
        this.main = main;
    }

    //Player death event is when the player's inventory will be saved
    @EventHandler
    public static void onPlayerDamage(EntityDamageEvent e) {
        Entity entity = e.getEntity();
        if (entity.getType() == EntityType.PLAYER) {
            Player player = (Player) e.getEntity();
                main.saveInventory(player);



        }
    }





    }


