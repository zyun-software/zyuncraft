package com.zyunsoftware;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.zyunsoftware.actions.comands.MeCommand;
import com.zyunsoftware.actions.comands.PmCommand;
import com.zyunsoftware.actions.comands.RCommand;
import com.zyunsoftware.actions.listeners.ChatListener;

public class ZyunCraftCorePlugin extends JavaPlugin {
  private static ZyunCraftCorePlugin _instance;

  public static ZyunCraftCorePlugin getInstance() {
    return _instance;
  }

  public ZyunCraftCorePlugin() {
    saveDefaultConfig();
  }

  @Override
  public void onEnable() {
    _instance = this;

    _registerEvents();
    _registerCommands();

    getLogger().info("Плагін успішно ввімкнено!");
  }

  @Override
  public void onDisable() {
    _instance = null;

    // todo: 

    getLogger().info("Плагін вимкнено!");
  }

  private void _registerEvents() {
    PluginManager pluginManager = getServer().getPluginManager();
    pluginManager.registerEvents(new ChatListener(), _instance);
  }

  private void _registerCommands() {
    getCommand("я").setExecutor(new MeCommand());
    getCommand("пп").setExecutor(new PmCommand());
    getCommand("в").setExecutor(new RCommand());
  }
}
