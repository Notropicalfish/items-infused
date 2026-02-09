package shop.notropicalfish.itemsmp.procedures;

import shop.notropicalfish.itemsmp.ItemsmpMod;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import java.util.Comparator;

public class AetherBladeLivingEntityIsHitWithToolProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity, ItemStack itemstack) {
		if (entity == null || sourceentity == null)
			return;
		double fromZ = 0;
		double fromX = 0;
		double fromY = 0;
		double xRadius = 0;
		double loop = 0;
		double zRadius = 0;
		double particleAmount = 0;
		if (!(sourceentity instanceof Player _plrCldCheck1 && _plrCldCheck1.getCooldowns().isOnCooldown(itemstack.getItem()))) {
			if (sourceentity instanceof Player _player)
				_player.getCooldowns().addCooldown(itemstack.getItem(), 200);
			{
				final Vec3 _center = new Vec3((entity.getX()), (entity.getY()), (entity.getZ()));
				for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(6 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
					if (!(sourceentity == entityiterator)) {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.dragon_fireball.explode")), SoundSource.NEUTRAL, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.dragon_fireball.explode")), SoundSource.NEUTRAL, 1, 1, false);
							}
						}
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.enchantment_table.use")), SoundSource.NEUTRAL, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.enchantment_table.use")), SoundSource.NEUTRAL, 1, 1, false);
							}
						}
						loop = 0;
						particleAmount = 8;
						xRadius = 1;
						zRadius = 1;
						while (loop < particleAmount) {
							if (world instanceof ServerLevel _level)
								_level.sendParticles(ParticleTypes.SNOWFLAKE, (entity.getX() + Math.cos(((Math.PI * 2) / particleAmount) * loop) * xRadius), (entity.getY() + 0.2),
										(entity.getZ() + Math.sin(((Math.PI * 2) / particleAmount) * loop) * zRadius), 5, 0.3, 0.3, 0.3, 0.02);
							loop = loop + 1;
						}
						loop = 0;
						particleAmount = 12;
						xRadius = 2;
						zRadius = 2;
						while (loop < particleAmount) {
							if (world instanceof ServerLevel _level)
								_level.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, (entity.getX() + Math.cos(((Math.PI * 2) / particleAmount) * loop) * xRadius), (entity.getY() + 0.2),
										(entity.getZ() + Math.sin(((Math.PI * 2) / particleAmount) * loop) * zRadius), 5, 0.3, 0.3, 0.3, 0.02);
							loop = loop + 1;
						}
						ItemsmpMod.queueServerWork(1, () -> {
							entityiterator.setDeltaMovement(new Vec3(0, 1, 0));
						});
					}
				}
			}
		}
	}
}