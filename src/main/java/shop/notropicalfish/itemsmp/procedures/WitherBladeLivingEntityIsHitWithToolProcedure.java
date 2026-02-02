package shop.notropicalfish.itemsmp.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.Item;

public class WitherBladeLivingEntityIsHitWithToolProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (Math.random() <= 0.25) {
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.WITHER, (int) (60 + 40 * Math.random()), 0, false, true));
			// Spawn particles around entity's bounding box
			int particleCount = Mth.nextInt(RandomSource.create(), 14, 18);
			for (int i = 0; i < particleCount; i++) {
				if (world instanceof ServerLevel _level) {
					double px = entity.getBoundingBox().minX + Math.random() * entity.getBoundingBox().getXsize();
					double py = entity.getBoundingBox().minY + Math.random() * entity.getBoundingBox().getYsize();
					double pz = entity.getBoundingBox().minZ + Math.random() * entity.getBoundingBox().getZsize();
					_level.sendParticles(ParticleTypes.SOUL, px, py, pz, 1, 0, 0, 0, 0);
				}
			}
			// Cooldown & item damage for non-creative players
			if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 100);
				if (world instanceof ServerLevel _level) {
					itemstack.hurtAndBreak((int) Mth.nextDouble(RandomSource.create(), 4, 6), _level, null, _stkprov -> {
					});
				}
			}
		}
	}
}