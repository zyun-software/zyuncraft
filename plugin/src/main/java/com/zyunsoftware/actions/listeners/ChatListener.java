package com.zyunsoftware.actions.listeners;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.zyunsoftware.utilities.PlayerUtility;
import com.zyunsoftware.utilities.TextUtility;

import io.papermc.paper.chat.ChatRenderer;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;

public class ChatListener implements Listener {
  private ChatRenderer _chatRenderer;

  public ChatListener() {
    _chatRenderer = new ChatRenderer() {
      @Override
      public Component render(Player player, Component source, Component message, Audience audience) {   
        String nickname = TextUtility.getCustomNickname(player.getName());

        String text = TextUtility.serializeMessage(message);

        Boolean isGlobalChat = text.startsWith("!");
        if (isGlobalChat) {
          text = text.substring(1);
        }

        text = TextUtility.replaceSubstrings(text);

        Component result = Component.text(nickname + ChatColor.DARK_GRAY + ": " + (isGlobalChat ? ChatColor.WHITE : ChatColor.GRAY) + text);

        return result;
      }
    };
  }

  @EventHandler
  public void onPlayerChat(AsyncChatEvent event) {
    Player sender = event.getPlayer();

    String text = TextUtility
      .serializeMessage(event.message())
      .replaceAll("\\s+", " ")
      .trim();

    Boolean isGlobalChat = text.startsWith("!");

    if (isGlobalChat && text.length() == 1) {
      event.setCancelled(true);
      return;
    }

    if (!isGlobalChat) {
      List<Player> localRecipients = PlayerUtility.nearest(sender);

      for (Player player : localRecipients) {
        if (text.toLowerCase().contains(player.getName().toLowerCase())) {
          PlayerUtility.playSound(player, Sound.ENTITY_PLAYER_LEVELUP);
        }
      }

      localRecipients.add(sender);

      event.viewers().clear();
      event.viewers().addAll(localRecipients);

      if (localRecipients.size() == 1) {
        event.setCancelled(true);
        sender.sendMessage(Component.text(TextUtility.nobodyHeard()));
        return;
      }
    } else {
      for (Player player : Bukkit.getOnlinePlayers()) {
        if (text.toLowerCase().contains(player.getName().toLowerCase())) {
          PlayerUtility.playSound(player, Sound.ENTITY_PLAYER_LEVELUP);
        }
      }
    }

    event.renderer(_chatRenderer);

    return;
  }
}
