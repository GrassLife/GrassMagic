package life.grass.grassmagic.magic.sequence.compiler;

import life.grass.grassmagic.magic.MagicAspect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

import java.util.Arrays;

public class SimpleCompiler extends MagicCompiler {
    private static final double RADIUS = 2.5;

    public SimpleCompiler(MagicAspect aspect, Location location) {
        super(aspect, location);
    }

    @Override
    public boolean isCallable() {
        return getCount() <= 20 * 4;
    }

    @Override
    protected void handle() {
        World world = getLocation().getWorld();

        Arrays.asList(1, -1).forEach(multiply -> {
            double radian = Math.toRadians(getCount() * 12 % 360);
            double x = Math.cos(radian * multiply) * RADIUS;
            double z = Math.sin(radian * multiply) * RADIUS;

            Location location = getLocation().clone().add(x, 0.3, z);

            world.spawnParticle(Particle.SPELL_WITCH, location, 3, 0.05, 0.05, 0.05, 0);
            world.spawnParticle(Particle.ENCHANTMENT_TABLE, location, 2, 0.05, 0.05, 0.05, 0);
        });

        for (int i = 60; i <= 360; i += 60) {
            double radian = Math.toRadians(i);
            double x = Math.cos(radian) * (RADIUS + 2.5);
            double z = Math.sin(radian) * (RADIUS + 2.5);

            Location location = getLocation().clone().add(x, 0.3, z);
            getAspect().spark(location.clone().add(Math.random() * 0.06 - 0.03, Math.random() * 0.6 - 0.3, Math.random() * 0.06 - 0.03));
        }
    }
}
