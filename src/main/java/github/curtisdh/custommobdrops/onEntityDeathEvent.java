package github.curtisdh.custommobdrops;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Husk;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class onEntityDeathEvent implements Listener
{
    public Map<String, Map<String, Integer>> EntityItemToDropAndDropRate;

    @EventHandler
    public void mobDeathEvent(EntityDeathEvent event)
    {
        LivingEntity entity = event.getEntity();
        for (Map.Entry<String, Map<String, Integer>> entities : EntityItemToDropAndDropRate.entrySet())
        {
            try
            {
                EntityType.valueOf(entities.getKey());
            }
            catch(Exception e)
            {
                System.out.println("NO ENTITY FOUND OF TYPE:"+entities.getKey());
                System.out.println(e);
                return;
            }

            if (entity.getType() == EntityType.valueOf(entities.getKey()))
            {
                for (Map.Entry<String, Integer> ItemMaterialAndDropRate : entities.getValue().entrySet())
                {
                    entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(),
                            new ItemStack(
                                    Material.valueOf(ItemMaterialAndDropRate.getKey()),
                                    ItemMaterialAndDropRate.getValue()));

                }
            }
        }
//        if(entity instanceof Husk)
//        {
//            entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(),new ItemStack(Material.valueOf(itemMaterial), dropRate));
//        }
    }
}
