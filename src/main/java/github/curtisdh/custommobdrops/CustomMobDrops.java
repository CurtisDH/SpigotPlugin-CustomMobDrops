package github.curtisdh.custommobdrops;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class CustomMobDrops extends JavaPlugin
{
    public static CustomMobDrops Instance;
    onEntityDeathEvent deathEvent;

    @Override
    public void onEnable()
    {
        PrintWithClassName(this, "Starting...");
        Instance = this;
        deathEvent = new onEntityDeathEvent();
        LoadConfig();
        SetupCommands();
        getServer().getPluginManager().registerEvents(deathEvent, this);
        PrintWithClassName(this, "Initialised");
    }

    public static void PrintWithClassName(Object obj, String str)
    {
        System.out.println(obj.getClass().getName() + "::" + str);
    }

    private void SetupCommands()
    {
        PrintWithClassName(this, "Setting up commands....");
        getCommand("reloadConfig").setExecutor(new onRefreshConfigCommand());
        PrintWithClassName(this, "command:'reloadConfig' setup successfully");
    }

    public void LoadConfig()
    {
        PrintWithClassName(this, "Loading config");
        saveDefaultConfig();
        Map<String, Map<String, Integer>> EntityData = new HashMap<>();
        for (String key : getConfig().getConfigurationSection("settings.mobs").getKeys(false))
        {
            Map<String, Integer> mobDropInfo = new HashMap<>();
            Object droprateObj = getConfig().getConfigurationSection("settings.mobs." + key)
                    .getValues(true).get("droprate");

            Object itemMaterial = getConfig().getConfigurationSection("settings.mobs." + key)
                    .getValues(true).get("itemMaterial");

            int dropRate = Integer.parseInt(droprateObj.toString());
            key = key.toUpperCase(Locale.ROOT);

            mobDropInfo.put(itemMaterial.toString(), dropRate);
            PrintWithClassName(this, "Loaded mob: " + key +
                    "materialDrop: " + itemMaterial + " droprate:" + dropRate);
            EntityData.put(key, mobDropInfo);
        }
        deathEvent.EntityItemToDropAndDropRate = EntityData;
        saveConfig();
    }

    @Override
    public void onDisable()
    {

    }
}
