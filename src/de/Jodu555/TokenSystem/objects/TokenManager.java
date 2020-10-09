package de.Jodu555.TokenSystem.objects;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;

import de.Jodu555.TokenSystem.api.AbstractConfig;

public class TokenManager {

	private ArrayList<Token> tokens;
	private AbstractConfig config;

	public TokenManager() {
		tokens = new ArrayList<>();
		config = new AbstractConfig("TokenSystem", "config.yml", config -> {

			config.getCfg().set("Tokens", new ArrayList<String>());
			config.getCfg().set("performCommand", "pex user %name% group set %rang%");

			config.save();
		});

	}

	public void save() {
		tokens.forEach(token -> {
			config.getCfg().set("Token." + token.getToken() + ".Token", token.getToken());
			config.getCfg().set("Token." + token.getToken() + ".UserUUID", token.getUserUUID());
			config.getCfg().set("Token." + token.getToken() + ".Rang", token.getRang());
			config.save();
		});
	}

	private ArrayList<String> getTokensList() {
		return (ArrayList<String>) config.getCfg().getList("Tokens");
	}

	private void setTokensList(ArrayList<String> list) {
		config.getCfg().set("Tokens", list);
		config.save();
	}
	
	public void load() {
		getTokensList().forEach(redeemToken -> {
			String userUUID = config.getCfg().getString("Token." + redeemToken + ".UserUUID");
			String rang = config.getCfg().getString("Token." + redeemToken + ".Rang");
			Token token = new Token(redeemToken, userUUID, rang);
			tokens.add(token);
		});
	}

	public void redeemToken(Token token) {
		config.getCfg().set("Token." + token.getToken(), null);
		config.save();
		
		ArrayList<String> list = getTokensList();
		list.remove(token.getToken());
		setTokensList(list);
		
		String name = Bukkit.getPlayer(UUID.fromString(token.getUserUUID())).getName();
		String cmd = config.getCfg().getString("performCommand").replaceAll("%name%", name).replaceAll("%rang%",
				token.getRang());
		System.out.println("Executed Command: " + cmd);
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd);
	}

	public Token getToken(String token) {
		for (Token tokens : tokens) {
			if (tokens.getToken().equals(token))
				return tokens;
		}
		return null;
	}

	public String generateToken() {
		Random random = new Random();
		String letterString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		ArrayList<String> list = new ArrayList<>();
		for (String split : letterString.split("")) {
			list.add(split);
		}

		String token = "";

		for (int i = 0; i < 4; i++) {
			token += list.get(random.nextInt(list.size()));
		}

		token += "-";

		for (int i = 0; i < 4; i++) {
			token += list.get(random.nextInt(list.size()));
		}

		return token;
	}

	public void addtoken(Token token) {
		ArrayList<String> list = getTokensList();
		list.add(token.getToken());
		setTokensList(list);
		getTokens().add(token);
	}

	public ArrayList<Token> getTokens() {
		return tokens;
	}

	public AbstractConfig getConfig() {
		return config;
	}

}
