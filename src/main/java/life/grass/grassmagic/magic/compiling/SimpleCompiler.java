package life.grass.grassmagic.magic.compiling;

import life.grass.grassmagic.magic.MagicAspect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SimpleCompiler extends MagicCompiler {
    private static final double RADIUS = 2.5;

    public SimpleCompiler(Location location) {
        super(location);
    }

    @Override
    public List<LivingEntity> run(MagicAspect aspect) {
        int count = getCount();

        World world = getLocation().getWorld();

        Arrays.asList(1, -1).forEach(multiply -> {
            double radian = Math.toRadians(count * 12 % 360);
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

            aspect.spark(location.clone().add(Math.random() * 0.06 - 0.03, Math.random() * 0.6 - 0.3, Math.random() * 0.06 - 0.03));
        }

        increaseCount();
        return Collections.emptyList();
    }

    @Override
    protected int getMaxCount() {
        return 20 * 3 + 10;
    }
}
