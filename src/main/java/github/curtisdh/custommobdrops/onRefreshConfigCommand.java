package github.curtisdh.custommobdrops;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class onRefreshConfigCommand implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(!(sender.isOp()))
        {
            sender.sendMessage(ChatColor.RED+"You do not have permission for this command.");
            return true;
        }
        CustomMobDrops.PrintWithClassName(this, "Reloading config");
        CustomMobDrops.Instance.reloadConfig();
        CustomMobDrops.Instance.LoadConfig();
        sender.sendMessage(ChatColor.GREEN+"Config successfully reloaded");
        return true;
    }
}
