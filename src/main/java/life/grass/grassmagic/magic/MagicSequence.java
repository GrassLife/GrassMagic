package life.grass.grassmagic.magic;

public abstract class MagicSequence {
    private int count = 0;

    public abstract boolean isCallable();

    protected abstract void handle();

    public final void call() {
        count++;

        if (isCallable()) handle();
    }

    protected int getCount() {
        return count;
    }
}