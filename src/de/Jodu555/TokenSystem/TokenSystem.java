package de.Jodu555.TokenSystem;

import org.bukkit.plugin.java.JavaPlugin;

import de.Jodu555.TokenSystem.commands.token;
import de.Jodu555.TokenSystem.objects.TokenManager;

public class TokenSystem extends JavaPlugin {
	
	private static TokenSystem instance;
	private TokenManager manager;
	
	private String prefix = "§8[§aBongo-System§8] §7";
	
	@Override
	public void onEnable() {
		instance = this;
		
		manager = new TokenManager();
		
		manager.load();
		
		getCommand("token").setExecutor(new token());
		
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		manager.save();
		
		super.onDisable();
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	public TokenManager getManager() {
		return manager;
	}
	
	public static TokenSystem getInstance() {
		return instance;
	}
	
}
