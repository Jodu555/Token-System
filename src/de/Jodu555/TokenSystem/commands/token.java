package de.Jodu555.TokenSystem.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.Jodu555.TokenSystem.TokenSystem;
import de.Jodu555.TokenSystem.objects.Token;

public class token implements CommandExecutor {

	// Token generate
	// /token create [Name] [Rang]
	// /token redeem [Token]
	// /token list

	// ****-****

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;

			System.out.println(args.length);

			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("list")) {
					if (player.hasPermission("TokenSystem.token.list")) {

						player.sendMessage(TokenSystem.getInstance().getPrefix() + "Mögliche Tokens");
						for (Token tokens : TokenSystem.getInstance().getManager().getTokens()) {
							String name = Bukkit.getPlayer(UUID.fromString(tokens.getUserUUID())).getName();
							player.sendMessage(
									" §7- §4" + tokens.getToken() + " §7| §c" + name + " §7: §4" + tokens.getRang());
						}
					} else {
						sendNoPerm(player);
					}
				} else {
					sendHelp(player);
				}
			} else if (args.length == 2) {
				if (args[0].equalsIgnoreCase("redeem")) {
					Token token = TokenSystem.getInstance().getManager().getToken(args[1]);
					if (token != null) {
						TokenSystem.getInstance().getManager().redeemToken(token);
					} else {
						player.sendMessage(
								TokenSystem.getInstance().getPrefix() + "Dieser §cToken §7ist §cnicht gültig§7!");
					}
				} else {
					sendHelp(player);
				}
			} else if (args.length == 3) {
				if (args[0].equalsIgnoreCase("create")) {
					if (player.hasPermission("TokenSystem.token.create")) {
						String name = args[1];
						String rang = args[2];

						String token = TokenSystem.getInstance().getManager().generateToken();

						Token realToken = new Token(token, Bukkit.getPlayer(name).getUniqueId().toString(), rang);

						TokenSystem.getInstance().getManager().addtoken(realToken);

						player.sendMessage(TokenSystem.getInstance().getPrefix() + "Es wurde ein neuer Token für §c"
								+ name + "§7 erstellt für den Rang: §4" + rang + "§7!");
						player.sendMessage(TokenSystem.getInstance().getPrefix() + "Der Token: §c" + token);
					} else {
						sendNoPerm(player);
					}
				} else {
					sendHelp(player);
				}
			} else {
				sendHelp(player);
			}
		}

		return false;
	}

	public void sendNoPerm(Player player) {
		player.sendMessage(TokenSystem.getInstance().getPrefix() + "§4Du hast keine Rechte hierfür!");
	}

	public void sendHelp(Player player) {

		// /bingo setup/start/pause/resume /win /top

		player.sendMessage(TokenSystem.getInstance().getPrefix()
				+ "§aNutze§7: §2/token help §8| §7Um diese Hilfe angezeigt zu bekommen!");
		player.sendMessage(TokenSystem.getInstance().getPrefix()
				+ "§aNutze§7: §2/bingo redeem §8| §7Um ein neues Bingo Spiel zu erstellen!");
		player.sendMessage(TokenSystem.getInstance().getPrefix()
				+ "§aNutze§7: §2/bingo list §8| §7Um das Bingo Spiel zu starten!");
		player.sendMessage(TokenSystem.getInstance().getPrefix()
				+ "§aNutze§7: §2/bingo create §8| §7Um das Bingo Spiel vorübergehend zu stoppen!");
		player.sendMessage(TokenSystem.getInstance().getPrefix() + "§5 Plugin by Jodu555");
	}

}
