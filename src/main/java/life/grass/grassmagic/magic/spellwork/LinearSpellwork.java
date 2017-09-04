package life.grass.grassmagic.magic.spellwork;

import life.grass.grassmagic.magic.MagicAspect;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class LinearSpellwork extends MagicSpellwork {
    private static final double DISTANCE_PER_TICK = 1.2;
    private static final int REACH = 40;

    public LinearSpellwork(MagicAspect aspect, LivingEntity caster, Location location, Vector vector) {
        super(aspect, caster, location, vector);
    }

    @Override
    public boolean isCallable() {
        return 0 < REACH - DISTANCE_PER_TICK * (double) getCount();
    }

    @Override
    protected void handle() {
        double x = getVector().getX() * (double) getCount() * DISTANCE_PER_TICK;
        double y = getVector().getY() * (double) getCount() * DISTANCE_PER_TICK;
        double z = getVector().getZ() * (double) getCount() * DISTANCE_PER_TICK;

        getAspect().spark(getLocation().clone().add(x, y, z), getCaster());
    }
}
