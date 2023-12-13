package com.zyunsoftware.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import net.kyori.adventure.text.Component;

public class PlayerUtility {
  private static Map<String, String> _privateMessages = new HashMap<>();

  public static List<Player> nearest(Player player) {
    List<Player> nearest = new ArrayList<>();

    for (Player recipient : Bukkit.getOnlinePlayers()) {
      if (recipient.equals(player) || !player.getWorld().equals(recipient.getWorld())) {
        continue;
      }

      double distance = player.getLocation().distance(recipient.getLocation());
      if (distance <= 50) {
        nearest.add(recipient);
      }
    }

    return nearest;
  }

  public static String getPmSender(String nickname) {
    return _privateMessages.get(nickname);
  }

  public static void sendPm(Player player, String receiverNickname, String message) {
    Player receiver = Bukkit.getPlayer(receiverNickname);
    if (receiver == null) {
      player.sendMessage(Component.text(TextUtility.recipientNotFound()));
      return;
    }

    String playerName = player.getName();
    String receiverName = receiver.getName();

    receiver.sendMessage(Component.text(TextUtility.whisperFrom(playerName, message)));
    player.sendMessage(Component.text(TextUtility.youWhisperTo(receiverName)));

    _privateMessages.put(receiverName, playerName);
    _privateMessages.remove(playerName);

    playSound(receiver, Sound.ENTITY_PLAYER_LEVELUP);
  }

  public static void playSound(Player player, Sound sound) {
    player.playSound(player.getLocation(), sound, 1.0f, 1.0f);
  }
}
