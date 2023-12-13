package com.zyunsoftware.actions.comands;

import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.zyunsoftware.utilities.PlayerUtility;
import com.zyunsoftware.utilities.TextUtility;

import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;

public class MeCommand implements CommandExecutor, TabCompleter {
  @Override
  public boolean onCommand(
    CommandSender sender,
    Command command,
    String label,
    String[] args
  ) {
    if (sender instanceof Player) {
      Player player = (Player) sender;
      List<Player> nearby = PlayerUtility.nearest(player);
      nearby.add(player);

      String nickname = TextUtility.getCustomNickname(player.getName());

      String action = args.length == 0
        ? TextUtility.action()
        : String.join(" ", args);

      action = TextUtility.replaceSubstrings(action);

      for (Player p : nearby) {
        p.sendMessage(Component.text(ChatColor.YELLOW +  "* " + nickname + " " + action));
      }
    }

    return true;
  }

  @Override
  public @Nullable List<String> onTabComplete(
    @NotNull CommandSender sender,
    @NotNull Command command,
    @NotNull String alias,
    @NotNull String[] args
  ) {
    return Collections.emptyList();
  }
}
