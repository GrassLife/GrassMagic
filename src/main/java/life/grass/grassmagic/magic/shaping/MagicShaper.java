package life.grass.grassmagic.magic.shaping;

import life.grass.grassmagic.magic.MagicRunnable;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public abstract class MagicShaper implements MagicRunnable {
    private int count;
    private Location location;
    private Vector vector;

    public MagicShaper(Location location, Vector vector) {
        this.location = location;
        this.vector = vector;
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

    protected Vector getVector(){
        return vector;
    }
}
