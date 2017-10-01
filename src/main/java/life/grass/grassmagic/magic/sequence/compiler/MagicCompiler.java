package life.grass.grassmagic.magic.sequence.compiler;

import life.grass.grassmagic.magic.MagicAspect;
import life.grass.grassmagic.magic.sequence.MagicSequence;
import org.bukkit.Location;

public abstract class MagicCompiler extends MagicSequence {
    private MagicAspect aspect;
    private Location location;

    public MagicCompiler(MagicAspect aspect, Location location) {
        this.aspect = aspect;
        this.location = location;
    }

    protected MagicAspect getAspect() {
        return aspect;
    }

    protected Location getLocation() {
        return location;
    }
}
