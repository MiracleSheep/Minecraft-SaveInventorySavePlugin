/**
 * Description: This is a class for game commands. It listens for the user to enter a command and calls code based on the command
 *
 * @author: John Khalife
 * @version: Created August 8th 2021
 */


//Name of the package this script is in
package com.MiracleSheep.SaveInventory.Commands;

//importing libraries and other packages
import com.MiracleSheep.SaveInventory.SaveInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

//this is the class that contains the command of the plugin
public class PluginCommands implements CommandExecutor {

    //creating an intsance of the main plugin class
    private final SaveInventory main;

    //Passing the commands to the main instance
    public PluginCommands(SaveInventory main) {
        this.main = main;
    }


    //This searches for the event when a player enters a command and calls the code within
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        //Checking if the sender of the command is not a player and returning if true
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only Players And CommandBlocks can use that command");
            return true;
        }

        //Checking if the commmand sent is equal to the string and calling if it is
        if (cmd.getName().equalsIgnoreCase("invsave")) {

            //Getting the sender of the command
            Player player = (Player) sender;

            //checking if the user has the proper permissions
            if (!sender.hasPermission("invsave.save")) {
                player.sendMessage(ChatColor.RED + "You do not have permission to save your inventory!");
                return true;
            }


            if (args.length <= 2) {
                if (args.length == 0) {
                    //leading the player's inventory
                    player.sendMessage(ChatColor.GREEN + "Saving your inventory...");
                    //Try catch in case the user does not have an inventory saved
                    try {
                        main.saveInventory(player);
                    } catch (Exception e) {
                        player.sendMessage(ChatColor.RED + "There was an error saving your inventory.");
                    }
                } else if (args.length == 1) {

                    Player target = Bukkit.getPlayerExact(args[0]);
                    //leading the player's inventory
                    target.sendMessage(ChatColor.GREEN + "Saving your inventory...");
                    player.sendMessage(ChatColor.GREEN + "Saving your target's inventory...");
                    //Try catch in case the user does not have an inventory saved
                    try {
                        main.saveInventory(target);
                    } catch (Exception e) {
                        player.sendMessage(ChatColor.RED + "There was an error saving the inventory of your target.");
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED + "This is the incorrect number of arguments!");
                return true;
            }
        }

        //Checking if the commmand sent is equal to the string and calling if it is
        if (cmd.getName().equalsIgnoreCase("invload")) {

            //Getting the sender of the command
            Player player = (Player) sender;

            //checking if the player has the proper permissions
            if (!sender.hasPermission("invsave.load")) {
                player.sendMessage(ChatColor.RED + "You do not have permission to load your inventory!");
                return true;
            }

            if (args.length <= 2) {
                if (args.length == 0) {
                    //leading the player's inventory
                    player.sendMessage(ChatColor.GREEN + "Loading your inventory...");
                    //Try catch in case the user does not have an inventory saved
                    try {
                        main.restoreInventory(player);
                    } catch(Exception e) {
                        player.sendMessage(ChatColor.RED + "You do not have an inventory saved.");
                    }
                } else if (args.length == 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    //leading the player's inventory
                    target.sendMessage(ChatColor.GREEN + "Loading your inventory...");
                    player.sendMessage(ChatColor.GREEN + "Loading your target's inventory...");
                    //Try catch in case the user does not have an inventory saved
                    try {
                        main.restoreInventory(target);
                    } catch(Exception e) {
                        target.sendMessage(ChatColor.RED + "You do not have an inventory saved.");
                        player.sendMessage(ChatColor.RED + "Your target does not have an inventory saved.");
                    }
                }

            } else {
                player.sendMessage(ChatColor.RED + "This is the incorrect number of arguments!");
                return true;
            }


        }

        //Returning from the function if none of the commands matched
        return true;
    }





}
