package me.azewilous.uprising;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Chat extends JavaPlugin implements Listener{
	public final Logger Uprising = Logger.getLogger("Uprising");

	Plugin plugin;
	FileConfiguration config;
	File cFile;
	
	HashMap<String, Boolean> inChatAdmin = new HashMap<String, Boolean>();
	HashMap<String, Boolean> inChatSpy = new HashMap<String, Boolean>();
	
	public void onEnable(){	
		PluginDescriptionFile pdfFile = this.getDescription();
		this.Uprising.info(pdfFile.getName() + " Version " + pdfFile.getVersion());
		getServer().getPluginManager().registerEvents(this, this);
		
		Uprising.info("Uprising Staff Chat Plugin");
		
		config = getConfig();
		config.options().copyDefaults(true);
		cFile = new File(getDataFolder(), "config.yml");
		saveConfig();
		
		this.getCommand("uchat").setExecutor(new UChat(this));
		registerEvents(this, new ChatListener(this));
	}
	
	public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners){
		for(Listener listener : listeners ){
			Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
		}
	}

	@Override
    public void onDisable(){
    	PluginDescriptionFile pdfFile = this.getDescription();
        this.Uprising.info(pdfFile.getName() + " Version " + pdfFile.getVersion());
        saveConfig();
    }
	
}
