package love.toad;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.block.BlockIgniteEvent;

public class Boom extends JavaPlugin implements Listener
{
    static final String PLUGIN_NAME = "Boom";
    static final String VERSION = "1.0.3";
    Logger log = Logger.getLogger("Minecraft");

    private String getInfo() {
        return PLUGIN_NAME + "/" + VERSION;
    }

    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(this, this);
        log.info("[" + getInfo() + "] Enabled");
    }

    public void onDisable()
    {
        log.info("[" + getInfo() + "] Disabled");
    }

    @EventHandler
    public void onBlockPlace (BlockPlaceEvent event)
    {
        Block block = event.getBlock();

        if (block.getType().equals(Material.TNT))
        {
            Player player = event.getPlayer();
            log.info ("[" + getInfo() + "] <" + player.getName() + "> placed TNT at " + block.getX() + "," + block.getY() + "," + block.getZ());
        }
    }

    @EventHandler
    public void onInteract (PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        if (event.getClickedBlock().getType().equals(Material.TNT)) {
            Player player = (Player) event.getPlayer();
            Block block = event.getClickedBlock();
            log.info ("[" + getInfo() + "] <" + player.getName() + "> ignited " + block.getType() + " at " + block.getX() + "," + block.getY() + "," + block.getZ());
        }
    }

    @EventHandler
    public void onExplosionPrime (ExplosionPrimeEvent event)
    {
        if (event.isCancelled())
        {
            return;
        }

        Entity entity = event.getEntity();

        if (entity instanceof TNTPrimed)
        {
            TNTPrimed tnt = (TNTPrimed) entity;
            log.info ("[" + getInfo() + "] Explosion at " + entity.getLocation().getBlockX() + "," + entity.getLocation().getBlockY() + "," + entity.getLocation().getBlockZ());
        }
    }
}
