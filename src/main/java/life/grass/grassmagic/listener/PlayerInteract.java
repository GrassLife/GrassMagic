package life.grass.grassmagic.listener;

import life.grass.grassmagic.magic.MagicAspect;
import life.grass.grassmagic.magic.Spell;
import life.grass.grassmagic.magic.compiling.SimpleCompiler;
import life.grass.grassmagic.magic.shaping.LinearShaper;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        Vector vector = location.getDirection();

        // debugging TODO: remove
        new Spell(player, new SimpleCompiler(player.getLocation()), new LinearShaper(player.getEyeLocation(), vector), MagicAspect.FIRE).cast();
    }
}
