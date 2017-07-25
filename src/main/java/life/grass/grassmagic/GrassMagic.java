package life.grass.grassmagic;

import org.bukkit.plugin.java.JavaPlugin;

public class GrassMagic extends JavaPlugin {
    private static GrassMagic instance;

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        instance = null;
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }

    public static GrassMagic getInstance() {
        return instance;
    }
}
