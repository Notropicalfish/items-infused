package shop.notropicalfish.itemsmp.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

public class AetherBladeToolInHandTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, x, y, z, 3, 0.5, 1, 0.5, 0.2);
	}
}