package life.grass.grassmagic.magic.shaping;

import life.grass.grassmagic.magic.MagicAspect;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import java.util.List;

public class LinearShaper extends MagicShaper {
    private static final double DISTANCE_PER_TICK = 1.2;
    private static final int REACH = 40;

    public LinearShaper(Location location, Vector vector) {
        super(location, vector);
    }

    @Override
    protected int getMaxCount() {
        return (int) (REACH / DISTANCE_PER_TICK);
    }

    @Override
    public List<LivingEntity> run(MagicAspect aspect) {
        double x = getVector().getX() * (double) getCount() * DISTANCE_PER_TICK;
        double y = getVector().getY() * (double) getCount() * DISTANCE_PER_TICK;
        double z = getVector().getZ() * (double) getCount() * DISTANCE_PER_TICK;

        increaseCount();
        return aspect.spark(getLocation().clone().add(x, y, z));
    }
}
