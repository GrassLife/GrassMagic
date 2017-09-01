package life.grass.grassmagic.magic.compiling;

import life.grass.grassmagic.magic.MagicRunnable;
import org.bukkit.Location;

public abstract class MagicCompiler implements MagicRunnable {
    private int count;
    private Location location;

    public MagicCompiler(Location location) {
        this.location = location;
    }

    protected abstract int getMaxCount();

    @Override
    public boolean canRun() {
        return 0 <= getMaxCount() - count;
    }

    protected int getCount() {
        return count;
    }

    protected void increaseCount() {
        this.count++;
    }

    protected Location getLocation() {
        return location;
    }
}
