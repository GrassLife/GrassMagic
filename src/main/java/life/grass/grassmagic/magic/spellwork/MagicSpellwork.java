package life.grass.grassmagic.magic.spellwork;

import life.grass.grassmagic.magic.MagicAspect;
import life.grass.grassmagic.magic.MagicSequence;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public abstract class MagicSpellwork extends MagicSequence {
    private MagicAspect aspect;
    private LivingEntity caster;
    private Location location;
    private Vector vector;

    public MagicSpellwork(MagicAspect aspect, LivingEntity caster, Location location, Vector vector) {
        this.aspect = aspect;
        this.caster = caster;
        this.location = location;
        this.vector = vector;
    }

    protected MagicAspect getAspect() {
        return aspect;
    }

    protected LivingEntity getCaster() {
        return caster;
    }

    protected Location getLocation() {
        return location;
    }

    protected Vector getVector() {
        return vector;
    }
}
