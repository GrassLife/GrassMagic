package life.grass.grassmagic.magic;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public enum MagicAspect {
    FIRE(false, true, 1.2, (location -> {
        World world = location.getWorld();

        world.spawnParticle(Particle.FLAME, location, 3, 0.05, 0.05, 0.05, 0);
    }));

    private boolean canExertAlly, canExertEnemy;
    private double exertableRange;
    private Consumer<Location> sparkConsumer;

    MagicAspect(boolean canExertAlly, boolean canExertEnemy, double exertableRange, Consumer<Location> sparkConsumer) {
        this.canExertAlly = canExertAlly;
        this.canExertEnemy = canExertEnemy;
        this.exertableRange = exertableRange;
        this.sparkConsumer = sparkConsumer;
    }

    public List<LivingEntity> spark(Location location) {
        sparkConsumer.accept(location);
        return location.getWorld().getNearbyEntities(location, exertableRange, exertableRange, exertableRange).stream()
                .filter(entity -> entity instanceof LivingEntity)
                .map(entity -> (LivingEntity) entity)
                .collect(Collectors.toList());
    }

    public boolean canExertAlly() {
        return canExertAlly;
    }

    public boolean canExertEnemy() {
        return canExertEnemy;
    }
}
