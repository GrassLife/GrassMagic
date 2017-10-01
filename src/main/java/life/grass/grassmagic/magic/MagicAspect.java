package life.grass.grassmagic.magic;

import life.grass.grasscombat.datatype.DamageType;
import life.grass.grasscombat.entity.Victim;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public enum MagicAspect {
    FIRE(false, true) {
        @Override
        public void exert(LivingEntity damager, LivingEntity entity) {
            Victim victim = new Victim(entity);

            victim.causeDamage(3, damager, DamageType.MAGIC_DAMAGE);
            entity.setFireTicks(30);
        }

        @Override
        public void spark(Location location) {
            World world = location.getWorld();

            world.spawnParticle(Particle.FLAME, location, 2, 0.1, 0.1, 0.1, 0);
        }
    },
    PLASMA(false, true) {
        @Override
        public void exert(LivingEntity damager, LivingEntity entity) {
            Victim victim = new Victim(entity);

            victim.causeDamage(5, damager, DamageType.MAGIC_DAMAGE);
        }

        @Override
        public void spark(Location location) {
            World world = location.getWorld();

            world.spawnParticle(Particle.ITEM_CRACK, location, 2, 0.1, 0.1, 0.1, 0, new ItemStack(Material.DIAMOND_BLOCK));
            world.spawnParticle(Particle.CRIT_MAGIC, location, 5, 0.3, 0.3, 0.3, 0);
        }
    };

    private boolean canExertAlly, canExertEnemy;

    MagicAspect(boolean canExertAlly, boolean canExertEnemy) {
        this.canExertAlly = canExertAlly;
        this.canExertEnemy = canExertEnemy;
    }

    public abstract void exert(LivingEntity damager, LivingEntity entity);

    public abstract void spark(Location location);

    public void exertWithSpark(Location location, LivingEntity damager) {
        this.spark(location);

        Arrays.stream(location.getWorld().getNearbyEntities(location, 1.5, 1.5, 1.5).toArray())
                .filter(entity -> entity instanceof LivingEntity)
                .map(entity -> ((LivingEntity) entity))
                .filter(entity -> !damager.equals(entity) && (damager instanceof Player == entity instanceof Player ? canExertAlly() : canExertEnemy()))
                .forEach(entity -> exert(damager, entity));
    }

    public boolean canExertAlly() {
        return canExertAlly;
    }

    public boolean canExertEnemy() {
        return canExertEnemy;
    }

    public enum Level {
        NORMAL, POPPING
    }
}
