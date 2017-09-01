package life.grass.grassmagic;

import life.grass.grassmagic.listener.PlayerInteract;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class GrassMagic extends JavaPlugin {
    private static GrassMagic instance;

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;

        registerEvents();
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

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new PlayerInteract(), this);
    }
}
