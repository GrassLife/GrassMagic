package life.grass.grassmagic.magic.sequence;

public abstract class MagicSequence {
    private int count = 1;

    public abstract boolean isCallable();

    protected abstract void handle();

    public final void call() {
        if (isCallable()) handle();

        count++;
    }

    protected int getCount() {
        return count;
    }
}