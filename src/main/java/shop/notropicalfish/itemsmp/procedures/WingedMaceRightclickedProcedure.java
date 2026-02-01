package shop.notropicalfish.itemsmp.procedures;

import net.neoforged.bus.api.Event;

public class WingedMaceRightclickedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (entity.getData(ItemsmpModVariables.PLAYER_VARIABLES).is_leaping == false && itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("combo") >= 3) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.horse.breathe")), SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.horse.breathe")), SoundSource.NEUTRAL, 1, 1, false);
				}
			}
			entity.setDeltaMovement(new Vec3(0, 1, 0));
			{
				final String _tagName = "combo";
				final double _tagValue = 0;
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putDouble(_tagName, _tagValue));
			}
			ItemsmpMod.queueServerWork(1, () -> {
				{
					ItemsmpModVariables.PlayerVariables _vars = entity.getData(ItemsmpModVariables.PLAYER_VARIABLES);
					_vars.is_leaping = true;
					_vars.markSyncDirty();
				}
			});
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.WHITE_SMOKE, x, y, z, 12, 0.3, 0.3, 0.3, 0.5);
		}
	}
}