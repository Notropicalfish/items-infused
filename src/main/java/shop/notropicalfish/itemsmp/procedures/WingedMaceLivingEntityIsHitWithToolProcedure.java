package shop.notropicalfish.itemsmp.procedures;

import shop.notropicalfish.itemsmp.network.ItemsmpModVariables;
import shop.notropicalfish.itemsmp.ItemsmpMod;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.BlockPos;

public class WingedMaceLivingEntityIsHitWithToolProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity, ItemStack itemstack) {
		if (entity == null || sourceentity == null)
			return;
		sourceentity.fallDistance = 0;
		if (!(sourceentity.getData(ItemsmpModVariables.PLAYER_VARIABLES).is_leaping == true) && itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("combo") < 3) {
			{
				final String _tagName = "combo";
				final double _tagValue = (itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("combo") + 1);
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putDouble(_tagName, _tagValue));
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.iron_golem.repair")), SoundSource.NEUTRAL, (float) 0.7, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.iron_golem.repair")), SoundSource.NEUTRAL, (float) 0.7, 1, false);
				}
			}
		}
		if (sourceentity.getData(ItemsmpModVariables.PLAYER_VARIABLES).is_leaping == true) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.anvil.land")), SoundSource.NEUTRAL, (float) 0.7, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.anvil.land")), SoundSource.NEUTRAL, (float) 0.7, 1, false);
				}
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.iron_golem.damage")), SoundSource.NEUTRAL, (float) 0.7, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.iron_golem.damage")), SoundSource.NEUTRAL, (float) 0.7, 1, false);
				}
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.horse.breathe")), SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.horse.breathe")), SoundSource.NEUTRAL, 1, 1, false);
				}
			}
			entity.hurt(new DamageSource(world.holderOrThrow(DamageTypes.MAGIC)), (float) ((entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) * 0.35));
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.EXPLOSION_EMITTER, (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 3, 0.4, 0.4, 0.4, 0.4);
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.WHITE_SMOKE, (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 12, 0.4, 0.4, 0.4, 0.4);
			ItemsmpMod.queueServerWork(1, () -> {
				sourceentity.setDeltaMovement(new Vec3(0, 1, 0));
				if (sourceentity instanceof LivingEntity _entity)
					_entity.setHealth((float) ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) + (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) * 0.25));
			});
		}
		if (itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("combo") == 3) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.end_portal_frame.fill")), SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.end_portal_frame.fill")), SoundSource.NEUTRAL, 1, 1, false);
				}
			}
		}
	}
}