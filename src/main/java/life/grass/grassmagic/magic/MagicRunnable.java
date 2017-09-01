package life.grass.grassmagic.magic;

import org.bukkit.entity.LivingEntity;

import java.util.List;

public interface MagicRunnable {

    boolean canRun();

    List<LivingEntity> run(MagicAspect aspect);

}
