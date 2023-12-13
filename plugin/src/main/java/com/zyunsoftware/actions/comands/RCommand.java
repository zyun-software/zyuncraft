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

public class RCommand implements CommandExecutor, TabCompleter {
  @Override
  public boolean onCommand(
    CommandSender sender,
    Command command,
    String label,
    String[] args
  ) {
    if (sender instanceof Player) {
      Player player = (Player) sender;

      if (args.length < 1) {
        return false;
      }

      String receiver = PlayerUtility.getPmSender(player.getName());
      if (receiver == null) {
        player.sendMessage(Component.text(TextUtility.recipientNotFound()));
        return true;
      }

      PlayerUtility.sendPm(player, receiver, String.join(" ", args));
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
