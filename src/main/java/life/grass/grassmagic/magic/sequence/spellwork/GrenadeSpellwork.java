package life.grass.grassmagic.magic.sequence.spellwork;

import life.grass.grassmagic.magic.MagicAspect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class GrenadeSpellwork extends MagicSpellwork {
    private static final double DISTANCE_PER_TICK = 1.8;
    private static final double EXPLOSION_RADIUS = 3.5;

    private boolean isCallable = true;
    private double dropDistance = 0;

    public GrenadeSpellwork(MagicAspect aspect, LivingEntity caster, Location location, Vector vector) {
        super(aspect, caster, location, vector);
    }

    @Override
    public boolean isCallable() {
        return isCallable;
    }

    @Override
    protected void handle() {
        World world = getLocation().getWorld();
        Location location = getLocation().clone();
        double x = getVector().getX() * (double) getCount() * DISTANCE_PER_TICK;
        double y = getVector().getY() * (double) getCount() * DISTANCE_PER_TICK - dropDistance;
        double z = getVector().getZ() * (double) getCount() * DISTANCE_PER_TICK;

        getAspect().exertWithSpark(location.add(x, y, z), getCaster());
        world.spawnParticle(Particle.FIREWORKS_SPARK, location, 2, 0.1, 0.1, 0.1, 0);

        if (world.getBlockAt(location).getType() != Material.AIR || location.getY() < 0) {
            isCallable = false;

            for (int i = 0; i < 50; i++) {
                getAspect().spark(location.clone().add(
                        Math.random() + 2.0 * EXPLOSION_RADIUS * Math.random() - EXPLOSION_RADIUS,
                        Math.random() + 1.0 * EXPLOSION_RADIUS * Math.random(),
                        Math.random() + 2.0 * EXPLOSION_RADIUS * Math.random() - EXPLOSION_RADIUS));
            }

            world.spawnParticle(Particle.EXPLOSION_LARGE, location, 1);
        }

        assert getCount() != 0;
        dropDistance = (1.0 / 36.0) * Math.pow(getCount(), 2.0);
    }
}
