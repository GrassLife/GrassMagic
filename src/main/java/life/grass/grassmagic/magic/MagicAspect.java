package life.grass.grassmagic.magic;

import life.grass.grasscombat.datatype.DamageType;
import life.grass.grasscombat.entity.Victim;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;

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

            System.out.println(Particle.ITEM_CRACK.getDataType());
            world.spawnParticle(Particle.ITEM_CRACK, location, 3, 0.1, 0.1, 0.1, 0, Material.DIAMOND_BLOCK);
        }
    };

    private boolean canExertAlly, canExertEnemy;

    MagicAspect(boolean canExertAlly, boolean canExertEnemy) {
        this.canExertAlly = canExertAlly;
        this.canExertEnemy = canExertEnemy;
    }

    public abstract void exert(LivingEntity damager, LivingEntity entity);

    public abstract void spark(Location location);

    public boolean canExertAlly() {
        return canExertAlly;
    }

    public boolean canExertEnemy() {
        return canExertEnemy;
    }
}
