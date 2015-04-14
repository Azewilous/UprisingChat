package me.azewilous.uprising;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UChat implements CommandExecutor {

Chat plugin;	
	public UChat(Chat chat){
		this.plugin = chat;
	}
	
	HashMap<String, Boolean> roomName = new HashMap<String, Boolean>();
	
	public boolean doesExist(){
		return true;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel,
			String[] args) {
		
	if(sender instanceof Player){
		Player player = (Player) sender;
		String playerName = player.getName();
		
		if(args.length == 0){
			player.sendMessage(ChatColor.DARK_AQUA + "Uprising Chat Channels Plugin"
					+ ChatColor.DARK_AQUA + "\nUprising Chat Channels Plugin Commands"
					+ ChatColor.DARK_AQUA + "\n/UChat Goto <ChannelName>"
					+ ChatColor.DARK_AQUA + "\n/Uchat Leave");
		} else if(args.length == 1){
			if(args[0].equalsIgnoreCase("Goto")){
				player.sendMessage(ChatColor.RED + "Please Select Channel");
			} else 
				if(args[0].equalsIgnoreCase("leave")){
					if(plugin.inChatAdmin.containsKey(playerName)){
						plugin.inChatAdmin.remove(playerName, true);
					} else
						if(!plugin.inChatAdmin.containsKey(playerName)){
							player.sendMessage(ChatColor.RED + "Not Currently In A Chat");
						}
				} else if(args[0].equalsIgnoreCase("spy")){
					if(plugin.inChatSpy.containsKey(playerName)){
						plugin.inChatSpy.remove(playerName, true);
					} else
						plugin.inChatSpy.put(playerName, true);
				} else 
					if(args[0].equalsIgnoreCase("create")){
					player.sendMessage(ChatColor.RED + "Please Choose A Name For YOur ChatRoom");
				}
		} else if(args.length == 2){
			if(args[1].equalsIgnoreCase("Admin")){
				if(!plugin.inChatAdmin.containsKey(playerName)){
					plugin.inChatAdmin.put(playerName, true);
					player.sendMessage(ChatColor.GREEN + "Moved Into Admin Chat");
					return true;
				} else
					player.sendMessage(ChatColor.RED + "Currently In This Chat");
			} else if(args[1].equals(roomName)){
				if(!roomName.containsKey(player)){
					roomName.put(playerName, true);
					player.sendMessage(ChatColor.GREEN + "Created Chat" + roomName);
					return true;
				}
			}
		}
	} else
		sender.sendMessage("Must Be A Player To Use This Command");
		return true;
	}
}