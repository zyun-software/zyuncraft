package com.zyunsoftware.actions.comands;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.zyunsoftware.utilities.PlayerUtility;
import com.zyunsoftware.utilities.TextUtility;

import net.md_5.bungee.api.ChatColor;

public class NumberCommand implements CommandExecutor, TabCompleter {
  @Override
  public boolean onCommand(
    CommandSender sender,
    Command command,
    String label,
    String[] args
  ) {
    if (sender instanceof Player) {
      Player player = (Player) sender;
      if (args.length != 2 || !args[0].matches("^\\d+$") || !args[1].matches("^\\d+$")) {
        return false;
      }

      int min = Integer.parseInt(args[0]);
      int max = Integer.parseInt(args[1]);
      
      if (max <= min) {
        return false;
      }

      Random random = new Random();
      int result = random.nextInt((max - min) + 1) + min;

      List<Player> nearest = PlayerUtility.nearest(player);

      String nickname = TextUtility.getCustomNickname(player.getName());

      player.sendMessage(ChatColor.YELLOW + "Ви згенерували число: " + ChatColor.GOLD + result);

      for (Player p : nearest) {
        p.sendMessage(nickname + ChatColor.YELLOW + " згенерував число (від " + ChatColor.GOLD + min + ChatColor.YELLOW + " до " + ChatColor.GOLD + max + ChatColor.YELLOW + "): " + ChatColor.GOLD + result);
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
