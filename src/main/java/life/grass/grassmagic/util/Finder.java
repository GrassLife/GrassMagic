package life.grass.grassmagic.util;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.List;
import java.util.stream.Collectors;

public class Finder {

    public static List<LivingEntity> findNearbyLivingEntities(Location location, double range) {
        return location.getWorld().getNearbyEntities(location, range, range, range).stream()
                .filter(entity -> entity instanceof LivingEntity)
                .map(entity -> (LivingEntity) entity)
                .collect(Collectors.toList());
    }
}
