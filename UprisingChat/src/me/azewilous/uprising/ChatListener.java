package me.azewilous.uprising;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

Chat plugin;
	public ChatListener(Chat chat){
		this.plugin = chat;
	}
	
	@EventHandler
	   public void onChat(AsyncPlayerChatEvent apc) {
	   String playerName = apc.getPlayer().getName();
	   	try {
	     if (plugin.inChatAdmin.get(playerName) == true) {
	        for (Player player : Bukkit.getOnlinePlayers()) {
	            if (plugin.inChatAdmin.get(player.getName()) == true) {
	                player.sendMessage(ChatColor.GOLD + "[UprisingChat] " + ChatColor.GRAY + playerName + ": " + ChatColor.WHITE + apc.getMessage());
	                apc.setCancelled(true);
	                plugin.Uprising.info("[UprisingChat] " + playerName + ": " + apc.getMessage());
	                Set<Player> recip = apc.getRecipients();
	                recip.remove(playerName);
	            } 
	        }
	     }
	    } catch (NullPointerException npe){
	      }
	}
	
	@EventHandler
	public void onChatSpy(AsyncPlayerChatEvent apc){
		
	}
}
