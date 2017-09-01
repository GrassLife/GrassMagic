package life.grass.grassmagic.magic.compiling;

import life.grass.grassmagic.magic.MagicAspect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;

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
        double radian = Math.toRadians(count * 12 % 360);

        double x = Math.cos(radian) * RADIUS;
        double z = Math.sin(radian) * RADIUS;

        Location location = getLocation().clone().add(x, 0.2, z);
        World world = location.getWorld();

        world.spawnParticle(Particle.SPELL_WITCH, location, 3, 0.05, 0.05, 0.05, 0);
        world.spawnParticle(Particle.ENCHANTMENT_TABLE, location, 2, 0.05, 0.05, 0.05, 0);

        increaseCount();
        return Collections.emptyList();
    }

    @Override
    protected int getMaxCount() {
        return 20 * 3 + 10;
    }
}
