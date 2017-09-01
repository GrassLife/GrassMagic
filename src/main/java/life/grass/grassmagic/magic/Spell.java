package life.grass.grassmagic.magic;

import life.grass.grasscombat.datatype.DamageType;
import life.grass.grasscombat.entity.Victim;
import life.grass.grassmagic.GrassMagic;
import life.grass.grassmagic.magic.compiling.MagicCompiler;
import life.grass.grassmagic.magic.shaping.MagicShaper;
import life.grass.grassplayer.GrassPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.stream.Stream;

public class Spell implements Runnable {
    private static GrassMagic instance;
    private static BukkitScheduler scheduler;

    private LivingEntity caster;
    private MagicCompiler compiler;
    private MagicShaper shaper;
    private MagicAspect aspect;
    private int count;

    private GrassPlayer grassPlayer;
    private boolean byPlayer;
    private int taskId;

    static {
        instance = GrassMagic.getInstance();
        scheduler = instance.getServer().getScheduler();
    }

    public Spell(LivingEntity caster, MagicCompiler compiler, MagicShaper shaper, MagicAspect aspect) {
        this.caster = caster;
        this.compiler = compiler;
        this.shaper = shaper;
        this.aspect = aspect; /* TODO: change to variable arguments */

        this.grassPlayer = caster instanceof Player ? GrassPlayer.findOrCreate(((Player) caster)) : null;
        this.byPlayer = grassPlayer != null;
    }

    public void cast() {
        taskId = instance.getServer().getScheduler().runTaskTimer(instance, this, 0, 1).getTaskId();
    }

    @Override
    public void run() {
        if (!caster.isValid()) this.cancel();
        if (byPlayer && count == 0) grassPlayer.setCasting(true);

        MagicRunnable runnable = Stream.of(compiler, shaper)
                .filter(MagicRunnable::canRun)
                .findFirst().orElse(null);

        if (runnable == null) {
            this.cancel();
            return;
        }

        if (runnable == shaper && byPlayer && grassPlayer.isCasting()) grassPlayer.setCasting(false);

        runnable.run(aspect).stream()
                .filter(entity -> byPlayer == entity instanceof Player ? aspect.canExertAlly() : aspect.canExertEnemy())
                .forEach(this::exert);

        count++;
    }

    public void cancel() {
        if (byPlayer && grassPlayer.isCasting()) grassPlayer.setCasting(false);

        scheduler.cancelTask(taskId);
    }

    // TODO: recoding
    private void exert(LivingEntity entity) {
        Victim victim = new Victim(entity);

        switch (aspect) {
            case FIRE:
                victim.causeDamage(1, caster, DamageType.MAGIC_DAMAGE);
                if (Math.random() * 100 < 15) entity.setFireTicks(8);
                break;
            case PLASMA:
                victim.causeDamage(3, caster, DamageType.MAGIC_DAMAGE);
                break;
        }
    }
}
