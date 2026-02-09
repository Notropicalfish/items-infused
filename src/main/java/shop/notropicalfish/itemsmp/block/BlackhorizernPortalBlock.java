package shop.notropicalfish.itemsmp.block;

import org.slf4j.Logger;

public class BlackhorizernPortalBlock extends NetherPortalBlock {

	private static final Logger LOGGER = LogUtils.getLogger();

	public static void portalSpawn(Level world, BlockPos pos) {
		Optional<BlackhorizernPortalShape> optional = BlackhorizernPortalShape.findEmptyPortalShape(world, pos, Direction.Axis.X);
		if (optional.isPresent()) {
			optional.get().createPortalBlocks();
		}
	}

	public BlackhorizernPortalBlock() {
		super(BlockBehaviour.Properties.of().noCollission().randomTicks().pushReaction(PushReaction.BLOCK).strength(-1.0F).sound(SoundType.GLASS).lightLevel(s -> 0).noLootTable());
	}

	private BlackhorizernTeleporter getTeleporter(ServerLevel level) {
		return new BlackhorizernTeleporter(level);
	}

	@Override /* failed to load code for net.minecraft.world.level.block.NetherPortalBlock */

	@Override
	@Nullable /* failed to load code for net.minecraft.world.level.block.NetherPortalBlock */

	@Nullable /* failed to load code for net.minecraft.world.level.block.NetherPortalBlock */

	/* failed to load code for net.minecraft.world.level.block.NetherPortalBlock */

	/* failed to load code for net.minecraft.world.level.block.NetherPortalBlock */

	@Override
	public int getPortalTransitionTime(ServerLevel world, Entity entity) {
		return 0;
	}

	@Override
	public Portal.Transition getLocalTransition() {
		return Portal.Transition.NONE;
	}

	@Override
	public void randomTick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
		for (int i = 0; i < 4; i++) {
			double px = pos.getX() + random.nextFloat();
			double py = pos.getY() + random.nextFloat();
			double pz = pos.getZ() + random.nextFloat();
			double vx = (random.nextFloat() - 0.5) / 2.;
			double vy = (random.nextFloat() - 0.5) / 2.;
			double vz = (random.nextFloat() - 0.5) / 2.;
			int j = random.nextInt(4) - 1;
			if (world.getBlockState(pos.west()).getBlock() != this && world.getBlockState(pos.east()).getBlock() != this) {
				px = pos.getX() + 0.5 + 0.25 * j;
				vx = random.nextFloat() * 2 * j;
			} else {
				pz = pos.getZ() + 0.5 + 0.25 * j;
				vz = random.nextFloat() * 2 * j;
			}
			world.addParticle(ParticleTypes.ASH, px, py, pz, vx, vy, vz);
		}

		if (random.nextInt(110) == 0)
			world.playSound(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("ambient.cave")), SoundSource.BLOCKS, 0.5f, random.nextFloat() * 0.4f + 0.8f);
	}

}