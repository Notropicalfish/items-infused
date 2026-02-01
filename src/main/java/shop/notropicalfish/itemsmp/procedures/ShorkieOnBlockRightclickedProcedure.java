package shop.notropicalfish.itemsmp.procedures;

import net.neoforged.bus.api.Event;

public class ShorkieOnBlockRightclickedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double sqeek = 0;
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.HEART, x, y, z, 5, 0.3, 0.3, 0.3, 1);
		sqeek = Mth.nextInt(RandomSource.create(), 1, 4);
		if (sqeek == 1) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:shorkiesqueek")), SoundSource.MASTER, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:shorkiesqueek")), SoundSource.MASTER, 1, 1, false);
				}
			}
		} else if (sqeek == 2) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:shorkiesqueek")), SoundSource.MASTER, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:shorkiesqueek")), SoundSource.MASTER, 1, 1, false);
				}
			}
		} else if (sqeek == 3) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:shorkiesqueek")), SoundSource.MASTER, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:shorkiesqueek")), SoundSource.MASTER, 1, 1, false);
				}
			}
		} else if (sqeek == 4) {
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:shorkiesqueek")), SoundSource.MASTER, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("itemsmp:shorkiesqueek")), SoundSource.MASTER, 1, 1, false);
				}
			}
		}
	}
}