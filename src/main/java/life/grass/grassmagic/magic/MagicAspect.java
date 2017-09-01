package life.grass.grassmagic.magic;

import life.grass.grassmagic.util.Finder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;

import java.util.List;
import java.util.function.Consumer;

public enum MagicAspect {
    FIRE(false, true, 1.2, (location -> {
        World world = location.getWorld();

        world.spawnParticle(Particle.FLAME, location, 3, 0.05, 0.05, 0.05, 0);
    })),
    PLASMA(false, true, 1.2, (location -> {
        World world = location.getWorld();

        world.spawnParticle(Particle.ITEM_CRACK, location, 4, 0.1, 0.1, 0.1, 0, Material.GOLD_BLOCK);
    })) {
        @Override
        public List<LivingEntity> spark(Location location) {
            List<LivingEntity> entityList = super.spark(location);

            if (Math.random() * 100 < 8) {
                World world = location.getWorld();
                Location point = location.clone().add(Math.random() * 10 - 5, Math.random() * 10 - 5, Math.random() * 10 - 5);

                world.strikeLightningEffect(point);
                entityList.addAll(Finder.findNearbyLivingEntities(point, 3));
            }

            return entityList;
        }
    };

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
        return Finder.findNearbyLivingEntities(location, exertableRange);
    }

    public boolean canExertAlly() {
        return canExertAlly;
    }

    public boolean canExertEnemy() {
        return canExertEnemy;
    }
}
