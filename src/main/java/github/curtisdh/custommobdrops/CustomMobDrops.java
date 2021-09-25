package github.curtisdh.custommobdrops;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class CustomMobDrops extends JavaPlugin
{
    onEntityDeathEvent deathEvent;
    @Override
    public void onEnable()
    {
        PrintWithClassName(this,"Starting...");
        deathEvent = new onEntityDeathEvent();
        LoadConfig();
        getServer().getPluginManager().registerEvents(deathEvent, this);
        PrintWithClassName(this,"Initialised");
    }
    public static void PrintWithClassName(Object obj,String str)
    {
        System.out.println(obj.getClass().getName()+"::"+str);
    }
    private void LoadConfig()
    {
        PrintWithClassName(this,"Loading config");
        saveDefaultConfig();
        Map<String, Map<String, Integer>> EntityData = new HashMap<>();
        for (String key : getConfig().getConfigurationSection("settings.mobs").getKeys(false))
        {
            Map<String,Integer> mobDropInfo = new HashMap<>();
            Object droprateObj = getConfig().getConfigurationSection("settings.mobs." + key)
                    .getValues(true).get("droprate");

            Object itemMaterial = getConfig().getConfigurationSection("settings.mobs." + key)
                    .getValues(true).get("itemMaterial");

            int dropRate = Integer.parseInt(droprateObj.toString());
            key = key.toUpperCase(Locale.ROOT);

            mobDropInfo.put(itemMaterial.toString(),dropRate);
            PrintWithClassName(this,"Loaded mob: "+key+
                    "materialDrop: "+ itemMaterial + " droprate:"+dropRate);
            EntityData.put(key, mobDropInfo);
        }
        deathEvent.EntityItemToDropAndDropRate = EntityData;
        //TODO access the saved entity data, get the drop rate and item material and add it to the hashmap
//        int dropRate;
//        try
//        {
//            dropRate = Integer.parseInt(getConfig().getString("settings.mobs.husk.droprate"));
//        }
//        catch (Exception e)
//        {
//            PrintWithClassName(this,"droprate in config is undefined. Setting droprate to 1");
//            PrintWithClassName(this,"Config location is:"+getDataFolder().getAbsolutePath());
//            getConfig().createSection("settings.mobs.husk.droprate");
//            getConfig().set("settings.mobs.husk.droprate",1);
//            dropRate = 1;
//        }
//        String itemToDrop = getConfig().getString("settings.mobs.husk.itemMaterial");
//        if(itemToDrop == null)
//        {
//            PrintWithClassName(this,"itemToDrop is undefined. Setting to 'SAND'");
//            getConfig().createSection("settings.mobs.husk.itemMaterial");
//            PrintWithClassName(this,"Config location is:"+getDataFolder().getAbsolutePath());
//            getConfig().set("settings.mobs.husk.itemMaterial","SAND");
//        }
        saveConfig();
    }

    @Override
    public void onDisable()
    {

    }
}
