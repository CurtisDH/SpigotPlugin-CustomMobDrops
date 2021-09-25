package github.curtisdh.custommobdrops;

import org.bukkit.plugin.java.JavaPlugin;

public final class CustomMobDrops extends JavaPlugin
{

    @Override
    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(new onEntityDeathEvent(), this);
    }

    @Override
    public void onDisable()
    {

    }
}
