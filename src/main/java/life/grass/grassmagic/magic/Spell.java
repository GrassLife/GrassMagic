package life.grass.grassmagic.magic;

import life.grass.grassmagic.GrassMagic;
import life.grass.grassmagic.magic.compiling.MagicCompiler;
import life.grass.grassmagic.magic.spellwork.MagicSpellwork;
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
    private MagicSpellwork shaper;

    private GrassPlayer grassPlayer;
    private boolean byPlayer;
    private int taskId;

    static {
        instance = GrassMagic.getInstance();
        scheduler = instance.getServer().getScheduler();
    }

    public Spell(LivingEntity caster, MagicCompiler compiler, MagicSpellwork shaper) {
        this.caster = caster;
        this.compiler = compiler;
        this.shaper = shaper;

        this.grassPlayer = caster instanceof Player ? GrassPlayer.findOrCreate(((Player) caster)) : null;
        this.byPlayer = grassPlayer != null;
    }

    public void cast() {
        taskId = instance.getServer().getScheduler().runTaskTimer(instance, this, 0, 1).getTaskId();
    }

    @Override
    public void run() {
        if (!caster.isValid()) this.cancel();
        if (byPlayer) grassPlayer.setCasting(true);

        MagicSequence sequence = Stream.of(compiler, shaper)
                .filter(MagicSequence::isCallable)
                .findFirst().orElse(null);

        if (sequence == null) {
            this.cancel();
            return;
        }

        if (sequence == shaper && byPlayer && grassPlayer.isCasting()) grassPlayer.setCasting(false);

        sequence.call();
    }

    public void cancel() {
        if (byPlayer && grassPlayer.isCasting()) grassPlayer.setCasting(false);

        scheduler.cancelTask(taskId);
    }
}
