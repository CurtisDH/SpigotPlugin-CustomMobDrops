package github.curtisdh.custommobdrops;

import org.bukkit.Material;
import org.bukkit.entity.Husk;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class onEntityDeathEvent implements Listener
{
    @EventHandler
    public void mobDeathEvent(EntityDeathEvent event)
    {
        LivingEntity entity = event.getEntity();
        if(entity instanceof Husk)
        {
            entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(),new ItemStack(Material.SAND,1));
        }
    }
}
