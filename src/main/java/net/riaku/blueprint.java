package net.riaku;

import org.bukkit.plugin.java.JavaPlugin;

public final class blueprint extends JavaPlugin
{
    private blueprintListener BPL;

    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(new blueprintListener(this), this);
        this.saveDefaultConfig();
    }

    public void onDisable()
    {
        this.saveConfig();
    }

    public void onReload()
    {
        this.saveConfig();
        getLogger().info("Reloading blueprint");
    }
}