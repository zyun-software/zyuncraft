package com.zyunsoftware.actions.comands;

import java.util.Arrays;
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

public class PmCommand implements CommandExecutor, TabCompleter {
  @Override
  public boolean onCommand(
    CommandSender sender,
    Command command,
    String label,
    String[] args
  ) {
    if (sender instanceof Player) {
      Player player = (Player) sender;

      if (args.length < 2) {
        return false;
      }

      String message = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
      PlayerUtility.sendPm(player, args[0], message);
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
    if (args.length == 1) {
      return null;
    }

    return Collections.emptyList();
  }
}
