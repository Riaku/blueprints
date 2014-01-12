package net.riaku;

import org.bukkit.plugin.java.JavaPlugin;

public final class blueprint extends JavaPlugin
{

    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(new blueprintListener(this), this);
        this.saveDefaultConfig();
        getCommand("addblueprint").setExecutor(new blueprintCommands(this));
        getCommand("removeblueprint").setExecutor(new blueprintCommands(this));
        getCommand("blueprints").setExecutor(new blueprintCommands(this));
    }

    public void onDisable()
    {
        this.saveConfig();
    }

    public void onReload()
    {
        getLogger().info("Reloading blueprint");
    }
}